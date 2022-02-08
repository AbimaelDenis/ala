/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;


import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.RequestMaterial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class RequestMaterialTableModel extends AbstractTableModel {

   private List<RequestMaterial> requestMaterials = new ArrayList<>();
   private List<Material> budgetMaterials = new ArrayList<>();
    private String[] columns = {"Código", "Descrição", "Unidade", "Requisitado", "Orçado", "Acréscimo"};
    
    @Override
    public int getRowCount() {
       return requestMaterials.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return requestMaterials.get(rowIndex).getCode();
        }
        if(columnIndex == 1){
            return requestMaterials.get(rowIndex).getDescription();
        }
        if(columnIndex == 2){
            return requestMaterials.get(rowIndex).getUnits();
        }
        if(columnIndex == 3){
            return requestMaterials.get(rowIndex).getRequestQuantity();
        }
        if(columnIndex == 4){
            return 1;
        }
        else{
            return requestMaterials.get(rowIndex).getUnits();
        }
    }
}
