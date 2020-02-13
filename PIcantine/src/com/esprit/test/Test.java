/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Personne;
import com.esprit.Entite.cantine;
import com.esprit.Entite.enfant;
import com.esprit.Entite.inscription_cantine;
import com.esprit.Entite.menu;
import com.esprit.Service.ServiceCantine;
import com.esprit.Service.ServiceEnfant;
import com.esprit.Service.ServiceInscriptionCantine;
import com.esprit.Service.ServiceMenu;
import com.esprit.Service.ServicePersonne;
import com.esprit.Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        ServicePersonne ser = new ServicePersonne();
        ServiceMenu sermenu = new ServiceMenu();
        ServiceEnfant seren = new ServiceEnfant();
        ServiceCantine sercant = new ServiceCantine();
        ServiceInscriptionCantine serinsc = new ServiceInscriptionCantine();

        Personne p1 = new Personne("sana", "ben fadhel", 10);
        Personne p2 = new Personne("Karray", "Gargouri", 10);
        Personne p3 = new Personne("dali", "kaim", 2);

        menu m = new menu("entre", "plat", " dessert");
        menu m2 = new menu(1, "roti", "salade", "bariouch", "2020/12/12");
        //sermenu.ajouter(m2);
        enfant f1 = new enfant("jeremy", "ariol", 6, p2);
        enfant f2 = new enfant("mostfa", "belil", 3, p1);
        enfant f3 = new enfant(25, "mars", "avril", 4, p3);

        
        //cantine c1 = new cantine("12:00","14:00", 80);
        
        
        cantine c2=new cantine(2,"12:00","14:00", 60);
        inscription_cantine insc1 = new inscription_cantine(14, 2, 10,"2000-02-9");
        
        //ser.ajouterEnfant(f2);
        //sercant.ajouter(c1);
        //seren.ajouter(f1);
        System.out.println("*****************");
        System.out.println(serinsc.cherchertouteinscription1enfant(25));
        System.out.println("********************");
        //serinsc.chercherInscrp(14);
        //System.out.println( serinsc.cherchertouteinscriptionenfant(14));
        //System.out.println( serinsc.readOne(1));
        //serinsc.trierInscription("prix");
       /* try {
            serinsc.chercherInscrp(14);
            serinsc.ajouter(insc1);
          // serinsc.update(1, 20);
        /*    ser.update(p3);
            //sermenu.ajouter(m);
            // sermenu.update(m2);
            System.out.println("--------------------");
            List<menu> listm = sermenu.readAll();
            System.out.println(listm);
            sermenu.readOne(1);
            System.out.println("--------------------");
//         
            System.out.println("--------------------");
           // ser.ajouterEnfant(f1);
            List<enfant> listen = seren.readAll();
            System.out.println(listen);
            System.out.println("--------------------");

            // ser.ajouter1(p2);
             
            //ser.ajouter(p5);
            // ser.delete(p1);
            System.out.println("--------------------");
            
            List<Personne> list = ser.readAll();
            System.out.println(list);
            Personne p4 = new Personne();
            ser.readOne(4);
            System.out.println("--------------------");*/
           

       // } catch (SQLException ex) {
           // System.out.println(ex);
        //}*/
    }

}
