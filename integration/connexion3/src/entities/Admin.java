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
public class Admin extends User{
    
    int idAdmin ;
    
    
    public Admin() {
    }

    public Admin(String nom, String prenom, String mail, String tel, String mdp, String date_naissance, String sexe, int etat) {
        super(nom, prenom, mail, tel, mdp, date_naissance, sexe, etat);
    }



    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }


    


    
}
