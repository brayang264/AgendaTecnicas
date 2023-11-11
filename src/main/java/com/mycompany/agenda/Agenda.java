/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenda;

import com.mycompany.view.AddContact;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Agenda {
    public static void main(String[] args) throws SQLException {
        AddContact addContact = new AddContact();
        addContact.setVisible(true);
    }
}
