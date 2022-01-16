/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.entities.Service;
import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import com.mycompany.ala.exceptions.ServiceException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

/**
 *
 * @author Abimael
 */
public final class OrderServiceService {
    
    private static List<DataChangeListener> listeners = new ArrayList<>();
    
    public static String importOrderServices(String path){
        int sum = 0;
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
                try{   
                    OrderService service = new OrderService(loadId(row[0]), row[1].trim(), row[2].trim(), 
                                                        row[3].trim(), ServiceType.valueOf(row[4].trim()), 
                                                        row[5], row[6], Double.parseDouble(row[7].replace(",", ".")), 
                                                        row[9], new Date());
                    service.setStatusService(StatusService.REGISTRADO);
                    loadReserv(service, row[8]);       
                    System.out.println(service); 
                    listeners.forEach(x -> x.onDataChange(service));
                    sum++;
                }catch(ServiceException e){
                    throw new ServiceException(e.getMessage() + (sum+1));
                    //return "Id not processed in line " + (sum-1);    
                }
            }          
        }catch(IOException e){
            System.out.print(e.getMessage());
        }catch(IllegalArgumentException e1){
            System.out.print(e1.getMessage());
        }
        return "Importado " + sum + " serviços!";
    }
    
    public List<OrderService> getOrderServices(Predicate<OrderService> filter){
        
        return null;
    }
    
    public static void subscribeDataChangeListener(DataChangeListener dcl){
        if(!listeners.contains(dcl)){
            listeners.add(dcl);
        }    
    }
    
    private static String loadId(String id) throws ServiceException {
        String prefix;
        String[] newId = id.split("/");
        try{
            if(newId[0].trim().replace(" ", "").length() > 5 && newId[1].length() == 4){
                prefix = newId[0].replace(" ", "").trim().substring(0, 4);
                if(prefix.matches("[+-]?\\d*(\\.\\d+)?") || prefix.matches(".*\\d.*")) //verifica se o prex é um número ou contem algum
                    throw new ServiceException("Error of loadId() in line: ");
                newId[0] = newId[0].substring(5).trim();
                Integer numberId = Integer.parseInt(newId[0]);
                return prefix + " " +String.valueOf(numberId) + "/" + newId[1].trim();
            }else{          
                return null;
            }
        }catch(NumberFormatException e){
             throw new ServiceException("Error in load Id");
        }
    }
    
    private static void loadReserv(OrderService orderService, String reservs){
        if(reservs != null && !reservs.trim().equals("")){
            if(reservs.toUpperCase().trim().charAt(0) != 'R'){
                String[] reserv = reservs.split("-");
                for(String r : reserv){
                    orderService.addReserv(new Reserv(r.trim()));     
                }
            }else{
                orderService.setR(reservs.trim());
            }
        }else{
            orderService.setStatusService(StatusService.EMBARGADO);
        }
    }
}
