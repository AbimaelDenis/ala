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
    private List<RequestMaterial> requestMaterials = new ArrayList<>(); // material do serviço
    private List<RequestMaterial> basicRequestMaterials = new ArrayList<>(); // material exibido
    private List<RequestMaterial> structuriesRequestMaterials = new ArrayList<>(); // material de estrutura exibido
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
        } else {
            return basicRequestMaterials.get(rowIndex).getRequestQuantity();
        }

    }

    public void addMaterial(RequestMaterial material) {
        if (requestMaterials.contains(material)) {
            if (material.getRequestQuantity() > 0) {
                for (RequestMaterial mat : requestMaterials) {
                    if (mat.equals(material)) {
                        mat.setRequestQuantity(material.getRequestQuantity());
                        updateBasicList();
                    }
                }
            }else{
                requestMaterials.remove(material);
                updateBasicList();
            }
        } else {
            requestMaterials.add(material);
            updateBasicList();
        }
        dataChanged();
        this.fireTableDataChanged();
    }

    public void updateStructureMaterial(List<RequestMaterial> structureMaterials) {
        this.structuriesRequestMaterials = structureMaterials;
        updateBasicList();
        dataChanged();
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

    public RequestMaterial getRequestBasicMaterial(int index) {
        return this.basicRequestMaterials.get(index);
    }

    private void updateBasicList() {
        this.basicRequestMaterials = requestMaterials.stream().filter(m -> !m.isStructure()).collect(Collectors.toList());
        for (RequestMaterial material : structuriesRequestMaterials) {
            if(material.getRequestQuantity() > 0)
                basicRequestMaterials.add(material);
        }
        
    }

    public void subscribeListener(DataChangeListener listener) {
        this.listener = listener;
    }
    
    private void dataChanged(){
        //OrderServiceFormView update tbRequestMaterial
        if (listener != null) {
            listener.onDataChange(null);
        }
    }

}
