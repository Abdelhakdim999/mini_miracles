/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.User;
import com.mycompany.utils.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class UserService 
{
    private User loggedUser= new User();
    public ArrayList<User> users;

    public static UserService instance = null;
    public boolean resultOK;
    public int addRes;
    public int loginRes;
    public int actRes;
    String res;
    private ConnectionRequest req;
    private Database db;
    String token;
    
    private UserService() {
        req = new ConnectionRequest();
        try {
            db = Database.openOrCreate("minimiracles");
            db.execute("Create table if not exists rememberU (idUser TEXT , date TEXT , code TEXT );");
        } catch (IOException ex) {
            System.out.println("ERR");
            System.out.println(ex.getMessage());
        }
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    
    public User Authentification(String username, String password) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/evenements2/web/app_dev.php/loginmob?mail=" + username + "&pass=" + password;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        if(str.equals("false"))
        {
            loggedUser = null;
        }
        else
        {
            UserService ser = new UserService();
                try {
                    loggedUser = ser.parseUserJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    
                }
            Session.getInstance().setLoggedInUser(loggedUser);
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return loggedUser; 
    }
    
    public int login(String mail, String pass) {
        String url = "http://localhost/evenements2/web/app_dev.php/loginmob?mail=" + mail + "&pass=" + pass;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(req.getResponseData());
                if (res.contains("paspaspasNon")) {
                    loginRes = -1;
                } else {
                    loginRes = 0;
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return loginRes;
    }
    
    User found_user;
    
    public User fetch(int id) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/evenements2/web/app_dev.php/event/api/getUser/" + id;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
      
        
        
            UserService ser = new UserService();
                try {
                    found_user = ser.parseUserJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    
                }        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return found_user; 
    }
    
    
    public User parseUserJson(String json) throws ParseException {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setAvatar(obj.get("avatar").toString());
                listUsers.add(u);
            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listUsers.get(0);
    }
    
    public void SignUp(User u) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        String Url = "http://localhost/evenements2/web/app_dev.php/event/api/register?username=" + u.getUsername() + "&password=" + u.getPassword()
                +"&email="+u.getEmail();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager*/
    }
    
}
