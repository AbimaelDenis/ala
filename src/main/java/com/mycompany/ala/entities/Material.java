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
public final class Material {
    private String code;
    private String description;
    private Double budgetQuantity;
    private Double dispatchedQauntity;
    private Reserv reserv;

    public Material(String code, String description, Double budgetQuantity, Reserv reserv) {
        this.code = code;
        this.description = description;
        this.budgetQuantity = budgetQuantity;
        this.reserv = reserv;
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

    public Double getBudgetQuantity() {
        return budgetQuantity;
    }

    public void setBudgetQuantity(Double budgetQuantity) {
        this.budgetQuantity = budgetQuantity;
    }

    public Double getDispatchedQauntity() {
        return dispatchedQauntity;
    }

    public void dispatchMaterial(Double dispatchedQauntity) {
        this.dispatchedQauntity += dispatchedQauntity;
    }

    public Reserv getReserv() {
        return reserv;
    }

    public void setReserv(Reserv reserv) {
        this.reserv = reserv;
    }
 
}
