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
public class MaleContact extends Contact {

    
    public MaleContact(String name, double phoneNumber, String lastName, String email
    ,int gender, ImagenAlmacen image, int group,int intentions) {
        super(name, phoneNumber, lastName, email, gender, image,group,intentions);
                this.intentions = intentions;
    }
    @Override
    public int getIntentions() {
        return intentions;
    }
    @Override
    public void setIntentions(int intentions) {
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
    public void setPhoneNumber(double phoneNumber) {
    this.phoneNumber = phoneNumber;
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
    @Override
    public int getGroup() {
        return group;
    }

    @Override
    public void setGroup(int group) {
        this.group = group;
    }
}
