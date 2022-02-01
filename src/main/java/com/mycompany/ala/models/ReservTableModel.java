/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.services.DataChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class ReservTableModel extends AbstractTableModel implements DataChangeListener {

    private static List<Reserv> reservs = new ArrayList<>();

    private String[] columns = {"Reserva"};

    public ReservTableModel() {

    }

    @Override
    public int getRowCount() {
        return reservs.size();
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
        return reservs.get(rowIndex).getId();
    }

    @Override
    public void onDataChange(Object obj) {
        if (obj instanceof Reserv) {
            reservs.add((Reserv) obj);                      
            this.fireTableDataChanged();
        }
    }

    public void setReservList(List<Reserv> reservs) {
        this.reservs = reservs;
    }
    
    public List<Reserv> getReservList() {
        return this.reservs;
    }

    public Reserv getReserv(int index) {
        return reservs.get(index);
    }
}
