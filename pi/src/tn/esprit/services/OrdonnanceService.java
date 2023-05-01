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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        sql = "insert into ordonnance(date, nb_paquet, dosage, remarque, id_med) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setDate(1, (Date) t.getDate());
            ste.setInt(2, t.getNb_paquet());
            ste.setInt(3, t.getDosage());
            ste.setString(4, t.getRemarque());
            ste.setInt(5, t.getId_med());
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
                s.setId_med(rs.getInt(7));
                
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
    
    public List<ordonnance> searchOrd(String searchQuery) {
        List<ordonnance> list = new ArrayList<>();
        try {
            String req = "SELECT o.* FROM ordonnance o "
                    + "WHERE o.date LIKE ? OR o.id_med LIKE ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {

                ordonnance o = new ordonnance();
                o.setDate(RS.getDate("o.date"));
                o.setId_med(RS.getInt("o.id_med"));
                o.setDosage(RS.getInt("o.dosage"));
                o.setNb_paquet(RS.getInt("o.nb_paquet"));
                o.setRemarque(RS.getString("o.remarque"));

                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
       public Map<String, Integer> getMediStat() {
    Map<String, Integer> stats = new HashMap<>();
    try {
        //String req = "SELECT id_med, COUNT(*) as count FROM ordonnance GROUP BY id_med";
        String req = "SELECT o.id_med, COUNT(*) as count, m.libelle FROM ordonnance o INNER JOIN medicament m ON o.id_med = m.id GROUP BY o.id_med;";
        Statement st = cnx.createStatement();
        ResultSet RS = st.executeQuery(req);
        while (RS.next()) {
            String medi = RS.getString("m.libelle");
            int count = RS.getInt("count");
            stats.put(medi, count);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return stats;
}

}
