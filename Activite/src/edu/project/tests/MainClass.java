package edu.project.tests;

import edu.project.utils.PDFutil;
import edu.project.utils.TrayIconDemo;
import edu.project.entities.Activite;
import edu.project.entities.Enfant;
import edu.project.entities.JavamailUtil;
import edu.project.servises.ActiviteCrud;
import edu.project.servises.EnfantCrud;
import java.awt.SystemTray;


public class MainClass {
    
  public static void main(String[] args) throws Exception {
        ActiviteCrud Act = new ActiviteCrud();
        EnfantCrud Enf = new EnfantCrud();
        Activite A = new Activite();
        Activite A2= new Activite("musique","mozart","45","2020-08-04","E4-E1","A1");
        Enfant E= new Enfant("E80","eya","cherif","2020-02-05","F");
        //Enf.ajouterEnfant(E);
        //Enf.trierEnfant("dateNaiss");
        Enf.modifierEnfant("E1","Abdelhak","Dimassi","1998-05-09","M");
        Act.ajouterActivite(A2);
        Act.ajouterActivite2();
        //Act.modifierActivite(28,"danse","salsa","30","2019-08-04","E4-E1-E5","A1-A7");
        //Act.SupprimerActivite(28);
        //Act.afficherActivite();
        /*JavamailUtil mail = new JavamailUtil();
        mail.sendMail("bestgoldennumber1@gmail.com");*/
        //Act.rechercherActivite("duree", "60");
        //Act.trierActivite("date");
        /*PDFutil pdf=new PDFutil();
        pdf.FacturePdf();*/
        }    
}
