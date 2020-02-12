package edu.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Myconnection {
    
    public  String url ="jdbc:mysql://localhost:3306/esprit3a12";
    public  String login = "root";
    public  String pwd = "";
    public static Myconnection instance ;
    public  Connection cnx;

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    
    
    

    private Myconnection() {
         try {
            cnx =  DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Myconnection getInstance(){
        if(instance == null){
            instance = new Myconnection();}
        return instance ;}
        
        public static void main (String[] args){
                    
            Myconnection mc = Myconnection.getInstance();
            Myconnection mc2 = Myconnection.getInstance();
            
            System.out.println(mc.hashCode() +" - "+mc2.hashCode());
   
    
        }
    
    
    
    
}