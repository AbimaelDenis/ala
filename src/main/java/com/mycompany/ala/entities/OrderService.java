/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ExpenditureType;
import com.mycompany.ala.enums.ServiceType;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Abimael
 */

public class OrderService extends Service implements Serializable{
    private String lote;
    private Date createDate;
    private String description;
    private String technicalObject;
    private String local;
    private Double unlockKm;
    private ExpenditureType expenditureType;
    private String r;
    
    public OrderService(String id, String lote, String alim, String base, ServiceType serviceType, String technicalObject, String local, Double unlockKm, String description, Date registerDate) {
        super(id, registerDate, base);
        super.setAlim(alim);
        super.setServiceType(serviceType);
        this.lote = lote;
        this.description = description;
        this.technicalObject = technicalObject;
        this.local = local;  
        this.unlockKm = unlockKm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnicalObject() {
        return technicalObject;
    }

    public void setTechnicalObject(String technicalObject) {
        this.technicalObject = technicalObject;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ExpenditureType getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(ExpenditureType expenditureType) {
        this.expenditureType = expenditureType;
    }

    public Double getUnlockKm() {
        return unlockKm;
    }

    public void setUnlockKm(Double unlockKm) {
        this.unlockKm = unlockKm;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return super.getId() + ": " + getDescription();
    }
    
    
    
    
    
}
