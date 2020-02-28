/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ammouna_Zikou
 */
public class FonctionsPartagees {
    static Connection  cn2 = Myconnection.getInstance().getCnx();
    public static boolean verifierNumeroPhone(String date){
    String regex = "[1-9][0-9]{7}";
Pattern p = Pattern.compile(regex);
Matcher matcher = p.matcher(date);
     return matcher.matches();
     }
    
      public static boolean verifierAdresseMail(String email){
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
Pattern p = Pattern.compile(regex);
Matcher matcher = p.matcher(email);
     return matcher.matches();
     }
      
      
}
