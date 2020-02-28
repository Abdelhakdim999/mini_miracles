/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import utils.PdfEmploi;
import utils.Pdfgrp1;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.gestion_group;
import services.gestion_salle;
/**
 * FXML Controller class
 *
 * @author Darius
 */
public class AddController implements Initializable {

    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton annul;
    @FXML
    private JFXButton Nextbtn;
    @FXML
    private JFXTextField nomemp;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<?> cgroup;
    @FXML
    private JFXComboBox<?> csalle;
    @FXML
    private JFXCheckBox c1;
    @FXML
    private JFXCheckBox c2;
    @FXML
    private JFXCheckBox c3;
    @FXML
    private JFXCheckBox c4;
    @FXML
    private JFXCheckBox c5;
    @FXML
    private JFXCheckBox c6;
    @FXML
    private JFXCheckBox c7;
    @FXML
    private JFXCheckBox c8;
    @FXML
    private JFXCheckBox c9;
    @FXML
    private JFXCheckBox c10;
    @FXML
    private JFXCheckBox c11;
    @FXML
    private JFXCheckBox c12;
    @FXML
    private TextField t12;
    @FXML
    private TextField t11;
    @FXML
    private TextField t10;
    @FXML
    private TextField t9;
    @FXML
    private TextField t8;
    @FXML
    private TextField t7;
    @FXML
    private TextField t6;
    @FXML
    private TextField t5;
    @FXML
    private TextField t4;
    @FXML
    private TextField t3;
    @FXML
    private TextField t2;
    @FXML
    private TextField t1;
       @FXML
    private TextField tdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                      gestion_group ps = new gestion_group();
     
         try {
              ArrayList<String> pl = (ArrayList<String>) ps.readNom2();          
             ObservableList obs = FXCollections.observableArrayList(pl);
            cgroup.setItems(obs); 
      
    }
     catch (SQLException e) {
        }
                          gestion_salle gs = new gestion_salle();
     
         try {
              ArrayList<String> pl = (ArrayList<String>) gs.orderread2();          
             ObservableList obs = FXCollections.observableArrayList(pl);
            csalle.setItems(obs); 
      
    }
     catch (SQLException e) {
        }
    }    

    @FXML
    private void cancel(ActionEvent event) {
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
    private void nextaction(ActionEvent event) throws SQLException, DocumentException, IOException {
     if(c1.isSelected()){
         t1.setText("1");
     }
        if(c2.isSelected()){
         t2.setText("1");
     }
           if(c3.isSelected()){
         t3.setText("1");
     }
              if(c4.isSelected()){
         t4.setText("1");
     }
                 if(c5.isSelected()){
         t5.setText("1");
     }
                    if(c6.isSelected()){
         t6.setText("1");
     }
                       if(c7.isSelected()){
         t7.setText("1");
     }
                          if(c8.isSelected()){
         t8.setText("1");
     }
                          
                  if(c9.isSelected()){
         t9.setText("1");}
     
            if(c10.isSelected()){
         t10.setText("1");
     }
               if(c11.isSelected()){
         t11.setText("1");
     }
                  if(c12.isSelected()){
         t12.setText("1");
     }
     
      cgroup.getValue();
      csalle.getValue();
      nomemp.getText();
     tdate.setText(date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        PdfEmploi pdf=new  PdfEmploi();
       pdf.Pdfemp(cgroup.getValue().toString(),csalle.getValue().toString(),nomemp.getText(),tdate.getText(),t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText(),t8.getText(),t9.getText(),t10.getText(),t11.getText(),t12.getText()); 

      try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Emploi.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
        } 
    }
}
