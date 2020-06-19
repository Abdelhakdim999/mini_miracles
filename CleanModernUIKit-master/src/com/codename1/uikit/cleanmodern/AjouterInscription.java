/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
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
import java.util.List;

/**
 *
 * @author Ammouna_Zikou
 */
public class AjouterInscription extends BaseForm{

    String enfantNom;
    int enfantId;
    Button btnVal = new Button("S'inscrire");
    
    public AjouterInscription(Resources res,int idPack,String titrePack) {
       super("Vos enfants:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Inscription : ");
        setUIID("AfficherEnf");
        getContentPane().setScrollVisible(false);
   
        super.addSideMenu(res);
        
        
         ArrayList<Enfant> allEnfParent = EnfantService.getInstance().getAllEnfParent(usermail);
         System.out.println("liste enfant par parent");
         System.out.println(allEnfParent);
         String [] enfantNames = this.getAllEnfNames();
          Integer [] enfantIds = this.getAllEnfIds();
         
         /*
        for (int i = 0; i < allEnfParent.size(); i++) {
            System.out.println("enfant  : "+ allEnfParent.get(i).getNom());
            enfantNames[i] = allEnfParent.get(i).getNom();
        }
        
                for (int i = 0; i < allEnfParent.size(); i++) {
            enfantIds[i] = allEnfParent.get(i).getId();
        }*/
         
        MultiButton b = new MultiButton("enfants...");
        
        b.addActionListener(e -> {
    Dialog d = new Dialog();
    //d.setUIID("aide_Dialog");
    d.setDialogUIID("aide_Dialog");
    d.setDefaultBlurBackgroundRadius(8);
    //current.setTintColor(0);
    
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < enfantNames.length ; iter++) {
        int i = iter;
        MultiButton mb = new MultiButton(enfantNames[iter]);
        
        
        mb.addActionListener((evt) -> {
           enfantNom = mb.getTextLine1();
           enfantId = enfantIds[i];
        });
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            //b.setTextLine2(mb.getTextLine2());
            //b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    d.showPopupDialog(b);
});
        
        
        
        
        
        
        
        
        
            btnVal.addActionListener((evt) -> {
            System.out.println("aaaaaaaaaaa");
            System.out.println(enfantNom);
            System.out.println(enfantId);
            
            InscriptionPack dmnd = new InscriptionPack();
            /*dmnd.setTitre(tfTitre.getText());
            dmnd.setDescription(tfDesc.getText());
            dmnd.setIdCategorie(categorieId);
            dmnd.setIdUser(FLogIns_gui.userCon.getId());
            
            String photo =uploader.upload(imgp);
            dmnd.setPhoto(photo);
            
            
            dmnd.setNbReaction(0);
            dmnd.setEtat("valide");
            */
            
            dmnd.setIdPack(idPack);
            dmnd.setIdEnfant(enfantId);
            
            InscriptionPackService.getInstance().addInscri(dmnd);
            
            
            
            //new ListeDmndAide_gui().show();
            new AfficherInscriptions(res).show();
        });
    
        
        
        
        
        
        
        
        
        
        
        Label titreLbl = new Label(titrePack);
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         //Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         
         C1.add(titreLbl);
         C1.add(b);
         C1.add(btnVal);
  
        add(C1);
    }
    

    
        public String[] getAllEnfNames(){
        List<String> list = new ArrayList<>();
        String[] res;
    
        ArrayList<Enfant> allEnfParent = EnfantService.getInstance().getAllEnfParent(usermail);
              for (Enfant cat : allEnfParent) { 		      
           		list.add(cat.getNom());
      }
               res = list.toArray(new String[0]);
               return res;
    }
    
    
            public Integer[] getAllEnfIds(){
        List<Integer> list = new ArrayList<>();
        Integer[] res;
    
       ArrayList<Enfant> allEnfParent = EnfantService.getInstance().getAllEnfParent(usermail);
              for (Enfant cat : allEnfParent) { 		      
           		list.add(cat.getId());
      }
               res = list.toArray(new Integer[0]);
               return res;
    }
    
    
    
}
