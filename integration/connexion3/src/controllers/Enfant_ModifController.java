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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ActiviteCrud;
import services.EnfantCrud;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Enfant_ModifController implements Initializable {
    public int Id;
    public String nom;
    public String prenom;
    public String Date;
    public String Sexe;

    public Enfant_ModifController(int Id, String nom, String prenom, String Date, String Sexe) {
        this.Id = Id;
        this.nom = nom;
        this.prenom = prenom;
        this.Date = Date;
        this.Sexe = Sexe;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
    private Label lib_error;
    @FXML
    private Label desc_error;
    @FXML
    private Label anim_error;
    @FXML
    private Label date_error;
    @FXML
    private JFXButton valider;
    @FXML
    private TextField nom_text;
    @FXML
    private TextField prenom_text;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String combo[] = 
                   { "F", "M"};
        sexe.setItems(FXCollections.observableArrayList(combo));
        afficher_item();
    }    
    
    public void afficher_item(){
        nom_text.setText(nom);
        prenom_text.setText(prenom);
    }

    @FXML
    private void valider(ActionEvent event) throws AWTException {
        EnfantCrud act = new EnfantCrud();
        Enfant a = new Enfant();
        a.setNom(nom_text.getText());
        a.setPrenom(prenom_text.getText());
        a.setDateNaiss(String.valueOf(date.getValue()));
        a.setSexe(sexe.getValue());
        act.modifierEnfant(this.Id, a.getNom(), a.getPrenom(), a.getDateNaiss(),a.getSexe());
        if (event.getSource() == valider) {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            //Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_Afficher.fxml")));
            //stage.setScene(scene);
            //stage.show();

            }
    }
    
}
