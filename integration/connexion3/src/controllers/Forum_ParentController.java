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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Forum_ParentController implements Initializable {
    Parent root;
    private int idparent;
    @FXML
    private JFXButton kids;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton forum;

    public Forum_ParentController(int idparent) {
        this.idparent = idparent;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }
    
    

    @FXML
    private JFXButton deconnecter;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton insc_cantine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 


    @FXML
    private void changeScreenButtonPuched(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Gestion_sujet_parentController enfCon = new Gestion_sujet_parentController(idparent);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gestion_sujet_parent.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void insc_cantine(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Parent_ajjoutinscantineController enfCon = new Parent_ajjoutinscantineController(idparent);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Parent_ajjoutinscantine.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Forum_ParentController enfCon = new Forum_ParentController(idparent);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Forum_Parent.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
@FXML
    private void deconnecter(MouseEvent event) {
        if (event.getSource() == deconnecter) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login_parent.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }
    
    @FXML
    private void kids(ActionEvent event) throws IOException {
        if (event.getSource() == kids) {
             Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Home_Parent_frontController enfCon = new Home_Parent_frontController(idparent);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home_Parent_front.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
    
}
