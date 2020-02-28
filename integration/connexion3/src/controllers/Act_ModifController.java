/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Activite;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ActiviteCrud;
import services.EnfantCrud;
import services.gestion_group;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Act_ModifController implements Initializable {
    
    public int Id;
    public String Lib;
    public String Desc;
    public String Anim;
    public String Enf;
    public String Date;
    public int Duree;
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getAnim() {
        return Anim;
    }

    public void setAnim(String Anim) {
        this.Anim = Anim;
    }

    public String getEnf() {
        return Enf;
    }

    public void setEnf(String Enf) {
        this.Enf = Enf;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int Duree) {
        this.Duree = Duree;
    }

    public Act_ModifController(int Id, String Lib, String Desc, String Anim, String Enf, String Date, int Duree) {
        this.Id = Id;
        this.Lib = Lib;
        this.Desc = Desc;
        this.Anim = Anim;
        this.Enf = Enf;
        this.Date = Date;
        this.Duree = Duree;
    }

    @FXML
    private ComboBox<String> anim;
    @FXML
    private DatePicker date;
    @FXML
    private TextField duree;
    @FXML
    private TextField lib_text;
    @FXML
    private TextField desc_text;
    @FXML
    private Label enf;
    @FXML
    private Label id;
    @FXML
    private JFXButton valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestion_group act = new gestion_group();
        ArrayList<String> anims = act.Nom_Anim();
        anim.setItems(FXCollections.observableArrayList(anims));
        afficher_item();
    }    
    
    public void afficher_item(){
        id.setText(String.valueOf(Id));
        lib_text.setText(Lib);
        desc_text.setText(Desc);
        anim.setValue(Anim);
        enf.setText(Enf);
        duree.setText(String.valueOf(Duree));
        
    }

    @FXML
    private void valider(ActionEvent event) throws AWTException {
        ActiviteCrud act = new ActiviteCrud();
        Activite a = new Activite();
        a.setLib(lib_text.getText());
        a.setDescription(desc_text.getText());
        int savedValue = Integer.parseInt(duree.getText());
        a.setDuree(savedValue);
        a.setDate(String.valueOf(date.getValue()));
        a.setEnfants(Enf);
        a.setAnimateurs(anim.getValue());
        act.modifierActivite(Integer.parseInt(id.getText()), a.getLib(), a.getDescription(), a.getDuree(), a.getDate(), a.getEnfants(), a.getAnimateurs());
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
