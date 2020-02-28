/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class Commentaire {
    private int id_comm;
    private int id_sujet;
    private String contenu;
    private String date;
    
public Commentaire() {
    }

    public Commentaire(int id_sujet, String contenu, String date) {
        this.id_sujet = id_sujet;
        this.contenu = contenu;
        this.date = date;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }
    

    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
    public String getDate(){
        return this.date;
    }
    
    public void setDate(String date){
        this.date = date;
    }
   

    @Override
    public String toString() {
        return "Commentaire{" + "id_comm=" + id_comm + ", contenu=" +contenu+"date_event=" + date+ '}';
    }
    
}