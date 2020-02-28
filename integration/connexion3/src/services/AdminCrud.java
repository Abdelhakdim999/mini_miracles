/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.User;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FonctionsPartagees;
import utils.Myconnection;
import utils.TrayIconDemo1;

/**
 *
 * @author Ammouna_Zikou
 */
public class AdminCrud {
    
    Connection cn2;
    Statement st;

    public AdminCrud() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    
    
    public void ajouterAdmin(Admin a) {
if (FonctionsPartagees.verifierAdresseMail(a.getMail())  && FonctionsPartagees.verifierNumeroPhone(String.valueOf(a.getTel()))  ){
        try {
            int lastid = 0;
            String requete1 = "INSERT INTO user (nom , prenom, mail,tel,mdp,dateNaiss,sexe,etat) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete1);
            //pst.setInt(1, a.getId());
            pst.setString(1, a.getNom());
            pst.setString(2, a.getPrenom());
            pst.setString(3, a.getMail());
            pst.setString(4, a.getTel());
            pst.setString(5, a.getMdp());
            pst.setString(6, a.getDate_naissance());
            pst.setString(7, a.getSexe());
            pst.setInt(8, a.getEtat());
            pst.executeUpdate();
            String requete2 = "select last_insert_id() from user";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            ResultSet rs = pst2.executeQuery();
            while ( rs.next() )
            {
                lastid = rs.getInt("last_insert_id()");
            }
            //int lastid = rs.getInt("last_insert_id()");
            String requete3 = "INSERT INTO admin (id_user) VALUES (?)";
            PreparedStatement pst3 = cn2.prepareStatement(requete3);
            pst3.setInt(1, lastid);
            pst3.executeUpdate();

           if (SystemTray.isSupported()) {
            TrayIconDemo1 td = new TrayIconDemo1();
                try {
                    td.trayAjout();
        
            System.out.println("Admin ajouté !");        } catch (AWTException ex) {
                    Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
        }
            System.out.println("Admin ajouté !");
            
        } 
      
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
        
     else {System.out.println("verifier vos champs)");}
            if (SystemTray.isSupported()) {
            TrayIconDemo1 td = new TrayIconDemo1();
                try {
                    td.trayRefus();
                } catch (AWTException ex) {
                    Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void afficher() {

        try {
            String requete3 = "SELECT * FROM admin";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Admin a = new Admin();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                System.out.println("L'admin numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SupprimerAdmin(int id) {

        try {
            String requete = " DELETE FROM admin where idAdmin = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Admin DELETED");
    }
    
    public void modifierAdmin(int id, String nom, String prenom,String mail,String tel,String mdp, String dateNaiss, String sexe) {

        String requete5 = "UPDATE admin SET nom = ?, prenom = ? ,mail = ?, tel = ?, mdp = ?, dateNaiss = ?, sexe=? WHERE idAdmin = ?";

        try {

            PreparedStatement pst2 = cn2.prepareStatement(requete5);
            pst2.setString(1, nom);
            pst2.setString(2, prenom); 
            pst2.setString(3, mail);
            pst2.setString(4, tel);
            pst2.setString(5, mdp);
            pst2.setString(6, dateNaiss);
            pst2.setString(7, sexe);
            pst2.setInt(8, id);
            pst2.executeUpdate();
            System.out.println("Admin updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherAdmin(String besoin, String caractere) {

        String requete = "select * from admin where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                System.out.println("L'admin numero " + a.getId());
                System.out.println("Nom = " + a.getNom());
                System.out.println("Prenom = " + a.getPrenom());
                System.out.println("Mail = " + a.getMail());
                System.out.println("Tel = " + a.getTel());
                System.out.println("Mot de passe = " + a.getMdp());
                System.out.println("Date de naissance " + a.getDate_naissance());
                System.out.println("Sexe = " + a.getSexe());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void trierAdmin(String o) {
        try {

            String requete2 = "SELECT * FROM admin order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
       public List<User> readAll() throws SQLException {
        List<User> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select * from user e,admin a WHERE e.id_user = a.id_user ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               String prenom=rs.getString(3);
               String mail=rs.getString(4);
               String tel=rs.getString(5);
               String mdp=rs.getString(6);
               String date=rs.getString(7);
               String sexe=rs.getString(8);
               int etat=rs.getInt(9);
               
           User p = new User(nom,prenom,mail,tel,mdp,date,sexe,etat);
     arr.add(p);
     }
    return arr;
    
    
}
    
}
