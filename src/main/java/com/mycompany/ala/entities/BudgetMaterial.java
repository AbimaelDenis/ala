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
public class BudgetMaterial extends Material{
    private Double budgetQuantity;
    private Reserv reserv;
    
    public BudgetMaterial(String code, String description, String units, Double budgetQuantity, Reserv reserv) {
        super(code, description, units);
        this.budgetQuantity = budgetQuantity;
        this.reserv = reserv;
    }

    public Double getBudgetQuantity() {
        return budgetQuantity;
    }

    public void setBudgetQuantity(Double budgetQuantity) {
        this.budgetQuantity = budgetQuantity;
    }

    public Reserv getReserv() {
        return reserv;
    }

    public void setReserv(Reserv reserv) {
        this.reserv = reserv;
    }

    @Override
    public String toString() {
        return "Material " + getCode() + " " + getDescription();
    }
    
    
}
