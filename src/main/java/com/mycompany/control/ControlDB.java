/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
 */
package com.mycompany.control;

import com.mycompany.model.ConnectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author Brayan
 */
public class ControlDB {
    //Hace una instancias de la clase para las coneciones con la base
    private ConnectionDB db = new ConnectionDB();
    //Retorna una connecion con la base de datos
    public static Connection getConnection(){
        String url = "jdbc:mariadb://localhost:3308/agendatelefonica";
        String usuario = "root";
        String contraseña = "Brayan1201?!";
        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Valid todos los datos ingresados por el usuario para hacer una inserción de manera correcta
    public String ctrlAddContact(String name, String lastName, String phoneNum, String email,
            int gender,int intentions, int socialGrup){
        String reply  = "";
        //Comprueba que realmente el usuario haya rellanado los campos
        if(name.isEmpty()||lastName.isEmpty()||phoneNum.isEmpty()){
            reply = "Debe de rellenar todos los campos";
            return reply;
        }else{
            //Comprueba que el número ingresado por el usuario sea de tipo númerico
            if(!Validate.isNumber(phoneNum)){
                reply = "El número de teléfono ingresado es incorrecto";
                return reply;
            }else{
                double number = Validate.convertToDouble(phoneNum);
                //Comprueba que se se haga la inserción en la base de datos
                if(!db.addContact(name, lastName, number, email, gender, intentions, socialGrup)){
                    reply = "Ha acurrido un error al intentar crear el contacto, porfavor intentelo nuevamente";
                    return reply;
                }else{
                 return reply;   
                }
            }
        }
    }
    //Metodo para rellenar los elementos de un comboBox
    public JComboBox getElementsCB(JComboBox box, String column, String table) throws SQLException{
        //Hace la conecion y lo guarda en una variable
        ResultSet result = db.getColumn(column, table);
        if(result == null)return box;
        while(result.next()){
            //Añade las opciones al comboBox
            String value = result.getString(column);
            box.addItem(value);
        }
        return box;
    }
}
