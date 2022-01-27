/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.entities.BudgetMaterial;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.enums.ExpenditureType;
import com.mycompany.ala.enums.ReservType;
import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import com.mycompany.ala.exceptions.DbException;
import com.mycompany.ala.util.CustomConsumer;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 * @author Abimael
 */
public class ImportSAPServicesData2 extends Thread {   
    List<String> lines;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String path;
    private Component parentView;
    List<DataChangeListener> listeners = new ArrayList<>();
    OrderServiceDao osDao = DaoFactory.createOrderServiceDao();

    public ImportSAPServicesData2(Component parentView, String path) {
        this.parentView = parentView;
        this.path = path;
    }

    @Override
    public void run() {
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            Stream<String> str = br.lines();
            this.lines = str.collect(Collectors.toList());
            this.lines.remove(0);
                       
            List<OrderService> openServices = osDao.findAllOpenServices();
            for (OrderService os : openServices) {               
                if (os.getReservsId() != null && os.getReservsId().trim().length() > 0) {                    
                    updateOrderService(os, (x, f) -> {
                        if(f[0] != null && f[0].trim().length() > 0)
                            x.setR(f[0].trim().substring(2, 9));
                    });
                if(os.getR() == null || os.getR().trim().length() == 0)
                    os.setExpenditureType(ExpenditureType.CUSTEIO);
                else
                    os.setExpenditureType(ExpenditureType.INVESTIMENTO);
                
                System.out.println("OS: " + os.getId() + "->" + os.getR());
                                                    
                }else if(os.getR() != null && os.getR().trim().length() > 0) {                   
                    Set<Reserv> reservs = new HashSet();
                    String[] fields;
                    for (String line : lines) {
                        fields = line.split(";");
                        
                        if(fields[0].trim().length() > 5){
                            int r1 = Integer.parseInt(os.getR().trim());
                            int r2 = Integer.parseInt(fields[0].trim().substring(3, 9));
                            if (r1 == r2) {
                                Reserv res = new Reserv(fields[3].trim());
                                res.setService(os);
                                reservs.add(res);
                            }
                        }
                    }
                    for(Reserv r : reservs){
                        os.addReserv(r);
                    }
                                     
                    if(os.getReservsId().trim() == null || os.getReservsId().trim().length() > 0) 
                        os.setStatusService(StatusService.EMBARGADO);
                    else{ 
                        os.setExpenditureType(ExpenditureType.INVESTIMENTO);
                        updateOrderService(os, (x, y) -> {}); 
                    }
                    System.out.println("Projeto: " + os.getR() + "->" + os.getReservsId());
                }               
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DbException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();           
        }catch(ParseException e){
            System.out.println(e.getMessage());            
        }catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void updateOrderService(OrderService os, CustomConsumer<OrderService, String[]> con) throws ParseException {
        String[] res = os.getReservsId().trim().split(" ");
        String[] fields;
        for (String resId : res) {
            List<BudgetMaterial> materials = new ArrayList<>();
            Reserv currentReserv = os.getReservById(resId);
            
            for (String line : lines) { //////////
                fields = line.split(";");
                
                if (resId.trim().equals(fields[3].trim())) {
                    con.accept(os, fields);
                    String budgetQuantity = fields[12].trim().replace(",", ".");
                    BudgetMaterial bm = new BudgetMaterial(fields[6].trim(), fields[8].trim(),
                            fields[7].trim(),
                            Double.parseDouble(budgetQuantity.replace(".", "")),
                            currentReserv);
                    String dispatchedQuantity = fields[13].trim().replace(".", "");
                    bm.setDispatchedQauntity(Double.parseDouble(dispatchedQuantity.replace(",", ".")));
                    if (currentReserv.getNeedDate() == null) {
                        currentReserv.setNeedDate(sdf.parse(fields[9].trim().replace(".", "/")));
                    }
                    if (currentReserv.getReceptor() == null || currentReserv.getReceptor().trim().equals("")) {
                        currentReserv.setReceptor(fields[4].trim());
                    }
                    if (currentReserv.getReservType() == null) {
                        currentReserv.setReservType(ReservType.valueOf("T" + fields[5].trim()));
                    }
                    if(os.getCity() == null || os.getCity().trim().length() == 0 && fields[2].trim().length() > 0)
                        os.setCity(fields[2].trim());                   
                    if(fields[1].trim().contains("MULT") || fields[1].trim().contains("DV CKT"))
                        os.setServiceType(ServiceType.MELHORIA);
                    if(fields[1].trim().length() > 0){
                        String descript = os.getDescription();
                        os.setDescription(fields[1].trim() + "\n\n" + descript);
                    }                    
                    materials.add(bm);
                }
            }
            currentReserv.setBudgetMaterials(materials);
        }
    }

    public void subscribeDataChangeListener(DataChangeListener dataChangeListener) {
        listeners.add(dataChangeListener);
    }
}
