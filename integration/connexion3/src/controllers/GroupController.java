/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.lowagie.text.DocumentException;
import entities.Enfant;
import entities.Group;
import java.io.FileNotFoundException;
import services.EnfantCrud;
import services.gestion_group;
import utils.Pdfgrp1;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darius
 */
public class GroupController implements Initializable {

  @FXML
    private TableView<?> tablekid;
    @FXML
    private TableColumn<Enfant, String> nom;
    @FXML
    private TableColumn<Enfant, String> prenom;
    @FXML
    private TableColumn<Enfant, String> datenaiss;
     @FXML
    private TableColumn<Enfant, String> sexe;
    @FXML
    private TextField nomgroup;
    @FXML
    private TableColumn<? ,?> nomg;
    @FXML
    private TableColumn<?,?>idgrp;
   @FXML
    private TableView<?> grouptable;
    @FXML
    private JFXButton returnbtng;
    @FXML
    private JFXButton printbtn;
    @FXML
    private JFXButton btnajouter;
    @FXML
    private JFXButton Supprimerbtn;
    
    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton Modifybtn;
    @FXML 
    private TextField idgroup;
    @FXML
    private JFXButton addkid;
    @FXML
    private JFXButton affichekid;
    @FXML
    private TextField nomkid;
    @FXML
    private TextField prenomkid;
    @FXML
    private JFXButton modifkids;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestion_group ps = new gestion_group();
     
         try {
              ArrayList<Group> pl = (ArrayList<Group>) ps.readAll();          
             ObservableList obs = FXCollections.observableArrayList(pl);
            grouptable.setItems(obs);
            nomg.setCellValueFactory(new PropertyValueFactory<>("nom_group"));
        
             idgrp.setCellValueFactory(new PropertyValueFactory<>("id_group"));
            
        } catch (SQLException e) {
        }
    }    
         @FXML
    private void AjouterGroup(ActionEvent event) throws SQLException {
         gestion_group ps = new gestion_group();
        
        Group p = new Group(nomgroup.getText());
        
        ps.ajouter(p);
           ArrayList<Group> pl = (ArrayList<Group>) ps.readAll();
        
               
            ObservableList obs = FXCollections.observableArrayList(pl);
           grouptable.setItems(obs);
            nomg.setCellValueFactory(new PropertyValueFactory<>("nom_group")); 
       

    }
//    private void Search(ActionEvent event) throws SQLException {
//         gestion_group ps = new gestion_group();
//            
//           ArrayList<Enfant> pl = (ArrayList<Enfant>) ps.rechercherenfant(searchg.getText());
//        
//             ObservableList obs = FXCollections.observableArrayList(pl);
//            tablekid.setItems(obs);
//            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//         datenaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss")); 
//         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 
//    }  
    @FXML
    private void returnbackg(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Planning.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
  @FXML
  private void printg(ActionEvent event) throws SQLException, DocumentException, IOException, FileNotFoundException{
    TablePosition tablePosition=grouptable.getSelectionModel().getSelectedCells().get(0);
        int row=tablePosition.getRow();
        Object item=grouptable.getItems().get(row);
         TableColumn tablecolumn=tablePosition.getTableColumn();
         String data=(String) tablecolumn.getCellObservableValue(item).getValue();
            gestion_group ps = new gestion_group();
            
      Pdfgrp1 pdf=new  Pdfgrp1();
      try { 
          pdf.pdfgrp(data);
      } catch (com.itextpdf.text.DocumentException ex) {
          Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

            @FXML
    private void SupprimerGroup(ActionEvent event) throws SQLException {
        TablePosition tablePosition=grouptable.getSelectionModel().getSelectedCells().get(0);
        int row=tablePosition.getRow();
        Object item=grouptable.getItems().get(row);
         TableColumn tablecolumn=tablePosition.getTableColumn();
         String data=(String) tablecolumn.getCellObservableValue(item).getValue();
            gestion_group ps = new gestion_group(); 
        ps.delete(data);
           ArrayList<Group> pl = (ArrayList<Group>) ps.readAll();
            ObservableList obs = FXCollections.observableArrayList(pl);
           grouptable.setItems(obs);
            nomg.setCellValueFactory(new PropertyValueFactory<>("nom_group")); 
       
    }

    @FXML
    private void Search(MouseEvent event) throws SQLException {
        
        Group g=(Group) grouptable.getSelectionModel().getSelectedItem();
        
       String i=Integer.toString(g.getId_group());
       
       idgroup.setText(i);
       nomgroup.setText(g.getNom_group());
       
               gestion_group ps = new gestion_group();
            
           ArrayList<Enfant> pl = (ArrayList<Enfant>) ps.rechercherenfant(nomgroup.getText());
        
             ObservableList obs = FXCollections.observableArrayList(pl);
            tablekid.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss")); 
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 
         
         
         
    }
    


    @FXML
    private void updategrp(ActionEvent event) throws SQLException {
        
            int i = Integer.parseInt(idgroup.getText());
            Group g = new Group(i,nomgroup.getText())  ;
            gestion_group gs = new gestion_group();
            gs.update(g);
            
             ArrayList<Group> arrmen = (ArrayList<Group>) gs.readAll();
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            grouptable.setItems(obs);
            idgrp.setCellValueFactory(new PropertyValueFactory<>("id_group"));
            nomg.setCellValueFactory(new PropertyValueFactory<>("nom_group"));
        
    }

    @FXML
    private void afficherkidsansgrp(ActionEvent event) {
                 EnfantCrud er = new EnfantCrud();
            
           ArrayList<Enfant> pl = (ArrayList<Enfant>) er.afficherEnfantsansgrp();
        
             ObservableList obs = FXCollections.observableArrayList(pl);
            tablekid.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss")); 
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 
         
    }

    @FXML
      private void SearchKid(MouseEvent event) throws SQLException {
        
        Enfant e=(Enfant) tablekid.getSelectionModel().getSelectedItem();
       nomkid.setText(e.getNom());
       prenomkid.setText(e.getPrenom());
          
            
        }
@FXML
private void updatekid(ActionEvent event) throws SQLException {
  
    int i = Integer.parseInt(idgroup.getText()); 
   Enfant e = new Enfant(i,nomkid.getText(), prenomkid.getText())  ;
            EnfantCrud ec= new EnfantCrud();
            ec.updatekids(e);
             gestion_group gs = new gestion_group();
              ArrayList<Enfant> pl = (ArrayList<Enfant>) gs.rechercherenfant(nomgroup.getText());
        
             ObservableList obs = FXCollections.observableArrayList(pl);
            tablekid.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss")); 
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 
}

    @FXML
    private void modifaction(ActionEvent event) {
        
          EnfantCrud er = new EnfantCrud();
            
           ArrayList<Enfant> pl = (ArrayList<Enfant>) er.afficherEnfant();
        
             ObservableList obs = FXCollections.observableArrayList(pl);
            tablekid.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss")); 
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));  
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        
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


}
    
    
//    @FXML
//    private void rechgrp(KeyEvent event) throws SQLException {
//     
//       
//               gestion_group ps = new gestion_group();
//               
//               gestion_group ps = new gestion_group();
//               ArrayList<Group> pl = (ArrayList<Group>) ps.recherchergrp(rechg.getText());
//               
//               ObservableList obs = FXCollections.observableArrayList(pl);
//               grouptable.setItems(obs);
//               nomg.setCellValueFactory(new PropertyValueFactory<>("nom_group"));
//               
//           }