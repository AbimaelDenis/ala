/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.db;

import com.mycompany.ala.exceptions.DbException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Abimael
 */
public class DB {
    private static Connection conn = null;
    
    public static Connection getConnection(){
        if(conn == null){
            try{
                Properties props = loadProperties();
                conn = DriverManager.getConnection(props.getProperty("dburl"), props);
            }catch(SQLException e){
                throw new DbException("Error in getConnection");
            }
        }
        return conn;
    }
    
    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                throw new DbException("Error in closeConnection()");
            }
        }
    }
    
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch(IOException e){
            throw new DbException("Error in loadProperties()");
        }
    }
    
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                throw new DbException("Error in closeResultSet()");
            }
        }
    }
    
    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            }catch(SQLException e){
                throw new DbException("Error in closeStatement()");
            }
        }
    }
}
