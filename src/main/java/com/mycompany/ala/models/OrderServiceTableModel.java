/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.services.DataChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class OrderServiceTableModel extends AbstractTableModel implements DataChangeListener {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static List<OrderService> services = new ArrayList<>();
    private String[] columns = {"Id", "Lote", "Alimentador", "Obj. Técnico", "Local", "Projeto", "Reservas", "Tipo", "Status", "Data de Registro"};
    
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
            return services.get(rowIndex).getAlim();
        if(columnIndex == 3)
            return services.get(rowIndex).getTechnicalObject();
        if(columnIndex == 4)
            return services.get(rowIndex).getLocal();
        if(columnIndex == 5)
            return services.get(rowIndex).getR();
        if(columnIndex == 6)
            return services.get(rowIndex).getReservs();
        if(columnIndex == 7)
            return services.get(rowIndex).getServiceType();
        if(columnIndex == 7)
            return services.get(rowIndex).getStatusService();
        else       
            return sdf.format(services.get(rowIndex).getRegisterDate());
    }

    @Override
    public void onDataChange(Object obj) {
        if(obj instanceof OrderService){
            services.add((OrderService) obj);
            this.fireTableDataChanged();
        }
    }

    
    
}
