/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.db.DB;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.exceptions.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    public boolean containsOrderService(String id) {
       PreparedStatement st = null;
       ResultSet rs = null;
       
       try{
           st = conn.prepareStatement("SELECT Id FROM orderService WHERE Id = ?");
           
           st.setString(1, id);           
           rs = st.executeQuery();
           
           if(rs.next())
               return true;
           
           return false;          
       }catch(SQLException e){
           throw new DbException("Error in containsOrderService(): " + e.getMessage());
       }finally{
           DB.closeStatement(st);
           DB.closeResultSet(rs);
       }
    }

    @Override
    public void insertOrderService(OrderService os) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("INSERT INTO orderservice (Id, Lote, Alimentador, Base, " 
                                        + "Tipo, ObjetoTecnico, Localizacao, Km, R, Reserva, Descricao, "
                                        + "DataRegistro, Cidade, Fiscal, "
                                        + "Situacao) "
                                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            st.setString(1, os.getId());
            st.setString(2, os.getLote());
            st.setString(3, os.getAlim());
            st.setString(4, os.getBase());
            st.setString(5, os.getServiceType().toString());
            st.setString(6, os.getTechnicalObject());
            st.setString(7, os.getLocal());
            st.setDouble(8, os.getUnlockKm());
            st.setString(9, os.getR());
            st.setString(10, os.getReservsId().trim());
            st.setString(11, os.getDescription());           
            st.setDate(12, new java.sql.Date(os.getRegisterDate().getTime()));
            st.setString(13, os.getCity());
            st.setString(14, os.getFiscal());
            st.setString(15, os.getStatusService().toString());

            int rowsAffected = st.executeUpdate();
        }catch(SQLException e){
            throw new DbException("Error in insertOrderService(): " + e.getMessage());          
        }
        
    }
    
}
