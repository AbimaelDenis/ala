/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.MaterialDao;
import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.RequestMaterial;
import com.mycompany.ala.services.DataChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class RequestMaterialTableModel extends AbstractTableModel {
    private static DataChangeListener listener;
    private static MaterialDao materialDao = DaoFactory.createMaterialDao();
    private List<RequestMaterial> requestMaterials = new ArrayList<>();
    private List<RequestMaterial> basicRequestMaterials = new ArrayList<>();
    private List<RequestMaterial> structuriesRequestMaterials = new ArrayList<>();   
    private List<Material> budgetMaterials = new ArrayList<>();
    private String[] columns = {"Código", "Descrição", "Unidade", "Requisitado"};

    @Override
    public int getRowCount() {
        return basicRequestMaterials.size();
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
        if (columnIndex == 0) {
            return basicRequestMaterials.get(rowIndex).getCode();
        }
        if (columnIndex == 1) {
            return basicRequestMaterials.get(rowIndex).getDescription();
        }
        if (columnIndex == 2) {
            return basicRequestMaterials.get(rowIndex).getUnits();
        }
        else {
            return basicRequestMaterials.get(rowIndex).getRequestQuantity();
        }
        
    }

    public void addMaterial(RequestMaterial material) {
        if (requestMaterials.contains(material)) {
            for (RequestMaterial mat : requestMaterials) {
                if (mat.equals(material)) {                                                         
                    mat.setRequestQuantity(material.getRequestQuantity());
                    updateBasicList();
                }
            }
        } else {
            requestMaterials.add(material);
            updateBasicList();
        }
        this.fireTableDataChanged();
    }
    
    public void updateStructureMaterial(List<RequestMaterial> structureMaterials) {       
        this.structuriesRequestMaterials = structureMaterials;
        updateBasicList();
        this.fireTableDataChanged();
    }

    public void setRequestMaterials(List<RequestMaterial> requestMaterials) {
        this.requestMaterials = requestMaterials;
        updateBasicList();
        this.fireTableDataChanged();
    }

    public List<RequestMaterial> getRequestMaterials() {
        return this.requestMaterials;
    }
    
    public List<RequestMaterial> getBasicRequestMaterials() {
        return this.basicRequestMaterials;
    }

    private void updateBasicList() {
        this.basicRequestMaterials = requestMaterials.stream().filter(m -> !m.isStructure()).collect(Collectors.toList());
        for(RequestMaterial material : structuriesRequestMaterials){
            basicRequestMaterials.add(material);
        }
        if(listener != null){
            listener.onDataChange(null);
        }
    }
    
    public void subscribeListener(DataChangeListener listener){
        this.listener = listener;
    }
    
    
}
