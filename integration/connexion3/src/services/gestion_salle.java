/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import com.esprit.IService.IService;
import entities.Salle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import utils.Myconnection;
/**
 *
 * @author Darius
 */
public  class gestion_salle {
     private Connection cn2;
    private Statement st;

    public gestion_salle() {
        cn2 = Myconnection.getInstance().getCnx();


}

    public void ajouter(Salle s) throws SQLException {
        st = cn2.createStatement();
        String requeteInsert = "INSERT INTO salles (`nom_salle`,`capacite`) VALUES ('" + s.getNom_salle() +"','" + s.getCapacite() + "');";
        st.executeUpdate(requeteInsert);
    }
    public void update(Salle s,String nums) throws SQLException{
	st = cn2.createStatement();
	PreparedStatement pre=cn2.prepareStatement("update salles set num_salle= '"+s.getNom_salle()+"',capacite='"+s.getCapacite()+"' where num_salle=? ");
        pre.setString(1,nums);
    pre.executeUpdate();
        
	
}
       public boolean updateS(Salle S) throws SQLException {
        PreparedStatement pre=cn2.prepareStatement("UPDATE salles SET nom_salle = ?,capacite=? WHERE num_salle = ? ;");
         pre.setString(1, S.getNom_salle());
         pre.setString(2, S.getCapacite());
    pre.setInt(3, S.getNum_salle());
    pre.executeUpdate();
        return true;
    }
    
 
    public List<Salle> readAll() throws SQLException {
    List<Salle> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select * from salles ");
     while (rs.next()) {                
              int num_salle=rs.getInt(1);
               String nom_salle=rs.getString(2);
               String capacite=rs.getString(3);
               Salle s=new Salle(num_salle,nom_salle, capacite);
     arr.add(s);
     }
    return arr;
    }

     
    
   
    public List<Salle> orderread() throws SQLException {
    List<Salle> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select * from salles order by nom_salle");
     while (rs.next()) {    
                int num_salle=rs.getInt(1);
               String nom_salle=rs.getString(2);
               String capacite=rs.getString(3);
               
               Salle s=new Salle(nom_salle, capacite,num_salle);
     arr.add(s);
     }
    return arr;
    }
       public List<String> orderread2() throws SQLException {
    List<String> arr=new ArrayList<>();
    st=cn2.createStatement();
    ResultSet rs=st.executeQuery("select nom_salle from salles order by nom_salle");
     while (rs.next()) {    
               
               String nom_salle=rs.getString(1);
               
               
              
     arr.add(nom_salle);
     }
    return arr;
    }
 
    public List<Salle> recherchersalle(String num) throws SQLException{
        List<Salle> arr=new ArrayList<>();
          st=cn2.createStatement();
          ResultSet rs=st.executeQuery("select * from salles where nom_salle="+num );
      while (rs.next()) { 
            int num_salle=rs.getInt(1);
               String nom_salle=rs.getString(2);
               String capacite=rs.getString(3);
            
               Salle s=new Salle(nom_salle, capacite,num_salle);
     arr.add(s);
     }
    return arr;
    }    
        
}

