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
    
    public ImportServicesFromFile(Component parentView, String path){
        this.parentView = parentView;
        this.path = path;
    }
    @Override
    public void run() {
        int registered = 0;
        int noRegistered = 0;
        int countLine = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
           
            String line = br.readLine();          
                    line = br.readLine();
                           
            while(line != null && !line.trim().equals("END")){
                String[] row = line.split(";");
                
                line = br.readLine();
                while(line == null || line.length() == 0){  
                    line = br.readLine();
                }
               
                while(!line.contains(";") && !line.trim().equals("END")){                   
                    row[9] += "\n " + line.trim();                                        
                    line = br.readLine(); 
                    while(line == null || line.length() == 0){
                        line = br.readLine();                       
                    }
                }
                if(loadId(row[0]) == null) throw new ServiceException("Invalid Id in line: ");
                OrderService service = new OrderService(loadId(row[0]), row[1].trim(), row[2].trim().replace(" ",""), 
                                                    row[3].trim(), ServiceType.valueOf(row[4].trim()), 
                                                    row[5], row[6], Double.parseDouble(row[7].replace(",", ".")), 
                                                    row[9], new Date());
                service.setStatusService(StatusService.REGISTRADO);
                loadReserv(service, row[8]);
                if(osDao.containsOrderService(service.getId()))
                    noRegistered++;
                services.add(service);
                countLine++;              
            }
            
            ProgressInfoView piv = new ProgressInfoView();
            piv.setMaxValueToProgressBar(services.size());
            piv.setTitle("Registrando no banco de dados");
            
            for(OrderService service : services){
                if(registerInDataBase(service)){
                    registered++;                       
                    piv.setValueToProgress(registered);
                    piv.setTextInfo(service.getId());
                    listeners.forEach(x -> x.onDataChange(service));                   
                }         
            }
            
            piv.dispose();
            if(parentView != null)
                JOptionPane.showMessageDialog(parentView, "Concluido. \n " + registered + " itens registrados com sucesso !");
                        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }catch(IllegalArgumentException e1){
            System.out.print(e1.getMessage());
        }catch(ServiceException e){          
            System.out.print(e.getMessage() + (countLine+1));                      
        }catch(DbException e){
            System.out.print(e.getMessage() + (countLine+1));
        }      
    }
    
    private String loadId(String id) throws ServiceException {
        String prefix;
        String[] newId = id.split("/");
        try{
            if(newId[0].trim().replace(" ", "").length() > 5 && newId[1].length() == 4){
                prefix = newId[0].replace(" ", "").trim().substring(0, 4);
                if(prefix.matches("[+-]?\\d*(\\.\\d+)?") || prefix.matches(".*\\d.*")) //verifica se o prefix é um número ou contem algum
                    throw new ServiceException("Error of prefix in loadId() in line: ");
                newId[0] = newId[0].substring(5).trim();
                Integer numberId = Integer.parseInt(newId[0]);
                return prefix + " " + String.valueOf(numberId) + "/" + newId[1].trim();
            }else{ 
                return null;
            }
        }catch(NumberFormatException e){
             throw new ServiceException("Error in loadId(): número de identificação contem letra(s)");
        }
    }
    
    private void loadReserv(OrderService orderService, String reservs){
        if(reservs != null && !reservs.trim().equals("")){
            if(reservs.toUpperCase().trim().charAt(0) != 'R'){
                String[] reserv = reservs.split("-");
                for(String r : reserv){
                    Reserv res = new Reserv(r.trim());
                    res.setService(orderService);
                    orderService.addReserv(res);     
                }
            }else{
                int r = Integer.parseInt(reservs.trim().replace("R", "").replace("-", ""));
                orderService.setR(String.valueOf(r));
                orderService.setStatusService(StatusService.EMBARGADO);
            }
        }else{
            orderService.setStatusService(StatusService.EMBARGADO);
        }
    }
    
    private boolean registerInDataBase(OrderService os) throws DbException{
        if(osDao.containsOrderService(os.getId())){                   
            return false;
        }else{          
             osDao.insertOrderService(os);       
        }
        return true;
    }
    
    public void subscribeDataChangeListener(DataChangeListener dcl){
        if(!listeners.contains(dcl)){
            listeners.add(dcl);
        }    
    }

}
