/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Activite;
import entities.Enfant;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ActiviteCrud;
import services.EnfantCrud;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Enfant_ParentController implements Initializable {
    Parent root;
    public int id;
    public String Nom;
    public String Prenom;
    public String Date;
    public String Sexe;

    public Enfant_ParentController(int id,String Nom, String Prenom, String Date, String Sexe) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Date = Date;
        this.Sexe = Sexe;
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
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label sexe;
    @FXML
    private Label date;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXButton modif;

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
    }

    @FXML
    private void supprimer_act(ActionEvent event) throws IOException, AWTException {
        EnfantCrud act = new EnfantCrud();
        act.SupprimerEnfant(this.id);
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Home_Parent_frontController enfCon = new Home_Parent_frontController(2);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home_Parent_front.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void modif_act(ActionEvent event) {
        if (event.getSource() == modif) {
            EnfantCrud act= new EnfantCrud();
            Enfant a= act.rechercherEnf_id(this.id);
                try {
                    System.out.println(Sexe);
                    Enfant_ModifController actCon = new Enfant_ModifController(a.getId(), a.getNom(), a.getPrenom(),a.getDateNaiss(), a.getSexe());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Enfant_Modif.fxml"));
            loader.setController(actCon);
                        Parent root1 = (Parent) loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                } 
                catch(Exception e) {
                    e.printStackTrace();
                }
        }
    }
    
}
