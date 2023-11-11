/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Control.Contact;

/**
 *
 * @author sren9
 */
public class MaleContact extends Contact {

    public MaleContact(String name, int phoneNumber) {
        super(name, phoneNumber);
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
    
}
