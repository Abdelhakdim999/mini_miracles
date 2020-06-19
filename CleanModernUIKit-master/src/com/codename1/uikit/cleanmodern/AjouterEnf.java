/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Enfant;
import com.mycompany.Service.EnfantService;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dimassi Abdelhak
 */
public class AjouterEnf extends BaseForm{
    
    private TextField nom = new TextField("", "Nom", 20, TextField.ANY);
    private TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
    RadioButton rb1 = new RadioButton("Feminin");
    RadioButton rb2 = new RadioButton("Masculin");
    Container contentsexe = new Container();
    
    private Picker date = new Picker();
        
    private boolean isFormEmpty()
    {
        return (nom.getText().equals("") || prenom.getText().equals(""))||(!rb1.isSelected()&&!rb2.isSelected());
    }
    
    public AjouterEnf(Resources res) {
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Enfant");
        setUIID("AjouterEnf");
        super.addSideMenu(res);
        
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        Button submit = new Button("Terminer");
        
        
        Label lnom= new Label();
        Label lprenom= new Label();
       Label ldate= new Label();
        
        
            new ButtonGroup(rb1, rb2);
            
        Container content = BoxLayout.encloseY(
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                rb1,rb2,
                createLineSeparator(),
                date,
                createLineSeparator(),
                submit
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        submit.requestFocus();
        
        
                        
        
        submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String sexe="";
            if(isFormEmpty()){
                Dialog.show("Alert", "Saisir les attributs manquants!!", new Command("OK"));
            }
            else{
                if(rb1.isSelected()){
                    sexe="F";
                }
                if(rb2.isSelected()){
                    sexe="M";
                }
                Date d = date.getDate();
                
                Calendar c = Calendar.getInstance();
                
                c.setTime(d);
                
                String m1 = Integer.toString(c.get(Calendar.MONTH) + 1);
                String y1 = Integer.toString(c.get(Calendar.YEAR));
                String d1 = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
                
                if(d1.length()==1){
                    d1='0'+d1;
                }
                if(m1.length()==1){
                    m1='0'+m1;
                }
                
                String datefinal= y1+"-"+m1+"-"+d1;
                
                Enfant t = new Enfant(nom.getText(), prenom.getText(),sexe,datefinal,usermail);
                System.out.println(t);
                try {
                if( EnfantService.getInstance().addEnfant(t))
                            Dialog.show("Success","Votre enfant est ajouté avec succee",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                new AfficherEnf(res).show();
            }
        }
    });
    }

    
}
    
    
