/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.Material;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class BasicMaterialTableModel extends AbstractTableModel {
    private static List<Material> materials = new ArrayList<>();
    private String[] columns = {"Código", "Descrição", "Unidade"};
    
    @Override
    public int getRowCount() {
       return materials.size();
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
            return materials.get(rowIndex).getCode();
        }
        if(columnIndex == 1){
            return materials.get(rowIndex).getDescription();
        }
        else{
            return materials.get(rowIndex).getUnits();
        }
    }
    
}
