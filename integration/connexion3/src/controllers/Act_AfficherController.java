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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ActiviteCrud;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Act_AfficherController implements Initializable {

    @FXML
    private JFXButton activite;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView back_image;
    @FXML
    private JFXButton deconnecter;
    @FXML
    private ComboBox<String> searchbox;
    @FXML
    private ImageView searchbutton;
    @FXML
    private ComboBox<String> sortingbox;
    @FXML
    private ImageView soringbutton;
    @FXML
    private TextField searchtext;
    @FXML
    private ImageView refreshbutton;
    @FXML
    private JFXButton kids;
    @FXML
    private JFXButton utilisateur;
    @FXML
    private JFXButton cantine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String combo[] = 
                   { "id", "lib", "description", 
                                    "duree", "date" };
        searchbox.setItems(FXCollections.observableArrayList(combo));
        sortingbox.setItems(FXCollections.observableArrayList(combo));
        try {
            refreshNodes();
        } catch (IOException ex) {
            Logger.getLogger(Act_AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void refreshNodes() throws IOException
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[200];
        ActiviteCrud act= new ActiviteCrud();
        List<Activite> ActList = act.listAct();
        int i=0;
        for(Activite a :ActList)
        {   
            ActiviteController actCon = new ActiviteController(a.getId(), a.getLib(), a.getDescription(), a.getAnimateurs(), a.getEnfants(), a.getDate(), a.getDuree());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Activite.fxml"));
            loader.setController(actCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
        }  
    }
    
    @FXML
    private void search_click(MouseEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[200];
        ActiviteCrud act= new ActiviteCrud();
        List<Activite> ActList = act.rechercherAct_list(searchbox.getValue(), searchtext.getText());
        int i=0;
        for(Activite a :ActList)
        {   
            ActiviteController actCon = new ActiviteController(a.getId(), a.getLib(), a.getDescription(), a.getAnimateurs(), a.getEnfants(), a.getDate(), a.getDuree());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Activite.fxml"));
            loader.setController(actCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
        }  
    }

    @FXML
    private void sorting_click(MouseEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[200];
        ActiviteCrud act= new ActiviteCrud();
        List<Activite> ActList = act.trierAct_list(sortingbox.getValue());
        int i=0;
        for(Activite a :ActList)
        {   
            ActiviteController actCon = new ActiviteController(a.getId(), a.getLib(), a.getDescription(), a.getAnimateurs(), a.getEnfants(), a.getDate(), a.getDuree());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Activite.fxml"));
            loader.setController(actCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
        }  
    }
    
    @FXML
    private void refresh(MouseEvent event) throws IOException {
        searchtext.clear();
        refreshNodes();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
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
    private void deconnecter(MouseEvent event) {
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

    @FXML
    private void cantine(ActionEvent event) {
    }

    

    
    
}
