/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.control.Contact;
import java.util.ArrayList;

/**
 *
 * @author sren9
 */
public class ContactCollection {
    private static ArrayList<Contact> directory = new ArrayList<>();
    
    public ContactCollection() {
    }
    
    public void addContact(Contact contact){
        directory.add(contact);
    }   
    /*
    Pendiente por moficar
    */
    public String searchContact(Contact contact){  
        int searchIndex = directory.indexOf(contact); 
        if (searchIndex != -1){
            return directory.get(searchIndex).getName() +" "+ Integer.toString(directory.get(searchIndex).getPhoneNumber());
        }
        return "Contacto no encontrado";
    }
}
