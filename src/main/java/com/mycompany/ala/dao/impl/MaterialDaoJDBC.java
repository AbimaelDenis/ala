/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.MaterialDao;
import com.mycompany.ala.db.DB;
import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.RequestMaterial;
import com.mycompany.ala.entities.Structure;
import com.mycompany.ala.exceptions.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Abimael
 */
public class MaterialDaoJDBC implements MaterialDao {

    private Connection conn;

    public MaterialDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Material material) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO material (Codigo, Descricao, Unidade) VALUES (?, ?, ?)");

            st.setString(1, material.getCode().trim());
            st.setString(2, material.getDescription().trim());
            st.setString(3, material.getUnits().trim());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error in MaterialDao: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT*FROM material");

            while (rs.next()) {
                String code = rs.getString("Codigo");
                String descript = rs.getString("DEscricao");
                String unit = rs.getString("Unidade");

                Material material = new Material(code, descript, unit);

                materials.add(material);
            }
            return materials;
        } catch (SQLException e) {
            throw new DbException("Error in MaterialDao: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public int insertRequest(RequestMaterial material) {
        PreparedStatement st = null;
        try {
            if (!containsMaterial(material.getServiceId(), material.getCode())) {
                st = conn.prepareStatement("INSERT INTO requestmaterial (Servico, Codigo, Descricao, Unidade, Quantidade) "
                        + " VALUES (?, ?, ?, ?, ?)");
                st.setString(1, material.getServiceId());
                st.setString(2, material.getCode());
                st.setString(3, material.getDescription());
                st.setString(4, material.getUnits());
                st.setDouble(5, material.getRequestQuantity());

                return st.executeUpdate();
            } else {
                st = conn.prepareStatement("UPDATE requestmaterial SET Quantidade = ? WHERE Servico = ? AND Codigo = ?");
                st.setDouble(1, material.getRequestQuantity());
                st.setString(2, material.getServiceId());
                st.setString(3, material.getCode());
                return st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DbException("Error in MaterialDao - insertRequest(): " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<RequestMaterial> findRequest(String serviceId) {
        List<RequestMaterial> materials = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT*FROM requestmaterial WHERE Service = ?");
            st.setString(1, serviceId);
            rs = st.executeQuery();

            while (rs.next()) {
                String code = rs.getString("Codigo");
                String descript = rs.getString("Descricao");
                String unit = rs.getString("Unidade");
                Double quantity = rs.getDouble("Quantidade");
                String servId = rs.getString("Servico");

                RequestMaterial material = new RequestMaterial(serviceId, code, descript, unit, quantity);
                materials.add(material);
            }
            return materials;
        } catch (SQLException e) {
            throw new DbException("Error in MaterialDao - findRequest(): " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Structure> findAllStructure() {
        Set<String> names = new HashSet<>();
        List<Structure> structuries= new ArrayList<>();
        List<Structure> filterStructuries = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT*FROM structure ORDER BY id");

            while (rs.next()) {
                Structure sttr = new Structure(rs.getInt("Id"), rs.getString("Nome").trim());
                structuries.add(sttr);
                names.add(sttr.getName().trim());
            }
            
            for (String name : names) {
                int max = 0;
                for(Structure sttr : structuries){
                    if((name.equals(sttr.getName().trim()) && (max < sttr.getId()))){
                        max = sttr.getId();
                    }
                }
                filterStructuries.add(new Structure(max, name));
            }
            return filterStructuries;
        } catch (SQLException e) {
            throw new DbException("Error int findAllStructure(): " + e.getMessage());
        }
    }

    private boolean containsMaterial(String serviceId, String code) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT Servico, Codigo FROM requestmaterial WHERE Servico = ? AND Codigo = ?");

            st.setString(1, serviceId);
            st.setString(2, code);

            rs = st.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DbException("Error in MaterialDao - containsMaterial(): " + e.getMessage());
        }
    }

}
