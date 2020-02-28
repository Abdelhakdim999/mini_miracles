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
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceMenu;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Parent_listmenuController implements Initializable {
    Parent root;
    int id;

    public Parent_listmenuController(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton kids;
    @FXML
    private JFXButton insc_cantine;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton forum;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login_parent.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void kids(ActionEvent event) throws IOException {
        if (event.getSource() == kids) {
             Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Home_Parent_frontController enfCon = new Home_Parent_frontController(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home_Parent_front.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }

    @FXML
    private void insc_cantine(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void retourinscri(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Parent_ajjoutinscantineController enfCon = new Parent_ajjoutinscantineController(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Parent_ajjoutinscantine.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void remplirchampsmodif(MouseEvent event) {
    }
    
}
