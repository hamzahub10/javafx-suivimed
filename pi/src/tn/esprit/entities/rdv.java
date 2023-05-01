/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;


/**
 *
 * @author nourb
 */
public class rdv {
    private int id;
    private Date date;
    private String confirmation;
    private int heure;
    //private Personne p;
   
    public rdv() {
    }

    /*public rdv(Date date, String confirmation, int heure, Personne p) {
        this.date = date;
        this.confirmation = confirmation;
        this.heure = heure;
        this.p = p;
    }

    public rdv(int id, Date date, String confirmation, int heure, Personne p) {
        this.id = id;
        this.date = date;
        this.confirmation = confirmation;
        this.heure = heure;
        this.p = p;
    }*/

    
    
    public rdv(int id, Date date, String confirmation, int heure) {
        this.id = id;
        this.date = date;
        this.confirmation = confirmation;
        this.heure = heure;
    }

    public rdv(Date date, String confirmation, int heure) {
        this.date = date;
        this.confirmation = confirmation;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    /*public Personne getP() {
        return p;
    }

    public void setP(Personne p) {
        this.p = p;
    }*/

    @Override
    public String toString() {
        return "rdv{"+ " date=" + date + ", confirmation=" + confirmation + ", heure=" + heure + "} \n";
    }
    
    
}
