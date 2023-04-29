/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.ordonnance;
import tn.esprit.services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AfficherOrdController implements Initializable {

    @FXML
    private ListView<ordonnance> liste_ord;
    @FXML
    private AnchorPane rootPane;
    static public String rq;
    static public int id,paq,dos;
    static public Date date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView list2 = liste_ord;
        ordonnance o = new ordonnance();
        OrdonnanceService os = new OrdonnanceService();
        List<ordonnance> list = os.afficher();
        for (int i = 0; i < list.size(); i++) {
            ordonnance ordonnance = list.get(i);
            list2.getItems().add(ordonnance);
        }
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ajouterOrd.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifier(ActionEvent event) {
        ListView<ordonnance> list = liste_ord; // assuming listView is a ListView<CoVoiturage>
        
        OrdonnanceService os = new OrdonnanceService();
        
        int selectedID = list.getSelectionModel().getSelectedIndex();
        
        ordonnance o = list.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*
        
        id = o.getId();
        date = o.getDate();
        paq = o.getNb_paquet();
        dos = o.getDosage();
        rq = o.getRemarque();

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/modifierOrd.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ListView<ordonnance> list_supp = liste_ord;
        OrdonnanceService os = new OrdonnanceService();
        int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        ordonnance o = list_supp.getSelectionModel().getSelectedItem(); 
        
        os.supprimer(o.getId()); 
        list_supp.getItems().remove(selectedID);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/homepage.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    
}
