/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Enfant;
import com.mycompany.Service.EnfantService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dimassi Abdelhak
 */
public class AfficherEnf extends BaseForm{

    public AfficherEnf(Resources res) {
        super("Vos enfants:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Vos Enfant : ");
        setUIID("AfficherEnf");
        getContentPane().setScrollVisible(false);
        ArrayList<Enfant> allEnfParent = EnfantService.getInstance().getAllEnfParent(usermail);
        super.addSideMenu(res);
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(EnfantService.getInstance().getAllEnfParent(usermail).toString());
        add(sp);*/
        
        for (Enfant c : allEnfParent) {
            addItem(c,allEnfParent,res);
        }
    }
    
    public void addItem(Enfant enf,ArrayList<Enfant> enfants,Resources res) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer();
        img.setImage(res.getImage("child.png"));
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label nom = new Label("Nom et Prenom: "+enf.getNom());
        Label nom2 = new Label(enf.getNom());
        Label prenom = new Label(enf.getPrenom());
        Label sexe = new Label("Sexe: "+enf.getSexe());
        Label date = new Label("Date de naissance: "+enf.getDateNaiss());
        
        nom.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Boolean y=Dialog.show("Enfant", "Nom : " + nom2.getText() + " \n Prenom : " + prenom.getText(), "Ok", "Supprimer");
                if(y==false){
                    enfants.remove(enf);
                    removeAll();
                        for (Enfant c1 : enfants) {
                            addItem(c1,enfants,res);
                        }
                        showBack();
                }
                    
            }
        });

        C3.add(nom);
        C3.add(prenom);
        C2.add(C3);
        C2.add(sexe);
        C2.add(date);
        
        C1.add(img);
        C1.add(C2);
      C1.setLeadComponent(nom);
      
        add(C1);
        refreshTheme();

    }
    
}
