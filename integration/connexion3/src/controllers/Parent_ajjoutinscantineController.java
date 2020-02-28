/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.lowagie.text.DocumentException;
import edu.project.utils.pdfInscriptionCantine;
import entities.Enfant;
import entities.inscription_cantine;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.EnfantCrud;
import services.ParentCrud;
import services.ServiceInscriptionCantine;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Parent_ajjoutinscantineController implements Initializable {
    Parent root;
    int id;
     @FXML
    private JFXButton kids;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton forum;
    @FXML
    private JFXButton menu;
    @FXML
    private JFXButton insc_cantine;

    public Parent_ajjoutinscantineController(int id) {
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
    private ImageView refresh;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private TextField tnbrj;
    @FXML
    private TextField tpri;
    @FXML
    private Button baj;
    @FXML
    private DatePicker dateinsc;
    @FXML
    private TextField tid;
    @FXML
    private TableView<?> tabinsc;
    @FXML
    private TableColumn<?, ?> colenf;
    @FXML
    private TableColumn<?, ?> colnbj;
    @FXML
    private TableColumn<?, ?> colpri;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colnomenf;
    @FXML
    private TextField tnomenf;
    @FXML
    private TableView<?> tabenfant;
    @FXML
    private TableColumn<?, ?> coli;
    @FXML
    private TableColumn<?, ?> coln;
    @FXML
    private TableColumn<?, ?> colp;
    @FXML
    private TableColumn<?, ?> cola;
    @FXML
    private TextField idenf;
    @FXML
    private TableView<?> tabinsc1;
    @FXML
    private TableColumn<?, ?> colnomenf1;
    @FXML
    private TableColumn<?, ?> colenf1;
    @FXML
    private TableColumn<?, ?> colid1;
    @FXML
    private TableColumn<?, ?> coldate1;
    @FXML
    private TableColumn<?, ?> colnbj1;
    @FXML
    private TableColumn<?, ?> colpri1;
    @FXML
    private TextField idtttt;
ObservableList oblist = FXCollections.observableArrayList();
ObservableList oblist2 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try {
            
            
            
            
            
           
            
            
            
            
            
           /* try {
                // TODO
                
                
                ServicePersonne serp = new ServicePersonne();
                List<String> arr = serp.readAllEnfant3(6);
                
                combenf.setItems(FXCollections.observableArrayList(arr));
                
                //combenf.setItems(FXCollections.observableArrayList(arr));
            } catch (SQLException ex) {
                Logger.getLogger(AjoutinscController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            /* ArrayList<inscription_cantine> arrmen = (ArrayList<inscription_cantine>) serinsc.readAll();
            ObservableList obs = FXCollections.observableArrayList(arrmen);
            tabinsc.setItems(obs);
            colenf.setCellValueFactory(new PropertyValueFactory<>("id_enf"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("nbrjour"));
            colpri.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date_insc"));*/
            
            //ArrayList<inscription_cantine> arrmen = (ArrayList<inscription_cantine>) serinsc.readAll();
            ServiceInscriptionCantine serinsc= new ServiceInscriptionCantine();
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
            
            
            
            
            
            
            
            //////remplissage table view de enfant
            
            
            
            
            
             EnfantCrud serm= new EnfantCrud();
        ArrayList<Enfant> arrmn = (ArrayList<Enfant>) serm.listEnf_Parent(id);
            ObservableList obs2 = FXCollections.observableArrayList(arrmn);
            tabenfant.setItems(obs2);
            coli.setCellValueFactory(new PropertyValueFactory<>("id"));
            coln.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colp.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cola.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login_parent.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
    }

    @FXML
    private void refresh(MouseEvent event) {
    }

    @FXML
    private void inscrireenfant(ActionEvent event) {
         try {
            String e = idenf.getText();
            String p = tnbrj.getText();
            String d = tpri.getText();
            int iiid= Integer.parseInt(idenf.getText());
            int inbj= Integer.parseInt(tnbrj.getText());
            int ipr =Integer.parseInt(tpri.getText());
            
            String dat = dateinsc.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            ServiceInscriptionCantine serinsc= new ServiceInscriptionCantine();
            inscription_cantine in = new inscription_cantine(iiid, 1, inbj, ipr, dat);
            serinsc.ajouter(in);
            
            
             Enfant men2 = (Enfant) tabenfant.getSelectionModel().getSelectedItem();
            String i85 = Integer.toString(men2.getId());
            idtttt.setText(i85);
            ServiceInscriptionCantine sertyui = new ServiceInscriptionCantine();
            
            ObservableList obs1 = FXCollections.observableArrayList(sertyui.cherchertouteinscription1enfant(men2.getId()));
            tabinsc1.setItems(obs1);
            colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
            colenf1.setCellValueFactory(new PropertyValueFactory<>("idenf"));
            colnbj1.setCellValueFactory(new PropertyValueFactory<>("nbj"));
            colpri1.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate1.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
            colnomenf1.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  
    @FXML
    private void consultermenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Parent_listmenuController enfCon = new Parent_listmenuController(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Parent_listmenu.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void ddd(MouseEvent event) {
        int o = Integer.parseInt(tnbrj.getText())*2;
        String z = Integer.toString(o);
        tpri.setText(z);
    }

    @FXML
    private void ajinsc(ActionEvent event) {
         try {
            String e = idenf.getText();
            String p = tnbrj.getText();
            String d = tpri.getText();
            int iiid= Integer.parseInt(idenf.getText());
            int inbj= Integer.parseInt(tnbrj.getText());
            int ipr =Integer.parseInt(tpri.getText());
            
            String dat = dateinsc.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            ServiceInscriptionCantine serinsc= new ServiceInscriptionCantine();
            inscription_cantine in = new inscription_cantine(iiid, 1, inbj, ipr, dat);
            serinsc.ajouter(in);
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerinsc(ActionEvent event) {
         try {
            ServiceInscriptionCantine serinsc = new ServiceInscriptionCantine();
            int i = Integer.parseInt(tid.getText());
            serinsc.delete(i);
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void remplirleschamps(MouseEvent event) {
         inscription_cantine men = (inscription_cantine) tabinsc.getSelectionModel().getSelectedItem();
        String i = Integer.toString(men.getId());
        tid.setText(i);
        String i2 = Integer.toString(men.getNbj());
        tnbrj.setText(i2);
        String i3 = Float.toString(men.getPrix());
        tpri.setText(i3);
        String i4 = Float.toString(men.getIdenf());
       
        dateinsc.setPromptText(men.getDate_insc());
        tnomenf.setText(men.getNomenf());
    }

    @FXML
    private void remplirnomenfant(MouseEvent event) {
        try {
            Enfant en = (Enfant) tabenfant.getSelectionModel().getSelectedItem();
            String i = Integer.toString(en.getId());
            idenf.setText(i);
            String i2 = en.getNom()+ " "+ en.getPrenom();
            tnomenf.setText(i2);
            
            
            
            
            Enfant men2 = (Enfant) tabenfant.getSelectionModel().getSelectedItem();
            String i85 = Integer.toString(men2.getId());
            idtttt.setText(i85);
            ServiceInscriptionCantine sertyui = new ServiceInscriptionCantine();
            
            ObservableList obs1 = FXCollections.observableArrayList(sertyui.cherchertouteinscription1enfant(men2.getId()));
            tabinsc1.setItems(obs1);
            colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
            colenf1.setCellValueFactory(new PropertyValueFactory<>("idenf"));
            colnbj1.setCellValueFactory(new PropertyValueFactory<>("nbj"));
            colpri1.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coldate1.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
            colnomenf1.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void genererpdf(ActionEvent event) {
        try {
            inscription_cantine men = (inscription_cantine) tabinsc1.getSelectionModel().getSelectedItem();
            pdfInscriptionCantine pdf=new  pdfInscriptionCantine();
            pdf.pdfInscrcantine(men.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parent_ajjoutinscantineController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insc_cantine(ActionEvent event) throws IOException {
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
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Forum_ParentController enfCon = new Forum_ParentController(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Forum_Parent.fxml"));
                loader.setController(enfCon);
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

}
