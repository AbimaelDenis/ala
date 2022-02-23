/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.RequestMaterial;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class StructureMaterialTableModel extends AbstractTableModel{
  
    private List<RequestMaterial> materials = null;
    private String[] column = {"Código", "Descrição", "Quantidade", "Unidade"};
    
    public StructureMaterialTableModel(List<RequestMaterial> materials){
        this.materials = materials;       
    }
    
    @Override
    public int getRowCount() {
        return materials.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }
    
    @Override
    public String getColumnName(int column){
        return this.column[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return materials.get(rowIndex).getCode();
        if(columnIndex == 1)
            return materials.get(rowIndex).getDescription();
        if(columnIndex == 2)
            return materials.get(rowIndex).getRequestQuantity();
        else
            return materials.get(rowIndex).getUnits();
    }
    
}
