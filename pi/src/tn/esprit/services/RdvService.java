/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.rdv;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author nourb
 */
public class RdvService implements Fonctionsconsul<rdv>{

    Connection cnx;
    String sql="";
    
    public RdvService(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(rdv t) {
        sql = "insert into rdv(date, confirmation, heure) values (?,?,?)";
        try {
            
            PreparedStatement ste = cnx.prepareStatement(sql);
            
            ste.setDate(1,  t.getDate());
            ste.setString(2, t.getConfirmation());
            ste.setInt(3, t.getHeure());
            ste.executeUpdate();
            System.out.println("Rendez-vous ajouté avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<rdv> afficher() {
        List<rdv> rdvs = new ArrayList<>();
        sql="select * from rdv";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                rdv r = new rdv();
                r.setId(rs.getInt(1));
                r.setDate(rs.getDate(3));
                r.setHeure(rs.getInt(4));
                r.setConfirmation(rs.getString(5));
                
                rdvs.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rdvs;
    }

    @Override
    public void supprimer(int id) {
        sql="delete from rdv where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Rendez-vous supprimé avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(rdv t, int id) {
        sql="update rdv set date = ' " + t.getDate() + " ', confirmation = ' " + t.getConfirmation() + " ', heure = ' " + t.getHeure() + " ' where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Rendez-vous modifié avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<rdv> rechrdv(Date d) {
    List<rdv> list = new ArrayList<>();
    try {
        String sqlQuery = "SELECT * FROM rdv WHERE `date` = ?";
        PreparedStatement statement = cnx.prepareStatement(sqlQuery);
        statement.setDate(1, d);
        ResultSet rs = statement.executeQuery(); // pass the PreparedStatement object to executeQuery()
        while (rs.next()) {
            rdv r = new rdv();
            r.setId(rs.getInt(1));
            r.setDate(rs.getDate(3));
            r.setHeure(rs.getInt(4));
            r.setConfirmation(rs.getString(5));

            list.add(r);
        }
    } catch (SQLException ex) {
        System.err.println(ex);
    }

    return list;
}
    
  
}
