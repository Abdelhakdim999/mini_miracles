/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Item_commController implements Initializable {
    
    private int id_comm;
    private String contenu;
    private String date;
    
    public Item_commController(int id_comm, String contenu, String date) {
        this.id_comm = id_comm;
        this.contenu = contenu;
        this.date = date;
    }

    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
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
    private Label dateLabel;
    @FXML
    private Label id_label;
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
        id_label.setText(String.valueOf(id_comm));
        contenuLabel.setText(contenu);
        dateLabel.setText(date);
    }

    @FXML
    private void supp_comm(ActionEvent event) {
        ServiceCommentaire act = new ServiceCommentaire();
        act.delete1(this.id_comm);
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
