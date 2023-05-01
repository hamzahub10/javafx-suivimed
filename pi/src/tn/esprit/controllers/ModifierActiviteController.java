/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.controllers;

import tn.esprit.entities.ActivitePhysique;
import tn.esprit.services.ActiviteService;
import tn.esprit.services.RegimeService;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierActiviteController implements Initializable {

    @FXML
    private TextField reg;
    @FXML
    private TextField per;
    @FXML
    private TextField nbr;
    @FXML
    private TextField desc;
    @FXML
    private TextField obj;
    @FXML
    private Button modAct;
    @FXML
    private Button affAct;
    @FXML
    private TextField mat;
    @FXML
    private TextField vid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modAct(ActionEvent event) {
        ActiviteService actservice = new ActiviteService();
         int regime = Integer.parseInt(reg.getText());
         int periode = Integer.parseInt(per.getText());
         int nombre = Integer.parseInt(nbr.getText());
         String descd = desc.getText();
         String objet = obj.getText();
         String materiel = mat.getText();
         String video= vid.getText();
         ActivitePhysique act = new ActivitePhysique(regime,periode,nombre,descd,objet,materiel,video);
         actservice.updateActivite(act);
    }

    @FXML
    private void affAct(ActionEvent event) {
        try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.AfficherActivite.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }
    
}
