/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.control;

import com.mycompany.model.ImagenAlmacen;

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
    protected int group;
    protected int intentions;

    public Contact(String name, double phoneNumber, String lastName, String email, int gender, ImagenAlmacen image, int group,int intentions) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.group = group;
        this.intentions = intentions;
    }
    public abstract void setIntentions(int intentions);
    public abstract int getIntentions();
    public abstract String getName();
    public abstract void setName(String name);
    public abstract double getPhoneNumber();
    public abstract void setPhoneNumber(double phoneNumber);
    public abstract String getLastName();
    public abstract void setLastName(String lastName);
    public abstract String getEmail();
    public abstract void setEmail(String email);
    public abstract int getGender();
    public abstract void setGender(int gender);
    public abstract ImagenAlmacen getImage();
    public abstract void setImage(ImagenAlmacen image);
    public abstract int getGroup();
    public abstract void setGroup(int group);

    
    
    
}
