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
import tn.esprit.entities.ordonnance;
import tn.esprit.services.MedicamentService;
import tn.esprit.services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AfficherOrdController implements Initializable {

    private ListView<ordonnance> liste_ord;
    @FXML
    private AnchorPane rootPane;
    static public String rq;
    static public int id,paq,dos,id_med;
    static public Date date;
    @FXML
    private TableView<ordonnance> affich_ord;
    @FXML
    private TableColumn<ordonnance, Date> dat;
    @FXML
    private TableColumn<ordonnance, Integer> med;
    @FXML
    private TableColumn<ordonnance, Integer> nb;
    @FXML
    private TableColumn<ordonnance, Integer> doss;
    @FXML
    private TableColumn<ordonnance, String> rqs;
    @FXML
    private TextField search;
    @FXML
    private Button srchbtn;
    @FXML
    private ImageView imageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageV.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
        //ListView list2 = liste_ord;
        
       
        dat.setCellValueFactory(new PropertyValueFactory<>("date"));
        med.setCellValueFactory(new PropertyValueFactory<>("id_med"));
        nb.setCellValueFactory(new PropertyValueFactory<>("nb_paquet"));
        doss.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        rqs.setCellValueFactory(new PropertyValueFactory<>("remarque"));

       
        
        OrdonnanceService os = new OrdonnanceService();
        affich_ord.setItems(FXCollections.observableArrayList(os.afficher()));
        /*List<ordonnance> list = os.afficher();
        for (int i = 0; i < list.size(); i++) {
            ordonnance ordonnance = list.get(i);
            list2.getItems().add(ordonnance);
        }*/
    }    



    





    @FXML
    private void ajouter(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ajouterOrd.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void modifier(MouseEvent event) {
        ListView<ordonnance> list = liste_ord; // assuming listView is a ListView<CoVoiturage>
        
        OrdonnanceService os = new OrdonnanceService();
        
            ObservableList selectedItems = affich_ord.getSelectionModel().getSelectedItems();

//int selectedID = list.getSelectionModel().getSelectedIndex();
        
        ordonnance o = affich_ord.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*
        
        id = o.getId();
        date = o.getDate();
        id_med= o.getId_med();
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
    private void srch(ActionEvent event) {
        OrdonnanceService ec = new OrdonnanceService();
        affich_ord.setItems(FXCollections.observableArrayList( ec.searchOrd( search.getText() ) ));
    }
    
    

    private void search(ActionEvent event) {
      OrdonnanceService ec = new OrdonnanceService();
      affich_ord.setItems(FXCollections.observableArrayList( ec.searchOrd( search.getText() ) ));
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/home.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void supprimer(MouseEvent event) {
                ListView<ordonnance> list_supp = liste_ord;
        OrdonnanceService os = new OrdonnanceService();
        //int selectedID = list_supp.getSelectionModel().getSelectedIndex();
        ObservableList selectedItems = affich_ord.getSelectionModel().getSelectedItems();

        ordonnance o = affich_ord.getSelectionModel().getSelectedItem(); 
        
        os.supprimer(o.getId()); 
        affich_ord.getItems().remove(o);
    }

   
    
    
}
