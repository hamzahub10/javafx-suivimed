
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.services.MedicamentService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AfficherController implements Initializable {

    private ListView<medicament> liste_med;
    @FXML
    private AnchorPane rootPane;
    
    static public String libelle,fabricant,duree,forme,gamme;
    static public int id;
    @FXML
    private Button st;
    @FXML
    private TableView<medicament> affich_med;
    @FXML
    private TableColumn<medicament, String> lib;
    @FXML
    private TableColumn<medicament, String> fab;
    @FXML
    private TableColumn<medicament, String> dur;
    @FXML
    private TableColumn<medicament, String> form;
    @FXML
    private TableColumn<medicament, String> gam;
    @FXML
    private TextField search;
    @FXML
    private Button searchbtn;
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageV.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
        ListView list2 = liste_med;
        medicament m = new medicament();
        lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        fab.setCellValueFactory(new PropertyValueFactory<>("fabricant"));
        dur.setCellValueFactory(new PropertyValueFactory<>("duree_conservation"));
        form.setCellValueFactory(new PropertyValueFactory<>("forme"));
        gam.setCellValueFactory(new PropertyValueFactory<>("gamme"));
        MedicamentService ms = new MedicamentService();

       ;
        affich_med.setItems(FXCollections.observableArrayList(ms.afficher()));
        
       /* List<medicament> list = ms.afficher();
        for (int i = 0; i < list.size(); i++) {
            medicament medicament = list.get(i);
            list2.getItems().add(medicament);
        }*/
    }    
   
    

    @FXML
    private void Statistiques(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/stat.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void search(ActionEvent event) {
        MedicamentService ec = new MedicamentService();
        affich_med.setItems(FXCollections.observableArrayList( ec.searchMed( search.getText() ) ));
    }

    @FXML
    private void serachbar(ActionEvent event) {
      MedicamentService ec = new MedicamentService();
      affich_med.setItems(FXCollections.observableArrayList( ec.searchMed( search.getText() ) ));
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ajouter.fxml"));
        rootPane.getChildren().setAll(pane);
    }



    @FXML
    private void supprimer(MouseEvent event) {
        ListView<medicament> list_supp = liste_med;
        MedicamentService ms = new MedicamentService();
        //int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_med.getSelectionModel().getSelectedItems();

        medicament m = affich_med.getSelectionModel().getSelectedItem(); 
        
        ms.supprimer(m.getId()); 
        affich_med.getItems().remove(m);
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/home.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifie(MouseEvent event) {
        ListView<medicament> list = liste_med;
        
        MedicamentService ms = new MedicamentService();
        
        //int selectedID = list.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_med.getSelectionModel().getSelectedItems();

        
        medicament m = affich_med.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*
        
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
    
}
