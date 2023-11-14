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
    protected String lastName;
    protected String email;
    protected int gender;
    protected ImagenAlmacen image;
    protected double phoneNumber;

    public Contact(String name, double phoneNumber, String lastName, String email, int gender, ImagenAlmacen image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.image = image;
    }

    public abstract String getName();
    public abstract void setName(String name);
    public abstract double getPhoneNumber();
    public abstract void setPhoneNumber(int phoneNumber);
    public abstract String getLastName();
    public abstract void setLastName(String lastName);
    public abstract String getEmail();
    public abstract void setEmail(String email);
    public abstract int getGender();
    public abstract void setGender(int gender);
    public abstract ImagenAlmacen getImage();
    public abstract void setImage(ImagenAlmacen image);
    

    
    
    
}
