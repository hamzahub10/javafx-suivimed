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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class StatController implements Initializable {
      private Connection connection;
    private PreparedStatement smt;
    private ResultSet result;
    private MaConnexion con;

    @FXML
    private AnchorPane stat;
    @FXML
    private Label total;
    @FXML
    private Label doc;
    @FXML
    private Label pat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 con=new MaConnexion();
          try {
              total();
              doc();
              pat();
              
              // TODO
          } catch (SQLException ex) {
              Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    public void total() throws SQLException{
          connection=(Connection) con.getCnx();
        String sql="select count(id) from user";
        int countData=0;
        smt=connection.prepareStatement(sql);
        result=smt.executeQuery();
        while(result.next()){
        countData=result.getInt("COUNT(id)");
        total.setText(String.valueOf(countData));
        }
    }
     public void doc() throws SQLException{
          connection=(Connection) con.getCnx();
        String sql="select count(id) from user where roles='Doctor'";
        int countData=0;
        smt=connection.prepareStatement(sql);
        result=smt.executeQuery();
        while(result.next()){
        countData=result.getInt("COUNT(id)");
        doc.setText(String.valueOf(countData));
        }
    }
         public void pat() throws SQLException{
          connection=(Connection) con.getCnx();
        String sql="select count(id) from user where roles='Patiant'";
        int countData=0;
        smt=connection.prepareStatement(sql);
        result=smt.executeQuery();
        while(result.next()){
        countData=result.getInt("COUNT(id)");
        pat.setText(String.valueOf(countData));
        }
    }
    
        
    
    
}
