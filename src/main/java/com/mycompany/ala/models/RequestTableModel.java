/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;


import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.RequestMaterial;
import com.mycompany.ala.services.DataChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class RequestTableModel extends AbstractTableModel implements DataChangeListener {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<RequestMaterial> requestMaterials = new ArrayList<>();
    
    private String[] columns = {"Código", "Descrição", "Requisitado", "Orçado", "Acréscimo"};
    
    public RequestTableModel(){
      this.requestMaterials = new ArrayList<>();
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
        if(columnIndex == 2)
            return requestMaterials.get(rowIndex).getRequestQuantity();
        if(columnIndex == 3)
            return 1;
        else       
            return 1;
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
    
    public void setRequestMaterial(List<RequestMaterial> materials){
        this.requestMaterials = materials;
        this.fireTableDataChanged();
    }

   

    
    
}
