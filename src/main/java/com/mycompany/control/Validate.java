/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
 */
package com.mycompany.control;

import javax.swing.JOptionPane;

/**
 *
 * @author brayan
 */
public class Validate {
    public static boolean isNumber(String number){
        try{
            Double.parseDouble(number);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public static double convertToDouble(String number){
        return Double.parseDouble(number);
    }
    public static void consolePrint(String text){
        System.out.println(text);
    }
    public static void print(String text){
        JOptionPane.showMessageDialog(null, text, "Aviso", 1);
    }
}
