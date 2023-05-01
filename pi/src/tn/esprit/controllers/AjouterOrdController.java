/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.medicament;
import tn.esprit.entities.ordonnance;
import tn.esprit.services.Email;
import tn.esprit.services.MedicamentService;
import tn.esprit.services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AjouterOrdController implements Initializable {

    @FXML
    private DatePicker tf_date;
    @FXML
    private TextField tf_dos;
    @FXML
    private TextField tf_req;
    @FXML
    private Button ajouter;
    @FXML
    private TextField tf_paq;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ChoiceBox<medicament> tf_med;
    private medicament selectedMedicament;
    private int id_med;
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageV.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
            MedicamentService ms = new MedicamentService();
            List<medicament> list = ms.afficher();
            tf_med.getItems().addAll(list);
            tf_med.getSelectionModel().selectFirst();
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
            
            selectedMedicament = tf_med.getSelectionModel().getSelectedItem();
            id_med = selectedMedicament.getId();
            MedicamentService ms = new MedicamentService();
            List<medicament> list = ms.afficher();
            String rq;
            int paq,dos;
            LocalDate localDate = tf_date.getValue();
            Date d = Date.valueOf(localDate);
            rq = tf_req.getText();
            paq = Integer.parseInt(tf_paq.getText());
            dos = Integer.parseInt(tf_dos.getText());
            tf_med.getItems().addAll(list);
            
           
            LocalDate now = LocalDate.now();
            if (!localDate.isAfter(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("La date sélectionnée doit dépasser la date actuelle!!");
            alert.showAndWait();
            return;}
            else{
            medicament m = tf_med.getValue();
            ordonnance o = new ordonnance(paq, dos, d, rq, id_med);
            OrdonnanceService os = new OrdonnanceService();
            

            os.ajouter(o);
            
            Notifications n = Notifications.create()
                    .title("HealthMate")
                    .text("Vous avez une nouvelle ordonnance")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();
            
            //Emailsender.sendEmail_add("dorra.lajmi@esprit.tn", "succés");
            Email.sendEmail("dorra.lajmi@esprit.tn",o,m.getLibelle());
            
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
            rootPane.getChildren().setAll(pane);}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
