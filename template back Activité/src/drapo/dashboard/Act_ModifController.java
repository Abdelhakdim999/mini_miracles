/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Act_ModifController implements Initializable {

    
    
      @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton activite;
    
    private void handleButtonAction(MouseEvent event) {        
       refreshNodes();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         refreshNodes();
    }    
    
    private void refreshNodes()
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<2; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Activite.fxml"));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(Act_ModifController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
