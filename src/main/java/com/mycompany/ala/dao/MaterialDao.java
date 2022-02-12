/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.entities.Material;
import com.mycompany.ala.entities.RequestMaterial;
import com.mycompany.ala.entities.Structure;
import java.util.List;

/**
 *
 * @author Abimael
 */
public interface MaterialDao {
    void insert(Material material);
    List<Material> findAll();
    int insertRequest(RequestMaterial material);
    List<RequestMaterial> findRequest(String serviceId);
    List<Structure> findAllStructure();
}
