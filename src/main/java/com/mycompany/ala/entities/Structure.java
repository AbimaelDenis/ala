/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Abimael
 */
public class Structure extends Material {  
    
    private List<RequestMaterial> materials = new ArrayList<>();
    
    public Structure(Long code, String description){
        super(code.toString(), description, "CDA", true);   
    }

    public List<RequestMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<RequestMaterial> materials) {
        this.materials = materials;
    }
    
     
}
