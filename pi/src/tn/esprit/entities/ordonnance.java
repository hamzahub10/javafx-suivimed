/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dorra
 */
public class ordonnance {

    private int id, nb_paquet, dosage;
    private Date date;
    private String remarque;
    private int id_med;

    public ordonnance(int id, int nb_paquet, int dosage, Date date, String remarque) {
        this.id = id;
        this.nb_paquet = nb_paquet;
        this.dosage = dosage;
        this.date = date;
        this.remarque = remarque;
    }

    public ordonnance(int nb_paquet, int dosage, Date date, String remarque) {
        this.nb_paquet = nb_paquet;
        this.dosage = dosage;
        this.date = date;
        this.remarque = remarque;

    }

    public ordonnance(int nb_paquet, int dosage, Date date, String remarque, int id_med) {
        this.nb_paquet = nb_paquet;
        this.dosage = dosage;
        this.date = date;
        this.remarque = remarque;
        this.id_med = id_med;
    }



    public ordonnance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_paquet() {
        return nb_paquet;
    }

    public void setNb_paquet(int nb_paquet) {
        this.nb_paquet = nb_paquet;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }




    @Override
    public String toString() {
        return "ordonnance{" + "id=" + id + ", nb_paquet=" + nb_paquet + ", dosage=" + dosage + ", date=" + date + ", remarque=" + remarque + "} \n";
    }

}
