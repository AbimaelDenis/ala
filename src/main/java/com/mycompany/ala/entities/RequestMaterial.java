/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

/**
 *
 * @author Abimael
 */
public class RequestMaterial extends Material{
    private Double requestQuantity;
    
    public RequestMaterial(String code, String description, String units, Double requestQuantity) {
        super(code, description, units);
        this.requestQuantity = requestQuantity; 
    }

    public Double getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(Double requestQuantity) {
        this.requestQuantity = requestQuantity;
    }
    
    
    
}
