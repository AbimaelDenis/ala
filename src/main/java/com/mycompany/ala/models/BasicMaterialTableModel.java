/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.entities.Material;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class BasicMaterialTableModel extends AbstractTableModel {
    private static List<Material> allMaterials = DaoFactory.createMaterialDao().findAll();
    private List<Material> filterMaterials = new ArrayList<>();
    private String[] columns = {"Código", "Descrição", "Unidade"};
    
    public BasicMaterialTableModel(){
        this.filterMaterials = this.allMaterials;    
    }
    
    @Override
    public int getRowCount() {
       return filterMaterials.size();
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
            return filterMaterials.get(rowIndex).getCode();
        }
        if(columnIndex == 1){
            return filterMaterials.get(rowIndex).getDescription();
        }
        else{
            return filterMaterials.get(rowIndex).getUnits();
        }
    }
    
    public Material getMaterial(int index){
        return filterMaterials.get(index);
    }
    
    public void setFilter(Predicate<Material> filter){
        this.filterMaterials = (List<Material>) allMaterials.stream().filter(filter).collect(Collectors.toList());
        this.fireTableDataChanged();
    }
}
