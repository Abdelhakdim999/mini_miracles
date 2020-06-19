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
public class Pack {
    private double id , reduction ;
    private String titre , description , type , image , dureeDu , dureeA;

    public Pack(int reduction, String titre, String description, String type, String dureeDu, String dureeA) {
        this.reduction = reduction;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.dureeDu = dureeDu;
        this.dureeA = dureeA;
    }

    public Pack() {
    }

    public Pack(int id, int reduction, String titre, String description, String type, String dureeDu, String dureeA) {
        this.id = id;
        this.reduction = reduction;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.dureeDu = dureeDu;
        this.dureeA = dureeA;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDureeDu() {
        return dureeDu;
    }

    public void setDureeDu(String dureeDu) {
        this.dureeDu = dureeDu;
    }

    public String getDureeA() {
        return dureeA;
    }

    public void setDureeA(String dureeA) {
        this.dureeA = dureeA;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", reduction=" + reduction + ", titre=" + titre + ", description=" + description + ", type=" + type + ", image=" + image + ", dureeDu=" + dureeDu + ", dureeA=" + dureeA + '}';
    }
    
    
    
}
