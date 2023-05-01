/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

/**
 *
 * @author USER
 */
public class ActivitePhysique {
    private int id;
    private int id_regime;
    private int periode;
    private int nbr_fois;
    private String objectif;
    private String materiel;
    private String description;
    private String video;

    public ActivitePhysique() {
    }
    

    public ActivitePhysique(int id, int id_regime, int periode, int nbr_fois, String objectif, String materiel, String description, String video) {
        this.id = id;
        this.id_regime = id_regime;
        this.periode = periode;
        this.nbr_fois = nbr_fois;
        this.objectif = objectif;
        this.materiel = materiel;
        this.description = description;
        this.video=video;
    }

    public ActivitePhysique(int id_regime, int periode, int nbr_fois, String objectif, String materiel, String description, String video) {
       
        this.id_regime = id_regime;
        this.periode = periode;
        this.nbr_fois = nbr_fois;
        this.objectif = objectif;
        this.materiel = materiel;
        this.description = description;
        this.video=video;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegime() {
        return id_regime;
    }

    public void setRegime(int id_regime) {
        this.id_regime = id_regime;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public int getNbr_fois() {
        return nbr_fois;
    }

    public void setNbr_fois(int nbr_fois) {
        this.nbr_fois = nbr_fois;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    

    @Override
    public String toString() {
        return "ActivitePhysique{" + "id=" + id + ", regime=" + id_regime + ", periode=" + periode + ", nbr_fois=" + nbr_fois + ", objectif=" + objectif + ", materiel=" + materiel + ", description=" + description +", video=" + video + '}';
    }
    
    
    
}
