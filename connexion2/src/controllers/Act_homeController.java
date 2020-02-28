/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.lowagie.text.DocumentException;
import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ActiviteCrud;
import utils.JavamailUtil;
import utils.Myconnection;
import utils.PDFutil;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class Act_homeController implements Initializable {
    Connection cn2;
    Statement st;
    
    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton affich;
    @FXML
    private JFXButton deconnecter;
    @FXML
    private JFXButton pdf;
    @FXML
    private JFXButton mail;
    @FXML
    private JFXButton tictactoc;
    private BarChart<String, Number> barchart;
    @FXML
    private JFXButton kids;
    @FXML
    private PieChart piechart;
    
    ObservableList < PieChart.Data > piechartdata;
 ArrayList < String > p = new ArrayList < String > ();
   ArrayList < Integer > c = new ArrayList < Integer > ();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
       
      /* XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
       dataSeries2.setName("Cours");
 
       dataSeries2.getData().add(new XYChart.Data<String, Number>("dance", 4));
       dataSeries2.getData().add(new XYChart.Data<String, Number>("musique", 7));
       dataSeries2.getData().add(new XYChart.Data<String, Number>("alphabet", 12));
 
       // Add Series to BarChart.
       barchart.getData().add(dataSeries2);*/
      loadData();           
  piechart.setData(piechartdata);
    }    
    
    public void loadData() {
     
    String query = "select * From activite "; //ORDER BY P asc
 
    piechartdata = FXCollections.observableArrayList();

      cn2 = Myconnection.getInstance().getCnx();
 
    try {
      
      ResultSet rs = cn2.createStatement().executeQuery(query);
  
      while (rs.next()) {
       
        piechartdata.add(new PieChart.Data(rs.getString("lib"), rs.getInt("duree")));
        p.add(rs.getString("lib"));
        c.add(rs.getInt("duree"));
      }
    } catch (SQLException e) {
  System.out.println(e.getMessage());
    }}

    @FXML
    private void ajouter_act(ActionEvent event) {
        if (event.getSource() == ajout) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_Ajout.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void afficher_act(ActionEvent event) {
        if (event.getSource() == affich) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Act_Afficher.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void deconnecter(MouseEvent event) {
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
    private void pdf(ActionEvent event) throws SQLException {
        
        PDFutil pdf=new PDFutil();
        try {
            pdf.listActivite();
        } catch (DocumentException ex) {
            Logger.getLogger(Act_homeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Act_homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void envoyer_mail(ActionEvent event) throws Exception {
        JavamailUtil mail = new JavamailUtil();
        mail.sendMail("bestgoldennumber1@gmail.com");

    }

    @FXML
    private void tictactoc(ActionEvent event) {
        if (event.getSource() == tictactoc) {
            //login here
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TicTacToc.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                } 
                catch(Exception e) {
                    e.printStackTrace();
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

    
}
