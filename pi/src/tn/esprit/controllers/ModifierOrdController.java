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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static tn.esprit.controllers.AfficherOrdController.date;
import tn.esprit.entities.ordonnance;
import tn.esprit.services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ModifierOrdController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField tf_paq;
    @FXML
    private TextField tf_dos;
    @FXML
    private TextField tf_req;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tf_paq.setText(String.valueOf(AfficherOrdController.paq));
         tf_dos.setText(String.valueOf(AfficherOrdController.dos));
         tf_req.setText(String.valueOf(AfficherOrdController.rq));
         date.setPromptText(AfficherOrdController.date.toString());  
        
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try {
            int id;
            id = Integer.parseInt(String.valueOf(AfficherOrdController.id));
            String rq = tf_req.getText();
            int paq = Integer.parseInt(tf_paq.getText());
            int dos = Integer.parseInt(tf_dos.getText());
            LocalDate localDate = date.getValue();
            Date d = Date.valueOf(localDate);

            ordonnance o = new ordonnance(id, paq, dos, d, rq);

            OrdonnanceService os = new OrdonnanceService();

            os.modifier(o, id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Cette ordonnance est modifié avec succés");
            alert.showAndWait();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
