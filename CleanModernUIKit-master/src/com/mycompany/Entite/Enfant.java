/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Dimassi Abdelhak
 */
public class Enfant {
    private String nom,prenom,sexe,dateNaiss,idParent;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enfant() {
    }

    public Enfant(String nom, String prenom, String sexe, String dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
    }

    public Enfant(String nom, String prenom, String sexe, String dateNaiss, String idParent) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.idParent = idParent;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    @Override
    public String toString() {
        return "Enfant{" + "nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", dateNaiss=" + dateNaiss + ", idParent=" + idParent + '}';
    }
    
    
}
