/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.entities.OrderService;

/**
 *
 * @author Abimael
 */
public interface OrderServiceDao {
    void insertOrderService(OrderService os);
    boolean containsOrderService(String id);
    
}
