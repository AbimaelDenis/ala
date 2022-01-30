/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.Reserv;
import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class MaterialTableModel extends AbstractTableModel{
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Reserv reserv = null;
    private String[] column = {"Reserva", "Código", "Descrição", "Unidade", "Orçado", "Atendido", "Data Ncessidate" , "Tipo"};
    
    public MaterialTableModel(Reserv reserv){
        this.reserv = reserv;       
    }
    
    @Override
    public int getRowCount() {
        return reserv.getBudgetMaterials().size();
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
            return reserv.getId();        
        if(columnIndex == 1)
            return reserv.getBudgetMaterials().get(rowIndex).getCode();
        if(columnIndex == 2)
            return reserv.getBudgetMaterials().get(rowIndex).getDescription();
        if(columnIndex == 3)
            return reserv.getBudgetMaterials().get(rowIndex).getUnits();
        if(columnIndex == 4)
            return reserv.getBudgetMaterials().get(rowIndex).getBudgetQuantity();
        if(columnIndex == 5)
            return reserv.getBudgetMaterials().get(rowIndex).getDispatchedQauntity();
        if(columnIndex == 6)
            return sdf.format(reserv.getNeedDate());
        else
           return String.valueOf(reserv.getReservType()).replace("T", "");
        
    }
    
}
