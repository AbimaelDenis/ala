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
public final class Log<T> {
    private String author;
    private Date date;
    private String Description;
    private T obj;

    public Log(String author, Date date, String Description) {
        this.author = author;
        this.date = date;
        this.Description = Description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    } 
}
