/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class BarsController implements Initializable {

    @FXML
    private AnchorPane mainForm;
    @FXML
    private BarChart<?, ?> bars;
    private Connection connection;
    private PreparedStatement smt;
    private ResultSet result;
    private MaConnexion con;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          con=new MaConnexion();
        try {
            chart();
        } catch (SQLException ex) {
            Logger.getLogger(BarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void chart() throws SQLException{
    String chart="SELECT roles, COUNT(*) as nombre_utilisateurs FROM user GROUP BY roles ORDER BY roles ASC";
     connection=(Connection) con.getCnx();
     XYChart.Series chartData=new XYChart.Series();
     smt=connection.prepareStatement(chart);
     result=smt.executeQuery();
     while(result.next()){
     chartData.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
   
     }
     bars.getData().add(chartData);
    }
    
}
