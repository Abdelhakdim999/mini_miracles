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
import mini_miracles.entites.Animateur;
import utils.Myconnection;

/**
 *
 * @author Ammouna_Zikou
 */
public class AnimateurCrud {
    Connection cn2;
    Statement st;

    public AnimateurCrud() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    
    
    public void ajouterAnimateur(Animateur a) {

        try {
            String requete1 = "INSERT INTO animateur (idAnimateur ,nom , prenom, mail,tel,mdp,dateNaiss,sexe,salaire) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getNom());
            pst.setString(3, a.getPrenom());
            pst.setString(4, a.getMail());
            pst.setString(5, a.getTel());
            pst.setString(6, a.getMdp());
            pst.setString(7, a.getDate_naissance());
            pst.setString(8, a.getSexe());
            pst.setInt(9, a.getSalaire());
            pst.executeUpdate();
            System.out.println("Animateur ajouté !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficher() {

        try {
            String requete3 = "SELECT * FROM animateur";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Animateur a = new Animateur();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setSalaire(rs.getInt(9));
                System.out.println("L'animateur numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());
                System.out.println("Salaire = " + a.getSalaire());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SupprimerAnimateur(int id) {

        try {
            String requete = " DELETE FROM animateur where idAnimateur = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Animateur DELETED");
    }
    
    public void modifierAnimateur(int id, String nom, String prenom,String mail,String tel,String mdp, String dateNaiss, String sexe, int salaire) {

        String requete5 = "UPDATE animateur SET nom = ?, prenom = ? ,mail = ?, tel = ?, mdp = ?, dateNaiss = ?, sexe=?, salaire=?  WHERE idAnimateur = ?";

        try {

            PreparedStatement pst2 = cn2.prepareStatement(requete5);
            pst2.setString(1, nom);
            pst2.setString(2, prenom); 
            pst2.setString(3, mail);
            pst2.setString(4, tel);
            pst2.setString(5, mdp);
            pst2.setString(6, dateNaiss);
            pst2.setString(7, sexe);
            pst2.setInt(8, salaire);
            pst2.setInt(9, id);
            pst2.executeUpdate();
            System.out.println("Animateur updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherAnimateur(String besoin, String caractere) {

        String requete = "select * from animateur where " + besoin + " LIKE '" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Animateur a = new Animateur();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setSalaire(rs.getInt(9));
                System.out.println("L'animateur numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());
                System.out.println("Salaire = " + a.getSalaire());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void trierAnimateur(String o) {
        try {

            String requete2 = "SELECT * FROM animateur order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Animateur a = new Animateur();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setSalaire(rs.getInt(9));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
