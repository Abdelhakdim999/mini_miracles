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
public class inscription_cantine {
    

    int id;
    int idenf;
    int idcant;
    int nbj;
    float prix;
    String date_insc;
    
    public inscription_cantine() {
    }

    public inscription_cantine(int id, int idenf, int idcant, int nbj, float prix, String date_insc) {
        this.id = id;
        this.idenf = idenf;
        this.idcant = idcant;
        this.nbj = nbj;
        this.prix = prix;
        this.date_insc = date_insc;
    }

    public inscription_cantine(int idenf, int idcant, int nbj, float prix, String date_insc) {
        this.idenf = idenf;
        this.idcant = idcant;
        this.nbj = nbj;
        this.prix = prix;
        this.date_insc = date_insc;
    }

    public inscription_cantine(int idenf, int idcant, int nbj, String date_insc) {
        this.idenf = idenf;
        this.idcant = idcant;
        this.nbj = nbj;
        this.date_insc = date_insc;
        prix=nbj*2;
    }

    

   
   

   

        
  

    public int getNbj() {
        return nbj;
    }

    public float getPrix() {
        return prix;
    }

    public int getId() {
        return id;
    }

    public String getDate_insc() {
        return date_insc;
    }

    public void setDate_insc(String date_insc) {
        this.date_insc = date_insc;
    }
    

   
    public void setNbj(int nbj) {
        this.nbj = nbj;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdenf() {
        return idenf;
    }

    public int getIdcant() {
        return idcant;
    }

    public void setIdenf(int idenf) {
        this.idenf = idenf;
    }

    public void setIdcant(int idcant) {
        this.idcant = idcant;
    }

    @Override
    public String toString() {
        return "inscription_cantine{" + "id=" + id + ", idenf=" + idenf + ", idcant=" + idcant + ", nbj=" + nbj + ", prix=" + prix + ", date_insc=" + date_insc + '}';
    }

   
    
   

}
