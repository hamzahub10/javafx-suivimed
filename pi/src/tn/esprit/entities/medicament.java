/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.List;

/** 
 *
 * @author Dorra
 */
public class medicament {
    private int id;
    private String libelle,fabricant,duree_conservation,forme,gamme; 
    private List<ordonnance> ordonnances;

    public medicament(int id, String libelle, String fabricant, String duree_conservation, String forme, String gamme) {
        this.id = id;
        this.libelle = libelle;
        this.fabricant = fabricant;
        this.duree_conservation = duree_conservation;
        this.forme = forme;
        this.gamme = gamme;
        
    }

    public medicament(String libelle, String fabricant, String duree_conservation, String forme, String gamme) {
        this.libelle = libelle;
        this.fabricant = fabricant;
        this.duree_conservation = duree_conservation;
        this.forme = forme;
        this.gamme = gamme;
    }

    public medicament() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getDuree_conservation() {
        return duree_conservation;
    }

    public void setDuree_conservation(String duree_conservation) {
        this.duree_conservation = duree_conservation;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }
    
    public List<ordonnance> getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(List<ordonnance> ordonnances) {
        this.ordonnances = ordonnances;
    }

    @Override
    public String toString() {
        return "medicament{" + " libelle=" + libelle + ", fabricant=" + fabricant + ", duree_conservation=" + duree_conservation + ", forme=" + forme + ", gamme=" + gamme + "}\n";
    }
    
}
