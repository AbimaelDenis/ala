/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import java.util.Date;

/**
 *
 * @author Abimael
 */
public abstract class Material {
    private String code;
    private String description;  
    private Double dispatchedQauntity;  
    private String units;

    public Material(String code, String description, String units) {
        this.code = code;
        this.description = description;
        this.units = units;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
  
    public Double getDispatchedQauntity() {
        return dispatchedQauntity;
    }

    public void setDispatchedQauntity(Double dispatchedQauntity) {
        this.dispatchedQauntity = dispatchedQauntity;
    }
    
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
