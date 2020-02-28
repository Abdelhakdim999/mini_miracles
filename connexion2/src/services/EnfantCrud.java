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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnection;

/**
 *
 * @author Dimassi Abdelhak
 */
public class EnfantCrud {
    Connection cn2;
    Statement st;

    public EnfantCrud() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    public boolean verifchamps(String nom,String prenom){
    ControleSaisie ctrs = new ControleSaisie();
    EnfantCrud ac = new EnfantCrud();
     if (ctrs.isString(nom) && ctrs.isString(prenom))
     {
         System.out.println("test true");
     return true;
     }
     return false;
}
    
    public void ajouterEnfant(Enfant a) {

        int lastid = 0;
        try {
            String requete2 = "select id from enfant";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            ResultSet rs = pst2.executeQuery();
            while ( rs.next() )
            {
                lastid = rs.getInt(1)+1;
                
            }
            String lastidString = "E"+lastid;
            String requete1 = "INSERT INTO enfant (idEnfant,nom , prenom, dateNaiss,sexe,idParent) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            pst.setString(1, lastidString);
            pst.setString(2, a.getNom());
            pst.setString(3, a.getPrenom());
            pst.setString(4, a.getDateNaiss());
            pst.setString(5, a.getSexe());
            pst.setInt(6, a.getIdParent());
            pst.executeUpdate();
            
            System.out.println("Enfant ajouté !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouterEnfant1(Enfant a) {

        try {
            int lastid = 0;
            
            
            String requete1 = "INSERT INTO enfant (idEnfant,nom , prenom, dateNaiss,sexe,idParent) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            pst.setString(1, "E");
            pst.setString(2, a.getNom());
            pst.setString(3, a.getPrenom());
            pst.setString(4, a.getDateNaiss());
            pst.setString(5, a.getSexe());
            pst.setInt(6, a.getIdParent());
            pst.executeUpdate();
            
            String requete2 = "select id from enfant";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            ResultSet rs = pst2.executeQuery();
            while ( rs.next() )
            {
                lastid = rs.getInt(1);
                
            }
            String lastidString = "E"+lastid;
            String requete5 = "UPDATE enfant SET idEnfant = ? WHERE id = ?";
            PreparedStatement pst3 = cn2.prepareStatement(requete5);
            pst3.setString(1, lastidString);
            pst3.setInt(2, lastid);
            pst3.executeUpdate();
            System.out.println("Enfant updated");
        } catch (SQLException ex) {
            Logger.getLogger(EnfantCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        
            
        
    }
    
    public int rechercherParent_id(String password, String email) {
        int id=0;
        try {
            
        String requete =   "SELECT user.id_user FROM user INNER JOIN parent ON user.id_user = parent.idParent and user.mail = '" + email + "' and user.mdp = '" + password + "'";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");
            ResultSet rs = pst2.executeQuery();
            
            while (rs.next()) {
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    
    public String Parent_Name_id(int id) {
        String fullName="";
        try {
            
        String requete =   "SELECT nom,prenom FROM user where id_user ='" + id + "'";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            ResultSet rs = pst2.executeQuery();
            
            while (rs.next()) {
                fullName=rs.getString(1)+" "+rs.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return fullName;
    }

    public void afficher() {

        try {
            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                System.out.println("L'enfant numero " + a.getIdEnfant());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Date de naissance = " + a.getDateNaiss());
                System.out.println("Sexe = " + a.getSexe());

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Enfant> listEnf() {
        ArrayList<Enfant> Enflist = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                a.setIdParent(rs.getInt(7));
                Enflist.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Enflist;
    }
    
    public List<Enfant> listEnf_Parent(int idparent) {
        ArrayList<Enfant> Enflist = new ArrayList<>();
        ArrayList<Enfant> enfants = new ArrayList<>();
        String requete = "select * from enfant where idParent LIKE '" + idparent + "'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Enfant a = new Enfant();
                a.setId(rs.getInt(1));
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                enfants.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return enfants;
    }
    public ArrayList<String> ID_Enf() {
        ArrayList<String> Enflist = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();
            String result = "";
            while (rs.next()) {
                result = "";
                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                result = a.getIdEnfant();
                Enflist.add(result);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Enflist;
    }
    
    public ArrayList<String> Nom_Enf() {
        ArrayList<String> Enflist = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();
            String result = "";
            while (rs.next()) {
                result = "";
                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                result = a.getNom()+" "+a.getPrenom();
                Enflist.add(result);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Enflist;
    }
    
    public ArrayList<String> ID_Anim() {
        ArrayList<String> Animlist = new ArrayList<>();
        try {

            String requete3 = "SELECT id FROM animateur";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Animateur a = new Animateur();
                a.setAnimID(rs.getString(1));
                Animlist.add(a.getAnimID());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Animlist;
    }
    
    public ArrayList<String> Nom_Anim() {
        ArrayList<String> Animlist = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM animateur";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Animateur a = new Animateur();
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                Animlist.add(a.getNom()+" "+a.getPrenom());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Animlist;
    }
    
    
    public List<Enfant> afficherEnfant() {
        ArrayList<Enfant> kidList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM enfant";
            PreparedStatement pst = cn2.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Enfant a = new Enfant();

                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                kidList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kidList;
    }

    public void ajouterEnfant2() {
        try {
            String dateNaiss="1998-05-09";
            String requete = "INSERT INTO enfant (nom , prenom, dateNaiss,sexe) VALUES ('abdelhak'','dimassi','"+dateNaiss+"','M')";
            st = cn2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout enfant done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerEnfant(int id) {

        try {
            String requete = " DELETE FROM enfant where id = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Enfant DELETED");
    }
    
    public void modifierEnfant(int id, String nom, String prenom, String dateNaiss, String sexe) {

        String requete5 = "UPDATE enfant SET nom = ?, prenom = ?  , dateNaiss = ?, sexe=?  WHERE id = ?";

        try {

            PreparedStatement pst2 = cn2.prepareStatement(requete5);
            pst2.setString(1, nom);
            pst2.setString(2, prenom); 
            pst2.setString(3, dateNaiss);
            pst2.setString(4, sexe);
            pst2.setInt(5, id);
            pst2.executeUpdate();
            System.out.println("Enfant updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherEnfant(String besoin, String caractere) {

        String requete = "select * from enfant where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                System.out.println("L'enfant numero " + a.getIdEnfant());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Date de naissance = " + a.getDateNaiss());
                System.out.println("Sexe = " + a.getSexe());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Enfant> rechercherEnf_list(String besoin, String caractere) {

        ArrayList<Enfant> enfants = new ArrayList<>();
        String requete = "select * from enfant where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Enfant a = new Enfant();
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                a.setIdParent(rs.getInt(7));
                enfants.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return enfants;
    }
    
    public Enfant rechercherEnf_id(int id) {
Enfant a = new Enfant();
        String requete = "select * from enfant where id LIKE '%" + id + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                
                a.setId(rs.getInt(1));
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
    
    public void trierEnfant(String o) {
        try {

            String requete2 = "SELECT * FROM enfant order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Enfant a = new Enfant();
                a.setIdEnfant(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                System.out.println(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public List<Enfant> trierEnf_list(String o) {
            ArrayList<Enfant> enfants = new ArrayList<>();
            String requete2 = "SELECT * FROM enfant order by " + o;
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Enfant a = new Enfant();
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setDateNaiss(rs.getString(5));
                a.setSexe(rs.getString(6));
                a.setIdParent(rs.getInt(7));
                enfants.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return enfants;
    }
}
