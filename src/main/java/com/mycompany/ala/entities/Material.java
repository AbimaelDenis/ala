/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;


import java.util.Objects;

/**
 *
 * @author Abimael
 */
public class Material {
    private String code;
    private String description;  
    private Double dispatchedQuantity;  
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
        return dispatchedQuantity;
    }

    public void setDispatchedQuantity(Double dispatchedQauntity) {
        this.dispatchedQuantity = dispatchedQauntity;
    }
    
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.code);
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
    
    
}
