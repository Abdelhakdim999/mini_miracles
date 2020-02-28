/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Aff_commentaireController implements Initializable {
private int id_sujet;
    @FXML
    private Label id;
    @FXML
    private VBox pnl_scroll;
public Aff_commentaireController(int id_sujet) {
        this.id_sujet = id_sujet;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        refreshNodes();
    } catch (IOException ex) {
        Logger.getLogger(Aff_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
    private void refreshNodes() throws IOException
    {
        id.setText(String.valueOf(id_sujet));
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[200];
        ServiceCommentaire suj= new ServiceCommentaire();
        List<Commentaire> ActList = suj.rechercherAct_id(id_sujet);
        int i=0;
        for(Commentaire a :ActList)
        {   
            Item_commController actCon = new Item_commController(a.getId_comm(), a.getContenu(), a.getDate());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item_comm.fxml"));
            loader.setController(actCon);
            nodes[i] = loader.load();
            pnl_scroll.getChildren().add(nodes[i]);
            i++;
           
        }  
    }
    
}
