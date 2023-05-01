/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.esprit.services;


import java.util.List;

/**
 *
 * @author USER
 */
public interface RegimeIService<T> {
    public void insertRegime(T t);
    public void deleteRegime(int id);
    public void updateRegime(T t);
    public List<T>readAllRegime();
    public T readByIdRegime(int id);
    
}
