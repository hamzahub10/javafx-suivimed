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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.consultation;
import tn.esprit.entities.rdv;
import tn.esprit.services.ConsultationService;
import tn.esprit.services.RdvService;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class AjouterConsulController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField tf_lienvisio;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_lienerg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            String vision = tf_lienvisio.getText();
            int prix = Integer.parseInt(tf_prix.getText());
            String enregistrement = tf_lienerg.getText();
            
            if (vision.isEmpty() || enregistrement.isEmpty()|| prix<0) {
            // Afficher un message d'erreur si un champ obligatoire est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return;
        }
            else{
            

            consultation c = new consultation(prix, vision, enregistrement);

            ConsultationService cs = new ConsultationService();

            cs.ajouter(c);

            Notifications n = Notifications.create()
                    .title("HealthMate")
                    .text("Consultation ajouté !")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
            rootPane.getChildren().setAll(pane);}
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherConsul.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
