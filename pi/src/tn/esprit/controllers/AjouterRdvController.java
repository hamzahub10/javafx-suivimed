/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.rdv;
import tn.esprit.services.RdvService;


import tn.esprit.services.Emailsender;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class AjouterRdvController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextField tf_heure;
    @FXML
    private TextField tf_confirmation;
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
    private void ajouter(ActionEvent event) {
        try {

            String confirmation = tf_confirmation.getText();
            LocalDate localDate = date.getValue();
            Date d = Date.valueOf(localDate);
            int heure = Integer.parseInt(tf_heure.getText());
            
            LocalDate now = LocalDate.now();
            if (!localDate.isAfter(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("La date sélectionnée doit dépasser la date actuelle!!");
            alert.showAndWait();
            return;}
            else{

            rdv r = new rdv(d, confirmation, heure);

            RdvService rs = new RdvService();

            rs.ajouter(r);
            String ACCOUNT_SID = "AC80e7e175d3dd5ef37d46453445beaba3";
            String AUTH_TOKEN = "e9e3f409b61c9b7c2bd2468d083afd00";
            String FROM_NUMBER = "+15074195094"; // your Twilio phone number
            String TO_NUMBER = "+21653748811"; // the recipient's phone number
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(TO_NUMBER),
                new PhoneNumber(FROM_NUMBER),
                "Rendez vous"+r.getId()+"ajouté avec succés")
                .create();

        System.out.println(message.getSid());
    
            
            Emailsender.sendEmail_add("nourhouda.bejaoui@esprit.tn", "Votre rendez-vous a été ajouté avec succés"); Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Ce rdv est ajouté avec succés");
            alert.showAndWait();
            
            Notifications n = Notifications.create()
                    .title("HealthMate")
                    .text("RDV ajouté !")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherRdv.fxml"));
            rootPane.getChildren().setAll(pane);}
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
