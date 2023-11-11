/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
 */
package com.mycompany.control;

import java.sql.ResultSet;

/**
 *
 * @author Brayan
 */
public interface Connections {
    public boolean addContact(String name, String lastName, double phoneNum, String email,
            int gender,int intentions, int socialGrup);
    public ResultSet getColumn(String column, String table);
}
