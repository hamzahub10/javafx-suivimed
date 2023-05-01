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
import javafx.scene.control.ChoiceBox;
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
    @FXML
    private ChoiceBox<String> choice_box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choice_box.getItems().addAll("Medicaments","Ordonnances","Rdvs","Consultations","Regimes Alimentaires","Activités Physiques","Blog");
                choice_box.getSelectionModel().selectFirst();

        try {
            fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/total.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
    
        // TODO
    }    

    @FXML
    private void stat(MouseEvent event) throws IOException {
       fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/total.fxml"));
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

    @FXML
    private void choice(MouseEvent event) {
        choice_box.setOnMouseClicked(e->{
        String choix=choice_box.getValue();
        switch(choix){
            case "Medicaments":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
            case "Ordonnances":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
             case "Rdv":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
                case "Consultations":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
                case "Regimes Alimentaires":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRegimeFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
                case "Activités Physiques":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherActivite.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
                case "Blog":
        {
            try {
                fxml=FXMLLoader.load(getClass().getResource("/tn/esprit/gui/"));
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       root.getChildren().removeAll();
       root.getChildren().setAll(fxml);
                break;
                default:
                    break;
                
        
        
        }
        
        
        });
    }
        
    }
    
    
    

