/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Commentaire;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceCommentaire;
import services.ServiceSujet;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class ItemController implements Initializable {
    private int id;
    private String contenu;
    private String date;

    public ItemController(int id, String contenu, String date) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    @FXML
    private Label contenuLabel;
    @FXML
    private JFXButton aff_comm;
    @FXML
    private TextField comm_text;
    @FXML
    private JFXButton comm_button;
    @FXML
    private Label dateLabel;
    @FXML
    private Label id_sujetLabel;
    @FXML
    private JFXButton supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_item();
    }    
public void afficher_item(){
        id_sujetLabel.setText(String.valueOf(id));
        contenuLabel.setText(contenu);
        dateLabel.setText(date);
    }
    @FXML
    private void aff_com(ActionEvent event) {
        if (event.getSource() == aff_comm) {
            ServiceSujet act= new ServiceSujet();
            Sujet a= act.rechercherAct_id(this.id);
                try {
                    Aff_commentaireController actCon = new Aff_commentaireController(a.id_sujet);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Aff_commentaire.fxml"));
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

    @FXML
    private void comm_envoyer(ActionEvent event) throws SQLException {
        ServiceCommentaire svc =new ServiceCommentaire();
        Commentaire c =new Commentaire();
        c.setId_sujet(id);
       // c.setDate("2020-08-08");
        c.setContenu(comm_text.getText());
        svc.ajouter(c);
        comm_text.clear();
    }

    @FXML
    private void supp_comm(ActionEvent event) {
        ServiceSujet act = new ServiceSujet();
        act.delete1(this.id);
        try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Aff_Forum_Admin.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }
    
}
