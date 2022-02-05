/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.entities.OrderService;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Abimael
 */
public interface OrderServiceDao<T> {
    int insertOrderService(OrderService os);
    boolean containsOrderService(String id);
    List<OrderService> findAllOpenServices(); 
    OrderService findOrderServiceById(String id);
    void updateSapCheck(OrderService os);
    int updateField(String sql, Consumer<PreparedStatement> con);
}
