package edu.project.tests;

import edu.project.utils.PDFutil;
import edu.project.utils.TrayIconDemo;
import edu.project.entities.Activite;
import edu.project.entities.Enfant;
import edu.project.utils.JavamailUtil;
import edu.project.servises.ActiviteCrud;
import edu.project.servises.EnfantCrud;
import java.awt.SystemTray;


public class MainClass {
    
  public static void main(String[] args) throws Exception {
        ActiviteCrud Act = new ActiviteCrud();
        EnfantCrud Enf = new EnfantCrud();
        
    //CRUD Activite
      //Ajout
        //Activite A= new Activite("Alphabet anglais","Les 20 premières lettres","55","2020-08-04","E4-E1-E5-E2","A1");
        //Act.ajouterActivite(A);
        
      //Modif Act
        //Act.modifierActivite(28,"danse","salsa","30","2019-08-04","E4-E1-E5","A1-A7");
        
      //Suppression Act
        //Act.SupprimerActivite(51);
        
      //Affichage Act
        //Act.afficherActivite();
        
      //Recherche Act
        //Act.rechercherActivite("duree", "60");
        
      //Tri Act
        //Act.trierActivite("date");
        
    //CRUD Enfant
      //Ajout Enf
        //Enfant E= new Enfant("E80","eya","cherif","2020-02-05","F");
        //Enf.ajouterEnfant(E);
        
      //Modif Enf
        //Enf.modifierEnfant("E80","Abdelhak","Dimassi","1998-05-09","M");
        
      //Suppression Enf
        //Enf.SupprimerEnfant("E80");
        
      //Affichage Enf
        //Enf.afficher();
        
      //Recherche Enf
        //Enf.rechercherEnfant("nom", "bdel");
        
      //Tri Enf
        //Enf.trierEnfant("dateNaiss");
        
    //EMAIL
        //JavamailUtil mail = new JavamailUtil();
        //mail.sendMail("bestgoldennumber1@gmail.com");
        
    //PDF
        //PDFutil pdf=new PDFutil();
        //pdf.listActivite();
        }    
}
