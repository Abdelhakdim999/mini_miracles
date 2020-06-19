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
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.InscriptionPack;
import com.mycompany.Entite.Pack;
import com.mycompany.Service.InscriptionPackService;
import com.mycompany.Service.PackService;
import java.util.ArrayList;

/**
 *
 * @author Ammouna_Zikou
 */
public class AfficherInscriptionsOld extends BaseForm{
    
    public AfficherInscriptionsOld(Resources res) {
        /*
        super("Vos inscriptions:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Vos inscriptions : ");
        setUIID("AfficherInscriptions");
        getContentPane().setScrollVisible(false);
        ArrayList<InscriptionPack> allInscription = InscriptionPackService.getInstance().getInscriptions();
        super.addSideMenu(res);*/
        super("Vos inscriptions:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Vos inscriptions : ");
        setUIID("AfficherInscriptions");
        getContentPane().setScrollVisible(false);
        //ArrayList<InscriptionPack> allInscription = InscriptionPackService.getInstance().getInscriptions();
        ArrayList<Pack> allInscription = PackService.getInstance().getPacks();
        super.addSideMenu(res);
        //SpanLabel span = new SpanLabel(allInscription.toString());

        
        for (Pack c : allInscription) {
            /*String idTxt = String.valueOf(c.getId());
            Label lblId = new Label(idTxt);
            add(idTxt);*/
            System.out.println("foooooooor: "+c.getId());
            addItem(c, allInscription, res);
        }
        //add(span);
    }
    /*
     public void addItem(InscriptionPack insc,ArrayList<InscriptionPack> inscriptions,Resources res) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer();
        img.setImage(res.getImage("child.png"));
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label pack = new Label("Pack: "+insc.getIdPack());
        Label enfant = new Label("Enfant: "+insc.getIdEnfant());
        Label dateinsc = new Label("Date d'inscription: "+insc.getDateInsc());

        
        enfant.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Boolean y=Dialog.show("Pack", "" + pack.getText() + " \n Enfant : " + enfant.getText(), "Ok", "Annuler");
                if(y==false){
                    
                }
                    
            }
        });

        C2.add(pack);
        C2.add(enfant);
        C2.add(dateinsc);
 
        
        C1.add(img);
        C1.add(C2);
      C1.setLeadComponent(enfant);
      
        add(C1);
        refreshTheme();

    }*/
    
        public void addItem(Pack insc,ArrayList<Pack> inscriptions,Resources res) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer();
        img.setImage(res.getImage("child.png"));
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        String id = String.valueOf(insc.getId());
        Label titre = new Label(id);
        /*Label description = new Label("Description: "+enf.getDescription());
        Label type = new Label("Type: "+enf.getType());
        Label datedu = new Label("DureeDu: "+enf.getDureeDu());
        Label datea = new Label("DureeA: "+enf.getDureeA());*/
//        Label sexe = new Label("Sexe: "+enf.getSexe());
//        Label date = new Label("Date de naissance: "+enf.getDateNaiss());
        
       /* titre.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Boolean y=Dialog.show("Pack", "" + titre.getText() + " \n Description : " + description.getText(), "Ok", "Ajouter inscription");
                if(y==false){
                    
                }
                    
            }
        });*/

        C2.add(titre);
       /* C2.add(description);
        C2.add(type);
        C2.add(datedu);
        C2.add(datea);*/
        
       // C1.add(img);
        C1.add(C2);
      C1.setLeadComponent(titre);
      
        add(C1);
        refreshTheme();

    }
        


}
