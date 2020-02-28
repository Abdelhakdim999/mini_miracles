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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceSujet;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Aff_Forum_ChefController implements Initializable {

    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton forum;
    @FXML
    private VBox pnl_scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refreshNodes();
        } catch (IOException ex) {
            Logger.getLogger(Aff_Forum_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void refreshNodes() throws IOException
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[200];
        ServiceSujet suj= new ServiceSujet();
        List<Sujet> ActList = suj.listAct();
        int i=0;
        for(Sujet a :ActList)
        {   
            ItemController actCon = new ItemController(a.getId_sujet(), a.getContenu(), a.getDate());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));
            loader.setController(actCon);
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
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    
}
