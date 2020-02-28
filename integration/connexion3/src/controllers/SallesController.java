/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.jfoenix.controls.JFXButton;
import com.lowagie.text.DocumentException;
import entities.Group;
import entities.Salle;
import java.io.FileNotFoundException;
import services.gestion_group;
import services.gestion_salle;
import utils.PdfSalle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darius
 */
public class SallesController implements Initializable {

  @FXML
    private TableView<?> tablesalle;
    @FXML
    private TableColumn<Salle, String> num_salle;
    @FXML
    private TableColumn<Salle, String> capacite;
    @FXML
    private TextField numsalle;
     @FXML
    private TextField capsalle;
       @FXML
    private TextField searchs;
    @FXML
    private JFXButton activite;
    @FXML
    private TableColumn<?, ?> idsalle;
    @FXML
    private JFXButton returnbtns;
    @FXML
    private JFXButton imp;
    @FXML
    private JFXButton btnajouter;
    @FXML
    private JFXButton recherchebtn;
    @FXML
    private TextField idsal;
    @FXML
    private JFXButton Modifybtn;
    @FXML
    private JFXButton cantine;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton planning;
    @FXML
    private JFXButton utilisateur;
    @FXML
    private JFXButton forum;
    @FXML
    private JFXButton kids;
    @FXML
    private JFXButton deconnecter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         gestion_salle ps = new gestion_salle();
        try {
              ArrayList<Salle> pl = (ArrayList<Salle>) ps.readAll();
            ObservableList obs = FXCollections.observableArrayList(pl);
            tablesalle.setItems(obs);
             idsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
            num_salle.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
            capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            
        } catch (Exception e) {
        }
    }   
        @FXML
    private void AjouterSalle(ActionEvent event) throws SQLException {
         gestion_salle ps = new gestion_salle();
        
        Salle p = new Salle(numsalle.getText(), capsalle.getText());
        
        ps.ajouter(p);
           ArrayList<Salle> pl = (ArrayList<Salle>) ps.readAll();
        
               
            ObservableList obs = FXCollections.observableArrayList(pl);
            tablesalle.setItems(obs);
             idsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
            num_salle.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
            capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
           
        
    }
    @FXML
    private void searchsalle (ActionEvent event) throws SQLException {
         gestion_salle ps = new gestion_salle();
            
           ArrayList<Salle> pl = (ArrayList<Salle>) ps.recherchersalle(searchs.getText());
        
           ObservableList obs = FXCollections.observableArrayList(pl);
            tablesalle.setItems(obs);
             idsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
           num_salle.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
            capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
          
            
    }
    
@FXML
    private void returnbacks(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Planning.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
        }
    }
  @FXML
  private void print(ActionEvent event) throws SQLException, DocumentException, IOException, FileNotFoundException{
        PdfSalle pdf=new  PdfSalle();
      try {
          pdf.pdfsalle();
      } catch (com.itextpdf.text.DocumentException ex) {
          Logger.getLogger(SallesController.class.getName()).log(Level.SEVERE, null, ex);
      }
          
  }

    @FXML
    private void Searchsal(MouseEvent event) {
         
        Salle g=(Salle) tablesalle.getSelectionModel().getSelectedItem();
       String i=Integer.toString(g.getNum_salle());
       
       idsal.setText(i);
       numsalle.setText(g.getNom_salle());
        capsalle.setText(g.getCapacite());
        
        
    }



    @FXML
    private void updatesalle(ActionEvent event) throws SQLException { 
        
        
        int i = Integer.parseInt(idsal.getText());
            Salle S = new Salle(i,numsalle.getText(), capsalle.getText())  ;
            gestion_salle gs = new gestion_salle();
            gs.updateS(S);
            
             ArrayList<Salle> arrmen = (ArrayList<Salle>) gs.readAll();
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tablesalle.setItems(obs);
             idsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
              num_salle.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
            capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
           
            
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
    private void evenement(ActionEvent event) {
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
    }    


    

