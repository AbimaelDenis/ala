/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ReservType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abimael
 */
public final class Reserv {
    private String id;
    private String receptor;
    private Date needDate;
    private ReservType reservType;
    
    private List<Service> services;
    private List<Material> materials;
    
     public Reserv(String id) {
        this.id = id;
    }

    public Reserv(String id, String receptor, ReservType reservType) {
        this.id = id;
        this.receptor = receptor;
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
    
    public void referService(Service service){
        services.add(service);
    }
    
    public void addMaterial(Material material){
        materials.add(material);
    }

    @Override
    public String toString() {
        return getId();
    }
    
    
}
