/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class DashboardController implements Initializable {
    private Parent fxml;
      @FXML
    private AnchorPane root;
    @FXML
    private Button logout;
    @FXML
    private Button logout1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/stat.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    
        // TODO
    }    

    @FXML
    private void stat(MouseEvent event) throws IOException {
       fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/stat.fxml"));
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void users(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Users.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }
    
    

    @FXML
    private void logout(MouseEvent event) throws IOException {
        Alert alert =new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to logout?");
        Optional<ButtonType> option=alert.showAndWait();
        if (option.get().equals(ButtonType.OK)){
        logout.getScene().getWindow().hide();
        fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Login.fxml"));
        Stage stage=new Stage();
        Scene sc=new Scene(fxml);
        stage.setScene(sc);
        stage.show();
        
        }
    }

    @FXML
    private void bars(MouseEvent event) throws IOException {
         fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/bars.fxml"));
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }
        
    }
    
    
    

