/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Activite;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ActiviteCrud;
import services.EnfantCrud;
import services.gestion_group;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Act_AjoutController implements Initializable {

    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton deconnecter;
    @FXML
    private ImageView back_image;
    @FXML
    private TextField lib;
    @FXML
    private TextField desc;
    @FXML
    private TextField duree;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> anim;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXButton kids;
    @FXML
    private ListView<String> leftListView;
    @FXML
    private ListView<String> rightListview;
    @FXML
    private JFXButton send_right;
    @FXML
    private JFXButton send_left;
    
    private ObservableList<String> available = FXCollections.observableArrayList();
    private ObservableList<String> selected = FXCollections.observableArrayList();
    @FXML
    private JFXButton utilisateur;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            gestion_group act = new gestion_group();
            ArrayList<String> enfants = (ArrayList<String>) act.readNom2();
            available.addAll(enfants);
            rightListview.setItems(selected);
            leftListView.setItems(available);
            ArrayList<String> anims = act.Nom_Anim();
            anim.setItems(FXCollections.observableArrayList(anims));
        } catch (SQLException ex) {
            Logger.getLogger(Act_AjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     @FXML
    private void send_right(ActionEvent event) {
        String potential = leftListView.getSelectionModel()
          .getSelectedItem();
      if (potential != null) {
        leftListView.getSelectionModel().clearSelection();
        available.remove(potential);
        selected.add(potential);
    }
    }
    
    @FXML
    private void send_left(ActionEvent event) {
        String s = rightListview.getSelectionModel().getSelectedItem();
      if (s != null) {
        rightListview.getSelectionModel().clearSelection();
        selected.remove(s);
        available.add(s);
    }
    }

    @FXML
    private void valider(ActionEvent event) throws AWTException {
        ActiviteCrud act = new ActiviteCrud();
        if (act.verifchamps(lib.getText(),desc.getText(),duree.getText())==true)
        {
        
        Activite a = new Activite();
        a.setLib(lib.getText());
        a.setDescription(desc.getText());
        int savedValue = Integer.parseInt(duree.getText());
        a.setDuree(savedValue);
        a.setDate(String.valueOf(date.getValue()));
        a.setEnfants(rightListview.getItems().stream()
                       .map(Object::toString)
                       .collect(Collectors.joining("-")));
        a.setAnimateurs(anim.getValue());
        act.ajouterActivite(a);
        try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_home.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }
    }

    @FXML
    private void back(ActionEvent event) {
        if (event.getSource() == back) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_home.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void back_image(MouseEvent event) {
        if (event.getSource() == back_image) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_home.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
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
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    

    @FXML
    private void kids_list(ActionEvent event) {
        if (event.getSource() == kids) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Enf_Afficher.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }
    /*ServicePersonne serp = new ServicePersonne();
            List<String> arr = serp.readAllEnfant3(6);
             
              combenf.setItems(FXCollections.observableArrayList(arr));*/

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void utilisateur(ActionEvent event) {
        if (event.getSource() == utilisateur) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/User_Home.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    

   
}
