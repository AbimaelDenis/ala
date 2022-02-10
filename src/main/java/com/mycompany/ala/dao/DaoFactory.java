/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.dao.impl.MaterialDaoJDBC;
import com.mycompany.ala.dao.impl.OrderServiceDaoJDBC;
import com.mycompany.ala.dao.impl.ReservDaoJDBC;
import com.mycompany.ala.db.DB;

/**
 *
 * @author Abimael
 */
public class DaoFactory {
    
    public static OrderServiceDao createOrderServiceDao(){
        return new OrderServiceDaoJDBC(DB.getConnection());
    }
    
    public static ReservDao createReservDao(){
        return new ReservDaoJDBC(DB.getConnection());
    }
    
    public static MaterialDao createMaterialDao(){
        return new MaterialDaoJDBC(DB.getConnection());
    }
}
