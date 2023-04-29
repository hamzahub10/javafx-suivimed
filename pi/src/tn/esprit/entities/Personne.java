/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

/**
 *
 * @author hamza
 */
public class Personne {
    private int id;
    private String nom;
    private String email;
    private int num_tel;
    private String role;
    private String password;
    private String address;

   
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
  
    
    
    public Personne(){}

    public Personne( String nom, String email, String role,int num_tel,String addresse){
        this.nom=nom;
        this.email = email;
        this.role = role;
        this.num_tel = num_tel;
        this.address = addresse;
    }

  

    public int getId() {
        return id;
    }

   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String prenom) {
        this.email = prenom;
    }

    public int getnumtel() {
        return num_tel;
    }

    public void setnumtel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    
    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", num_tel=" + num_tel + ", role=" + role + ", address=" + address +  '}';
    }
    
    
    
}
