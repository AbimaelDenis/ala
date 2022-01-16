/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import com.mycompany.ala.enums.ServiceType;
import com.mycompany.ala.enums.StatusService;
import java.util.Date;

/**
 *
 * @author Abimael
 */
public final class Project extends Service {
     private String note;
     private String title;
     private Date contractDate;
     private Date dueDate;

    public Project(String id, String title, String note, String city,  String base, Date contractDate, Date dueDate, Date registerDate) {
        super(id, registerDate, base);
        this.note = note;
        this.title = title;
        this.contractDate = contractDate;
        this.dueDate = dueDate;
    }

   

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
