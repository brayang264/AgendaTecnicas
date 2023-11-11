/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.control.Contact;

/**
 *
 * @author sren9
 */
public class FemaleContact extends Contact{
    private int intentions;

    public FemaleContact(String name, int phoneNumber, int intentions) {
        super(name, phoneNumber);
        this.intentions = intentions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
    this.name = name;
    }

    @Override
    public int getPhoneNumber() {
    return phoneNumber;
    }

    @Override
    public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
    }

    public int getIntentions() {
        return intentions;
    }

    public void setIntentions(short intentions) {
        this.intentions = intentions;
    }
    
}
