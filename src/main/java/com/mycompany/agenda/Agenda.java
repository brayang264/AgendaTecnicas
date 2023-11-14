/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agenda;

import com.mycompany.view.ContactList;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Agenda {
    public static void main(String[] args) throws SQLException {
        ContactList contact = new ContactList();
        contact.setVisible(true);
    }
}
