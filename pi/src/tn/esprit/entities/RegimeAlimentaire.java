/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class RegimeAlimentaire {
    private int id;
    private int calories;
    private String repas_petitdej;
    private String repas_dej;
    private String repas_diner;
    private String description;

    public RegimeAlimentaire() {
    }
    

    public RegimeAlimentaire(int id, int calories, String repas_petitdej, String repas_dej, String repas_diner, String description) {
        this.id = id;
        this.calories = calories;
        this.repas_petitdej = repas_petitdej;
        this.repas_dej = repas_dej;
        this.repas_diner = repas_diner;
        this.description = description;
    }

    public RegimeAlimentaire(int calories, String repas_petitdej, String repas_dej, String repas_diner, String description) {
        this.calories = calories;
        this.repas_petitdej = repas_petitdej;
        this.repas_dej = repas_dej;
        this.repas_diner = repas_diner;
        this.description = description;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getRepas_petitdej() {
        return repas_petitdej;
    }

    public void setRepas_petitdej(String repas_petitdej) {
        this.repas_petitdej = repas_petitdej;
    }

    public String getRepas_dej() {
        return repas_dej;
    }

    public void setRepas_dej(String repas_dej) {
        this.repas_dej = repas_dej;
    }

    public String getRepas_diner() {
        return repas_diner;
    }

    public void setRepas_diner(String repas_diner) {
        this.repas_diner = repas_diner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RegimeAlimentaire{" + "id=" + id + ", calories=" + calories + ", repas_petitdej=" + repas_petitdej + ", repas_dej=" + repas_dej + ", repas_diner=" + repas_diner + ", description=" + description +  '}';
    }
    
    
    
    
}
