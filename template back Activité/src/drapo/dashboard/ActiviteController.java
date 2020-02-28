/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import edu.project.entities.Activite;
import com.jfoenix.controls.JFXButton;
import edu.project.servises.ActiviteCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class ActiviteController implements Initializable {

    @FXML
    private JFXButton supp;
    @FXML
    private JFXButton modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void supprimer_act(ActionEvent event,Activite a) {
        ActiviteCrud act = new ActiviteCrud();
        act.SupprimerActivite(a.getId());
    }

    @FXML
    private void modif_act(ActionEvent event) {
    }

    @FXML
    private void supprimer_act(ActionEvent event) {
    }
    
}
