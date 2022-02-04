/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.entities.OrderService;

/**
 *
 * @author Abimael
 */
public class CompareOrderService extends Thread{
    private OrderService os1;
    private OrderService os2;
    private OrderServiceDao osDao = DaoFactory.createOrderServiceDao();
    
    public CompareOrderService(OrderService os1, OrderService os2){
        this.os1 = os1;
        this.os2 = os2;
    }
    
    @Override
    public void run() {
        
    }
    
}
