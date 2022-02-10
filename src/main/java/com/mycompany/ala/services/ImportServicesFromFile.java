/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.enums.ExpenditureType;
import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import com.mycompany.ala.exceptions.DbException;
import com.mycompany.ala.exceptions.ServiceException;
import com.mycompany.ala.gui.ProgressInfoView;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Abimael
 */
public class ImportServicesFromFile extends Thread {

    private Component parentView;
    private String path;

    private List<DataChangeListener> listeners = new ArrayList<>();
    private Set<OrderService> services = new LinkedHashSet<>();

    private static OrderServiceDao osDao = DaoFactory.createOrderServiceDao();

    public ImportServicesFromFile(Component parentView, String path) {
        this.parentView = parentView;
        this.path = path;
    }

    @Override
    public void run() {
        int registered = 0;
        int noRegistered = 0;
        int countLine = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

            String line = br.readLine();
            line = br.readLine();

            while (line != null && !line.trim().equals("END")) {
                String[] column = line.split(";");

                line = br.readLine();
                while (line == null || line.length() == 0) {
                    line = br.readLine();
                }

                while (!line.contains(";") && !line.trim().equals("END")) {
                    column[9] += "\n " + line.trim();
                    line = br.readLine();
                    while (line == null || line.length() == 0) {
                        line = br.readLine();
                    }
                }
                String reservs = "";
                if (column[9].matches("(?s).*[Rr][eE][sS][eE][rR][vV][aA][sS]?\\s*[:;-]\\s*\n?[:;-]?\\s*(\\d{6,9}\\s*[,.;:-]?\\s*)+.*")) {                    
                    reservs = column[9].replaceAll("(?s).*[Rr][eE][sS][eE][rR][vV][aA][sS]?\\s*[:;-]\\s*\n?[:;-]?\\s*((\\d{6,9}\\s*[,.;:-]?\\s*)+).*", "$1").replaceAll("(\\d{6,9})\\s*[,.;:-]?\\s*", "$1 ").replace("\n", "");
                    System.out.println(reservs);
                }               

                if (loadId(column[0]) == null) {
                    throw new ServiceException("Invalid Id in line: ");
                }
                OrderService service = new OrderService(loadId(column[0]), column[1].trim(), column[2].trim().replace(" ", ""),
                        column[3].trim(), ServiceType.valueOf(column[4].trim().replace(" ", "_")),
                        column[5], column[6],
                        column[9], new Date());

                if (column[7].trim() != null && column[7].trim().length() > 0 && column[7].trim().replace(",", "").replace(".", "").matches("[+-]?\\d*(\\.\\d+)?")) {
                    service.setUnlockKm(Double.parseDouble(column[7].replace(",", ".")));
                }
                service.setStatusService(StatusService.REGISTRADO);              
                loadReserv(service, column[8]);
                if (service.getReservsId().trim().length() == 0) {
                    loadReserv(service, reservs.trim());
                }
                if (osDao.containsOrderService(service.getId())) {
                    noRegistered++;
                }
                services.add(service);
                countLine++;
            }

            ProgressInfoView proogressInfoView = new ProgressInfoView();
            proogressInfoView.setMaxValueToProgressBar(services.size());
            proogressInfoView.setTitle("Registrando no banco de dados");

            for (OrderService service : services) {
                if (registerInDataBase(service)) {
                    registered++;
                    proogressInfoView.setValueToProgress(registered);
                    proogressInfoView.setTextInfo(service.getId());

                }
            }

            proogressInfoView.dispose();
            if (parentView != null) {
                JOptionPane.showMessageDialog(parentView, "Concluido. \n " + registered + " itens registrados com sucesso !");
            }
            listeners.forEach(x -> x.onDataChange(null));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentView, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(parentView, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentView, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (DbException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentView, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String loadId(String id) throws ServiceException {
        String prefix;
        String[] newId = id.split("/");
        try {
            if (newId[0].trim().replace(" ", "").length() > 5 && newId[1].length() == 4) {
                prefix = newId[0].replace(" ", "").trim().substring(0, 4);
                if (prefix.matches("[+-]?\\d*(\\.\\d+)?") || prefix.matches(".*\\d.*")) //verifica se o prefix é um número ou contem algum
                {
                    throw new ServiceException("Error of prefix in loadId() in line: ");
                }
                newId[0] = newId[0].substring(5).trim();
                Integer numberId = Integer.parseInt(newId[0]);
                return prefix + " " + String.valueOf(numberId) + "/" + newId[1].trim();
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Error in loadId(): número de identificação contem letra(s)");
        }
    }

    private void loadReserv(OrderService orderService, String reservs) {
        if (reservs != null && !reservs.trim().equals("")) {
            if (reservs.toUpperCase().trim().charAt(0) != 'R') {
                String[] reserv;
                if(reservs.contains("-")){
                    reserv = reservs.trim().split("-");
                }else{
                    reserv = reservs.trim().split(" ");
                }
                for (String r : reserv) {
                    if (r.trim().length() > 4 && r.trim().matches("[+-]?\\d*(\\.\\d+)?")) {
                        r.replace(" ", "");
                        Reserv res = new Reserv(r.trim());
                        res.setService(orderService);
                        orderService.addReserv(res);
                    }
                }
                
                if (orderService.getReservsId().trim().length() == 0) {
                    orderService.setEmbarg(true);
                }else{
                    orderService.setEmbarg(false);
                }
                orderService.setExpenditureType(ExpenditureType.CUSTEIO);
            } else {
                if (reservs.trim().replace("R", "").replace("-", "").matches("[+-]?\\d*(\\.\\d+)?")) {
                    int r = Integer.parseInt(reservs.trim().replace("R", "").replace("-", ""));
                    orderService.setR(String.valueOf(r));
                }               
                orderService.setEmbarg(true);
                orderService.setExpenditureType(ExpenditureType.INVESTIMENTO);
            }
        } else {
            orderService.setEmbarg(true);
        }
    }

    private boolean registerInDataBase(OrderService os) throws DbException {
        if (osDao.containsOrderService(os.getId())) {
            return false;
        } else {
            osDao.insertOrderService(os);
        }
        return true;
    }

    public void subscribeDataChangeListener(DataChangeListener dcl) {
        if (!listeners.contains(dcl)) {
            listeners.add(dcl);
        }
    }

}
