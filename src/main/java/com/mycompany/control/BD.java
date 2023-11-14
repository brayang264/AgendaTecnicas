package com.mycompany.control;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class BD {
    private static Connection Conection;
    private static Statement Consulta;
    private static ResultSet Resultado;

    private final String bd;
    private final String user;
    private final String password;
    
    private String SQL_AGREGAR = "INSERT INTO imagen (titulo, imagen) VALUES (?,?)";
    private String SQL_CONSULTA = "SELECT *     FROM imagen";

    public BD(String bd, String user, String password) {
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Conection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + bd, user, password);
            return Conection != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void desconectar() {
        try {
            this.Conection.close();
        } catch (Exception e) {
        }
    }
    
    public void AgregarImagen(ImagenAlmacen mImagen){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conection.prepareStatement(SQL_AGREGAR);
            preparedStatement.setString(1, mImagen.getTitulo());
            preparedStatement.setBytes(2, mImagen.getImagen());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
            }
        }
    }
    
    public ArrayList CargarImagenes(){
        ArrayList Imagenes = new ArrayList();
        try {
            Consulta = Conection.createStatement();
            Resultado = Consulta.executeQuery(SQL_CONSULTA);
            while (Resultado.next()) {
                ImagenAlmacen mImagen = new ImagenAlmacen();
                mImagen.setImagen_id(Resultado.getInt("imagen_id"));
                mImagen.setTitulo(Resultado.getString("titulo"));
                mImagen.setImagen(Resultado.getBytes("imagen"));
                Imagenes.add(mImagen);
            }
            
        }catch (Exception e){
            return null;
        }
        return Imagenes;
    }
}
    