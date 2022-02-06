/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.ReservDao;
import com.mycompany.ala.db.DB;
import com.mycompany.ala.entities.BudgetMaterial;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.enums.ReservType;
import com.mycompany.ala.exceptions.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abimael
 */
public class ReservDaoJDBC implements ReservDao {

    private static Connection conn = null;

    public ReservDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void findReservsById(OrderService os) {
        List<Reserv> reservs = new ArrayList<>();

        PreparedStatement st = null;
        ResultSet rs = null;
        for (Reserv reserv : os.getReservs()) {
            try {
                List<BudgetMaterial> budgetMaterials = new ArrayList<>();
                st = conn.prepareStatement("SELECT*FROM budgetmaterial WHERE Reserva = ?");

                st.setString(1, reserv.getId().trim());

                rs = st.executeQuery();

                while (rs.next()) {
                    String reservId = rs.getString("Reserva");
                    String code = rs.getString("Codigo");
                    String descrip = rs.getString("Descricao");
                    String unit = rs.getString("Unidade");
                    Double budgetQuantity = rs.getDouble("Orcado");
                    Double dispatchedQuantity = rs.getDouble("Atendido");
                    Date needDate = rs.getDate("DataNecessidade");
                    String receptor = rs.getString("Recebedor");
                    ReservType type = ReservType.valueOf(rs.getString("Tipo"));

                    reserv.setReceptor(receptor);
                    reserv.setNeedDate(needDate);
                    reserv.setReservType(type);
                    BudgetMaterial bm = new BudgetMaterial(code, descrip, unit, budgetQuantity, reserv);
                    bm.setDispatchedQauntity(dispatchedQuantity);
                    budgetMaterials.add(bm);
                }

                reserv.setBudgetMaterials(budgetMaterials);

            } catch (SQLException e) {
                throw new DbException("Error in findReservByServiceId(): " + e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResultSet(rs);
            }
        }
    }

    @Override
    public Reserv findReservById(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Reserv reserv = new Reserv(id);
        List<BudgetMaterial> budgetMaterials = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT*FROM budgetmaterial WHERE Reserva = ?");
            st.setString(1, id.trim());
            rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("Codigo");
                String descrip = rs.getString("Descricao");
                String unit = rs.getString("Unidade");
                Double budgetQuantity = rs.getDouble("Orcado");
                Double dispatchedQuantity = rs.getDouble("Atendido");
                Date needDate = rs.getDate("DataNecessidade");
                String receptor = rs.getString("Recebedor");
                ReservType type = ReservType.valueOf(rs.getString("Tipo"));

                reserv.setReceptor(receptor);
                reserv.setNeedDate(needDate);
                reserv.setReservType(type);
                BudgetMaterial bm = new BudgetMaterial(code, descrip, unit, budgetQuantity, reserv);
                bm.setDispatchedQauntity(dispatchedQuantity);
                budgetMaterials.add(bm);
            }
            reserv.setBudgetMaterials(budgetMaterials);
            return reserv;
        } catch (SQLException e) {
            throw new DbException("Error in findReservById(): " + e.getMessage());
        }
    }

    @Override
    public void deleteReservById(String id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM budgetMaterial WHERE Reserva = ?");

            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error in deleteReservByServiceId(): " + e.getMessage());
        }
    }

    @Override
    public void insertReserv(Reserv reserv) {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);
            deleteReservById(reserv.getId());
            for (BudgetMaterial bm : reserv.getBudgetMaterials()) {
                st = conn.prepareStatement("INSERT INTO budgetmaterial (Reserva, Codigo, Descricao, Unidade, "
                        + "Orcado, Atendido, DataNecessidade, Recebedor, Tipo) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

                st.setString(1, bm.getReserv().getId());
                st.setString(2, bm.getCode());
                st.setString(3, bm.getDescription());
                st.setString(4, bm.getUnits());
                st.setDouble(5, bm.getBudgetQuantity());
                st.setDouble(6, bm.getDispatchedQauntity());
                st.setDate(7, new java.sql.Date(bm.getReserv().getNeedDate().getTime()));
                st.setString(8, bm.getReserv().getReceptor());
                st.setString(9, String.valueOf(bm.getReserv().getReservType()));
                //st.setString(10, bm.getReserv().getService().getId());

                st.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back. Error in updateSapCheck(): " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback. Error in updateSapCheck(): " + e.getMessage());
            }
        }
    }

}
