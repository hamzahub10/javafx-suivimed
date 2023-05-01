/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import tn.esprit.entities.rdv;

/**
 *
 * @author nourb
 */
public interface Fonctionsconsul <T>{
    public void ajouter (T t);
    public List<T> afficher();
    public void supprimer (int id);
    public void modifier (T t, int id);
}
