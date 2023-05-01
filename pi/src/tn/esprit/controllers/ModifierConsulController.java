/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.consultation;
import tn.esprit.services.ConsultationService;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class ModifierConsulController implements Initializable {
    
    @FXML
    private TextField tf_prix;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField tf_visio;
    @FXML
    private TextField tf_lien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_visio.setText(String.valueOf(AfficherConsulController.lien_visio));
        tf_prix.setText(String.valueOf(AfficherConsulController.prix));
        tf_lien.setText(String.valueOf(AfficherConsulController.lien_enregistrement));
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try {
            int id;
            id = AfficherConsulController.id;
            String lien_visio = tf_visio.getText();
            int prix = Integer.parseInt(tf_prix.getText());
            String lien_enregistrement = tf_lien.getText();   
            
            consultation c = new consultation(prix, lien_visio, lien_enregistrement);
            
            ConsultationService cs = new ConsultationService();
            
            cs.modifier(c, id);
           
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    
    
}

