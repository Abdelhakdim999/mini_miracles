/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.menu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceMenu;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Home_ChefController implements Initializable {

    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton forum;
    @FXML
    private JFXButton dcced;
    @FXML
    private TextField tplat;
    @FXML
    private TextField tdessert;
    @FXML
    private TextField tentre;
    @FXML
    private Button bajmenu;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<?> tabmenu;
    @FXML
    private TableColumn<?, ?> cdat;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> centree;
    @FXML
    private TableColumn<?, ?> cplat;
    @FXML
    private TableColumn<?, ?> cdessert;
    @FXML
    private Button bsup;
    @FXML
    private TextField tid;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceMenu serm= new ServiceMenu();
        ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
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
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home_2.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forum(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Forum_Chef.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterlemenu(ActionEvent event) {
        try {
            String e = tentre.getText();
            String p = tplat.getText();
            String d = tdessert.getText();
            
            String dat = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            menu m =new menu(e, p, d, dat);
            ServiceMenu serm= new ServiceMenu();
            
            serm.ajouter(m);
            
            ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifiermenu(ActionEvent event) {
        try {
            int i = Integer.parseInt(tid.getText());
            String dat = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            menu men = new menu(i,tentre.getText(), tplat.getText(), tdessert.getText(), dat)  ;
            ServiceMenu serm = new ServiceMenu();
            serm.update(men);
            
            ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerunmenu(ActionEvent event) {
        try {
            ServiceMenu serm = new ServiceMenu();
            int i = Integer.parseInt(tid.getText());
            serm.delete2(i);
            
            
            
            
            
            ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutermenu(ActionEvent event) {
        try {
            String e = tentre.getText();
            String p = tplat.getText();
            String d = tdessert.getText();
            
            String dat = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            menu m =new menu(e, p, d, dat);
            ServiceMenu serm= new ServiceMenu();
            
            serm.ajouter(m);
            
            ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void remplirchampsmodif(MouseEvent event) {
        menu men = (menu) tabmenu.getSelectionModel().getSelectedItem();
        String i = Integer.toString(men.getId());
        tid.setText(i);
        tentre.setText(men.getEntre());
        tplat.setText(men.getPlat());
        tdessert.setText(men.getDessert());
        datepicker.setPromptText(men.getDate()); 
    }

    @FXML
    private void suppmen(ActionEvent event) {
        try {
            ServiceMenu serm = new ServiceMenu();
            int i = Integer.parseInt(tid.getText());
            serm.delete2(i);
            
            
            
            
            
             ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifmenu(ActionEvent event) {
        try {
            int i = Integer.parseInt(tid.getText());
            String dat = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            menu men = new menu(i,tentre.getText(), tplat.getText(), tdessert.getText(), dat)  ;
            ServiceMenu serm = new ServiceMenu();
            serm.update(men);
            
             ArrayList<menu> arrmen = (ArrayList<menu>) serm.trierMenu("date");
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabmenu.setItems(obs);
            cdat.setCellValueFactory(new PropertyValueFactory<>("date"));
            centree.setCellValueFactory(new PropertyValueFactory<>("entre"));
            cplat.setCellValueFactory(new PropertyValueFactory<>("plat"));
            cdessert.setCellValueFactory(new PropertyValueFactory<>("dessert"));
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Home_ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
