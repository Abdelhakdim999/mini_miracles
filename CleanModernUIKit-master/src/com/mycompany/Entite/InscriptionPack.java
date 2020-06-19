/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Ammouna_Zikou
 */
public class InscriptionPack {
    
    private String dateInsc ;
    private int id  , idPack , idEnfant ;
    private String nomEnfant, prenomEnfant;
//    private String titrePack;
//
//    public String getTitrePack() {
//        return titrePack;
//    }
//
//    public void setTitrePack(String titrePack) {
//        this.titrePack = titrePack;
//    }
//    

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }
    public InscriptionPack() {
    }

    public InscriptionPack(String dateInsc, int idPack, int idEnfant) {
        this.dateInsc = dateInsc;
        this.idPack = idPack;
        this.idEnfant = idEnfant;
    }

    public InscriptionPack(String dateInsc, int id, int idPack, int idEnfant) {
        this.dateInsc = dateInsc;
        this.id = id;
        this.idPack = idPack;
        this.idEnfant = idEnfant;
    }

    public String getDateInsc() {
        return dateInsc;
    }

    public void setDateInsc(String dateInsc) {
        this.dateInsc = dateInsc;
    }

 
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    @Override
    public String toString() {
        return "InscriptionPack{" + "dateInsc=" + dateInsc + ", id=" + id + ", idPack=" + idPack + ", idEnfant=" + idEnfant + '}';
    }
    
    
    
}
