/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceSujet;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class AjouterSujet_parentController implements Initializable {
    Parent root;
    private int idparent;

    public AjouterSujet_parentController(int idparent) {
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
    private JFXButton kids;
    @FXML
    private JFXButton insc_cantine;
    @FXML
    private JFXButton evenement;
    @FXML
    private TextField contenuTextField;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button ViderButton;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deconnecter(ActionEvent event) {
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
    private void insertData(ActionEvent event) throws SQLException, IOException {
        ServiceSujet svc = new ServiceSujet();
        Sujet s=new Sujet();
        s.setContenu(contenuTextField.getText());
        svc.ajouter(s);
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
    private void vider(ActionEvent event) {
        contenuTextField.clear();
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
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
    
}