/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Personne;
import com.esprit.Entite.enfant;
import com.esprit.Entite.menu;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ServiceEnfant implements IService<enfant>{
private Connection con;
    private Statement ste;

    public ServiceEnfant() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(enfant p) throws SQLException {
        
        
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

    @Override
    public boolean delete(enfant t) throws SQLException {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `3a12`.`enfant` WHERE id = ? ");
    pre.setInt(1, t.getId());
   
    
    pre.executeUpdate();
        return true;

    }

    @Override
    public boolean update(enfant t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<enfant> readAll() throws SQLException {
         List<enfant> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(4);
               String prenom=rs.getString(3);
               int age=rs.getInt(4);
               int idparent=rs.getInt(5);
            ServicePersonne serparent = new ServicePersonne();
           enfant p = new enfant(id, nom, prenom, age,serparent.readOne(rs.getInt(id)));
     arr.add(p);
     }
    return arr;
    }

      public enfant readOne(int id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("select * from enfant WHERE id = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     
     while (rs.next()) {                
              /*System.out.println("id de l'inscription=" + rs.getInt(1) );
               System.out.println("heure ouverture" + rs.getString(2));
                System.out.println("heure fermeture" + rs.getString(3));
                 System.out.println("capacite" + rs.getInt(4));*/
              ServicePersonne l = new ServicePersonne();
                 enfant p = new enfant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),l.readOne(rs.getInt(5)));
                 
                return p; 
     }
        return null;
    
    }
}
