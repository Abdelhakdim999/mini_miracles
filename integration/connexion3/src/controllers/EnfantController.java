/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class EnfantController implements Initializable {
    
    public String Nom;
    public String Prenom;
    public String Date;
    public String Sexe;
    public String Parent;
    @FXML
    private Label parent;

    public EnfantController(String Nom, String Prenom, String Date, String Sexe,String Parent) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Date = Date;
        this.Sexe = Sexe;
        this.Parent= Parent;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String Parent) {
        this.Parent = Parent;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }
    

    @FXML
    private Label date;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label sexe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_item();
    }    

    public void afficher_item(){
        nom.setText(String.valueOf(Nom));
        prenom.setText(Prenom);
        date.setText(Date);
        sexe.setText(Sexe);
        parent.setText(Parent);
    }
}
