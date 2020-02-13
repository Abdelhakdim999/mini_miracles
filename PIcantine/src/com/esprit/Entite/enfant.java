/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author User
 */
public class enfant {
    int id;
    String nom;
    String prenom;
    int age;
    Personne parent = new Personne();

    public enfant() {
    }

    public enfant(String nom, String prenom, int age, Personne parent) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.parent = parent;
    }

    public enfant(int id, String nom, String prenom, int age, Personne parent) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.parent = parent;
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public Personne getParent() {
        return parent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setParent(Personne parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "enfant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + '}';
    }

   
    
}
