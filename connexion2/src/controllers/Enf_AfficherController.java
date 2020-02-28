/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Enfant;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.EnfantCrud;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Enf_AfficherController implements Initializable {

    @FXML
    private JFXButton activite;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton deconnecter;
    @FXML
    private ComboBox<String> searchbox;
    @FXML
    private TextField searchtext;
    @FXML
    private ImageView searchbutton;
    @FXML
    private ComboBox<String> sortingbox;
    @FXML
    private ImageView soringbutton;
    @FXML
    private ImageView refreshbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String combo[] = 
                   { "nom", "prenom", "dateNaiss", 
                                    "sexe" };
        searchbox.setItems(FXCollections.observableArrayList(combo));
        sortingbox.setItems(FXCollections.observableArrayList(combo));
        try {
            refreshNodes();
        } catch (IOException ex) {
            Logger.getLogger(Enf_AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void refreshNodes() throws IOException
    {
        pnl_scroll.getChildren().clear();
        String nomparent="";
        Node [] nodes = new  Node[200];
        EnfantCrud enf= new EnfantCrud();
        List<Enfant> Enflist = enf.listEnf();
        int i=0;
        for(Enfant a :Enflist)
        {   nomparent=enf.Parent_Name_id(a.getIdParent());
            EnfantController enfCon = new EnfantController(a.getNom(), a.getPrenom(), a.getDateNaiss(), a.getSexe(),nomparent);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Enfant.fxml"));
            loader.setController(enfCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
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
    private void activite(ActionEvent event) {
        if (event.getSource() == activite) {
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
    private void search_click(MouseEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        String nomparent="";
        Node [] nodes = new  Node[200];
        EnfantCrud enf= new EnfantCrud();
        List<Enfant> ActList = enf.rechercherEnf_list(searchbox.getValue(), searchtext.getText());
        int i=0;
        for(Enfant a :ActList)
        {   nomparent=enf.Parent_Name_id(a.getIdParent());
            EnfantController enfCon = new EnfantController(a.getNom(), a.getPrenom(), a.getDateNaiss(), a.getSexe(),nomparent);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Enfant.fxml"));
            loader.setController(enfCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
        }
    }

    @FXML
    private void sorting_click(MouseEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        String nomparent="";
        Node [] nodes = new  Node[200];
        EnfantCrud enf= new EnfantCrud();
        List<Enfant> ActList = enf.trierEnf_list(sortingbox.getValue());
        int i=0;
        for(Enfant a :ActList)
        {   nomparent=enf.Parent_Name_id(3);
            EnfantController enfCon = new EnfantController(a.getNom(), a.getPrenom(), a.getDateNaiss(), a.getSexe(),nomparent);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Enfant.fxml"));
            loader.setController(enfCon);
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

    
}
