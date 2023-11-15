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

    public FemaleContact(String name, double phoneNumber, int intentions, String lastName, String email
    ,int gender, ImagenAlmacen image) {
        super(name, phoneNumber,lastName,email,gender,image);
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
    public double getPhoneNumber() {
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

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getGender() {
        return gender;
    }

    @Override
    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public ImagenAlmacen getImage() {
        return image;
    }

    @Override
    public void setImage(ImagenAlmacen image) {
        this.image = image;
    }
    
}
