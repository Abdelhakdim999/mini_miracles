/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Personne;
import com.esprit.Entite.enfant;
import com.esprit.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class ServicePersonne implements IService<Personne> {

    private Connection con;
    private Statement ste;

    public ServicePersonne() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Personne t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `3a12`.`personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAge() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Personne p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `3a12`.`personne` ( `nom`, `prenom`, `age`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getAge());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Personne t) throws SQLException {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `3a12`.`personne` WHERE nom = ? AND prenom = ?;");
    pre.setString(1, t.getNom());
    pre.setString(2, t.getPrenom());
    
    pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Personne t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `3a12`.`personne` SET age = ? WHERE nom = ? AND prenom = ?;");
    pre.setString(2, t.getNom());
    pre.setString(3, t.getPrenom());
    pre.setInt(1, t.getAge());
    pre.executeUpdate();
        return true;
    }

    @Override
    public List<Personne> readAll() throws SQLException {
    List<Personne> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from personne");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
     arr.add(p);
     }
    return arr;
    }
    
    
    public Personne readOne(int id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("select * from personne WHERE id = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     
     while (rs.next()) {                
            //  System.out.println("id=" + rs.getString(1) );
              // System.out.println("nom=" + rs.getString(2));
                //System.out.println("prenom=" + rs.getString(3));
                 //System.out.println("age=" + rs.getString(4));
                 Personne p = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return p; 
     }
        return null;
    
    }
    
      public void ajouterEnfant(enfant p) throws SQLException {
          
          
          ste = con.createStatement();
        String sql = "SELECT id FROM `personne` WHERE nom='" + p.getParent().getNom() + "' AND prenom='" + p.getParent().getPrenom() + "' " ;
        ResultSet rs= ste.executeQuery(sql);
        rs.absolute(1);
        int id=rs.getInt(1);
         PreparedStatement pre=con.prepareStatement("INSERT INTO `3a12`.`enfant` ( `nom`, `prenom`, `age`,`id_parent`) VALUES ( ?, ?, ?,?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getAge());
    pre.setInt(4, id);
    
    pre.executeUpdate();
          
          
    }
      
       public List<Personne> readAllEnfant() throws SQLException {
    List<Personne> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant e,personne p WHERE e.parent_id =p.id ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               
               Personne p=new Personne(id, nom, prenom, age);
     arr.add(p);
     }
    return arr;
    }
    
    
    
}
