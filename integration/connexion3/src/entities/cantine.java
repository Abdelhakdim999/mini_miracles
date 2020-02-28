/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class cantine {
    int id ;
    String heurdeb;
    String heurfin ;
    int capacite ;
     List<inscription_cantine> listinscant;


    public cantine() {
       listinscant=new ArrayList<>();

    }

    public cantine(String heurdeb, String heurfin, int capacite) {
         listinscant=new ArrayList<>();
        this.heurdeb = heurdeb;
        this.heurfin = heurfin;
        this.capacite = capacite;
    }

    public cantine(int id, String heurdeb, String heurfin, int capacite) {
         listinscant=new ArrayList<>();
        this.id = id;
        this.heurdeb = heurdeb;
        this.heurfin = heurfin;
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public String getHeurdeb() {
        return heurdeb;
    }

    public String getHeurfin() {
        return heurfin;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeurdeb(String heurdeb) {
        this.heurdeb = heurdeb;
    }

    public void setHeurfin(String heurfin) {
        this.heurfin = heurfin;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<inscription_cantine> getListinscant() {
        return listinscant;
    }

    @Override
    public String toString() {
        return "cantine{" + "id=" + id + ", heurdeb=" + heurdeb + ", heurfin=" + heurfin + ", capacite=" + capacite + ", listinscant=" + listinscant + '}';
    }

    
    
    
}
