/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.Animateur;
import entities.Chef_cantine;
import entities.Parent;
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
import utils.TrayIconDemo;
import utils.TrayIconDemo1;

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
    
    public Parent readOne(int id) throws SQLException {
    PreparedStatement pre=cn2.prepareStatement("select * from parent WHERE id = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     
     while (rs.next()) {                
            //  System.out.println("id=" + rs.getString(1) );
              // System.out.println("nom=" + rs.getString(2));
                //System.out.println("prenom=" + rs.getString(3));
                 //System.out.println("age=" + rs.getString(4));
                 Parent p = new Parent(rs.getInt(1), rs.getString(2), rs.getString(3));
                return p; 
     }
        return null;
    
    }
    
    public void ajouterParent(Parent a) {
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
            String requete3 = "INSERT INTO parent (id_user,nb_enfants) VALUES (?,?)";
            PreparedStatement pst3 = cn2.prepareStatement(requete3);
            pst3.setInt(1, lastid);
            pst3.setInt(2, a.getNb_enfants());
            pst3.executeUpdate();
           if (SystemTray.isSupported()) {
            TrayIconDemo1 td = new TrayIconDemo1();
                try {
                    td.trayAjout();
        
            System.out.println("Parent ajouté !");        } catch (AWTException ex) {
                    Logger.getLogger(ParentCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
        }
            System.out.println("Parent ajouté !");
            
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
                    Logger.getLogger(ParentCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
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

        String requete = "select * from parent where " + besoin + " LIKE '%" + caractere + "%'";

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
        /*public Parent readOne(int id) throws SQLException {
    PreparedStatement pre=cn2.prepareStatement("select * from parent WHERE idParent = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     
     while (rs.next()) {                
            //  System.out.println("id=" + rs.getString(1) );
              // System.out.println("nom=" + rs.getString(2));
                //System.out.println("prenom=" + rs.getString(3));
                 //System.out.println("age=" + rs.getString(4));
                 Parent p = new Parent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return p; 
     }
        return null;
    
    }*/
        
        
        
                         public List<User> readAll() throws SQLException {
        List<User> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select * from user e,parent a WHERE e.id_user = a.id_user ");
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
              int nb_enfants=rs.getInt(12);
               
           Parent p = new Parent(nb_enfants, nom, prenom, mail, tel, mdp, date, sexe, etat);
           System.out.println(p);
     arr.add(p);
     }
    return arr;
    
    
}
        
}

