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
import static com.codename1.uikit.cleanmodern.BaseForm.usermail;
import com.mycompany.Entite.Enfant;
import com.mycompany.Entite.InscriptionPack;
import com.mycompany.Entite.Pack;
import com.mycompany.Service.EnfantService;
import com.mycompany.Service.InscriptionPackService;
import com.mycompany.Service.PackService;
import java.util.ArrayList;

/**
 *
 * @author Ammouna_Zikou
 */
public class AfficherInscriptions extends BaseForm{

    public AfficherInscriptions(Resources res) {super("Vos enfants:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Vos Inscriptions  ");
        setUIID("AfficherEnf");
        getContentPane().setScrollVisible(false);
        ArrayList<InscriptionPack> allEnfParent = InscriptionPackService.getInstance().getInscriptions();
        super.addSideMenu(res);
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(EnfantService.getInstance().getAllEnfParent(usermail).toString());
        add(sp);*/
        
        for (InscriptionPack c : allEnfParent) {
            addItem(c,allEnfParent,res);
        }
    }
    
    public void addItem(InscriptionPack enf,ArrayList<InscriptionPack> enfants,Resources res) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer();
        img.setImage(res.getImage("child.png"));
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label titre = new Label("Titre: "+enf.getId());
        Label enfLbl = new Label("enf: "+enf.getIdEnfant());
        Label enfNomLbl = new Label("Nom Enfant: "+enf.getNomEnfant());
        Label enfPrenomLbl = new Label("Prenom Enfant: "+enf.getPrenomEnfant());
        
        Label dateins = new Label("Date d'inscription :"+enf.getDateInsc());
//        Label description = new Label("Description: "+enf.getDescription());
//        Label type = new Label("Type: "+enf.getType());
//        Label datedu = new Label("DureeDu: "+enf.getDureeDu());
//        Label datea = new Label("DureeA: "+enf.getDureeA());
//        Label sexe = new Label("Sexe: "+enf.getSexe());
//        Label date = new Label("Date de naissance: "+enf.getDateNaiss());
        
        titre.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

//               Boolean y=Dialog.show("Pack", "" + titre.getText(), "Ok", "Ajouter inscription");
//                if(y==false){
//                    
//                }
                    
            }
        });

        Label space1 = new Label("             ");
        Label space2 = new Label("             ");
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(0xeeeeee);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        
        
       // C2.add(titre);
        //C2.add(enfLbl);
        C2.add(space1);
        C2.add(enfNomLbl);
        C2.add(enfPrenomLbl);
        C2.add(dateins);
        C2.add(space2);
        C2.add(separator);
//        C2.add(type);
//        C2.add(datedu);
//        C2.add(datea);
        
        C1.add(img);
        C1.add(C2);
      C1.setLeadComponent(titre);
      
        add(C1);
        
        refreshTheme();

    }
        
    }
    
    
    

