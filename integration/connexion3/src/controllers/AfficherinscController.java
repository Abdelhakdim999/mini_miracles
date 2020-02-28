/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.inscription_cantine;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceInscriptionCantine;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherinscController implements Initializable {

    @FXML
    private Button baj;
    @FXML
    private TextField tid;
    @FXML
    private TableView<?> tabinsc;
    @FXML
    private TableColumn<?, ?> colnomenf;
    @FXML
    private TableColumn<?, ?> colnbj;
    @FXML
    private TableColumn<?, ?> colpri;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colenf;
    @FXML
    private TableColumn<?, ?> colid;
ObservableList oblist = FXCollections.observableArrayList();
    @FXML
    private JFXButton supprimerinscription;
    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton utilisateur;
    @FXML
    private JFXButton kids;
    @FXML
    private JFXButton cantine;
    @FXML
    private JFXButton planning;
    @FXML
    private JFXButton forum;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            ServiceInscriptionCantine serinsc= new ServiceInscriptionCantine();
            /* ArrayList<inscription_cantine> arrmen = (ArrayList<inscription_cantine>) serinsc.readAll();
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabinsc.setItems(obs);
            colenf.setCellValueFactory(new PropertyValueFactory<>("id_enf"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("nbrjour"));
            colpri.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date_insc"));*/
            
            //ArrayList<inscription_cantine> arrmen = (ArrayList<inscription_cantine>) serinsc.readAll();
            oblist.addAll(serinsc.readAll());
            ArrayList<String> listnomenf = (ArrayList<String>) serinsc.affichtsinscri();
            
            
            ObservableList obs = FXCollections.observableArrayList(listnomenf);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colenf.setCellValueFactory(new PropertyValueFactory<>("idenf"));
            colnbj.setCellValueFactory(new PropertyValueFactory<>("nbj"));
            colpri.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
            colnomenf.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
            tabinsc.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherinscController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void retourhome(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home_2.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherinscController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajinsc(ActionEvent event) {
    }

    @FXML
    private void supprimerinsc(ActionEvent event) {
        try {
            ServiceInscriptionCantine serinsc = new ServiceInscriptionCantine();
            int i = Integer.parseInt(tid.getText());
            serinsc.delete(i);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherinscController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void remplirleschamps(MouseEvent event) {
         inscription_cantine men = (inscription_cantine) tabinsc.getSelectionModel().getSelectedItem();
        String i = Integer.toString(men.getId());
        tid.setText(i);

    }

    @FXML
    private void supprimeruneinscription(ActionEvent event) {
        try {
            ServiceInscriptionCantine serinsc = new ServiceInscriptionCantine();
            int i = Integer.parseInt(tid.getText());
            serinsc.delete(i);
            
              ObservableList obs = FXCollections.observableArrayList(serinsc.readAll());
              tabinsc.setItems(obs);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colenf.setCellValueFactory(new PropertyValueFactory<>("idenf"));
            colnbj.setCellValueFactory(new PropertyValueFactory<>("nbj"));
            colpri.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
            colnomenf.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherinscController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void acitive(ActionEvent event) {
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
    private void cantine(ActionEvent event) {
        if (event.getSource() == cantine) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Afficherinsc.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void forum(ActionEvent event) {
        if (event.getSource() == forum) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Forum_Admin.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void planning(ActionEvent event) {
        if (event.getSource() == planning) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Planning.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void actitive(ActionEvent event) {
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
    private void utilisateurs(ActionEvent event) {
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