/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;




/**
 *
 * @author Ammouna_Zikou
 */
public class Parent extends User{
    
    int idParent ;
    int nb_enfants ;


   

    public Parent() {
    }

    public Parent(int nb_enfants,String nom, String prenom, String mail, String tel, String mdp, String date_naissance, String sexe, int etat) {
        super(nom, prenom, mail, tel, mdp, date_naissance, sexe, etat);
        this.nb_enfants = nb_enfants;
    }

    public Parent(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getNb_enfants() {
        return nb_enfants;
    }

    public void setNb_enfants(int nb_enfants) {
        this.nb_enfants = nb_enfants;
    }
    
    






    
        
    }
 




 
    
    


    
    
    
    
    
    
    

