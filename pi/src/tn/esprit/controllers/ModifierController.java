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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.services.MedicamentService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField tf_lib;
    @FXML
    private TextField tf_fab;
    @FXML
    private TextField tf_duree;
    @FXML
    private TextField tf_forme;
    @FXML
    private TextField tf_gamme;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tf_lib.setText(String.valueOf(AfficherController.libelle));
        tf_fab.setText(String.valueOf(AfficherController.fabricant));
        tf_duree.setText(String.valueOf(AfficherController.duree));
        tf_forme.setText(String.valueOf(AfficherController.forme));
        tf_gamme.setText(String.valueOf(AfficherController.gamme));
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            int id;
            id = Integer.parseInt(String.valueOf(AfficherController.id));
            String lib = tf_lib.getText();
            String fab = tf_fab.getText();
            String duree = tf_duree.getText();
            String forme = tf_forme.getText();
            String gamme = tf_gamme.getText();

            medicament m = new medicament(id, lib, fab, duree, forme, gamme);

            MedicamentService ms = new MedicamentService();

            ms.modifier(m, id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Ce medicament est modifié avec succés");
            alert.showAndWait();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
