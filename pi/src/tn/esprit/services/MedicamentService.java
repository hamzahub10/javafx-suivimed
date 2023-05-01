/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.medicament;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Dorra
 */
public class MedicamentService implements Fonctions<medicament> {

    Connection cnx;
    String sql = "";

    public MedicamentService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(medicament t) {
        sql = "insert into medicament(libelle, fabricant, duree_conservation, forme, gamme) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, t.getLibelle());
            ste.setString(2, t.getFabricant());
            ste.setString(3, t.getDuree_conservation());
            ste.setString(4, t.getForme());
            ste.setString(5, t.getGamme());
            ste.executeUpdate();
            System.out.println("Medicament ajouté avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<medicament> afficher() {
        List<medicament> m = new ArrayList<>();
        sql = "select * from medicament";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                //Sinistre s = new Sinistre(rs.getInt(1), rs.getTimestamp(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                medicament s = new medicament();
                s.setId(rs.getInt(1));
                s.setLibelle(rs.getString(2));
                s.setFabricant(rs.getString(3));
                s.setDuree_conservation(rs.getString(4));
                s.setForme(rs.getString(5));
                s.setGamme(rs.getString(6));

                m.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }

    @Override
    public void supprimer(int id) {
        sql = "delete from medicament where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("medicament supprimé avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(medicament t, int id) {
        sql = "update medicament set libelle = ' " + t.getLibelle() + " ',  fabricant= ' " + t.getFabricant() + " ', duree_conservation = ' " + t.getDuree_conservation() + " ', forme = ' " + t.getForme() + " ', gamme = ' " + t.getGamme() + " ' where id= " + id;
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("medicament modifié avec succès.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<medicament> searchMed(String searchQuery) {
        List<medicament> list = new ArrayList<>();
        try {
            String req = "SELECT m.* FROM medicament m "
                    + "WHERE m.libelle LIKE ? OR m.fabricant LIKE ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {

                medicament m = new medicament();
                m.setLibelle(RS.getString("m.libelle"));
                m.setFabricant(RS.getString("m.fabricant"));
                m.setDuree_conservation(RS.getString("m.duree_conservation"));
                m.setForme(RS.getString("m.forme"));
                m.setGamme(RS.getString("m.gamme"));

                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public medicament getById(int id) {
        medicament r = new medicament();
        try {
            String req = "Select * from medicament  WHERE `id` = " + id;
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                medicament s = new medicament();
                s.setId(rs.getInt(1));
                s.setLibelle(rs.getString(2));
                s.setFabricant(rs.getString(3));
                s.setDuree_conservation(rs.getString(4));
                s.setForme(rs.getString(5));
                s.setGamme(rs.getString(6));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
    }

}
