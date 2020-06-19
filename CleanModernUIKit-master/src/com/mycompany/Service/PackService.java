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
import com.mycompany.Entite.Enfant;
import com.mycompany.Entite.Pack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ammouna_Zikou
 */
public class PackService {
     public ArrayList<Pack> packs;
    public ArrayList<String> dureesdu;
    public ArrayList<String> dureesa;
    
    public static PackService instance=null;
    public boolean resultOK;
    private ConnectionRequest req,req2,req3;
    
    private PackService() {
         req = new ConnectionRequest();
         req2 = new ConnectionRequest();
         req3 = new ConnectionRequest();
    }

    public static PackService getInstance() {
        if (instance == null) {
            instance = new PackService();
        }
        return instance;
    }
    
    public ArrayList<Pack> parsePacks(String jsonText){
//        try {
//            packs=new ArrayList<>();
//            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
//
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//       //List<List<Map<String,Object>>> list = (List<List<Map<String,Object>>>)tasksListJson.get("root");
//              List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                Pack t = new Pack();
//                //float id = Float.parseFloat(obj.get("id").toString());
//                //t.setId((int) obj.get("id"));
//                //t.setReduction((int) obj.get("reduction"));
//                t.setTitre(obj.get("titre").toString());
//                t.setDescription(obj.get("description").toString());
//                t.setType(obj.get("type").toString());
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                packs.add(t);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return packs;
try {
            packs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Pack t = new Pack();
                //int id = Integer.parseInt((String) obj.get("id"));
                t.setId((double) obj.get("id"));
                t.setReduction((double) obj.get("reduction"));
                t.setTitre(obj.get("titre").toString());
                t.setDescription(obj.get("description").toString());
                t.setType(obj.get("type").toString());
                packs.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return packs;
    }
    
    public ArrayList<String> parseDureeDu(String jsonText){
        try {
            dureesdu=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,String>> list = (List<Map<String,String>>)tasksListJson.get("root");
            for(Map<String,String> obj : list){
                String t = new String();
                t=obj.get("dates").toString();
                
                dureesdu.add(t);
            }
            
        } catch (IOException ex) {
            
        }
//        System.out.println("lbaaarrraaaaaa  "+dureesdu);
        return dureesdu;
    }
    
    public ArrayList<String> parseDureeA(String jsonText){
        try {
            dureesa=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,String>> list = (List<Map<String,String>>)tasksListJson.get("root");
            for(Map<String,String> obj : list){
                String t = new String();
                t=obj.get("dates").toString();
                
                dureesa.add(t);
            }
            
        } catch (IOException ex) {
            
        }
//        System.out.println("lbaaarrraaaaaa  "+dureesa);
        return dureesa;
    }
    
    
    public ArrayList<Pack> getPacks(){
        String url = "http://localhost/evenements/web/app_dev.php/event/packMob/readPackMob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                packs = parsePacks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        String url2 = "http://localhost/evenements/web/app_dev.php/event/packMob/readDatesDu";
        req2.setUrl(url2);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt2) {
                dureesdu = parseDureeDu(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
                for (int i=0;i<packs.size();i++){
//            System.out.println(dureesdu.get(i));
//            System.out.println(packs.get(i));
            packs.get(i).setDureeDu(dureesdu.get(i));
        }
            }
        });
NetworkManager.getInstance().addToQueueAndWait(req2);
        
        String url3 = "http://localhost/evenements/web/app_dev.php/event/packMob/readDatesA";
        req3.setUrl(url3);
        req3.setPost(false);
        req3.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt2) {
                dureesa = parseDureeA(new String(req3.getResponseData()));
                req3.removeResponseListener(this);
                for (int i=0;i<packs.size();i++){
            //System.out.println(dureesa.get(i));
            //System.out.println(packs.get(i));
            packs.get(i).setDureeA(dureesa.get(i));
        }
            }
        });
        
        
        
        NetworkManager.getInstance().addToQueueAndWait(req3);
        System.out.println(packs);
        return packs;
    }
    
}
