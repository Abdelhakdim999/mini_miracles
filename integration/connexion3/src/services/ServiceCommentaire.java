package services;

import entities.Commentaire;
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

public class ServiceCommentaire  {

    private Connection con;
    private Statement ste;
    private int id_comm;
    public ServiceCommentaire() {
        con = DataBase.getInstance().getConnection();
    }


    public void ajouter(Commentaire t) throws SQLException {
        
        Date actuelle=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc=date;
        String requete1 = "INSERT INTO commentaire (id_sujet,contenu, date) VALUES (?,?,'"+dc+"')";
            PreparedStatement pst = con.prepareStatement(requete1);
            pst.setInt(1, t.getId_sujet());
            pst.setString(2, t.getContenu());
            
            
            pst.executeUpdate();
            System.out.println("commentaire ajouté !");    }

    //@Override
   /* public boolean delete(Commentaire t) throws SQLException {
try {
            if(id_comm != 0){
                
                PreparedStatement pstm = con.prepareStatement("delete from commentaire where id_comm=?");
                pstm.setInt(1, id_comm);
                int i = pstm.executeUpdate();
                System.out.println(i+"Data deleted succefully");
                
            }
            
        } catch (Exception e) {
        }return true;    }*/
    
    public void delete1(int id_comm) {
              try {
            if(id_comm != 0){
                
                PreparedStatement pstm = con.prepareStatement("delete from commentaire where id_comm=?");
                pstm.setInt(1, id_comm);
                int i = pstm.executeUpdate();
                System.out.println(i+"Data deleted succefully");
                
            }
            
        } catch (Exception e) {
        }
        
    }

    public boolean update(Commentaire t) throws SQLException {
String requete = "update commentaire set contenu=?,date=? where id_comm=?";
         if (t.getId_comm()!= 0) {
                PreparedStatement pst = con.prepareStatement(requete);
                pst.setString(1, t.getContenu());
                pst.setString(2, t.getDate());
                pst.setInt(3, t.getId_comm());
                

               pst.executeUpdate();
               

            }
         return true;    }    

public void chercher(String besoin, String caractere){
         
         ArrayList<Commentaire> commentaires = new ArrayList<>();
        String requete = "select * from commentaire where " + besoin + " LIKE '" + caractere + "%'";
        
        
        try {
            Statement st = con.createStatement();
            st.executeQuery(requete);
            ResultSet rs = st.executeQuery(requete);
            rs.last();
            int nbRow = rs.getRow();
            if(nbRow != 0){
                System.out.println("commentaire trouvé");
                System.out.println(this.getCommentaire(rs.getInt(1)));
            }else{
                System.out.println("commentaire non trouvé");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public Commentaire getCommentaire(int id_comm) {
        Commentaire commentaire = null;
        try {
            PreparedStatement pstm = con.prepareStatement("select * from commentaire where id_comm = ?");
            pstm.setInt(1, id_comm);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                commentaire = new Commentaire();
                commentaire.setId_comm(rs.getInt(1));
                commentaire.setContenu(rs.getString(2));
                commentaire.setDate(rs.getString(3));
                
            }
        } catch (Exception e) {
            
        }
        return commentaire;
    }

    public boolean delete(Commentaire t) throws SQLException {
      try {
            if(id_comm != 0){
                
                PreparedStatement pstm = con.prepareStatement("delete from commentaire where id_comm=?");
                pstm.setInt(1, id_comm);
                int i = pstm.executeUpdate();
                System.out.println(i+"Data deleted succefully");
                
            }
            
        } catch (Exception e) {
        }return true;
    }
    public List<Commentaire> listAct() {
        ArrayList<Commentaire> ActList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM commentaire";
            PreparedStatement pst = con.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Commentaire a = new Commentaire();

                a.setId_comm(rs.getInt(1));
                a.setContenu(rs.getString(3));
                a.setDate(rs.getString(4));
                ActList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActList;
    }
    
    public List<Commentaire> rechercherAct_id( int caractere) {
        ArrayList<Commentaire> ActList = new ArrayList<>();
        String requete = "select * from commentaire where id_sujet LIKE '%" + caractere + "%'";

        try {
            PreparedStatement pst2 = con.prepareStatement(requete);
            pst2.executeQuery();
            System.out.println("////recherche done");

            ResultSet rs = pst2.executeQuery();
            
            while (rs.next()) {
                Commentaire a = new Commentaire();
                a.setId_comm(rs.getInt(1));
                a.setId_sujet(rs.getInt(2));
                a.setContenu(rs.getString(3));
                a.setDate(rs.getString(4));
                ActList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActList;
    }

}
