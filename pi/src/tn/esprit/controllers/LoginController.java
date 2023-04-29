/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.tools.MaConnexion;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class LoginController implements Initializable {
     private Connection connection;
    private PreparedStatement smt;
    private ResultSet result;
    private MaConnexion con;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Button forget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con=new MaConnexion();
        
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        connection=(Connection) con.getCnx();
        String query="select * from user where email=? and password=?";
        smt=connection.prepareStatement(query);
        smt.setString(1, email.getText());
        smt.setString(2,password.getText());
        result=smt.executeQuery();
        int count=0;
        while(result.next()){
            count=count+1;
        }
        if(count==1){
            
                login.getScene().getWindow().hide();
                Parent root=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/dashboard.fxml"));
                Stage st=new Stage();
                Scene sc=new Scene(root);
                st.setScene(sc);
                st.show();
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
               alert.setHeaderText(null);
               alert.setContentText("Successfully login!");
               alert.show();
                       
        }
               
        else{
               Alert alert =new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setContentText("Incorrect Username or Password !");
               alert.show();
               
        }  
        
        
        
        
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        login.getScene().getWindow().hide();
        Stage register =new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Register.fxml"));
        Scene sc =new Scene(root);
        register.setScene(sc);
        register.show();
        register.setResizable(false);
    }
 
    public static String generatePassword(int length) {
    String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
    String specialChars = "!@#$%^&*()_+-=[]|,./?><";
    String numbers = "0123456789";
    String allChars = upperCaseChars + lowerCaseChars + specialChars + numbers;
    StringBuilder password = new StringBuilder();
    Random rnd = new Random();
    while (password.length() < length) { 
        int index = (int) (rnd.nextFloat() * allChars.length());
        password.append(allChars.charAt(index));
    }
    return password.toString();
}
    
    
 void sendPassword() throws SQLException{
      connection=(Connection) con.getCnx();
        System.out.println("cxcccccccccccccccccc");
                String query2="select * from user where email=? ";
                String email1="empty";
                 try {
            PreparedStatement smt = connection.prepareStatement(query2);
            smt.setString(1,email.getText());
             ResultSet rs= smt.executeQuery();
                if(rs.next()){
                   email1=rs.getString("email");
                 
                     
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
                  String newPassword = generatePassword(12);
                 sendMail(email1,newPassword);
                
                 
    }
    public void sendMail(String recepient,String newPassword) throws SQLException{
         connection=(Connection) con.getCnx();
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "pidevtest378@gmail.com";
        String passwordd = "jeubxkcugxuphmib";
       
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,passwordd);
            }
        });
        Message message =preparedMessage(session,myAccountEmail,recepient);
        try{
            Transport.send(message);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Healthers :: Boite Mail");
                alert.setHeaderText(null);
                alert.setContentText(" Email envoyé Consultez votre boite mail !!");
                alert.showAndWait();  
           
        }catch(Exception ex){
            ex.printStackTrace();
           
        }
               
    }
    
     private Message preparedMessage(Session session, String myAccountEmail, String recepient) throws SQLException{
          connection=(Connection) con.getCnx();
         String query2="select * from user where email=?";
         String userEmail="null" ;
         String pass=generatePassword(12);
        try {
            PreparedStatement smt = connection.prepareStatement(query2);
            smt.setString(1, email.getText());
             ResultSet rs= smt.executeQuery();
            
                if(rs.next()){
                   userEmail=rs.getString("email"); 
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            updatePassword(userEmail,pass);
       
        String text="Votre nouveau mot de pass est :"+pass+"";
        String object ="Recupération de mot de passe";
        Message message = new MimeMessage(session);
        try{
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getText()));
        message.setSubject(object);
        String htmlcode ="<h1> "+text+" </h1> <h2> <b> </b2> </h2> ";
        message.setContent(htmlcode, "text/html");
        
           
        return message;
       
        }catch(MessagingException ex){
            ex.printStackTrace();
        }
    return null;
    }
     
   private void updatePassword(String email, String newPassword) throws SQLException {
     connection=(Connection) con.getCnx();
        String updateQuery = "UPDATE user SET password = ? WHERE email = ?";

    try {
        smt = connection.prepareStatement(updateQuery);
      smt.setString(1, newPassword);
        smt.setString(2, email);
        int rowsUpdated = smt.executeUpdate();

        if (rowsUpdated > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Healthers :: Boite Mail");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe de l'utilisateur a été mis à jour avec succès !!");
                alert.showAndWait();
        } else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Healthers :: Boite Mail");
                alert.setHeaderText(null);
                alert.setContentText("Impossible de mettre à jour le mot de passe de l'utilisateur.");
                alert.showAndWait();
            
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la mise à jour du mot de passe de l'utilisateur: " + ex.getMessage());
    }

   }
    @FXML
    private void btn_forget(ActionEvent event) throws SQLException {
        
        sendPassword(); 
        
        
        
    }
     
        
        
    

    
   
}


