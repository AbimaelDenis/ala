/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Service;
import com.mycompany.ala.enums.ServiceType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author Abimael
 */
public final class OrderServiceService {
    private static List<OrderService> orderServices = new ArrayList<>();
    private static List<DataChangeListener> listeners = new ArrayList<>();
    
    public static void registerOrderServices(String path){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            String line = br.readLine();
            
            while(line != null && !line.trim().equals("END")){
                String[] row = line.split(";");
                
                line = br.readLine();
                while(line == null || line.length() == 0){  
                    line = br.readLine();
                }
               
                while(line.charAt(0) != 'O' && !line.trim().equals("END")){                   
                    row[8] += " " + line.trim();                                        
                    line = br.readLine(); 
                    while(line == null || line.length() == 0){
                        line = br.readLine();                       
                    }
                }
                               
                OrderService service = new OrderService(row[0].trim(), row[1].trim(), row[2].trim(), 
                                                    row[3].trim(), ServiceType.valueOf(row[4].trim()), 
                                                    row[5], Double.parseDouble(row[6].replace(",", ".")), 
                                                    row[8], new Date(), row[7]);
                System.out.println(service);
                orderServices.add(service); 
                listeners.forEach(x -> x.onDataChange(service));
            }
            
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
 
    }
    
    public List<OrderService> getOrderServices(Predicate<OrderService> filter){
        
        return null;
    }
    
    public static void subscribeDataChangeListener(DataChangeListener dcl){
        if(!listeners.contains(dcl)){
            listeners.add(dcl);
        }    
    }
}
