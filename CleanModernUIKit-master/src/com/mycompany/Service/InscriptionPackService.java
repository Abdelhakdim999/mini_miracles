/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.cleanmodern.CleanModern;
import com.mycompany.Entite.Date;
import com.mycompany.Entite.Enfant;
import com.mycompany.Entite.InscriptionPack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author Ammouna_Zikou
 */
public class InscriptionPackService {
    
    public ArrayList<InscriptionPack> inscriptions;
    public ArrayList<String> datesinsc;
        public Boolean reponse;
    public static InscriptionPackService instance=null;
    public boolean resultOK;
    private ConnectionRequest req,req2;
    
    private InscriptionPackService() {
         req = new ConnectionRequest();
         req2 = new ConnectionRequest();
    
    }
    
        public static InscriptionPackService getInstance() {
        if (instance == null) {
            instance = new InscriptionPackService();
        }
        return instance;
    }
//        public ArrayList<InscriptionPack> parseEvent(String jsonText) {
//            
//            
//        try {
//            inscriptions = new ArrayList<>();
//            JSONObject obj = new JSONObject(jsonText);
//            JSONArray arr = obj.getJSONArray("liste");
//            for (int i = 0; i < arr.length(); i++) {
//                InscriptionPack ch = new InscriptionPack();
//                ch.setId(arr.getJSONObject(i).getInt("idInscription"));
//               ch.setDateInsc(new Date(arr.getJSONObject(i).getJSONObject("dateInsc").getString("annee"),
//                arr.getJSONObject(i).getJSONObject("dateInsc").getString("mois"),
//                 arr.getJSONObject(i).getJSONObject("dateInsc").getString("jour"),
//                arr.getJSONObject(i).getJSONObject("dateInsc").getString("heure"),
//                 arr.getJSONObject(i).getJSONObject("dateInsc").getString("minute")));
//                 inscriptions.add(ch);
//                 }
//
//        } catch (JSONException ex) {
//            System.out.println("erreru");
//            System.out.println("mimi");
//            
//        }
//        return inscriptions;
//    }

    
    public Boolean addInscri(InscriptionPack i) {
        int idEnf = i.getIdEnfant();
        int idPack = i.getIdPack();

      String url = "http://localhost/evenements/web/app_dev.php/event/inscription_packMob/addInscriptionsMob?idEnfant="+idEnf+"&idPack="+idPack; // lzm tetrigel
     req.setUrl(url); //insertion de lurl de ntr demande de cnx
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONObject obj = new JSONObject(new String(req.getResponseData()));
                    if (obj.getJSONObject("requette").getString("reponse").equals("no")) {
                        CleanModern.messageGobal = obj.getJSONObject("requette").getString("message");
                        reponse = false;
                    } else {
                        reponse = true;
                    }
                } catch (JSONException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reponse;
    }
    
        public ArrayList<InscriptionPack> parseInscriptions(String jsonText){
           try {
            inscriptions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                InscriptionPack t = new InscriptionPack();
                 float id = Float.parseFloat(obj.get("idInscription").toString());
                 
                 Map<String,Object> enfantObj = (Map<String,Object>)obj.get("idEnfant");                
                 float idenfant = Float.parseFloat(enfantObj.get("id").toString());
                 String nomEnf = enfantObj.get("nom").toString();   
                 String prenomEnf = enfantObj.get("prenom").toString();
                 
                 
                 
                //int id = Integer.parseInt((String) obj.get("id"));
               t.setId((int)id);
               t.setIdEnfant((int)idenfant);
               t.setNomEnfant(nomEnf);
               t.setPrenomEnfant(prenomEnf);
            //    t.setIdEnfant((double) obj.get("enfant"));
           //     t.setIdPack((double) obj.get("pack"));
                inscriptions.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return inscriptions;
    }
        
        
        
//        public ArrayList<InscriptionPack> parseInscription(String jsonText){
//            try {
//            inscriptions=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj : list){
//                InscriptionPack t = new InscriptionPack();
//                //int id = Integer.parseInt((String) obj.get("id"));
//                t.setId((int) Float.parseFloat(obj.get("idInscription").toString()));
//                t.setIdEnfant((int) Float.parseFloat(obj.get("idEnfant").toString()));
//                t.setIdPack((int) Float.parseFloat(obj.get("idPack").toString()));
//                inscriptions.add(t);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return inscriptions;
//            
//}
        
        
        
        
           public ArrayList<String> parseDateInsc(String jsonText){
        try {
            datesinsc=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,String>> list = (List<Map<String,String>>)tasksListJson.get("root");
            for(Map<String,String> obj : list){
                String t = new String();
                t=obj.get("dates").toString();
                
                datesinsc.add(t);
            }
            
        } catch (IOException ex) {
            
        }
//        System.out.println("lbaaarrraaaaaa  "+dureesdu);
        return datesinsc;
    }
           
//    public ArrayList<InscriptionPack> getListEvent() {
//        String url = "http://localhost/evenements/web/app_dev.php/event/inscription_packMob/readInscriptionsMob22";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                inscriptions = parseEvent(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return inscriptions;
//    }
           
                  public ArrayList<InscriptionPack> getInscriptions(){
        String url = "http://localhost/evenements/web/app_dev.php/event/inscription_packMob/readInscriptionsMob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              //  inscriptions = parseInscriptions(new String(req.getResponseData()));
               inscriptions = parseInscriptions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        String url2 = "http://localhost/evenements/web/app_dev.php/event/inscription_packMob/readDatesinsc";
        req2.setUrl(url2);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt2) {
                datesinsc = parseDateInsc(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
                for (int i=0;i<inscriptions.size();i++){
//            System.out.println(dureesdu.get(i));
//            System.out.println(packs.get(i));
            inscriptions.get(i).setDateInsc(datesinsc.get(i));
        }
            }
        });
NetworkManager.getInstance().addToQueueAndWait(req2);
              System.out.println(inscriptions);
        return inscriptions;
               }
                 
                  
                  
                  
                  
}