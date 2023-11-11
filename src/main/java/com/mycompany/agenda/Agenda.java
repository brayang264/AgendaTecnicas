/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.agenda;

import Model.ContactCollection;
import Model.FemaleContact;
import Model.MaleContact;

/**
 *
 * @author sren9
 */
public class Agenda {

    public static void main(String[] args) {
        MaleContact prueba = new MaleContact("probando", 123);
        FemaleContact pruebaa = new FemaleContact("probando pero femenino", 456, 3);
        ContactCollection test = new ContactCollection();
        test.addContact(prueba);
        test.addContact(pruebaa);
        System.out.println(test.searchContact(pruebaa));
    }
}
