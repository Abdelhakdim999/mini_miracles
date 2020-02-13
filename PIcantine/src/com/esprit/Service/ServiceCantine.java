/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.cantine;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ServiceCantine implements IService<cantine>{
private Connection con;
    private Statement ste;

    public ServiceCantine() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(cantine p) throws SQLException {
       PreparedStatement pre=con.prepareStatement("INSERT INTO `3a12`.`cantine` ( `heurouv`, `heurferm`, `capacite`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getHeurdeb());
    pre.setString(2, p.getHeurfin());
    pre.setInt(3, p.getCapacite());
    
    
    pre.executeUpdate();
        System.out.println("ajout cantine reuissit");
    }

    @Override
    public boolean delete(cantine t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(cantine t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `3a12`.`cantine` SET heurouv = ? , heurferm = ? , capacite = ? WHERE id = ? ;");
         pre.setString(1, t.getHeurdeb());
          pre.setString(2, t.getHeurfin());
           pre.setInt(3, t.getCapacite());
            
          pre.setInt(5, t.getId());
    
   
    pre.executeUpdate();
        return true;
    }

    @Override
    public List<cantine> readAll() throws SQLException {
        List<cantine> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from cantine");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String hd=rs.getString(2);
               String hf=rs.getString(3);
               int cap=rs.getInt(4);
             
               
           cantine p = new cantine(id, hd, hf,cap);
     arr.add(p);
     }
    return arr;

    }
    
     public cantine readOne(int id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("select * from cantine WHERE id = ? ;");
  
    pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
     
     while (rs.next()) {                
              /*System.out.println("id=" + rs.getInt(1) );
               System.out.println("heure ouverture" + rs.getString(2));
                System.out.println("heure fermeture" + rs.getString(3));
                 System.out.println("capacite" + rs.getInt(4));*/
                 cantine p = new cantine(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return p; 
     }
        return null;
    
    }
     
    public void trierCantine(String o) {
        try {

            String requete2 = "select * from cantine order by '" +o+"'";
            
            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cantine c = new cantine();
                c.setId(rs.getInt(1));
                c.setHeurdeb(rs.getString(2));
                c.setHeurfin(rs.getString(3));
               
                c.setCapacite(rs.getInt(4));
                
                
                System.out.println(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    /* public void chercherCantine(String nom){
        String requete = "select * from evenement where nom='"+nom+"'";
        
        try {
            Statement st = con.createStatement();
            st.executeQuery(requete);
            ResultSet rs = st.executeQuery(requete);
            rs.last();
            int nbRow = rs.getRow();
            if(nbRow != 0){
                System.out.println("Evenement trouvÃ©e");
                System.out.println(this.getEvenement(rs.getInt(1)));
            }else{
                System.out.println("Evenement non trouvÃ©e");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
