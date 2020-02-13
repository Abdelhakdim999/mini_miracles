/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mini_miracles.entites.Admin;
import mini_miracles.entites.Animateur;
import mini_miracles.entites.Chef_cantine;
import mini_miracles.entites.Parent;
import utils.Myconnection;

/**
 *
 * @author Dimassi Abdelhak
 */
public class ParentCrud {
    Connection cn2;
    Statement st;
    
    public ParentCrud() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    
    public void ajouterParent(Parent a) {

        try {
            String requete1 = "INSERT INTO parent (idParent ,nom , prenom, mail,tel,mdp,dateNaiss,sexe,nb_enfants) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getNom());
            pst.setString(3, a.getPrenom());
            pst.setString(4, a.getMail());
            pst.setString(5, a.getTel());
            pst.setString(6, a.getMdp());
            pst.setString(7, a.getDate_naissance());
            pst.setString(8, a.getSexe());
            pst.setInt(9, a.getNb_enfants());
            pst.executeUpdate();
            System.out.println("Parent ajouté !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficher() {

        try {
            String requete3 = "SELECT * FROM parent";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Parent a = new Parent();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setNb_enfants(rs.getInt(9));
                System.out.println("Le parent numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());
                System.out.println("Nombre d'enfant(s) = " + a.getNb_enfants());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SupprimerParent(int id) {

        try {
            String requete = " DELETE FROM parent where idParent = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Parent DELETED");
    }
    
    public void modifierParent(int id, String nom, String prenom,String mail,String tel,String mdp, String dateNaiss, String sexe, int nb_enfants) {

        String requete5 = "UPDATE parent SET nom = ?, prenom = ? ,mail = ?, tel = ?, mdp = ?, dateNaiss = ?, sexe=?, nb_enfants=?  WHERE idParent = ?";

        try {

            PreparedStatement pst2 = cn2.prepareStatement(requete5);
            pst2.setString(1, nom);
            pst2.setString(2, prenom); 
            pst2.setString(3, mail);
            pst2.setString(4, tel);
            pst2.setString(5, mdp);
            pst2.setString(6, dateNaiss);
            pst2.setString(7, sexe);
            pst2.setInt(8, nb_enfants);
            pst2.setInt(9, id);
            pst2.executeUpdate();
            System.out.println("Parent updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherParent(String besoin, String caractere) {

        String requete = "select * from parent where " + besoin + " LIKE '" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Parent a = new Parent();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setNb_enfants(rs.getInt(9));
                System.out.println("Le parent numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());
                System.out.println("Nombre d'enfant(s) = " + a.getNb_enfants());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void trierParent(String o) {
        try {

            String requete2 = "SELECT * FROM parent order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Parent a = new Parent();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setNb_enfants(rs.getInt(9));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        public String listUtilisateurs(){
        String mail="";
        try {
            mail +="\n\n";
            String requete2 = "SELECT * FROM parent";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                mail +="\n\n";
                Parent a = new Parent();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                mail +="Le parent numero " + a.getId()+" :";
                mail +="\n  nom = " + a.getNom();
                mail +="\n  prenom = " + a.getPrenom();
            }
            String requete6 = "SELECT * FROM admin";
            PreparedStatement pst6 = cn2.prepareStatement(requete6);
            ResultSet rs6 = pst6.executeQuery();
            
            while (rs6.next()) {
                mail +="\n\n";
                Admin a1 = new Admin();
                a1.setId(rs6.getInt(1));
                a1.setNom(rs6.getString(2));
                a1.setPrenom(rs6.getString(3));
                mail +="\nL'admin numero " + a1.getId()+" :";
                mail +="\n  nom = " + a1.getNom();
                mail +="\n  prenom = " + a1.getPrenom();
            }
            
            String requete5 = "SELECT * FROM chef_cantine";
            PreparedStatement pst5 = cn2.prepareStatement(requete5);
            ResultSet rs5 = pst5.executeQuery();
            
            while (rs5.next()) {
                mail +="\n\n";
                Chef_cantine a2 = new Chef_cantine();
                a2.setId(rs5.getInt(1));
                a2.setNom(rs5.getString(2));
                a2.setPrenom(rs5.getString(3));
                mail +="\nLe chef cantine numero " + a2.getId()+" :";
                mail +="\n  nom = " + a2.getNom();
                mail +="\n  prenom = " + a2.getPrenom();
            }
            
            String requete4 = "SELECT * FROM animateur";
            PreparedStatement pst3 = cn2.prepareStatement(requete4);
            ResultSet rs3 = pst3.executeQuery();
            
            while (rs3.next()) {
                mail +="\n\n";
                Animateur a3 = new Animateur();
                a3.setId(rs3.getInt(1));
                a3.setNom(rs3.getString(2));
                a3.setPrenom(rs3.getString(3));
                mail +="\nL'animateur numero " + a3.getId()+" :";
                mail +="\n  nom = " + a3.getNom();
                mail +="\n  prenom = " + a3.getPrenom();
            }
                return mail;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mail;
        }
}

