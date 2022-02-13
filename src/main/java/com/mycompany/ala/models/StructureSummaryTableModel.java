/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.models;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.MaterialDao;
import com.mycompany.ala.entities.RequestMaterial;
import com.mycompany.ala.entities.Structure;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abimael
 */
public class StructureSummaryTableModel extends AbstractTableModel {

    private RequestMaterialTableModel requestMatModel;
    private static MaterialDao materialDao = DaoFactory.createMaterialDao();
    private List<RequestMaterial> materials = new ArrayList<>();
    private List<RequestMaterial> requestStructuries = new ArrayList<>();
    private List<Structure> structuries = new ArrayList<>();

    private String[] columns = {"id", "Nome", "Quantidade"};

    public StructureSummaryTableModel(RequestMaterialTableModel requestMatModel) {
        this.requestMatModel = requestMatModel;
    }

    @Override
    public int getRowCount() {
        return requestStructuries.size();
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
            return requestStructuries.get(rowIndex).getCode();
        }
        if (columnIndex == 1) {
            return requestStructuries.get(rowIndex).getDescription();
        } else {
            return requestStructuries.get(rowIndex).getRequestQuantity();
        }
    }

    public List<RequestMaterial> getStructuries() {
        return requestStructuries;
    }

    public void setRequestMaterials(List<RequestMaterial> materials) {
        this.materials = materials;
        updateStructureTable();
        loadBasicMaterialList();
    }

    public void addStructure(RequestMaterial structure) {
        if (requestStructuries.contains(structure)) {
            for (RequestMaterial sttr : requestStructuries) {
                if (sttr.equals(structure)) {
                    sttr.setRequestQuantity(structure.getRequestQuantity());
                }
            }
        } else {
            materials.add(structure);
            updateStructureTable();
        }
        loadBasicMaterialList();
        this.fireTableDataChanged();
    }

    private void updateStructureTable() {
        this.requestStructuries = materials.stream().filter(m -> m.isStructure()).collect(Collectors.toList());
    }

    private void loadBasicMaterialList() {
        List<RequestMaterial> structuriesMaterials = new ArrayList<>();
        for (RequestMaterial structure : requestStructuries) {
            Structure sttr = new Structure(Long.parseLong(structure.getCode()), structure.getDescription());
            materialDao.findStructureMaterial(sttr, structure.getServiceId());
            sttr.getMaterials().forEach(m -> m.setRequestQuantity(m.getRequestQuantity()*structure.getRequestQuantity()));
            for(RequestMaterial material: sttr.getMaterials()){
                if(structuriesMaterials.contains(material)){
                    for(RequestMaterial m : structuriesMaterials){
                        if(m.equals(material)){
                            m.setRequestQuantity(m.getRequestQuantity() + material.getRequestQuantity());
                        }
                    }
                }else{
                    structuriesMaterials.add(material);
                }
            }          
            structuries.add(sttr);
        }
        requestMatModel.updateStructureMaterial(structuriesMaterials);
    }
}
