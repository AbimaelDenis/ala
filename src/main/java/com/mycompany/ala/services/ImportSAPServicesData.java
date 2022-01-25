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
import com.mycompany.ala.exceptions.DbException;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Abimael
 */
public class ImportSAPServicesData extends Thread {
    int countLine = 0;
    private String path;
    private Component parentView;
    List<DataChangeListener> listeners = new ArrayList<>();
    OrderServiceDao osDao = DaoFactory.createOrderServiceDao();
    
    public ImportSAPServicesData(Component parentView, String path){
        this.parentView = parentView;
        this.path = path;
    }
     
    @Override
    public void run(){
        JOptionPane.showMessageDialog(parentView, "ImportSAPServicesData.run()");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            String line = br.readLine();
            line = br.readLine();
            countLine++;
            
            List<OrderService> openServices = osDao.findAllOpenServices();
            while(line != null){
                String[] fields = line.split(";");
                                                           
                //Percorre cada serviço aberto
                for(OrderService os : openServices){
                    
                    if(os.getReservsId().trim().length() > 0){
                        String[] reservsId = os.getReservsId().split(" ");
                        //Percorre cada reserva do serviço atual
                        for(String resId : reservsId){
                            if(resId.equals(fields[3])){
                                BudgetMaterial bm = new BudgetMaterial(fields[6].trim(), fields[8].trim(), fields[7].trim(), Double.parseDouble(fields[12].replace(",", ".").trim()), os.getReservById(resId));
                                System.out.println(resId + bm);
                                os.getReservById(resId).addMaterial(bm);
                            }
                        }
                    }
                }
                line = br.readLine();
                countLine++;
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(DbException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println(countLine);
        }
    }
    
    public void subscribeDataChangeListener(DataChangeListener dataChangeListener){
        listeners.add(dataChangeListener);
    }
}
