/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ReservType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Abimael
 */
public final class Reserv {
    private String id;
    private String receptor;
    private Date needDate;
    private ReservType reservType;
    
    private Service service;
    private List<BudgetMaterial> budgetMaterials = new ArrayList<>();
    
     public Reserv(String id) {
        this.id = id;
    }

    public Reserv(String id, String receptor, Date needDate, ReservType reservType) {
        this.id = id;
        this.receptor = receptor;
        this.needDate = needDate;
        this.reservType = reservType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public Date getNeedDate() {
        return needDate;
    }

    public void setNeedDate(Date needDate) {
        this.needDate = needDate;
    }

    public ReservType getReservType() {
        return reservType;
    }

    public void setReservType(ReservType reservType) {
        this.reservType = reservType;
    }
    
    public Service getService(){
        return service;
    }
    
    public void setService(Service service){
        this.service = service;
    }
    
    public void addMaterial(BudgetMaterial material){
        budgetMaterials.add(material);
    }

    public List<BudgetMaterial> getBudgetMaterials() {
        return budgetMaterials;
    }

    public void setBudgetMaterials(List<BudgetMaterial> budgetMaterials) {
        this.budgetMaterials = budgetMaterials;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserv other = (Reserv) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return getId();
    }
    
    
}
