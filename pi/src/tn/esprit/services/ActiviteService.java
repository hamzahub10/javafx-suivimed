/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import tn.esprit.entities.ActivitePhysique;
import tn.esprit.services.ActiviteIService;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import tn.esprit.tools.MaConnexion;
/**
 *
 * @author USER
 */
public class ActiviteService implements ActiviteIService<ActivitePhysique> {
    private Connection cnx;
    

    public ActiviteService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    //Ajouter une activite physique:
    @Override
    public void insertActivite(ActivitePhysique act) {
        String requete = "insert into activite(regime_id,periode,nbr_fois,description,objectif,materiel,video)"
                + "values('" + act.getRegime() + "','" + act.getPeriode()+ "','" + act.getNbr_fois()
                + "','" +act.getDescription() +  "','" + act.getObjectif()+ "','" +act.getMateriel()+"','" +act.getVideo()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Activité ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Supprimer une activite physique:
    @Override
    public void deleteActivite(int id) {
        try {
            String req = "DELETE FROM `activite` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Activité supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Modifier une activite physique:
    @Override
    public void updateActivite(ActivitePhysique act) {
        try {
            String req = "UPDATE `activite` SET `regime_id` = '"+ act.getRegime()+ "',`periode` = '" +act.getPeriode()+
                    "',`nbr_fois`='"+act.getNbr_fois()+"',`description`='"+act.getDescription()+"',`objectif`='"+
                    act.getObjectif()+"',`materiel`='"+act.getMateriel()+"',`video`='"+act.getVideo()+ "' WHERE `activite`.`id` = " + act.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Activité modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Afficher une activite physique:
    @Override
    public ObservableList<ActivitePhysique> readAllActivite() {
        ObservableList<ActivitePhysique> list= FXCollections.observableArrayList();
        String requete="select * from activite";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
                list.add(new ActivitePhysique(rs.getInt("id"),rs.getInt("regime_id"),rs.getInt("periode"),rs.getInt("nbr_fois")
                ,rs.getString("objectif"),rs.getString("materiel"),rs.getString("description"),rs.getString("video")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    //Afficher PAR ID un regime alimentaire:
    @Override
    public ActivitePhysique readByIdActivite(int id){
        ActivitePhysique act = new ActivitePhysique();
        try {
            Statement ste=cnx.createStatement();
            String req = "select * from activite where id = "+act.getId();
            
            ResultSet rs = ste.executeQuery(req);
            System.out.println("okkk"); 
        
            while (rs.next()) {
                ActivitePhysique rsActivite = new ActivitePhysique(rs.getInt("id"),rs.getInt("regime_id"),rs.getInt("periode"),rs.getInt("nbr_fois")
                ,rs.getString("objectif"),rs.getString("materiel"),rs.getString("description"),rs.getString("video"));
                System.out.println(rsActivite); 
                act=rsActivite;
            }
            System.out.println("iciii") ;

            System.out.println(act);
      
        } catch (SQLException ex) {
            System.out.println(ex);   
        }
   

        return act;
    }
    //Métier Video
   

    public void playVideo(String video) {
        //Create a GUI to display the video
        JFrame f = new JFrame();
        f.setLocation(100,100);
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        //Create an instance of Canvas which will be used to display the video
        Canvas c=new Canvas();
        //Background is black
        c.setBackground(Color.black);
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        //Video take all the surface of JPanel
        p.add(c);
        f.add(p);
        //Read the video file using vlcj 
        //load the native library
      
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:\\Program Files (x86)\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        //initialize the media player
        MediaPlayerFactory mpf=new MediaPlayerFactory();
        //control all the interactions with the user
        EmbeddedMediaPlayer emp=mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
        emp.setVideoSurface(mpf.newVideoSurface(c));
        //full screen
        emp.toggleFullScreen();
        //hide the cursor
        emp.setEnableMouseInputHandling(false);
        //disable the keyboard
        emp.setEnableKeyInputHandling(false);
       
        //prepare file to read
        emp.prepareMedia(video);
        //read the file
        emp.play();
        
    }
   
    
    
}
