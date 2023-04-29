/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.tools.MaConnexion;
/**
 * FXML Controller class
 *
 * @author hamza
 */
public class RegisterController implements Initializable {
    private Connection connection;
    private PreparedStatement smt;
    private ResultSet result;
    private MaConnexion con;
    

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private RadioButton doctor;
    @FXML
    private RadioButton patiant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=new MaConnexion();
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
         register.getScene().getWindow().hide();
        Stage register =new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Login.fxml"));
        Scene sc =new Scene(root);
        register.setScene(sc);
        register.show();
        register.setResizable(false);
    }
    
    public boolean ValidationEmail(){ 
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email.getText());
        
        if(match.find() && match.group().equals(email.getText()))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            
            return false;
        }
    }
    public static boolean estChiffresSeulement(String chaine) {
    for (int i = 0; i < chaine.length(); i++) {
        if (!Character.isDigit(chaine.charAt(i))) {
            return false;
        }
    }
    return true;
}

    @FXML
    private void register(ActionEvent event) throws IOException {
          String query="INSERT INTO user (username,email,num_tel,roles,password,full_address,is_verified)"
                    + "VALUES (?, ?, ?, ?, ?,?,0)";
          connection=(Connection) con.getCnx();
    
    try{
        
          if( username.getText().isEmpty()
                    | password.getText().isEmpty()
                    | email.getText().isEmpty()
                    | phone.getText().isEmpty()
                    | address.getText().isEmpty())
                    {
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(":: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields !!");
                alert.showAndWait();
    
        
         
          }else if(password.getText().length() < 8 ){
              
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be > 8 characters!!");
                alert.showAndWait();
                
                
          }
          else if (phone.getText().length() != 8 || !estChiffresSeulement(phone.getText())) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Message d'erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres");
    alert.showAndWait();
}
          else{
               if(ValidationEmail()){
                   String email_query="select * from user where email=?";
                   PreparedStatement emailsmt=connection.prepareStatement(email_query);
                   emailsmt.setString(1, email.getText());
                   ResultSet rs_email=emailsmt.executeQuery();
                   if(rs_email.next()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("User already exist");
                    alert.showAndWait();
                   }
                   else{
             smt=connection.prepareStatement(query);
             
        
            smt.setString(1, username.getText());
            smt.setString(5, password.getText());
            smt.setString(2, email.getText());
            smt.setString(3, phone.getText());
            smt.setString(4, getroles());
            smt.setString(6, address.getText());
            smt.executeUpdate();
              login.getScene().getWindow().hide();
                Parent root=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Login.fxml"));
                Stage st=new Stage();
                Scene sc=new Scene(root);
                st.setScene(sc);
                st.show();
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcome");
                alert.setHeaderText(null);
                alert.setContentText("Account created!!");
                alert.showAndWait();
                
                   }
       
                
              
                
          }
        
            
          }
    }
    catch(SQLException ex){
         System.out.println(ex.getMessage());
    }
        
        
        
    }
    
    public String getroles(){
        String gen="";
        if(doctor.isSelected()){
            gen="Doctor";
        }
        else if(patiant.isSelected()){
        gen="Patiant";
        }
    
    return gen;}
    
}
