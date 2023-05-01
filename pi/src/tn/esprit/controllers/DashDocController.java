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
public class DashDocController implements Initializable {
private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void med(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void ord(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void rdv(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void act(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherActivite.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void reg(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRegimeFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void cons(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
         Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
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
    private void blog(MouseEvent event) {
         try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    }
    
}

   