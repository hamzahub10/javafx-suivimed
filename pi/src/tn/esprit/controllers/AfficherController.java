/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.services.MedicamentService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AfficherController implements Initializable {

    @FXML
    private ListView<medicament> liste_med;
    @FXML
    private AnchorPane rootPane;
    
    static public String libelle,fabricant,duree,forme,gamme;
    static public int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView list2 = liste_med;
        medicament m = new medicament();
        MedicamentService ms = new MedicamentService();
        List<medicament> list = ms.afficher();
        for (int i = 0; i < list.size(); i++) {
            medicament medicament = list.get(i);
            list2.getItems().add(medicament);
        }
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ajouter.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifier(ActionEvent event) {
        ListView<medicament> list = liste_med; // assuming listView is a ListView<CoVoiturage>
        
        MedicamentService ms = new MedicamentService();
        
        int selectedID = list.getSelectionModel().getSelectedIndex();
        
        medicament m = list.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*
        
        id = m.getId();
        libelle = m.getLibelle();
        fabricant = m.getFabricant();
        duree = m.getDuree_conservation();
        forme = m.getForme();
        gamme = m.getGamme();

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/modifier.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ListView<medicament> list_supp = liste_med;
        MedicamentService ms = new MedicamentService();
        int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        medicament m = list_supp.getSelectionModel().getSelectedItem(); 
        
        ms.supprimer(m.getId()); 
        list_supp.getItems().remove(selectedID);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/homepage.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
