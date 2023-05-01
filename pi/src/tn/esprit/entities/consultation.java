/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author nourb
 */
public class consultation {
   private int id, prix;
   private String lien_visio, lien_enregistrement;
   
    public consultation() {
    }

    
   
   public consultation(int id, int prix, String lien_visio, String lien_enregistrement) {
        this.id = id;
        this.prix = prix;
        this.lien_visio = lien_visio;
        this.lien_enregistrement = lien_enregistrement;
    }

    public consultation(int prix, String lien_visio, String lien_enregistrement) {
        this.prix = prix;
        this.lien_visio = lien_visio;
        this.lien_enregistrement = lien_enregistrement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getLien_visio() {
        return lien_visio;
    }

    public void setLien_visio(String lien_visio) {
        this.lien_visio = lien_visio;
    }

    public String getLien_enregistrement() {
        return lien_enregistrement;
    }

    public void setLien_enregistrement(String lien_enregistrement) {
        this.lien_enregistrement = lien_enregistrement;
    }

    @Override
    public String toString() {
        return "consultation{" + "id=" + id + ", prix=" + prix + ", lien_visio=" + lien_visio + ", lien_enregistrement=" + lien_enregistrement + '}';
    }
   
   
}
