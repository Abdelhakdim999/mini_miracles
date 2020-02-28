/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Animateur;
import entities.Enfant;
import entities.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;
import utils.Myconnection;

/**
 *
 * @author Darius
 */
public class gestion_group {
    
     private Connection con;
    private Statement ste;
    
    
    public gestion_group(){
    con = DataBase.getInstance().getConnection();
    }
    
    public void ajouter(Group g) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO groupe (`id_group`,`nom_group`) VALUES (NULL, '" + g.getNom_group() + "');";
        ste.executeUpdate(requeteInsert);
    }
     public void delete(String nom) throws SQLException {
     ste = con.createStatement();
     PreparedStatement pre=con.prepareStatement("delete from groupe where  nom_group=?");
      pre.setString(1, nom);
    pre.executeUpdate();
    }
     
       public void Update(String first ,String nom) throws SQLException {
       ste = con.createStatement();
        String upd ="UPDATE group SET nom_group='"+nom+"' where nom_group='"+first+ "');";
    ste.executeUpdate(upd);
    } 
       
    public List<Group> readAll() throws SQLException {
    List<Group> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from groupe ");
     while (rs.next()) {                
               int id_group=rs.getInt(1);
               String nom_group=rs.getString(2);
               Group g=new Group(id_group,nom_group);
     arr.add(g);
     }
    return arr;
    }
     public List<Group> readNom() throws SQLException {
    List<Group> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_group from groupe ");
     while (rs.next()) {                
               
               String nom_group=rs.getString(1);
               Group g=new Group(nom_group);
                   System.out.println(g.getNom_group());
     arr.add(g);
     }
    return arr;
    }
       public List<String> readNom2() throws SQLException {
    List<String> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_group from groupe ");
     while (rs.next()) {                
               
               String nom_group=rs.getString(1);
              
     arr.add(nom_group);
     }
    return arr;
    }
 public List<Enfant> rechercherenfant(String nomg) throws SQLException{
        List<Enfant> arr=new ArrayList<>();
          ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select nom,prenom,dateNaiss,sexe from enfant where id_group=(select id_group from groupe where nom_group='"+nomg+"')");
      while (rs.next()) {                
               String nom=rs.getString(1);
               String prenom=rs.getString(2);
               String  dateNaiss=rs.getString(3);
                String  sexe=rs.getString(4);
               
               Enfant e=new Enfant(nom, prenom, dateNaiss, sexe);
     arr.add(e);
     }
    return arr;
    }    
 
     public List<Group> recherchergrp(String num) throws SQLException{
        List<Group> arr=new ArrayList<>();
          ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select nom_group from groupe where nom_group="+num );
      while (rs.next()) {                
               String nom_group=rs.getString(1);
            
               Group gs=new Group(nom_group);
     arr.add(gs);
     }
    return arr;
    }    
    public boolean update(Group t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE groupe SET nom_group = ? WHERE id_group = ? ;");
         pre.setString(1, t.getNom_group());
    pre.setInt(2, t.getId_group());
    pre.executeUpdate();
        return true;
    }
    
    public ArrayList<String> Nom_Anim() {
        ArrayList<String> Animlist = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM user INNER JOIN animateur ON user.id_user = animateur.id_user";
            PreparedStatement pst = con.prepareStatement(requete3);
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
}
