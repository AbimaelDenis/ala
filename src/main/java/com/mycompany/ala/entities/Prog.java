/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ProgType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abimael
 */
public final class Prog {
    private String id;
    private String si;
    private Date startDate;
    private Date endDate;
    private ProgType progType;
    private String resp;
    private String descrip;
    
    private List<Request> requests;
    private Service service;
    
    public Prog(Date startDate, Date endDate, ProgType progType, String resp){
        this.startDate = startDate;
        this.endDate = endDate;
        this.progType = progType;
        this.resp = resp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date end) {
        this.endDate = end;
    }

    public ProgType getProgType() {
        return progType;
    }

    public void setProgType(ProgType progType) {
        this.progType = progType;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
    
    public void addServices(Service service){
        
    }
    
    
    
    
}
