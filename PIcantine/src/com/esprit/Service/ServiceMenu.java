/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Personne;
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
public class ServiceMenu implements IService<menu> {
 private Connection con;
    private Statement ste;

    public ServiceMenu() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(menu p) throws SQLException {
   PreparedStatement pre=con.prepareStatement("INSERT INTO `3a12`.`menu` ( `entre`, `plat`, `dessert`,`date`) VALUES ( ?, ?, ?,?);");
    pre.setString(1, p.getEntre());
    pre.setString(2, p.getPlat());
    pre.setString(3, p.getDessert());
    pre.setString(4, p.getDate());
    
    pre.executeUpdate();
    }

    @Override
    public boolean delete(menu t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("DELETE FROM `3a12`.`menu` WHERE id = ? ");
    pre.setInt(1, t.getId());
   
    
    pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(menu t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `3a12`.`menu` SET entre = ? , plat = ? , dessert = ? , date = ? WHERE id = ? ;");
         pre.setString(1, t.getEntre());
          pre.setString(2, t.getPlat());
           pre.setString(3, t.getDessert());
            pre.setString(4, t.getDate());
    pre.setInt(5, t.getId());
    
   
    pre.executeUpdate();
        return true;
    }

    @Override
    public List<menu> readAll() throws SQLException {
        List<menu> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from menu");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String entre=rs.getString("entre");
               String plat=rs.getString(3);
               String dessert=rs.getString("dessert");
               String date=rs.getString("date");
               
           menu p = new menu(id, entre, plat, dessert,date);
     arr.add(p);
     }
    return arr;
    }
    
    public void readOne(int id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("select * from menu WHERE id = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     while (rs.next()) {                
              System.out.println("id=" + rs.getString(1) );
               System.out.println("entre=" + rs.getString(2));
                System.out.println("plat=" + rs.getString(3));
                 System.out.println("dessert=" + rs.getString(4));
                 System.out.println("date=" + rs.getString(5));
     }
    
    }
    
    public void trierMenu(String o) {
        try {

            String requete2 = "select * from menu order by '" +o+"'";
            
            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                menu c = new menu();
                c.setId(rs.getInt(1));
                c.setEntre(rs.getString(2));
                c.setPlat(rs.getString(3));
               c.setDessert(rs.getString(4));
                c.setDate(rs.getString(5));
                
                
                System.out.println(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

