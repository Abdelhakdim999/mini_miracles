/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Dimassi Abdelhak
 */
public class Animateur {
    private String animID;
private String nom;
private String prenom;
private String dateNaiss;
private String sexe;
private String cv;

    public Animateur() {
    }

    public Animateur(String animID, String nom, String prenom, String date_naissance, String sexe, String cv) {
        this.animID = animID;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
        this.cv = cv;
    }

    public String getAnimID() {
        return animID;
    }

    public void setAnimID(String animID) {
        this.animID = animID;
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

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Animateur{" + "animID=" + animID + ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss + ", sexe=" + sexe + ", cv=" + cv + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animateur other = (Animateur) obj;
        if (this.animID != other.animID) {
            return false;
        }
        return true;
    }
}
