/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import tn.esprit.entities.RegimeAlimentaire;
import tn.esprit.services.RegimeIService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author USER
 */
public class RegimeService implements RegimeIService<RegimeAlimentaire> {
    private Connection cnx;

    public RegimeService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    //Ajouter un regime alimentaire:
    @Override
    public void insertRegime(RegimeAlimentaire reg) {
        String requete = "insert into regime(calories,repas_petitdej,repas_dej,repas_dinner,description)"
                + "values('" + reg.getCalories() + "','" + reg.getRepas_petitdej()+ "','" + reg.getRepas_dej()
                + "','" + reg.getRepas_diner()+ "','" + reg.getDescription() + "')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Régime ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Supprimer un regime alimentaire:
    @Override
    public void deleteRegime(int id) {
        try {
            String req = "DELETE FROM `regime` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Regime deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Modifier un regime alimentaire:
    @Override
    public void updateRegime(RegimeAlimentaire reg) {
        try {
            String req = "UPDATE `regime` SET `calories` = '"+ reg.getCalories()+ "',`repas_petitdej` = '" +reg.getRepas_petitdej()+
                    "',`repas_dej`='"+reg.getRepas_dej()+"',`repas_dinner`='"+reg.getRepas_diner()+"',`description`='"+reg.getDescription()+
                    "' WHERE `regime`.`id` = " + reg.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Régime modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Afficher un regime alimentaire:
    @Override
    public ObservableList<RegimeAlimentaire> readAllRegime() {
        ObservableList<RegimeAlimentaire> list= FXCollections.observableArrayList();
        String requete="select * from regime";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
                list.add(new RegimeAlimentaire(rs.getInt("id"),rs.getInt("calories"),rs.getString("repas_petitdej"),rs.getString("repas_dej")
                ,rs.getString("repas_dinner"),rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    //Afficher PAR ID un regime alimentaire:
    @Override
    public RegimeAlimentaire readByIdRegime(int id){
        RegimeAlimentaire reg = new RegimeAlimentaire();
        try {
            Statement ste=cnx.createStatement();
            String req = "select * from regime where id = "+reg.getId();
            
            ResultSet rs = ste.executeQuery(req);
            System.out.println("okkk"); 
        
            while (rs.next()) {
                RegimeAlimentaire rsRegime = new RegimeAlimentaire(rs.getInt("calories"),rs.getString("repas_petitdej"),rs.getString("repas_dej")
                ,rs.getString("repas_dinner"),rs.getString("description"));
                System.out.println(rsRegime); 
                reg=rsRegime;
            }
            System.out.println("iciii") ;

            System.out.println(reg);
      
        } catch (SQLException ex) {
            System.out.println(ex);   
        }
   

        return reg;
    }
    
}
