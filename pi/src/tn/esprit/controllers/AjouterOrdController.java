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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.entities.ordonnance;
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
            String rq;
            int paq,dos;
            LocalDate localDate = tf_date.getValue();
            Date d = Date.valueOf(localDate);
            rq = tf_req.getText();
            paq = Integer.parseInt(tf_paq.getText());
            dos = Integer.parseInt(tf_dos.getText());
                

            ordonnance o = new ordonnance(paq, dos, d, rq);

            OrdonnanceService os = new OrdonnanceService();

            os.ajouter(o);
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
            rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficherOrd.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
