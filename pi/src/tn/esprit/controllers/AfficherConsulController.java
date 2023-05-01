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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.consultation;
import tn.esprit.services.ConsultationService;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class AfficherConsulController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    private ListView<consultation> liste_consultation;

    static public int id, prix;
    static public String lien_visio, lien_enregistrement;
    @FXML
    private TableView<consultation> affich_consul;
    @FXML
    private TableColumn<consultation, String> visio;
    @FXML
    private TableColumn<consultation, String> enreg;
    @FXML
    private TableColumn<consultation, Integer> prixx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ListView list2 = liste_consultation;

        visio.setCellValueFactory(new PropertyValueFactory<>("lien_visio"));
        enreg.setCellValueFactory(new PropertyValueFactory<>("lien_enregistrement"));
        prixx.setCellValueFactory(new PropertyValueFactory<>("prix"));

        ConsultationService os = new ConsultationService();
        affich_consul.setItems(FXCollections.observableArrayList(os.afficher()));

        /*List<consultation> list = cs.afficher();
        for (int i = 0; i < list.size(); i++) {
            consultation consultation = list.get(i);
            list2.getItems().add(consultation);
        }*/
    }
    
    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AjouterConsul.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifier(MouseEvent event) {
        ListView<consultation> list = liste_consultation; // assuming listView is a ListView<CoVoiturage>

        ConsultationService cs = new ConsultationService();

        //int selectedID = list.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_consul.getSelectionModel().getSelectedItems();

        consultation c = affich_consul.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*

        id = c.getId();
        prix = c.getPrix();
        lien_visio = c.getLien_visio();
        lien_enregistrement = c.getLien_enregistrement();

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ModifierConsul.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    @FXML
    private void supprimer(MouseEvent event) {
        ConsultationService cs = new ConsultationService();
        //int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_consul.getSelectionModel().getSelectedItems();

        consultation c = affich_consul.getSelectionModel().getSelectedItem();

        cs.supprimer(c.getId());
        affich_consul.getItems().remove(c);
        affich_consul.refresh();
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/home.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
