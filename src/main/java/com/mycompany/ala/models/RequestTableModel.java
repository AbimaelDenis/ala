/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Prog;
import com.mycompany.ala.entities.Request;
import com.mycompany.ala.entities.RequestMaterial;

import com.mycompany.ala.services.DataChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class RequestTableModel extends AbstractTableModel implements DataChangeListener {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static List<RequestMaterial> requestMaterials = new ArrayList<>();
    
    private String[] columns = {"Código", "Descrição", "Q. Requisitado"};
    
    public RequestTableModel(){
       
    }
    
    @Override
    public int getRowCount() {
       return requestMaterials.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column){
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return requestMaterials.get(rowIndex).getCode();      
        if(columnIndex == 1)
            return requestMaterials.get(rowIndex).getDescription();      
        else       
            return requestMaterials.get(rowIndex).getRequestQuantity();
    }

    @Override
    public void onDataChange(Object obj) {
        if(obj instanceof OrderService){          
            //progs.add((OrderService) obj);                      
            this.fireTableDataChanged();
        }
    }
    
    public void loadServicesList(){ 
        //progs = DaoFactory.createOrderServiceDao().findAllOpenServices();
        this.fireTableDataChanged();
    }
    
    

   

    
    
}
