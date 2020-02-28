/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Darius
 */
public class Salle {
    public int num_salle;
    public String nom_salle;
   private String capacite;

    public Salle( String nom_salle, String capacite, int num_salle) {
        this.num_salle = num_salle;
        this.nom_salle = nom_salle;
        this.capacite = capacite;
    }

    public Salle(int num_salle, String nom_salle, String capacite) {
        this.num_salle = num_salle;
        this.nom_salle = nom_salle;
        this.capacite = capacite;
    }

    public Salle() {
    }

    public Salle(String nom_salle, String capacite) {
        this.nom_salle = nom_salle;
        this.capacite = capacite;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Salle{" + "num_salle=" + num_salle + ", nom_salle=" + nom_salle + ", capacite=" + capacite + '}';
    }
   

  }

   
 