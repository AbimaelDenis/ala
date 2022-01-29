/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao.impl;

import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.db.DB;
import com.mycompany.ala.entities.BudgetMaterial;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.enums.ExpenditureType;
import com.mycompany.ala.enums.ReservType;
import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import com.mycompany.ala.exceptions.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Abimael
 */
public class OrderServiceDaoJDBC implements OrderServiceDao {

    private static Connection conn = null;

    public OrderServiceDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean containsOrderService(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT Id FROM orderService WHERE Id = ?");

            st.setString(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new DbException("Error in containsOrderService(): " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void insertOrderService(OrderService os) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO orderservice (Id, Lote, Alimentador, Base, "
                    + "Tipo, ObjetoTecnico, Localizacao, Km, R, Reserva, Descricao, "
                    + "DataRegistro, DataConclusao, DataEncerramento, Cidade, Fiscal, "
                    + "Situacao, TipoDespesa, Embargado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, os.getId());
            st.setString(2, os.getLote());
            st.setString(3, os.getAlim());
            st.setString(4, os.getBase());
            st.setString(5, os.getServiceType().toString());
            st.setString(6, os.getTechnicalObject());
            st.setString(7, os.getLocal());
            if (os.getUnlockKm() != null) {
                st.setDouble(8, os.getUnlockKm());
            } else {
                st.setDouble(8, 0);
            }
            st.setString(9, os.getR());
            st.setString(10, os.getReservsId().trim());
            st.setString(11, os.getDescription());

            st.setDate(12, new java.sql.Date(os.getRegisterDate().getTime()));
            if (os.getConclusionDate() != null) {
                st.setDate(13, new java.sql.Date(os.getConclusionDate().getTime()));
            } else {
                st.setDate(13, null);
            }
            if (os.getCloseDate() != null) {
                st.setDate(14, new java.sql.Date(os.getCloseDate().getTime()));
            } else {
                st.setDate(14, null);
            }

            st.setString(15, os.getCity());
            st.setString(16, os.getFiscal());
            st.setString(17, os.getStatusService().toString());

            if (os.getExpenditureType() != null) {
                st.setString(18, os.getExpenditureType().toString());
            } else {
                st.setDate(18, null);
            }
            st.setBoolean(19, os.isEmbarg());
            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error in insertOrderService(): " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public List<OrderService> findAllOpenServices() {
        List<OrderService> services = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("SELECT*FROM orderservice WHERE Situacao != 'ENCERRADO'");

            while (rs.next()) {
                services.add(instantiateOrderService(rs));             
            }
            return services;
        } catch (SQLException e) {
            throw new DbException("Error in findAllOpenServices(): " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private OrderService instantiateOrderService(ResultSet rs) throws SQLException {
        String id = rs.getString("Id");
        String lote = rs.getString("Lote");
        String alim = rs.getString("Alimentador");
        String base = rs.getString("Base");
        String serviceType = rs.getString("Tipo");
        String objTec = rs.getString("ObjetoTecnico");
        String local = rs.getString("Localizacao");
        Double unlockKm = rs.getDouble("Km");
        String R = rs.getString("R");
        String[] reservs = rs.getString("Reserva").split(" ");
        String decript = rs.getString("Descricao");
        Date registerDate = rs.getDate("DataRegistro");
        Date conclusionDate = rs.getDate("DataConclusao");
        Date closeDate = rs.getDate("DataEncerramento");
        String city = rs.getString("Cidade");
        String fiscal = rs.getString("Fiscal");
        String statusService = rs.getString("Situacao");
        String expenditureType = rs.getString("TipoDespesa");
        boolean embarg = rs.getBoolean("Embargado");

        OrderService os = new OrderService(id, lote, alim, base, ServiceType.valueOf(serviceType), objTec, local, decript, registerDate);
        os.setUnlockKm(unlockKm);
        os.setR(R);
        for (String r : reservs) {
            Reserv res = new Reserv(r);
            res.setService(os);
            os.addReserv(res);
        }

        os.setConclusionDate(conclusionDate);
        os.setCloseDate(closeDate);
        os.setCity(city);
        os.setFiscal(fiscal);
        if (statusService != null) {
            os.setStatusService(StatusService.valueOf(statusService));
        }
        if (expenditureType != null) {
            os.setExpenditureType(ExpenditureType.valueOf(expenditureType));
        }
        os.setEmbarg(embarg);
        
        
        for(Reserv r : findReservByServiceId(os.getId())){
            for(Reserv res : os.getReservs()){
                if(r.getId().trim().equals(res.getId().trim())){
                   res = r;                 
                }
            }
        }
        
        return os;
    }

    @Override
    public void updateSapCheck(OrderService os) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE orderservice SET Tipo = ?, R = ?, Reserva = ?, Descricao = ?, "
                    + "Cidade = ?, Situacao = ?, TipoDespesa = ?, Embargado = ? WHERE Id = ?");

            st.setString(1, String.valueOf(os.getServiceType()));
            st.setString(2, os.getR());
            st.setString(3, os.getReservsId().trim());
            st.setString(4, os.getDescription());
            st.setString(5, os.getCity());
            st.setString(6, String.valueOf(os.getStatusService()));
            st.setString(7, String.valueOf(os.getExpenditureType()));
            st.setBoolean(8, os.isEmbarg());
            st.setString(9, os.getId());
           
            System.out.println(os.getReservsId());
            st.executeUpdate();

            String[] reserv = os.getReservsId().split(" ");
            for (String r : reserv) {
                if (reserv.length > 0 && reserv != null) {
                    conn.setAutoCommit(false);
                    //deleteReservByServiceId(os.getId());
                    List<BudgetMaterial> materials = os.getReservById(r).getBudgetMaterials();
                    for (BudgetMaterial bm : materials) {
                        st = conn.prepareStatement("INSERT INTO budgetmaterial (Reserva, Codigo, Descricao, Unidade, "
                                + "Orcado, Atendido, DataNecessidade, Recebedor, Tipo, Servico) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                        st.setString(1, bm.getReserv().getId());
                        st.setString(2, bm.getCode());
                        st.setString(3, bm.getDescription());
                        st.setString(4, bm.getUnits());
                        st.setDouble(5, bm.getBudgetQuantity());
                        st.setDouble(6, bm.getDispatchedQauntity());
                        st.setDate(7, new java.sql.Date(bm.getReserv().getNeedDate().getTime()));
                        st.setString(8, bm.getReserv().getReceptor());
                        st.setString(9, String.valueOf(bm.getReserv().getReservType()));
                        st.setString(10, bm.getReserv().getService().getId());

                        st.executeUpdate();
                    }
                }
                conn.commit();
            }

            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            try{
                conn.rollback();
                throw new DbException("Transaction rolled back. Error in updateSapCheck(): " + e.getMessage());
            }catch(SQLException e1){
                throw new DbException("Error trying to rollback. Error in updateSapCheck(): " + e.getMessage());
            }
        } finally {
            DB.closeStatement(st);
        }
    }
    
    @Override
    public Set<Reserv> findReservByServiceId(String id){
        Set<Reserv> reservs = new HashSet<>();
        List<BudgetMaterial> budgetMaterials = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT*FROM budgetmaterial WHERE Servico = ?");
            
            st.setString(1, id.trim());
            
            rs = st.executeQuery();
            
            while(rs.next()){
                String reservId = rs.getString("Reserva");
                String code = rs.getString("Codigo");
                String descrip = rs.getString("Descricao");
                String unit = rs.getString("Unidade");
                Double budgetQuantity = rs.getDouble("Orcado");
                Double dispatchedQuantity = rs.getDouble("Atendido");
                Date needDate = rs.getDate("DataNecessidade");
                String receptor = rs.getString("Recebedor");
                ReservType type = ReservType.valueOf(rs.getString("Tipo"));
                
                Reserv r = new Reserv(reservId, receptor, needDate, type);
                reservs.add(r);
                BudgetMaterial bm = new BudgetMaterial(code, descrip, unit, budgetQuantity, r);
                bm.setDispatchedQauntity(dispatchedQuantity);
                budgetMaterials.add(bm);
            }
            
            for(Reserv r : reservs){
                for(BudgetMaterial bm : budgetMaterials){
                    if(bm.getReserv().getId().trim().equals(r.getId())){
                        r.addMaterial(bm);
                    }
                }
            }
            return reservs;         
        }catch(SQLException e){
            throw new DbException("Error in findReservByServiceId(): " + e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);          
        }   
    }
    
    @Override
    public void deleteReservByServiceId(String id){
        PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement("DELETE FROM budgetMaterial WHERE Servico = ?");
            
            st.setString(1, id);           
            st.executeUpdate();
        }catch(SQLException e){
            throw new DbException("Error in deleteReservByServiceId(): " + e.getMessage());
        }
    }
}
