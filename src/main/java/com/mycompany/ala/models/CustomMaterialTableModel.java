/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class CustomMaterialTableModel extends AbstractTableModel {
    private static List<Structure> allStructuries = DaoFactory.createMaterialDao().findAllStructure();
    private static List<Structure> filterStructuries = new ArrayList<>();
    private String[] columns = {"id", "Name"};
    
    public CustomMaterialTableModel(){
        this.filterStructuries = this.allStructuries;
    }
    
    @Override
    public int getRowCount() {
       return filterStructuries.size();
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
            return filterStructuries.get(rowIndex).getId();
        }
        else
            return filterStructuries.get(rowIndex).getName();
    }
}
