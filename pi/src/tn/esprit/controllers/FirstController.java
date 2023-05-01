/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class FirstController implements Initializable {

    @FXML
    private ImageView fond;
    @FXML
    private ImageView logo;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //fond.setImage(new Image("C:\\Users\\hamza\\Desktop\\javafx-suivimed\\pi\\src\\tn\\esprit\\images\\fond.png"));
       
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
}