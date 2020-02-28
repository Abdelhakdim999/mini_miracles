/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.esprit.IService.IService;
import entities.menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;

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
   PreparedStatement pre=con.prepareStatement("INSERT INTO `esprit3a12`.`menu` ( `entre`, `plat`, `dessert`,`date`) VALUES ( ?, ?, ?,?);");
    pre.setString(1, p.getEntre());
    pre.setString(2, p.getPlat());
    pre.setString(3, p.getDessert());
    pre.setString(4, p.getDate());
    
    pre.executeUpdate();
    }

    @Override
    public boolean delete(menu t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("DELETE FROM `esprit3a12`.`menu` WHERE id = ? ");
    pre.setInt(1, t.getId());
   
    
    pre.executeUpdate();
        return true;
    }
    
    
     public boolean delete2(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `esprit3a12`.`menu` WHERE id = ? ");
        pre.setInt(1, id);

        pre.executeUpdate();
        System.out.println("Suppression du menu réussit !!!!");
        return true;

    }

    @Override
    public boolean update(menu t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `esprit3a12`.`menu` SET entre = ? , plat = ? , dessert = ? , date = ? WHERE id = ? ;");
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
    
    public List<menu> trierMenu(String o) {
        try {
            List<menu> arr=new ArrayList<>();

            String requete2 = "select * from menu order by '" +o+"'";
            
            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
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

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return null;

    }
}

