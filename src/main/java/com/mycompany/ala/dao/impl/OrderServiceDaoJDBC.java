/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.exceptions.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abimael
 */
public class OrderServiceDaoJDBC implements OrderServiceDao {
    private static Connection conn = null;
    
    public OrderServiceDaoJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public boolean containsOrderService(OrderService os) {
       PreparedStatement st = null;
       ResultSet rs = null;
       
       try{
           st = conn.prepareStatement("SELECT Id FROM orderService WHERE Id = ?");
           
           st.setString(1, os.getId());           
           rs = st.executeQuery();
           
           if(rs.next())
               return true;
           
           return false;          
       }catch(SQLException e){
           throw new DbException("Error in containsOrderService(): " + e.getMessage());
       }       
    }
    
}
