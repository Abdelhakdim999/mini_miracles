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
public class Enfant {
    private int id;
    private String idEnfant;
private String nom;
private String prenom;
private String dateNaiss;
private String sexe;
private int idParent;
private int id_group;


    public Enfant() {
    }

    public Enfant(String nom, String prenom, String dateNaiss, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
    }

    public Enfant(int id, String nom, String prenom, String dateNaiss) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }
    
  public Enfant(int id, String nom, String prenom, String dateNaiss, String sexe, int id_group) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
        this.id_group = id_group;
    }

    public Enfant( int id_group, String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.id_group = id_group;
    }
      

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(String idEnfant) {
        this.idEnfant = idEnfant;
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

    @Override
    public String toString() {
        return "Enfant{" + "idEnfant=" + idEnfant + ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss + ", sexe=" + sexe + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Enfant other = (Enfant) obj;
        if (this.idEnfant != other.idEnfant) {
            return false;
        }
        return true;
    }
}
