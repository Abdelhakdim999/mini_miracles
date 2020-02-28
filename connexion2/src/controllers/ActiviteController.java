/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Activite;
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

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class ActiviteController implements Initializable {
    public int Id;
    public String Lib;
    public String Desc;
    public String Anim;
    public String Enf;
    public String Date;
    public int Duree;
    @FXML
    public JFXButton supp;
    @FXML
    public JFXButton modif;
    @FXML
    public Label lib;
    @FXML
    public Label desc;
    @FXML
    public Label enf;
    @FXML
    public Label anim;
    @FXML
    public Label date;
    @FXML
    public Label duree;
    @FXML
    public Label id;

    public ActiviteController(int Id, String Lib, String Desc, String Anim, String Enf, String Date, int Duree) {
        this.Id = Id;
        this.Lib = Lib;
        this.Desc = Desc;
        this.Anim = Anim;
        this.Enf = Enf;
        this.Date = Date;
        this.Duree = Duree;
    }
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getLib() {
        return Lib;
    }

    public void setLib(String Lib) {
        this.Lib = Lib;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_item();
    }    
    
    public void afficher_item(){
        id.setText(String.valueOf(Id));
        lib.setText(Lib);
        desc.setText(Desc);
        anim.setText(Anim);
        enf.setText(Enf);
        date.setText(Date);
        duree.setText(String.valueOf(Duree));
    }

    @FXML
    private void supprimer_act(ActionEvent event) {
        ActiviteCrud act = new ActiviteCrud();
        act.SupprimerActivite(this.Id);
        try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_Afficher.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        
    }

    @FXML
    private void modif_act(ActionEvent event) {
        if (event.getSource() == modif) {
            ActiviteCrud act= new ActiviteCrud();
            Activite a= act.rechercherAct_id(this.Id);
                try {
                    Act_ModifController actCon = new Act_ModifController(a.getId(), a.getLib(), a.getDescription(), a.getAnimateurs(), a.getEnfants(), a.getDate(), a.getDuree());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Act_Modif.fxml"));
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
