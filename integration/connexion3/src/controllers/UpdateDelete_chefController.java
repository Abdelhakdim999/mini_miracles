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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class UpdateDelete_chefController implements Initializable {

    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton forum;
    @FXML
    private TextField contenuTextField;
    @FXML
    private Button modifierButton;
    @FXML
    private TextField id_sujetTextField;
    @FXML
    private Button search;
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
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login_chef.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void retourhome(ActionEvent event) {
         try {
              Node node = (Node) event.getSource();
              Stage stage = (Stage) node.getScene().getWindow();
              //stage.setMaximized(true);
              stage.close();
              Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Home_Chef.fxml")));
              stage.setScene(scene);
              stage.show();
          } catch (IOException ex) {
              Logger.getLogger(Forum_AdminController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        ServiceSujet act = new ServiceSujet();
        Sujet a = new Sujet();
        int savedValue = Integer.parseInt(id_sujetTextField.getText());
        a.setId_sujet(savedValue);
        a.setContenu(contenuTextField.getText());
      //  a.setDate(String.valueOf(date_picker.getValue()));
        act.update(a);
        try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/gestion_sujet_admin.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void search(ActionEvent event) {
        ServiceSujet svc = new ServiceSujet();
        Sujet s  = new Sujet();
        int savedValue = Integer.parseInt(id_sujetTextField.getText());
        s=svc.rechercherAct_id(savedValue);
        contenuTextField.setText(s.getContenu());
    }

    @FXML
    private void retourner(ActionEvent event) {
        try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/gestion_sujet_chef.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }
    
}
