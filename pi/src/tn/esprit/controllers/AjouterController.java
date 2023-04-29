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
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.services.MedicamentService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField tf_lib;
    @FXML
    private TextField tf_fab;
    @FXML
    private TextField tf_dur;
    @FXML
    private TextField tf_form;
    @FXML
    private TextField tf_gam;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        String lib,fab,dur,form,gam;
        lib = tf_lib.getText();
        fab = tf_fab.getText();
        dur = tf_dur.getText();
        form = tf_form.getText();
        gam = tf_gam.getText();

            medicament m = new medicament(lib, fab, dur, form, gam);

            MedicamentService ms = new MedicamentService();

            ms.ajouter(m);
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
