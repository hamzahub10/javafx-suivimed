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
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.medicament;
import tn.esprit.entities.ordonnance;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Dorra
 */
public class OrdonnanceService implements Fonctions<ordonnance> {
        Connection cnx;
    String sql="";
    
    public OrdonnanceService(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(ordonnance t) {
        sql = "insert into ordonnance(date, nb_paquet, dosage, remarque) values (?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setDate(1, (Date) t.getDate());
            ste.setInt(2, t.getNb_paquet());
            ste.setInt(3, t.getDosage());
            ste.setString(4, t.getRemarque());
            ste.executeUpdate();
            System.out.println("Ordonnance ajoutée avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ordonnance> afficher() {
        List<ordonnance> o = new ArrayList<>();
        sql="select * from ordonnance";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                ordonnance s = new ordonnance();
                s.setId(rs.getInt(1));
                s.setDate(rs.getDate(3));
                s.setNb_paquet(rs.getInt(4));
                s.setDosage(rs.getInt(5));
                s.setRemarque(rs.getString(6));
                
                o.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return o;
    }

    @Override
    public void supprimer(int id) {
         sql="delete from ordonnance where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Ordonnance supprimée avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(ordonnance t, int id) {
        sql="update ordonnance set date = ' " + t.getDate() + " ',  nb_paquet = ' " + t.getNb_paquet() + " ', dosage = ' " + t.getDosage() + " ', remarque = ' " + t.getRemarque() + " ' where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Ordonance modifiée avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
