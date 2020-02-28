/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author User
 */
public class menu {
    int id;
    String entre;
    String plat;
    String dessert;
    String date;

    public menu() {
    }
    

    public menu(int id, String entre, String plat, String dessert, String date) {
        this.id = id;
        this.entre = entre;
        this.plat = plat;
        this.dessert = dessert;
        this.date = date;
    }

    public menu(String entre, String plat, String dessert, String date) {
        this.entre = entre;
        this.plat = plat;
        this.dessert = dessert;
        this.date = date;
    }

    
   
    public menu(String entre, String plat, String dessert) {
        this.entre = entre;
        this.plat = plat;
        this.dessert = dessert;
    }

    public menu(int id, String entre, String plat, String dessert) {
        this.id=id;
         this.entre = entre;
        this.plat = plat;
        this.dessert = dessert;
     
    }

    public int getId() {
        return id;
    }

    public String getEntre() {
        return entre;
    }

    public String getPlat() {
        return plat;
    }

    public String getDessert() {
        return dessert;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntre(String entre) {
        this.entre = entre;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "menu{" + "id=" + id + ", entre=" + entre + ", plat=" + plat + ", dessert=" + dessert + ", date=" + date + '}';
    }
    

    
    
}
