/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Activite;
import entities.Animateur;
import entities.ControleSaisie;
import entities.Enfant;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Myconnection;
import utils.TrayIconDemo;

/**
 *
 * @author Dimassi Abdelhak
 */
public class ActiviteCrud {
    Connection cn2;
    Statement st;

    public ActiviteCrud() {

        cn2 = Myconnection.getInstance().getCnx();
    }
    
    public boolean verifchamps(String lib,String description,String duree){
    ControleSaisie ctrs = new ControleSaisie();
    ActiviteCrud ac = new ActiviteCrud();
     if (ctrs.isString(lib) && ctrs.isString(description) && ctrs.isNumber(duree))
     {
         System.out.println("test true");
     return true;
     }
     return false;
}

    public void ajouterActivite(Activite a) throws AWTException {

        try {
            String requete1 = "INSERT INTO activite (lib, description, duree, date,enfants,animateurs) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            pst.setString(1, a.getLib());
            pst.setString(2, a.getDescription());
            pst.setInt(3, a.getDuree());
            pst.setString(4, a.getDate());
            pst.setString(5, a.getEnfants());
            pst.setString(6, a.getAnimateurs());
            pst.executeUpdate();
            System.out.println("Activite ajouté !");
            if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.trayAjout();
        } else {
            System.err.println("System tray not supported!");
        }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficher() {
        ArrayList<Activite> Act = new ArrayList<>();

        try {
            String requete3 = "SELECT * FROM activite";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                String idList = rs.getString(6);
                String idList1 = rs.getString(7);
                System.out.println("L'activite:");
                System.out.println("Libelle = " + a.getLib());
                System.out.println("Description = " + a.getDescription());
                System.out.println("Date = " + a.getDate());
                System.out.println("Duree = " + a.getDuree());
                System.out.println("les enfants qui vont participer sont : ");
                List<Enfant> KidList = IdEnfants();
                for (Enfant en : KidList) {
                    if (idList.contains(en.getIdEnfant())) {
                        System.out.println("l'enfant " + en.getIdEnfant() + " " + en.getNom() + " " + en.getPrenom());
                    }
                }
                List<Animateur> animList = IdAnimateurs();
                System.out.println("Et les animateurs sont : ");
                for (Animateur an : animList) {
                    if (idList1.contains(an.getAnimID())) {
                        System.out.println("l'animateur " + an.getAnimID() + " " + an.getNom() + " " + an.getPrenom());
                    }
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      //return Act;
    }
    public List<Enfant> IdEnfants() {
        ArrayList<Enfant> kidList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Enfant en = new Enfant();

                en.setIdEnfant(rs.getString(2));
                en.setNom(rs.getString(3));
                en.setPrenom(rs.getString(4));
                en.setDateNaiss(rs.getString(5));
                en.setSexe(rs.getString(6));
                kidList.add(en);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kidList;
    }

    public List<Animateur> IdAnimateurs() {
        ArrayList<Animateur> animList = new ArrayList<>();
        try {

            String requete4 = "SELECT * FROM animateur";
            PreparedStatement pst = cn2.prepareStatement(requete4);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Animateur a = new Animateur();

                a.setAnimID(rs.getString(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setDate_naissance(rs.getString(4));
                a.setSexe(rs.getString(5));
                animList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return animList;
    }
    
    public List<Activite> listAct() {
        ArrayList<Activite> ActList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM activite";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Activite a = new Activite();

                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                a.setAnimateurs(rs.getString(7));
                a.setEnfants(rs.getString(6));
                ActList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActList;
    }
    
    public void afficherActivite() {

        try {

            String requete2 = "SELECT * FROM activite";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                String idList = rs.getString(6);
                String idList1 = rs.getString(7);
                System.out.println("L'activite numero " + a.getId());
                System.out.println("Libelle = " + a.getLib());
                System.out.println("Description = " + a.getDescription());
                System.out.println("Date = " + a.getDate());
                System.out.println("Duree = " + a.getDuree());
                System.out.println("les enfants qui vont participer sont : ");
                List<Enfant> KidList = IdEnfants();
                for (Enfant en : KidList) {
                    if (idList.contains(en.getIdEnfant())) {
                        System.out.println("l'enfant " + en.getIdEnfant() + " " + en.getNom() + " " + en.getPrenom());
                    }
                }
                List<Animateur> animList = IdAnimateurs();
                System.out.println("Et les animateurs sont : ");
                for (Animateur an : animList) {
                    if (idList1.contains(an.getAnimID())) {
                        System.out.println("l'animateur " + an.getAnimID() + " " + an.getNom() + " " + an.getPrenom());
                    }
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public String listActivite(){
        String mail="";
        try {
            
            String requete2 = "SELECT * FROM activite";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mail +="\n----------\n";
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                String idList = rs.getString(6);
                String idList1 = rs.getString(7);
                mail +="L'activite:";
                mail +="\n  Libelle = " + a.getLib();
                mail +="\n  Description = " + a.getDescription();
                mail +="\n  Duree = " + a.getDuree();
                mail +="\n  Date = " + a.getDate();
                mail +="\n  Les enfants qui vont participer sont : ";
                List<Enfant> KidList = IdEnfants();
                for (Enfant en : KidList) {
                    if (idList.contains(en.getIdEnfant())) {
                        mail +="\n      l'enfant " + en.getIdEnfant() + " " + en.getNom() + " " + en.getPrenom();
                    }
                }
                List<Animateur> animList = IdAnimateurs();
                mail +="\n  Les animateurs sont : ";
                for (Animateur an : animList) {
                    if (idList1.contains(an.getAnimID())) {
                        mail +="\n      l'animateur " + an.getAnimID() + " " + an.getNom() + " " + an.getPrenom();
                    }
                }
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mail;
    }

    public void ajouterActivite2() {
        try {
            String date="2020-05-05";
            String requete = "INSERT INTO activite (lib, description, duree, date, enfants , animateurs) VALUES ('dessin','cartoon','60','"+date+"','E5-E6','A3')";
            st = cn2.createStatement();
            st.executeUpdate(requete);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerActivite(int id) throws AWTException {

        try {
            String requete = " DELETE FROM activite where id = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
            if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.traySupp();
        } else {
            System.err.println("System tray not supported!");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Activite DELETED");
    }

 /*   public Activite update(Activite obj) {
        
        try {
            //int result = this.connect.createStatement().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            String requete = "UPDATE activite SET lib = ?, description = ?, date = ?, duree = ?  WHERE id = ? " ;
            System.out.println("SQL REQUEST : " + requete);

            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setString(1, obj.getLib());
            pst2.setString(2, obj.getDescription());
            pst2.setString(3, obj.getDate());
            pst2.setString(4, obj.getDuree());
            pst2.setString(5, obj.getId());
            pst2.executeUpdate(requete);
            pst2.close();
           
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }*/
    
    public void modifierActivite(int id, String lib, String description , int duree, String date, String enfants, String animateurs) throws AWTException {

        String requete5 = "UPDATE activite SET lib = ?, description = ?  , duree = ?, date = ?, enfants=?, animateurs=?  WHERE id = ?";

        try {

            PreparedStatement pst2 = cn2.prepareStatement(requete5);
            pst2.setString(1, lib);
            pst2.setString(2, description); 
            pst2.setInt(3, duree);
            pst2.setString(4, date);
            pst2.setString(5, enfants);
            pst2.setString(6, animateurs);
            pst2.setInt(7, id);
            pst2.executeUpdate();
            System.out.println("Activite updated");
            if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.trayModif();
        } else {
            System.err.println("System tray not supported!");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherActivite(String besoin, String caractere) {

        ArrayList<Activite> activites = new ArrayList<>();
        String requete = "select * from activite where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                String idList = rs.getString(6);
                String idList1 = rs.getString(7);
                activites.add(a);
                System.out.println("L'activite numero " + a.getId());
                System.out.println("Libelle = " + a.getLib());
                System.out.println("Description = " + a.getDescription());
                System.out.println("Date = " + a.getDate());
                System.out.println("Duree = " + a.getDuree());
                System.out.println("les enfants qui vont participer sont : ");
                List<Enfant> KidList = IdEnfants();
                for (Enfant en : KidList) {
                    if (idList.contains(en.getIdEnfant())) {
                        System.out.println("l'enfant " + en.getIdEnfant() + " " + en.getNom() + " " + en.getPrenom());
                    }
                }
                List<Animateur> animList = IdAnimateurs();
                System.out.println("Et les animateurs sont : ");
                for (Animateur an : animList) {
                    if (idList1.contains(an.getAnimID())) {
                        System.out.println("l'animateur " + an.getAnimID() + " " + an.getNom() + " " + an.getPrenom());
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //return activites;
    }
    
    public List<Activite> rechercherAct_list(String besoin, String caractere) {

        ArrayList<Activite> activites = new ArrayList<>();
        String requete = "select * from activite where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                a.setEnfants(rs.getString(6));
                a.setAnimateurs(rs.getString(7));
                activites.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }
    public Activite rechercherAct_id( int caractere) {
        Activite a = new Activite();
        String requete = "select * from activite where id LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();
            
            while (rs.next()) {
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                a.setEnfants(rs.getString(6));
                a.setAnimateurs(rs.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
    
    public void trierActivite(String o) {
        try {

            String requete2 = "SELECT * FROM activite order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                a.setEnfants(rs.getString(6));
                a.setAnimateurs(rs.getString(7));
                System.out.println(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public List<Activite> trierAct_list(String o) {
            ArrayList<Activite> activites = new ArrayList<>();
            String requete2 = "SELECT * FROM activite order by " + o;
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getInt(4));
                a.setDate(rs.getString(5));
                a.setEnfants(rs.getString(6));
                a.setAnimateurs(rs.getString(7));
                activites.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }
}
