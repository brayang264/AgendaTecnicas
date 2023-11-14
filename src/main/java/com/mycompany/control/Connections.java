/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
 */
package com.mycompany.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brayan
 */
public interface Connections {
    public boolean addContact(String name, String lastName, double phoneNum, String email,
            int gender,int intentions, int socialGrup,ImagenAlmacen mImagen);
    public ResultSet getColumn(String column, String table);
    public boolean contactExist(double phoneNum);
    public ResultSet getTable(String nameTable);
    public void connectionClose(PreparedStatement  statement, Connection connection)throws SQLException;
}
