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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("file:C:\\Users\\nourb\\OneDrive\\Documents\\NetBeansProjects\\Pidev\\src\\tn\\esprit\\images\\logo.png"));
    }    

    @FXML
    private void voir_rdv(ActionEvent event) throws IOException {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
            rootPane.getChildren().setAll(pane);

    }

    @FXML
    private void voir_consul(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherOrd(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherOrd.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherMed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Afficher.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
