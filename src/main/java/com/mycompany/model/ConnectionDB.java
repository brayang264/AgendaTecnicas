/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
 */
package com.mycompany.model;
import com.mycompany.control.Connections;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.control.ControlDB;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brayan
 */
public class ConnectionDB implements Connections{
    //Variable unica de la clase para usar el codigo de inserción a la base de datos 
    private String sql;
    //Metodo para añadir un nuevo contacto a la base de datos.
    @Override
    public boolean addContact(String name, String lastName, double phoneNum, String email, int gender, int intentions, int socialGrup) {
        //Obtiewne la coneción a la base de datos a travez de un metodo de la clase controldb
        Connection connection = ControlDB.getConnection();
        //Comprueba si la coneccion es nula y en caso tal no se sigue con el insert
        if(connection == null){
            return false;
        }else{
            
            //Sentencia para la inserción
            sql = "INSERT INTO contactos (Numero, Nombre, apellido, Correo, Sexo, "
                   + "Intenciones, Grupo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            //Se hace una consulta parametrizada con el siguiente objeto
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                //Se agregan los valores a los parametros
                preparedStatement.setDouble(1,phoneNum);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, email);
                preparedStatement.setInt(5, gender);
                preparedStatement.setInt(6, intentions);
                preparedStatement.setInt(7,socialGrup);
                //Se ejecuta la sentencia de inserción
                int addedRows = preparedStatement.executeUpdate();
                return addedRows >0;
            }catch (SQLException e) {
                return false;
            }
        }
    }
    //Obtiene una columna en especifico de una tabla en especifica
    @Override
    public ResultSet getColumn(String column, String table) {
        Connection connection = ControlDB.getConnection();
        if(connection == null) return null;
        else{
            sql = "SELECT "+column+" FROM "+table;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet;
                
            } catch (SQLException ex) {
                return null;
            }
        }
    }
    
}
