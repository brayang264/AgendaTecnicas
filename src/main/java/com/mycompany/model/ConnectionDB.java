/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
 */
package com.mycompany.model;
import com.mycompany.control.Connections;
import com.mycompany.control.Contact;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.control.ControlDB;
import com.mycompany.control.Validate;
import java.sql.ResultSet;
/**
 *
 * @author Brayan
 */
public class ConnectionDB implements Connections{
    //Variable unica de la clase para usar el codigo de inserción a la base de datos 
    private String sql;
    //Metodo para añadir un nuevo contacto a la base de datos.
    @Override
    public boolean addContact(String name, String lastName, double phoneNum, String email, int gender, int intentions, int socialGrup,ImagenAlmacen mImagen) {
        //Obtiewne la coneción a la base de datos a travez de un metodo de la clase controldb
        Connection connection = ControlDB.getConnection();
        //Comprueba si la coneccion es nula y en caso tal no se sigue con el insert
        if(connection == null){
            return false;
        }else{
            
            //Sentencia para la inserción
            sql = "INSERT INTO contactos (Numero, Nombre, apellido, Correo, Sexo, "
                   + "Intenciones, Grupo, Imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            //Se hace una consulta parametrizada con el siguiente objeto
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                //Se agregan los valores a los parametros
                preparedStatement.setDouble(1,phoneNum);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, email);
                preparedStatement.setInt(5, gender+1);
                preparedStatement.setInt(6, intentions+1);
                preparedStatement.setInt(7,socialGrup+1);
                preparedStatement.setBytes(8, mImagen.getImagen());
                //Se ejecuta la sentencia de inserción
                int addedRows = preparedStatement.executeUpdate();
                connectionClose(preparedStatement, connection);
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
                preparedStatement.close();
                connection.close();
                connectionClose(preparedStatement,connection);
                return resultSet;
                
            } catch (SQLException ex) {
                return null;
            }
        }
    }
    
    //Verifica si un contacto ya existe
    @Override
    public boolean contactExist(double phoneNum){
        Connection connection = ControlDB.getConnection();
        try{
            sql = "SELECT COUNT(*) FROM contactos WHERE Numero = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                connectionClose(statement,connection);
                return count >0;
            }
        }catch(SQLException e){
            Validate.consolePrint(e.toString());
            return false;
        }
        return false;
    }
    @Override
    public ResultSet getTable(String nameTable){
        Connection connection = ControlDB.getConnection();
        sql = "SELECT * FROM "+nameTable;
        try{
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             connectionClose(statement,connection);
             return resultSet;
        }catch (SQLException e) {
            return null;
        }
    }
    //Metodo para cerrar las coneciones con la bd
    @Override
    public void connectionClose(PreparedStatement statement, Connection connection) throws SQLException {
        statement.close();
        connection.close();
    }

    @Override
    public String getGender(int id) {
        Connection connection = ControlDB.getConnection();
        sql = "SELECT sexo FROM sexo WHERE Tipo = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            String gender="";
            while(resultSet.next()){
                gender = resultSet.getString("sexo");
            }
            return gender;
        }catch(SQLException e){
            return "";
        }
    }

    @Override
    public boolean updateContact(Contact contact, int intentions) {
        Connection connection  = ControlDB.getConnection();
        sql = "UPDATE contactos set Nombre = ?, Apellido = ?, Correo = ?, Sexo = ?, "
                + "Intenciones = ?, Grupo = ?, Imagen = ? WHERE Numero = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,contact.getName());
            statement.setString(2,contact.getLastName());
            statement.setString(3, contact.getEmail());
            statement.setInt(4, contact.getGender()+1);
            statement.setInt(5, intentions+1);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean deleteContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
