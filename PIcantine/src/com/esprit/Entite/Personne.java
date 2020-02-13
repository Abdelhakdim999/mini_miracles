/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author House
 */
public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    List<enfant> listenf;
    List<inscription_cantine> listcant;

    public Personne(int id, String nom, String prenom, int age) {
         listenf=new ArrayList<>();
         listcant = new ArrayList<>();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Personne(String nom, String prenom, int age) {
         listenf=new ArrayList<>();
         listcant = new ArrayList<>();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Personne() {
        listenf=new ArrayList<>();
         listcant = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<enfant> getListenf() {
        return listenf;
    }

    public List<inscription_cantine> getListcant() {
        return listcant;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void addenfant(enfant f){
    listenf.add(f);
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", listenf=" + listenf + ", listcant=" + listcant + '}';
    }

   
    
}
