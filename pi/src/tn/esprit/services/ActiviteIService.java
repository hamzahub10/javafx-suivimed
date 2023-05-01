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
public interface ActiviteIService<T> {
    public void insertActivite(T t);
    public void deleteActivite(int id);
    public void updateActivite(T t);
    public List<T>readAllActivite();
    public T readByIdActivite(int id);
    
}
