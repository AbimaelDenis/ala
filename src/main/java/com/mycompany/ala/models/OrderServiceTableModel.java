/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.services.DataChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class OrderServiceTableModel extends AbstractTableModel implements DataChangeListener {
    
    private static List<OrderService> services = new ArrayList<>();
    private String[] columns = {"Id", "Lote", "Projeto", "Type", "Status"};
    
    @Override
    public int getRowCount() {
       return services.size();
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
            return services.get(rowIndex).getId();
        if(columnIndex == 1)
            return services.get(rowIndex).getLote();
        if(columnIndex == 2)
            return services.get(rowIndex).getR();
        if(columnIndex == 3)
            return services.get(rowIndex).getServiceType();
        else
            return services.get(rowIndex).getStatusService();        
    }

    @Override
    public void onDataChange(Object obj) {
        if(obj instanceof OrderService){
            services.add((OrderService) obj);
            this.fireTableDataChanged();
        }
    }

    
    
}
