/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.rdv;
import tn.esprit.services.RdvService;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class ModifierRdvController implements Initializable {

    @FXML
    private TextField tf_heure;
    @FXML
    private TextField tf_confirmation;
    @FXML
    private DatePicker datep;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_confirmation.setText(String.valueOf(AfficherRdvController.confirmation));
        tf_heure.setText(String.valueOf(AfficherRdvController.heure));
        datep.setValue(AfficherRdvController.localDate);
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try {
            int id;
            id = AfficherRdvController.id;
            String confirmation = tf_confirmation.getText();
            int heure = Integer.parseInt(tf_heure.getText());
            LocalDate localDate = datep.getValue();
            Date d = Date.valueOf(localDate);
            
            rdv r = new rdv(d, confirmation, heure);
            
            RdvService rs = new RdvService();
            
            rs.modifier(r, id);
           
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    @FXML
    private void retour(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
