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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Enfant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dimassi Abdelhak
 */
public class EnfantService {
    
    public ArrayList<Enfant> enfants;
    public ArrayList<String> dates;
    
    public static EnfantService instance=null;
    public boolean resultOK;
    private ConnectionRequest req,req2;

    private EnfantService() {
         req = new ConnectionRequest();
         req2 = new ConnectionRequest();
    }

    public static EnfantService getInstance() {
        if (instance == null) {
            instance = new EnfantService();
        }
        return instance;
    }
    
    public boolean addEnfant(Enfant t) {
        String url = "http://localhost/evenements2/web/app_dev.php/event/enfant/addMob?usermail=" + t.getIdParent() + "&name=" + t.getNom() + "&lastname=" + t.getPrenom() + "&sexe=" + t.getSexe() + "&date=" + t.getDateNaiss(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Enfant> parseEnfants(String jsonText){
        try {
            enfants=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
       //List<List<Map<String,Object>>> list = (List<List<Map<String,Object>>>)tasksListJson.get("root");
              List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Enfant t = new Enfant();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setSexe(obj.get("sexe").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                enfants.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return enfants;
    }
    
    public ArrayList<String> parseDates(String jsonText){
        try {
            dates=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,String>> list = (List<Map<String,String>>)tasksListJson.get("root");
            for(Map<String,String> obj : list){
                String t = new String();
                t=obj.get("dates").toString();
                
                dates.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        System.out.println("lbaaarrraaaaaa  "+dates);
        return dates;
    }
    
    public ArrayList<Enfant> getAllEnfParent(String mail){
        String url = "http://localhost/evenements2/web/app_dev.php/event/enfant/enfparent/"+mail;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                enfants = parseEnfants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        String url2 = "http://localhost/evenements2/web/app_dev.php/event/enfant/enfdates/"+mail;
        req2.setUrl(url2);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt2) {
                dates = parseDates(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
                for (int i=0;i<enfants.size();i++){
            System.out.println(dates.get(i));
            System.out.println(enfants.get(i));
            enfants.get(i).setDateNaiss(dates.get(i));
        }
            }
        });
        
        
        
        NetworkManager.getInstance().addToQueueAndWait(req2);
        System.out.println(enfants);
        return enfants;
    }
    
}
