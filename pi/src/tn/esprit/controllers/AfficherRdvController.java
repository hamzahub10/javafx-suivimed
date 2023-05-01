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
import java.time.ZoneId;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.rdv;
import tn.esprit.services.RdvService;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class AfficherRdvController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    private ListView<rdv> liste_rdv;

    static public int id, heure;
    static public String confirmation;
    static public Date date;
    @FXML
    private TableView<rdv> affich_rdv;
    @FXML
    private TableColumn<rdv, Date> dat;
    @FXML
    private TableColumn<rdv, Integer> hour;
    @FXML
    private TableColumn<rdv, String> conf;
    
    static public LocalDate localDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ListView list2 = liste_rdv;
        dat.setCellValueFactory(new PropertyValueFactory<>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<>("heure"));
        conf.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
       

       
        RdvService rs = new RdvService();
       
        affich_rdv.setItems(FXCollections.observableArrayList(rs.afficher()));
        /*rdv r = new rdv();
        RdvService rs = new RdvService();
        List<rdv> list = rs.afficher();
        for (int i = 0; i < list.size(); i++) {
            rdv rdv = list.get(i);
            list2.getItems().add(rdv);
        }*/

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/home.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifier(MouseEvent event) {
        ListView<rdv> list = liste_rdv; 

        RdvService rs = new RdvService();

        //int selectedID = list.getSelectionModel().getSelectedIndex();
        
        ObservableList selectedItems = affich_rdv.getSelectionModel().getSelectedItems();

        rdv r = affich_rdv.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*

        id = r.getId();
        heure = r.getHeure();
        confirmation = r.getConfirmation();
        date = r.getDate();
        localDate = date.toLocalDate();

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ModifierRdv.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AjouterRdv.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        ListView<rdv> list_supp = liste_rdv;
        RdvService rs = new RdvService();
        //int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_rdv.getSelectionModel().getSelectedItems();
        rdv r = affich_rdv.getSelectionModel().getSelectedItem();

        rs.supprimer(r.getId());
        affich_rdv.getItems().remove(r);
    }


    @FXML
    private void demain(ActionEvent event) {

        affich_rdv.getItems().clear();

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Date date_rdv = Date.valueOf(tomorrow);
        
        RdvService rs = new RdvService();

        affich_rdv.setItems(FXCollections.observableArrayList(rs.rechrdv(date_rdv)));

    }

    @FXML
    private void apres_demain(ActionEvent event) {
        
        affich_rdv.getItems().clear();

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(2);
        Date date_rdv = Date.valueOf(tomorrow);
        
        RdvService rs = new RdvService();

        affich_rdv.setItems(FXCollections.observableArrayList(rs.rechrdv(date_rdv)));
    }

    @FXML
    private void all(ActionEvent event) {

        ListView list2 = liste_rdv;
        rdv r = new rdv();
        RdvService rs = new RdvService();
        affich_rdv.setItems(FXCollections.observableArrayList(rs.afficher()));
    }

}
