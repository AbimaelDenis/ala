/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Abimael
 */
public interface OrderServiceDao {
    void insertOrderService(OrderService os);
    boolean containsOrderService(String id);
    List<OrderService> findAllOpenServices();  
    void updateSapCheck(OrderService os);
    Set<Reserv> findReservByServiceId(String id);
    void deleteReservByServiceId(String id);
}
