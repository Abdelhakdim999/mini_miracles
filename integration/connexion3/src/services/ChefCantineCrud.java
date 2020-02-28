/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Chef_cantine;
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
 * @author Ammouna_Zikou
 */
public class ChefCantineCrud {
    
    Connection cn2;
    Statement st;

    public ChefCantineCrud() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    
    
    public void ajouterChef(Chef_cantine a) {
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
            String requete3 = "INSERT INTO chef_cantine (id_user,salaire) VALUES (?,?)";
            PreparedStatement pst3 = cn2.prepareStatement(requete3);
            pst3.setInt(1, lastid);
            pst3.setInt(2, a.getSalaire());
            pst3.executeUpdate();
           if (SystemTray.isSupported()) {
            TrayIconDemo1 td = new TrayIconDemo1();
                try {
                    td.trayAjout();
        
            System.out.println("Chef Cantine ajouté !");        } catch (AWTException ex) {
                    Logger.getLogger(ChefCantineCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
        }
            System.out.println("Chef Cantine ajouté !");
            
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
                    Logger.getLogger(ChefCantineCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void afficher() {

        try {
            String requete3 = "SELECT * FROM chef_cantine";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                Chef_cantine a = new Chef_cantine();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setSalaire(rs.getInt(9));
                System.out.println("Le chef cantine numero " + a.getId());
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

    public void SupprimerChef(int id) {

        try {
            String requete = " DELETE FROM chef_cantine where idChef = ? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.setInt(1, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Chef cantine DELETED");
    }
    
    public void modifierChef(int id, String nom, String prenom,String mail,String tel,String mdp, String dateNaiss, String sexe, int salaire) {

        String requete5 = "UPDATE chef_cantine SET nom = ?, prenom = ? ,mail = ?, tel = ?, mdp = ?, dateNaiss = ?, sexe=?, salaire=?  WHERE idChef = ?";

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
            System.out.println("Chef cantine updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechercherChef(String besoin, String caractere) {

        String requete = "select * from chef_cantine where " + besoin + " LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Chef_cantine a = new Chef_cantine();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setMail(rs.getString(4));
                a.setTel(rs.getString(5));             
                a.setMdp(rs.getString(6));               
                a.setDate_naissance(rs.getString(7));
                a.setSexe(rs.getString(8));
                a.setSalaire(rs.getInt(9));
                System.out.println("Le chef cantine numero " + a.getId());
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
    
    public void trierChef(String o) {
        try {

            String requete2 = "SELECT * FROM chef_cantine order by " + o;
            PreparedStatement pst = cn2.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Chef_cantine a = new Chef_cantine();
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
    
            public List<User> readAll() throws SQLException {
        List<User> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select * from user e,chef_cantine a WHERE e.id_user = a.id_user ");
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
              int salaire=rs.getInt(12);
               
           Chef_cantine p = new Chef_cantine(salaire, nom, prenom, mail, tel, mdp, date, sexe, etat);
           System.out.println(p);
     arr.add(p);
     }
    return arr;
    
    
}
    
}
