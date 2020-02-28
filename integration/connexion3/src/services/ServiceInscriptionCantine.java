/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.inscription_cantine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataBase;

/**
 *
 * @author User
 */
public class ServiceInscriptionCantine {

    private Connection con;
    private Statement ste;

    public ServiceInscriptionCantine() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouter(inscription_cantine t) throws SQLException {
        //  ste = con.createStatement();
        //String sql = "SELECT id FROM `enfant` WHERE id_parent='" + t.getEnf().getParent().getId()+ "' AND prenom='" + t.getEnf().getPrenom()+ "' " ;
        //ResultSet rs= ste.executeQuery(sql);
        //rs.absolute(1);
        //int idenf=rs.getInt(1);

        // ste2 = con.createStatement();
        //String sql = "SELECT id FROM `cantine` WHERE id='" + t.getCant().+ "' AND prenom='" + t.getEnf().getPrenom()+ "' " ;
        //ResultSet rs= ste2.executeQuery(sql);
        //rs.absolute(1);
        //int idenf=rs.getInt(1);
        PreparedStatement pre = con.prepareStatement("INSERT INTO `esprit3a12`.`inscription_cantine` (`id_enf`, `id_cant`, `nbrjour`,`prix`,`date_insc`) VALUES (?, ?, ?, ?, ?);");

        pre.setInt(1, t.getIdenf());
        pre.setInt(2, t.getIdcant());
        pre.setInt(3, t.getNbj());
        pre.setFloat(4, t.getPrix());
        pre.setString(5, t.getDate_insc());

        pre.executeUpdate();
        System.out.println("Inscription a la cantine réussit !!!!");
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `esprit3a12`.`inscription_cantine` WHERE id = ? ");
        pre.setInt(1, id);

        pre.executeUpdate();
        System.out.println("Suppression de l'inscription a la cantine réussit !!!!");
        return true;

    }

    public boolean update(int id, int nbj) throws SQLException {
        float x = nbj * 2;
        PreparedStatement pre = con.prepareStatement("UPDATE `esprit3a12`.`inscription_cantine` SET nbrjour = ? , prix = ? WHERE id = ? ;");
        pre.setInt(1, nbj);
        pre.setFloat(2, x);
        pre.setInt(3, id);

        pre.executeUpdate();
        System.out.println("Modification de l'inscription a la cantine réussit !!!!");
        return true;

    }

    public List<inscription_cantine> readAll() throws SQLException {
        List<inscription_cantine> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from inscription_cantine");
        while (rs.next()) {
            int id = rs.getInt(1);
            int ide = rs.getInt(2);
            int idc = rs.getInt(3);
            int nbj = rs.getInt(4);
            float pri = rs.getFloat(5);
            String dat = rs.getString(6);

            inscription_cantine p = new inscription_cantine(id, ide, idc, nbj, pri, dat);
            arr.add(p);
        }
        return arr;
    }

    public inscription_cantine readOne(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("select * from inscription_cantine WHERE id = ? ;");

        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            /*System.out.println("id de l'inscription=" + rs.getInt(1) );
               System.out.println("heure ouverture" + rs.getString(2));
                System.out.println("heure fermeture" + rs.getString(3));
                 System.out.println("capacite" + rs.getInt(4));*/
            inscription_cantine p = new inscription_cantine(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));

            return p;
        }
        return null;

    }

    public List<inscription_cantine> cherchertouteinscription1enfant(int idenfant) throws SQLException {
        List<inscription_cantine> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from inscription_cantine where id_enf='" + idenfant + "' order by date_insc");
        while (rs.next()) {
            int id = rs.getInt(1);
            int ide = rs.getInt(2);
            int idc = rs.getInt(3);
            int nbj = rs.getInt(4);
            float pri = rs.getFloat(5);
            String dat = rs.getString(6);

            inscription_cantine p = new inscription_cantine(id, ide, idc, nbj, pri, dat);
            arr.add(p);
        }
        return arr;
    }

    public void chercherderniereinscri(int idenfant) {
        String requete = "select * from inscription_cantine where id_enf='" + idenfant + "'";

        try {
            Statement st = con.createStatement();
            st.executeQuery(requete);
            ResultSet rs = st.executeQuery(requete);
            rs.last();
            int nbRow = rs.getRow();
            if (nbRow != 0) {
                System.out.println("Inscription cantine trouvé !!!!!!");
                System.out.println(this.readOne(rs.getInt(1)));
            } else {
                System.out.println("Inscrption cantine non trouvÃ©e !!!!!");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceInscriptionCantine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void affichertoutlesenfantsinscri(int idcan) {
        try {

            String requete2 = "select * from inscription_cantine where id_cant='" + idcan + "' group by id_enf";

            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                EnfantCrud sek = new EnfantCrud();
                System.out.println(sek.readOne(rs.getInt(2)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    public List<String> affichtsinscri() {
        try {

            String requete2 = "select enfant.nom,enfant.prenom from inscription_cantine,enfant where inscription_cantine.id_enf=enfant.id";
            List<String> arr = new ArrayList<>();
            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String nom = rs.getString(1);
                String prenom = rs.getString(2);
                String nm = nom + " " + prenom;
                arr.add(nm);

            }
            return arr;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public void trierInscription(String o) {
        try {

            String requete2 = "select * from inscription_cantine order by '" + o + "'";

            PreparedStatement pst = con.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                inscription_cantine can = new inscription_cantine();
                can.setId(rs.getInt(1));
                can.setIdenf(rs.getInt(2));
                can.setIdcant(rs.getInt(3));
                can.setPrix(rs.getFloat(5));
                can.setNbj(rs.getInt(4));

                System.out.println(can);
                EnfantCrud sek = new EnfantCrud();
                System.out.println(sek.readOne(rs.getInt(2)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
