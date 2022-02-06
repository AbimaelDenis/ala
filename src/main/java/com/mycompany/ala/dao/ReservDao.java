/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.dao;

import com.mycompany.ala.entities.OrderService;
import com.mycompany.ala.entities.Reserv;

/**
 *
 * @author Abimael
 */
public interface ReservDao {
    void insertReserv(Reserv reserv);
    void findReservsById(OrderService os);
    Reserv findReservById(String id);
    void deleteReservById(String id);
}
