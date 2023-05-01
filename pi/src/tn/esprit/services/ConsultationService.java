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
import tn.esprit.entities.consultation;
import tn.esprit.entities.rdv;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author nourb
 */
public class ConsultationService implements Fonctionsconsul<consultation>{

    Connection cnx;
    String sql="";
    
    public ConsultationService(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    @Override

    
    public void ajouter(consultation t) {
        sql = "insert into consultation(prix, lien_visio, lien_enregistrement) values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            
            ste.setInt(1,t.getPrix());
            ste.setString(2, t.getLien_visio());
            ste.setString(3, t.getLien_enregistrement());
            ste.executeUpdate();
            System.out.println("Consultation ajouté avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<consultation> afficher() {
        List<consultation> consultations = new ArrayList<>();
        sql="select * from consultation";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                consultation r = new consultation();
                r.setId(rs.getInt(1));
                r.setPrix(rs.getInt(4));
                r.setLien_visio(rs.getString(3));
                r.setLien_enregistrement(rs.getString(5));
                
                consultations.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return consultations;
    }

    @Override
    public void supprimer(int id) {
        sql="delete from consultation where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Consultation supprimée avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(consultation t, int id) {
        sql="update consultation set prix = ' " + t.getPrix() + " ', Lien_visio = ' " + t.getLien_visio() + " ', Lien_enregistrement = ' " + t.getLien_enregistrement() + " ' where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Consultation modifiée avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
