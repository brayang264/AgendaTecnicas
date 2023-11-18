/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.control;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author sren9
 */
public class GenerateJson {
    private static ArrayList<Contact> collection = new ArrayList<>();
    // getter necesario para poder llamar a la función RetrieveDeletedContacts()
    public static ArrayList<Contact> getCollection() {
        return collection;
    }
//    Función auxiliar que nos permitirá establecer una conexión con la base de datos, para 
//    generar una consulta que se guarde para su uso por otras funciones de la clase 
    private static ResultSet RetrieveData() throws Exception {
      Connection con = com.mycompany.control.ControlDB.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select JSON_OBJECT ('Numero', Numero, 'Nombre', Nombre, 'Apellido', Apellido, 'Correo', Correo, 'Sexo', Sexo, 'Intenciones', Intenciones, 'Grupo', Grupo) from agendatelefonica.contactos;");
      return rs;  }
//    Extrae los resultado de la consulta como String, y les aplica el formato necesario
//    para que la función FileWriter, lo pueda escribir correctamente como un archivo json
//    que será guarda en la ruta especificada como "output"
   public static void dataBaseData() throws Exception {
      ResultSet rs = RetrieveData();
      int count = 0;
      String text = "{\"Personas\":[ \n";
      while(rs.next()) {
          if (count > 0) 
              text+= ",";
          String jsonResult = rs.getString(1);
          text += jsonResult;
          text += "\n";
          count += 1;     
      }
      text += "] \n }";
      try {
          String path = System.getProperty("user.home");
          try (FileWriter file = new FileWriter(path+"/Desktop/Contactos.json")) {
              file.write(text);
              file.close();
          }
      } catch (IOException e) {
          // TODO Auto-generated catch block
      }
   }
   public static void collectData(Contact contact) {
       collection.add(contact);
   }
   // Basado en https://www.tutorialspoint.com/how-to-read-retrieve-data-from-database-to-json-using-jdbc
   // Cada vez que se confirma el borrado de un contacto en la base de datos, es guardado en el array
   // collection, para que se puedan mostrar todos los contactos que se han borrado hasta el momento
   public static void RetrieveDeletedContacts(ArrayList<Contact> collection){
       JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      //Inserting ResutlSet data into the json object
     for(Contact contacts : collection) {
         JSONObject record = new JSONObject();
         //Inserting key-value pairs into the json object
         record.put("Numero", contacts.getPhoneNumber());
         record.put("Nombre", contacts.getName());
         record.put("Apellido", contacts.getLastName());
         record.put("Correo", contacts.getEmail());
         record.put("Sexo", contacts.getGender());
         record.put("Intenciones", contacts.getIntentions());
         record.put("Grupo", contacts.getGroup());
         array.add(record);
      }
      jsonObject.put("Contactos", array);
      try {
         String path = System.getProperty("user.home");
         FileWriter file = new FileWriter(path+"/Desktop/Papelera.json");
         file.write(jsonObject.toJSONString());
         file.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}

