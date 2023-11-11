/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.control;

/**
 *
 * @author sren9
 */
public abstract class Contact {
    protected String name;
    protected int phoneNumber;

    public Contact(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getName();
    public abstract void setName(String name);
    public abstract int getPhoneNumber();
    public abstract void setPhoneNumber(int phoneNumber);

    
    
    
}
