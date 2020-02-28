/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;


/**
 *
 * @author Ammouna_Zikou
 */
public class Chef_cantine extends User{
    

    int idChef ;
    int salaire ;

    public Chef_cantine() {
    }

    public Chef_cantine(int salaire,String nom, String prenom, String mail, String tel, String mdp, String date_naissance, String sexe, int etat) {
        super(nom, prenom, mail, tel, mdp, date_naissance, sexe, etat);
        this.salaire = salaire;
    }

    public int getIdChef() {
        return idChef;
    }

    public void setIdChef(int idChef) {
        this.idChef = idChef;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }


    
    
}
