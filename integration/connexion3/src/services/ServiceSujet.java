
package services;

import com.esprit.IService.IService;
import entities.Sujet;
import java.awt.SystemTray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataBase;

public class ServiceSujet {
     private Connection con;
    private Statement ste;
    private int id_sujet;

    public ServiceSujet() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouter(Sujet t) throws SQLException {
        
        Date actuelle=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc=date;
        
            String requete1 = "INSERT INTO sujet (contenu, date) VALUES (?,'"+dc+"')";
            PreparedStatement pst = con.prepareStatement(requete1);
            pst.setString(1, t.getContenu());
            
            pst.executeUpdate();
            System.out.println("sujet ajouté !");
            
         
                 
    
        
    }

    public boolean delete(Sujet t) throws SQLException {
     try {
            if(id_sujet != 0){
                
                PreparedStatement pstm = con.prepareStatement("delete from sujet where id_sujet=?");
                pstm.setInt(1, id_sujet);
                int i = pstm.executeUpdate();
                System.out.println(i+"Data deleted succefully");
                
            }
            
        } catch (Exception e) {
        }return true;}
     public void delete1(int id_sujet) {
              try {
            if(id_sujet != 0){
                
                PreparedStatement pstm = con.prepareStatement("delete from sujet where id_sujet=?");
                pstm.setInt(1, id_sujet);
                int i = pstm.executeUpdate();
                System.out.println(i+"Data deleted succefully");
                
            }
            
        } catch (Exception e) {
        }
        
    }

    public boolean update(Sujet t) throws SQLException {
        
         Date actuelle=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc=date;
        String requete = "update sujet set contenu=?,date='"+dc+"' where id_sujet=?";
         if (t.getId_sujet()!= 0) {
                PreparedStatement pst = con.prepareStatement(requete);
                pst.setString(1, t.getContenu());
                             //   pst.setString(2, t.getDate());

                pst.setInt(2, t.getId_sujet());
                

               pst.executeUpdate();
               

            }
         return true;    }
    public void chercher(String besoin, String caractere){
         
         ArrayList<Sujet> sujets = new ArrayList<>();
        String requete = "select * from sujet where " + besoin + " LIKE '" + caractere + "%'";
        
        
        try {
            Statement st = con.createStatement();
            st.executeQuery(requete);
            ResultSet rs = st.executeQuery(requete);
            rs.last();
            int nbRow = rs.getRow();
            if(nbRow != 0){
                System.out.println("sujet trouvé");
                System.out.println(this.getSujet(rs.getInt(1)));
            }else{
                System.out.println("sujet non trouvé");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public Sujet getSujet(int id_sujet) {
        Sujet sujet = null;
        try {
            PreparedStatement pstm = con.prepareStatement("select * from sujet where id_sujet = ?");
            pstm.setInt(1, id_sujet);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                sujet = new Sujet();
                sujet.setId_sujet(rs.getInt(1));
                sujet.setContenu(rs.getString(2));
                sujet.setDate(rs.getString(3));
                
            }
        } catch (Exception e) {
            
        }
        return sujet;
    }
     
     public List<Sujet> listAct() {
        ArrayList<Sujet> ActList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM sujet";
            PreparedStatement pst = con.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Sujet a = new Sujet();

                a.setId_sujet(rs.getInt(1));
                a.setContenu(rs.getString(2));
                a.setDate(rs.getString(3));
                ActList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActList;
    }
     
     public Sujet rechercherAct_id( int caractere) {
        Sujet a = new Sujet();
        String requete = "select * from sujet where id_sujet LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = con.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("recherche done");

            ResultSet rs = pst2.executeQuery();
            
            while (rs.next()) {
                a.setId_sujet(rs.getInt(1));
                a.setContenu(rs.getString(2));
                a.setDate(rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    

  
    
}
    

