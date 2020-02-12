/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini_miracles;

import mini_miracles.entites.Admin;
import mini_miracles.entites.Animateur;
import mini_miracles.entites.Chef_cantine;
import mini_miracles.entites.JavamailUtil;
import mini_miracles.entites.Parent;
import services.AdminCrud;
import services.AnimateurCrud;
import services.ChefCantineCrud;
import services.ParentCrud;

/**
 *
 * @author Ammouna_Zikou
 */
public class Mini_miracles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
            //AnimateurCrud Anim = new AnimateurCrud();
            //Animateur a1= new Animateur("eya", "cherif ", "gg", "55", "mdp", "2020-20-20", "F", 5000);
            //Anim.ajouterAnimateur(a1);
            //Anim.modifierAnimateur(1, "eeyyaaa", "Cheriiiiiiffff", "gg", "55", "mdp", "2020-20-20", "F", 5000);
            //Anim.SupprimerAnimateur(1);
            
            ChefCantineCrud par1 = new ChefCantineCrud();
            Chef_cantine a2= new Chef_cantine("rim", "iii ", "ll", "66", "mdp", "13-06-2021", "M", 2);
            par1.ajouterChef(a2);
            AdminCrud par = new AdminCrud();
            Admin a1= new Admin("eya","iii ", "ll", "66", "mdp", "13-06-2021", "M");
            par.ajouterAdmin(a1);
            //Chef.rechercherChef("prenom", "iii");
            //Chef.modifierChef(1, "eeyyaaa", "Cheriiiiiiffff", "gg", "55", "mdp", "2020-20-20", "F", 5000);
            //Chef.SupprimerChef(1);
            JavamailUtil mail = new JavamailUtil();
            mail.sendMail("bestgoldennumber1@gmail.com");
    }
    
}
