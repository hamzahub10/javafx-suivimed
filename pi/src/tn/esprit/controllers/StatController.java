/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.medicament;
import tn.esprit.services.MedicamentService;
import tn.esprit.services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class StatController implements Initializable {

    @FXML
    private PieChart f_stat;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imagev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagev.setImage(new Image("file:C:\\Users\\Dorra\\Documents\\NetBeansProjects\\pi\\src\\tn\\esprit\\images\\logo.png"));
        medicament ms= new medicament();
        OrdonnanceService ec = new OrdonnanceService();
ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
Map<String, Integer> stats = ec.getMediStat();

// Iterate over your statistics map and add the data to the PieChart
stats.entrySet().forEach((entry) -> {
    pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
});
f_stat.setData(pieChartData);
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
