/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;


/**
 *
 * @author Ammouna_Zikou
 */
public class Animateur extends User{
    
    
    int idAnimateur ;
    int salaire ; 
    String animID;

    public Animateur() {
    }

    public Animateur(String animID,int idAnimateur, int salaire, String id, String nom, String prenom, String mail, String tel, String mdp, String date_naissance, String sexe, int etat) {
        super(nom, prenom, mail, tel, mdp, date_naissance, sexe, etat);
        this.idAnimateur = idAnimateur;
        this.salaire = salaire;
        this.animID = animID;
    }

    

    public Animateur(int salaire, String nom, String prenom, String mail, String tel, String mdp, String date_naissance, String sexe, int etat) {
        super(nom, prenom, mail, tel, mdp, date_naissance, sexe, etat);
        this.salaire = salaire;
    }
    

    

    public String getAnimID() {
        return animID;
    }

    public void setAnimID(String animID) {
        this.animID = animID;
    }

    public int getIdAnimateur() {
        return idAnimateur;
    }

    public void setIdAnimateur(int idAnimateur) {
        this.idAnimateur = idAnimateur;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }



    
}
