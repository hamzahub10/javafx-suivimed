/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.controllers;

import tn.esprit.entities.RegimeAlimentaire;
import tn.esprit.services.RegimeService;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherRegimeFXMLController implements Initializable {

    @FXML
    private TableView<RegimeAlimentaire> regime;
    @FXML
    private TableColumn<RegimeAlimentaire, Integer> col_calo;
    @FXML
    private TableColumn<RegimeAlimentaire,String > col_petit;
    @FXML
    private TableColumn<RegimeAlimentaire, String> col_dej;
    @FXML
    private TableColumn<RegimeAlimentaire, String> col_dinner;
    @FXML
    private TableColumn<RegimeAlimentaire, String> col_desc;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private Button ajout;
    
    
    
    ObservableList<RegimeAlimentaire> regimeList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<RegimeAlimentaire, Integer> col_id;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
       
        
       
    }   
    
    

    @FXML
    private void supprimer(ActionEvent event) {
        RegimeService rservice = new RegimeService();
        RegimeAlimentaire r = regime.getSelectionModel().getSelectedItem();
        rservice.deleteRegime(r.getId());
        System.out.println(r);
         table();
    }

    @FXML
    private void modifier(ActionEvent event) {
        RegimeService rservice = new RegimeService();
        RegimeAlimentaire r = regime.getSelectionModel().getSelectedItem();
        try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.ModifierRegime.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        table();
    }

    @FXML
    private void allerAjout(ActionEvent event) {
         try {
       
         
         Parent root = FXMLLoader.load(getClass().getResource("tn.esprit.gui.AjouterRegime.fxml"));

        Scene scene = new Scene(root);
              Stage primaryStage = new Stage();
       
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }

    private void table() {
     RegimeService rservice = new RegimeService();
         
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_calo.setCellValueFactory(new PropertyValueFactory<>("calories"));
        col_petit.setCellValueFactory(new PropertyValueFactory<>("repas_petitdej"));
        col_dej.setCellValueFactory(new PropertyValueFactory<>("repas_dej"));
        col_dinner.setCellValueFactory(new PropertyValueFactory<>("repas_diner"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        regime.setItems(rservice.readAllRegime());
     }

    
    
    
}
