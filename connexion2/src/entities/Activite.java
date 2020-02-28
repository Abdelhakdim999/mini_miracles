/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Dimassi Abdelhak
 */
public class Activite {
    private int id ;
        private String  lib; 
        private String description;
        //private Date date;
        private String date;
        private int duree;
        private String enfantsID;
        private String AnimateursID;

    public Activite(String lib, String description, int duree, String date, String enfants, String Animateurs) {
        this.lib = lib;
        this.description = description;
        this.duree = duree;
        this.date = date;
        this.enfantsID = enfants;
        this.AnimateursID = Animateurs;
    }


    public String getEnfants() {
        return enfantsID;
    }

    public void setEnfants(String enfantsID) {
        this.enfantsID = enfantsID;
    }

    public void setAnimateurs(String AnimateursID) {
        this.AnimateursID = AnimateursID;
    }

    public String getAnimateurs() {
        return AnimateursID;
    }

    public Activite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", lib=" + lib + ", description=" + description + ", date=" + date + ", duree=" + duree + ", enfantsID=" + enfantsID + ", AnimateursID=" + AnimateursID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.lib);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.date);
        hash = 19 * hash + Objects.hashCode(this.duree);
        hash = 19 * hash + Objects.hashCode(this.enfantsID);
        hash = 19 * hash + Objects.hashCode(this.AnimateursID);
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
        final Activite other = (Activite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.lib, other.lib)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.enfantsID, other.enfantsID)) {
            return false;
        }
        if (!Objects.equals(this.AnimateursID, other.AnimateursID)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
}
