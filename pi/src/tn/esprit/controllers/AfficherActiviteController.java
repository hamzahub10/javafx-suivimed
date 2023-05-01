/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.controllers;


import tn.esprit.entities.ActivitePhysique;
import tn.esprit.services.ActiviteService;
import tn.esprit.services.RegimeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherActiviteController implements Initializable {

    @FXML
    private TableColumn<ActivitePhysique, Integer> id;
    @FXML
    private TableColumn<ActivitePhysique, Integer> id_reg;
    @FXML
    private TableColumn<ActivitePhysique, Integer> per;
    @FXML
    private TableColumn<ActivitePhysique, Integer> nbr;
    @FXML
    private TableColumn<ActivitePhysique, String> desc;
    @FXML
    private TableColumn<ActivitePhysique, String> obj;
    @FXML
    private TableColumn<ActivitePhysique, String> mat;
    @FXML
    private TableColumn<ActivitePhysique, String> vid;
    @FXML
    private Button ajout;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private TableView<ActivitePhysique> tab_act;
    ObservableList<ActivitePhysique> actList = FXCollections.observableArrayList();
    @FXML
    private Button affVid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        

    }    

    @FXML
    private void ajouterAct(ActionEvent event) {
        try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.AjouterActivite.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }
    
    

    @FXML
    private void supprimerAct(ActionEvent event) {
        ActiviteService actservice = new ActiviteService();
        ActivitePhysique act = tab_act.getSelectionModel().getSelectedItem();
        actservice.deleteActivite(act.getId());
        System.out.println(act);
         table();
    }

    @FXML
    private void modifierAct(ActionEvent event) {
        ActiviteService actservice = new ActiviteService();
        ActivitePhysique act = tab_act.getSelectionModel().getSelectedItem();
        try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.ModifierActivite.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        table();
        
    }
    
    private void table() {
     ActiviteService actservice = new ActiviteService();
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_reg.setCellValueFactory(new PropertyValueFactory<>("regime_id"));
        per.setCellValueFactory(new PropertyValueFactory<>("periode"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbr_fois"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        obj.setCellValueFactory(new PropertyValueFactory<>("objectif"));
        mat.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        vid.setCellValueFactory(new PropertyValueFactory<>("video"));

        
        tab_act.setItems(actservice.readAllActivite());
     }

    /*@FXML
    private void afficherVideo(ActionEvent event) {
        ActiviteService actservice = new ActiviteService();
        ActivitePhysique act = tab_act.getSelectionModel().getSelectedItem();
        actservice.playVideo(act.getVideo());
        
    }*/
    
}
