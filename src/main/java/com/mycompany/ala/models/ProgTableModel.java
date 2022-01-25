/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Prog;

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
public class ProgTableModel extends AbstractTableModel implements DataChangeListener {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static List<Prog> progs = new ArrayList<>();
    
    private String[] columns = {"Id", "SI", "Inicio", "Fim", "Tipo", "Responsável"};
    
    public ProgTableModel(){
       
    }
    
    @Override
    public int getRowCount() {
       return progs.size();
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
            return progs.get(rowIndex).getId();
        if(columnIndex == 1)
            return progs.get(rowIndex).getSi();
        if(columnIndex == 2)
            return sdf.format(progs.get(rowIndex).getStartDate());
        if(columnIndex == 3)
            return sdf.format(progs.get(rowIndex).getEndDate());
        if(columnIndex == 4)
            return progs.get(rowIndex).getProgType();
        else       
            return progs.get(rowIndex).getResp();
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
