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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageV.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        String lib,fab,dur,form,gam;
        lib = tf_lib.getText();
        fab = tf_fab.getText();
        dur = tf_dur.getText();
        form = tf_form.getText();
        gam = tf_gam.getText();
        
        if( tf_lib.getText().isEmpty()
                    | tf_fab.getText().isEmpty()
                    | tf_dur.getText().isEmpty()
                    | tf_form.getText().isEmpty()
                    | tf_gam.getText().isEmpty())
                    {
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();}
        else

        { medicament m = new medicament(lib, fab, dur, form, gam);

            MedicamentService ms = new MedicamentService();

            ms.ajouter(m);
            
            Notifications n = Notifications.create()
                    .title("HealthMate")
                    .text("Vous avez un nouveau médicament")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        rootPane.getChildren().setAll(pane);}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
