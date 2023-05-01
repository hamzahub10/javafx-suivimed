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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private AnchorPane rootPane;
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
    private Button modifier;
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageV.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
        tf_lib.setText(String.valueOf(AfficherController.libelle));
        tf_fab.setText(String.valueOf(AfficherController.fabricant));
        tf_dur.setText(String.valueOf(AfficherController.duree));
        tf_form.setText(String.valueOf(AfficherController.forme));
        tf_gam.setText(String.valueOf(AfficherController.gamme));
    }    

    @FXML
    private void modifier(ActionEvent event) {
         try {
            int id;
            id = Integer.parseInt(String.valueOf(AfficherController.id));
            String lib = tf_lib.getText();
            String fab = tf_fab.getText();
            String duree = tf_dur.getText();
            String forme = tf_form.getText();
            String gamme = tf_gam.getText();

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
    

