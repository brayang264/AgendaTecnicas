/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.control.Contact;
import com.mycompany.control.ControlDB;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



/**
 *
 * @author Juan José y Brayan
 */
public class ContactList extends javax.swing.JFrame {

    /**
     * Creates new form ListaContacts
     */
    public ContactList() throws SQLException {
        initComponents();
        getContacts();
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        viewContacts();
        
    }
    //Formato para los numeros de telefonos
    private DecimalFormat formatter = new DecimalFormat();

    //Instancia de la clase control
    private ControlDB controlDB = new ControlDB();
    //Arrays para guardar las imagenes, nombres y números de los contactos
    private ArrayList<JLabel> images = new ArrayList<>();
    //ArrayList de paneles los contactos
    private int contName =0;
    //ArrayList para poder almacenar los contactos
    private ArrayList<Contact> contacts;
    //Metodo para obtener el array de contactos
    private void getContacts() throws SQLException{
        contacts = controlDB.getAllContacts();
    }
    //Metodo para agregar de forma visual cada uno de los contactos
    private void viewContacts(){
        formatter.setMinimumFractionDigits(0);
        for(Contact contact:contacts){
            //Se crea un panel el cual almacena toda la info del contacto
            JPanel panel = new JPanel();
            //Se creo un label  y un imageIcon, el cual almacena la imagen del contacto
            JLabel image = new JLabel();
            if(contact.getImage().getImagen() != null){
                ImageIcon icon = convertBytesToImage(contact.getImage().getImagen());
                if(icon ==null){
                    image.setText("Sin imagen");
                }else{
                    image.setIcon(icon);
                }
            }
                images.add(image);
            //Se crea un label para agregar el nombre
            JLabel name = new JLabel();
            name.setText("         "+contact.getName()+"  "+contact.getLastName()+             "                                      ");
            //Se crea otro label para el numero de telefono
            JLabel number = new JLabel();
            String num = formatter.format(contact.getPhoneNumber());
            number.setText(num.replace(".", "")+"                                            ");
            //Se agregan las intenciones
            JLabel intentions = new JLabel();
            intentions.setText(controlDB.ctrlGetIntentions(contact.getIntentions()));
            //Se agregan todos los elementos para componer uno solo
            panel.add(image);
            panel.add(name);
            panel.add(number);
            panel.add(intentions);
            panel.setName(contName+"");
            contName++;
            //Se agrega un nuevo evento al panel.
            addPanelToArray(panel);
            //Se agrega al contenedor
            contactsPanel.add(panel);
        }
    }
    //Metodo para agregar al array de paneles y generar el evenento de click
    private void addPanelToArray(JPanel panel){
        //Se agrega los paneles al array y se crea su nuevo evento y cambia el icono del 
        //cursor cuando se pasa por enciam de uno de estos paneles 
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        viewContactInfo(Integer.parseInt(panel.getName()));
                    } catch (SQLException ex) {
                        Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el cursor cuando el mouse entra al JPanel
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el cursor cuando el mouse sale del JPanel
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            });
    }
    //Metodo para convertir bytes en imagenes
    private static ImageIcon convertBytesToImage(byte[] bytes) {
        try{
            BufferedImage bufferedImage = null;
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
            return new ImageIcon(bufferedImage.getScaledInstance(60, 60,0));
        }catch(IOException ex){
            return null;
        }
    }
    //Metodo para ver la info de un contacto, eliminarlo o agregarlo
    private void viewContactInfo(int index) throws SQLException{
        ViewContact view = new ViewContact();
        view.getInfoContact(contacts.get(index), images.get(index));
        view.setVisible(true);
        this.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        contactsPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel2.setText("Nombre");

        jLabel3.setText("Telefono");

        jLabel4.setText("Intenciones");

        jLabel5.setText("Foto");

        btnAdd.setText("Agregar Contacto Nuevo");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contactsPanelLayout = new javax.swing.GroupLayout(contactsPanel);
        contactsPanel.setLayout(contactsPanelLayout);
        contactsPanelLayout.setHorizontalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contactsPanelLayout.setVerticalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jButton2.setText("Generar PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Agregar Contacto Nuevo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(116, 116, 116)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(btnAdd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(contactsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(contactsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            AddContact addContact = new AddContact();
            addContact.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Document documento = new Document();
        
        try {
            //Indico el lugar en que se va a guardar el pdf generado
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Agenda.pdf"));
            documento.open();
            
            
            PdfPTable tabla = new PdfPTable(7);//Indico el numero de columnas que tendra el reporte
            //Le pongo nombre a cada una de las columnas
            tabla.addCell("Numero");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            tabla.addCell("Sexo");
            tabla.addCell("Intenciones");
            tabla.addCell("Grupo");
            
            try {
                //Vinculo con la base de batos para sacar datos de la misma 
                Connection cn = DriverManager.getConnection("jdbc:mariadb://localhost:3308/agendatelefonica","root","Brayan1201?!");
                PreparedStatement pst = cn.prepareStatement("select * from contactos");
            
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    do {                        
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (DocumentException | SQLException e) {  
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ContactList().setVisible(true);
                    new ContactList().setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JPanel contactsPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
