/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
 */
package com.mycompany.control;

import com.mycompany.model.ImagenAlmacen;
import com.mycompany.model.ConnectionDB;
import com.mycompany.model.FemaleContact;
import com.mycompany.model.MaleContact;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Brayan
 */
public class ControlDB {
    //Formato para los numeros de telefonos
    private final DecimalFormat formatter = new DecimalFormat();
    //Hace una instancias de la clase para las coneciones con la base
    private final ConnectionDB db = new ConnectionDB();
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
            return null;
        }
    }
    //Valid todos los datos ingresados por el usuario para hacer una inserción de manera correcta
    public String ctrlAddContact(String name, String lastName, String phoneNum, String email,
            int gender,int intentions, int socialGrup,ImagenAlmacen mImagen){
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
                //Si no se agrega correo se va a insertar un mensaje que le indique al usuario
                //que el contacto no cuenta con correo
                if(email.isEmpty()){
                    email = "No se agregó un correo";
                }
                double number = Validate.convertToDouble(phoneNum);
                //Se comprueba que un número de teléfono no exista en la BD
                if(db.contactExist(number)){
                    reply = "El número ingresado ya está asociado a otro contacto";
                    return reply;
                }else{
                   
                    //Comprueba que se se haga la inserción en la base de datos
                    if(!db.addContact(name, lastName, number, email, gender, intentions, socialGrup,mImagen)){
                        reply = "Ha acurrido un error al intentar crear el contacto, porfavor intentelo nuevamente";
                        return reply;
                    }else{
                    return reply;   
                    } 
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
    //Metodo para obtener todos los contactos
    public ArrayList<Contact> getAllContacts() throws SQLException{
        ArrayList<Contact> contacts = new ArrayList<>();
        //Se obtiene todos y cada uno de los contactos que hay en la bd
        ResultSet resultSet = db.getTable("contactos");
        //Si no hay datos o algo falla se devulve el array vacio
        if(resultSet == null)return contacts;
        //Se crean los contactos que ya existen en la base de datos
        else{
            while(resultSet.next()){
                String name = resultSet.getString("Nombre");
                double number = resultSet.getDouble("Numero");
                String lastName = resultSet.getString("Apellido");
                String email = resultSet.getString("Correo");
                int gender = resultSet.getInt("Sexo");
                int intentions = resultSet.getInt("Intenciones");
                int group = resultSet.getInt("Grupo");
                ImagenAlmacen mImage = new ImagenAlmacen();
                mImage.setImagen(resultSet.getBytes("Imagen"));
                Contact contact = createContact(name,number,intentions,lastName,email,gender,mImage,group);
                contacts.add(contact);
            }
            return contacts;
        }
    }
    //Crea contactos hombre o mujer dependiendo del tipo
    private Contact createContact(String name, double phoneNumber, int intentions, String lastName, String email
    ,int gender, ImagenAlmacen image,int group){
        Contact contact;
        if(gender ==1){
            contact = new MaleContact(name,phoneNumber,lastName,email,gender, image, group,intentions);
            return contact;
        }else{
            contact = new FemaleContact(name,phoneNumber,intentions,lastName,email,gender,image,group);
            return contact;
        }
    }
    //Metodo para obtener el género de un contacto
    public String ctrlGetGender(Contact contact){
        if(!db.contactExist(contact.getPhoneNumber())){
            return "El contacto no existe";
        }else{
            return db.getGender(contact.getGender());
        }
    }
    //Metodo para eliminar un contacto
    public String ctrlDeleteContact(Contact contact){
        String number = formatter.format(contact.getPhoneNumber());
        int answer = JOptionPane.showConfirmDialog(null, "¿Estás seguro de "
                + "eliminar a "+contact.getName()+" "+contact.getLastName()+"?\n"
                        + "Número: "+number.replace(".", ""),
                "¿Seguro de continuar?", 1);
        if(answer != JOptionPane.YES_OPTION){
            return "Se ha cancelado la eliminación del contacto con éxito";
        }else{
            if(!db.deleteContact(contact)){
                return "Ha sucedido un error durante la eliminación";
            }else{
                return "";
            }
        }
    }
    //Metodo para actualizar en contacto
    public String ctrlUpdateContact(Contact contact, int intentions){
        String number = formatter.format(contact.getPhoneNumber());
        int answer = JOptionPane.showConfirmDialog(null, "¿Estás seguro de "
                + "actualizar la información de "+contact.getName()+" "+contact.getLastName()+"?\n"
                        + "Número: "+number.replace(".", ""),
                "¿Seguro de continuar?", 1);
        if(answer != JOptionPane.YES_OPTION){
            return "Se cancelo la actualización de los datos";
       }else{
            if(!db.updateContact(contact, intentions)){
                return "Ha acurrido un error durante la actualización de los datos";
            }else{
                return "";
            }
        }
    }
    //Metodo para obtener las intenciones de un contacto
    public String ctrlGetIntentions(int type){
        return db.getIntentions(type);
    }
}
 
