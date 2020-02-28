/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Animateur;
import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Chef_cantine;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.AnimateurCrud;

public class Ajouter_AnimateurController implements Initializable {

    @FXML
    private JFXButton deconnecter;
    @FXML
    private ImageView back_image;
    @FXML
    private JFXButton back;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton sexe_m;
    @FXML
    private RadioButton sexe_f;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button val;
    @FXML
    private TextField sal;
    @FXML
    private JFXButton activite;
    @FXML
    private JFXButton kids;
    @FXML
    private ToggleGroup sexe;
    public int tt ;
    public static final String ACCOUNT_SID = "AC76d839725a7753acb7842059ab64e412";
    public static final String AUTH_TOKEN = "c0df5803737cce79ba1913956e428c79";
    @FXML
    private JFXButton cantine;
    @FXML
    private JFXButton planning;
    @FXML
    private JFXButton utilisateur;
    @FXML
    private JFXButton forum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void back_image(MouseEvent event) {
                                if (event.getSource() == back_image) {
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
    private void back(ActionEvent event) {
                                if (event.getSource() == back) {
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
    private void val(ActionEvent event) {
        
      AnimateurCrud ac = new AnimateurCrud();
        String txt1 = nom.getText();
        String txt2 = prenom.getText();
        String txt3 = mail.getText();
        String txt4 = tel.getText();
        String txt5 = mdp.getText();
        LocalDate dt = date.getValue();
        String dtt = dt.toString();
        String txt6 = sal.getText();

        
                      tt=Integer.parseInt(txt4);
        boolean rb = sexe_m.isSelected();
        
        Animateur anm = new Animateur();
        anm.setNom(txt1);
        anm.setPrenom(txt2);
        anm.setMail(txt3);
        anm.setTel(txt4);
        anm.setMdp(txt5);
        anm.setDate_naissance(dtt);
        int sal = Integer.parseInt(txt6);
        anm.setSalaire(sal);

        if (rb){
            anm.setSexe("M");
        }else{
            anm.setSexe("F");
        }
            sendSMS();
        ac.ajouterAnimateur(anm);
        
    }



    @FXML
    private void sexe_m(ActionEvent event) {
    }

    @FXML
    private void sexe_f(ActionEvent event) {
    }

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

    private void kids(ActionEvent event) {
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
       public void sendSMS(){
 

    
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
             .creator(new PhoneNumber("+216"+tt+""), // to
                                   //   .creator(new PhoneNumber("+21653955009"), // to

                        new PhoneNumber("+12058289098"), // from
                        "Bienvenue à Mini Miracles").create();
                

       System.out.println(message.getSid());
           
                                System.out.println("+216"+tt);


    
}

    @FXML
    private void acitive(ActionEvent event) {
    }

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
    private void kids_list(ActionEvent event) {
    }
    
    }
    

