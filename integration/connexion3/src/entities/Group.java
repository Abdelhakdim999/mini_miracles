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
public class Group {
    private int id_group;
    private String nom_group;
private int membres;

    public Group(int id_group, String nom_group, int membres) {
        this.id_group = id_group;
        this.nom_group = nom_group;
        this.membres = membres;
    }




    public Group(int id_group, String nom_group) {
        this.id_group = id_group;
        this.nom_group = nom_group;
    }
public Group(){}
    public Group(String nom_group) {
        this.nom_group = nom_group;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getNom_group() {
        return nom_group;
    }

    public void setNom_group(String nom_group) {
        this.nom_group = nom_group;
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
        final Group other = (Group) obj;
        if (this.id_group != other.id_group) {
            return false;
        }
        if (!Objects.equals(this.nom_group, other.nom_group)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Group{" + "id_group=" + id_group + ", nom_group=" + nom_group + ", membres=" + membres + '}';
    }

    
 
    
    
    
    
    
    
   
            }