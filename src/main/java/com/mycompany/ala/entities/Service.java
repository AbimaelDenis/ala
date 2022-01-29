/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Abimael
 */


public abstract class Service {
    
    private String id;
    private Date registerDate;//
    private Date conclusionDate;
    private Date closeDate;//
    private String fiscal;//
    private String alim;
    private String city;//
    private String base;
    
    private ServiceType serviceType;
    private StatusService statusService;//
    private boolean embarg;
    
    private Request request;
    private List<Log> logs = new ArrayList<>();//
    private List<Reserv> reservs = new ArrayList<>();
    private List<Prog> progs = new ArrayList<>();//
        
    public Service (String id, Date registerDate, String base){
        this.id = id;
        this.registerDate = registerDate;
        this.base = base;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(Date conclusionDate) {
        this.conclusionDate = conclusionDate;
    }
    
    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getFiscal() {
        return fiscal;
    }

    public void setFiscal(String fiscal) {
        this.fiscal = fiscal;
    }
    
    public String getAlim() {
        return alim;
    }

    public void setAlim(String alim) {
        this.alim = alim;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public StatusService getStatusService() {
        return statusService;
    }

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }
    
    public void addLog(Log log){
        logs.add(log);
    }
    
    public String getReservsId(){
        String reserv = "";
        for(Reserv r : reservs){
            reserv += r + " ";
        }
        return reserv;
    }
    
    public Reserv getReservById(String id){
        for(Reserv r : reservs){
            if(r.getId().equals(id)){
                return r;
            }
        }
        return null;
    }
    
    public void addReserv(Reserv reserv){
        reservs.add(reserv);
    }

    public List<Reserv> getReservs() {
        return reservs;
    } 
    
    public void addProg(Prog prog){
        progs.add(prog);
    }

    public boolean isEmbarg() {
        return embarg;
    }

    public void setEmbarg(boolean embarg) {
        this.embarg = embarg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Service other = (Service) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
