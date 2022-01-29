/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.ReservDao;
import com.mycompany.ala.entities.OrderService;
import java.sql.Connection;

/**
 *
 * @author Abimael
 */
public class ReservDaoJDBC implements ReservDao{
    private Connection conn = null;
    
    public ReservDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void findReservById(OrderService os) {
    }

    @Override
    public void deleteReservById(String id) {
    }
    
    
    
    
}
