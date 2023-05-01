/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.controllers;

import tn.esprit.entities.RegimeAlimentaire;
import tn.esprit.services.RegimeService;
import tn.esprit.services.SMSSender;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterRegimeController implements Initializable {

    @FXML
    private TextField cal;
    @FXML
    private TextField petit;
    @FXML
    private TextField dej;
    @FXML
    private TextField din;
    @FXML
    private TextField desc;
    @FXML
    private MenuButton vit;
    @FXML
    private Button ajoutReg;
    @FXML
    private Button update;
    @FXML
    private Button AffRegime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddRegime(ActionEvent event) {
         RegimeService rservice = new RegimeService();
         int cals = Integer.parseInt(cal.getText());
         String petitd = petit.getText();
         String dejd = dej.getText();
         String dind = din.getText();
         String descd= desc.getText();
         RegimeAlimentaire reg = new RegimeAlimentaire(cals,petitd,dejd,dind,descd);
         rservice.insertRegime(reg);
         SMSSender msg=new SMSSender();
         msg.send();
    }

    @FXML
    private void AffRegime(ActionEvent event) {
        try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.AfficherRegimeFXML.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }
    
}
