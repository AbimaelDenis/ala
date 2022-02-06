/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.services;

import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.OrderServiceDao;
import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;
import com.mycompany.ala.enums.StatusService;
import com.mycompany.ala.exceptions.DbException;
import com.mycompany.ala.gui.ProgressInfoView;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.function.Consumer;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Abimael
 */
public class CompareAndUpdateOrderService extends Thread {

    private boolean reservModified = false;
    private JFrame parentView;
    private DataChangeListener listener;
    private OrderService oldOs;
    private OrderService newOs;
    private OrderServiceDao osDao = DaoFactory.createOrderServiceDao();

    public CompareAndUpdateOrderService(JFrame parentview, OrderService oldOs, OrderService newOs) {
        this.parentView = parentview;
        this.oldOs = oldOs;
        this.newOs = newOs;
    }

    @Override
    public void run() {
//        if (!oldOs.getCloseDate().equals(newOs.getCloseDate())) {
//
//        }

        if ((oldOs.getLote() == null && newOs.getLote() != null)
                || (oldOs.getLote() != null && newOs.getLote() == null)
                || ((oldOs.getLote() != null && newOs.getLote() != null)
                && (!oldOs.getLote().trim().equals(newOs.getLote().trim())))) {
            int n = osDao.updateField("UPDATE orderservice SET Lote = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getLote() != null) {
                            t.setString(1, newOs.getLote());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }

            }
            );
        }

        if ((oldOs.getR() == null && newOs.getR() != null)
                || (oldOs.getR() != null && newOs.getR() == null)
                || ((oldOs.getR() != null && newOs.getR() != null)
                && (!oldOs.getR().trim().equals(newOs.getR().trim())))) {
            osDao.updateField("UPDATE orderservice SET R = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getR() != null) {
                            t.setString(1, newOs.getR());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }

            }
            );
        }

        if (newOs.getReservs().size() != oldOs.getReservs().size()) {
            updateReserv();
        } else {
            for (Reserv newRes : newOs.getReservs()) {
                if (!oldOs.getReservs().contains(newRes)) {
                    updateReserv();
                    reservModified = true;
                    System.out.println("Reserva adicionada!");
                }
            }
            if (!reservModified) {
                for (Reserv oldRes : oldOs.getReservs()) {
                    if (!newOs.getReservs().contains(oldRes)) {
                        updateReserv();
                        System.out.println("Reserva removida!");
                    }
                }
            }
        }

        if ((oldOs.getBase() == null && newOs.getBase() != null)
                || (oldOs.getBase() != null && newOs.getBase() == null)
                || ((oldOs.getBase() != null && newOs.getBase() != null)
                && (!oldOs.getBase().trim().equals(newOs.getBase().trim())))) {
            osDao.updateField("UPDATE orderservice SET Base = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getBase() != null) {
                            t.setString(1, newOs.getBase());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }

            }
            );
        }

        if ((oldOs.getCity() == null && newOs.getCity() != null)
                || (oldOs.getCity().trim() != null && newOs.getCity().trim() == null)
                || ((oldOs.getCity() != null && newOs.getCity() != null)
                && (!oldOs.getCity().trim().equals(newOs.getCity().trim())))) {
            osDao.updateField("UPDATE orderservice SET Cidade = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getCity() != null) {
                            t.setString(1, newOs.getCity());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }

            }
            );
        }

        if ((oldOs.getFiscal() == null && newOs.getFiscal() != null)
                || (oldOs.getFiscal() != null && newOs.getFiscal() == null)
                || ((oldOs.getFiscal() != null && newOs.getFiscal() != null)
                && (!oldOs.getFiscal().trim().equals(newOs.getFiscal().trim())))) {
            osDao.updateField("UPDATE orderservice SET Fiscal = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getFiscal() != null) {
                            t.setString(1, newOs.getFiscal());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getAlim() == null && newOs.getAlim() != null)
                || (oldOs.getAlim() != null && newOs.getFiscal() == null)
                || ((oldOs.getAlim() != null && newOs.getAlim() != null)
                && (!oldOs.getAlim().trim().equals(newOs.getAlim().trim())))) {
            osDao.updateField("UPDATE orderservice SET Alimentador = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getAlim() != null) {
                            t.setString(1, newOs.getAlim());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getTechnicalObject() == null && newOs.getTechnicalObject() != null)
                || (oldOs.getTechnicalObject() != null && newOs.getTechnicalObject() == null)
                || ((oldOs.getTechnicalObject() != null && newOs.getTechnicalObject() != null)
                && (!oldOs.getTechnicalObject().trim().equals(newOs.getTechnicalObject().trim())))) {
            osDao.updateField("UPDATE orderservice SET ObjetoTecnico = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getTechnicalObject() != null) {
                            t.setString(1, newOs.getTechnicalObject());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getLocal() == null && newOs.getLocal() != null)
                || (oldOs.getLocal() != null && newOs.getLocal() == null)
                || ((oldOs.getLocal() != null && newOs.getLocal() != null)
                && (!oldOs.getLocal().trim().equals(newOs.getLocal().trim())))) {
            osDao.updateField("UPDATE orderservice SET Localizacao = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getLocal() != null) {
                            t.setString(1, newOs.getLocal());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getUnlockKm() == null && newOs.getUnlockKm() != null)
                || (oldOs.getUnlockKm() != null && newOs.getUnlockKm() == null)
                || ((oldOs.getUnlockKm() != null && newOs.getUnlockKm() != null)
                && (!oldOs.getUnlockKm().equals(newOs.getUnlockKm())))) {
            osDao.updateField("UPDATE orderservice SET Km = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getUnlockKm() != null) {
                            t.setDouble(1, newOs.getUnlockKm());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getServiceType() == null && newOs.getServiceType() != null)
                || (oldOs.getServiceType() != null && newOs.getServiceType() == null)
                || ((oldOs.getServiceType() != null && newOs.getServiceType() != null)
                && (!oldOs.getServiceType().equals(newOs.getServiceType())))) {
            osDao.updateField("UPDATE orderservice SET Tipo = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getServiceType() != null) {
                            t.setString(1, newOs.getServiceType().toString());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getExpenditureType() == null && newOs.getExpenditureType() != null)
                || (oldOs.getExpenditureType() != null && newOs.getExpenditureType() == null)
                || ((oldOs.getExpenditureType() != null && newOs.getExpenditureType() != null)
                && (!oldOs.getExpenditureType().equals(newOs.getExpenditureType())))) {
            osDao.updateField("UPDATE orderservice SET TipoDespesa = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getExpenditureType() != null) {
                            t.setString(1, newOs.getExpenditureType().toString());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getStatusService() == null && newOs.getStatusService() != null)
                || (oldOs.getStatusService() != null && newOs.getStatusService() == null)
                || ((oldOs.getStatusService() != null && newOs.getStatusService() != null)
                && (!oldOs.getStatusService().equals(newOs.getStatusService())))) {
            osDao.updateField("UPDATE orderservice SET Situacao = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getStatusService() != null) {
                            t.setString(1, newOs.getStatusService().toString());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }

        if ((oldOs.getConclusionDate() == null && newOs.getConclusionDate() != null)
                || (oldOs.getConclusionDate() != null && newOs.getConclusionDate() == null)
                || ((oldOs.getConclusionDate() != null && newOs.getConclusionDate() != null)
                && (!oldOs.getConclusionDate().equals(newOs.getConclusionDate())))) {
            osDao.updateField("UPDATE orderservice SET DataConclusao = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getConclusionDate() != null) {
                            t.setDate(1, new java.sql.Date(newOs.getConclusionDate().getTime()));
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }
        
        if ((oldOs.getDescription()== null && newOs.getDescription() != null)
                || (oldOs.getDescription() != null && newOs.getDescription() == null)
                || ((oldOs.getDescription() != null && newOs.getDescription() != null)
                && (!oldOs.getDescription().trim().equals(newOs.getDescription().trim())))) {
            osDao.updateField("UPDATE orderservice SET Descricao = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
                @Override
                public void accept(PreparedStatement t) {
                    try {
                        if (newOs.getDescription() != null) {
                            t.setString(1, newOs.getDescription());
                        } else {
                            t.setString(1, null);
                        }
                        t.setString(2, oldOs.getId());
                    } catch (SQLException e) {
                        throw new DbException("Error in CompareAndUpdateOrderService()");
                    }
                }
            }
            );
        }
        if(newOs.getStatusService() != StatusService.ENCERRADO)           
            onDataChange(oldOs);          
        else{                  
            onDataChange(null);            
        }
    }

    private void updateReserv() {
        osDao.updateField("UPDATE orderservice SET Reserva = ? WHERE Id = ?;", new Consumer<PreparedStatement>() {
            @Override
            public void accept(PreparedStatement t) {
                try {
                    t.setString(1, newOs.getReservsId());
                    t.setString(2, oldOs.getId());
                } catch (SQLException e) {
                    throw new DbException("Error in CompareAndUpdateOrderService()");
                }
            }

        }
        );
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        this.listener = listener;
    }

    private void onDataChange(OrderService os) {
        if (this.listener != null) {
            listener.onDataChange(os);
        }
    }

}
