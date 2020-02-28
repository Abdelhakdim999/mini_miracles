/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;




import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Enfant;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Darius
 */
public class Pdfgrp1 {
Connection cn2;
    Statement st;    

 public Pdfgrp1() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    public void pdfgrp(String nomgr) throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        st=cn2.createStatement();
     
        ResultSet rs=st.executeQuery("select nom,prenom,dateNaiss,sexe from enfant where id_group=(select id_group from groupe where nom_group='"+nomgr+"')");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Grp.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste du group '"+nomgr+"':  ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC) ));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(80);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("Nom",FontFactory.getFont("Comic Sans MS",16,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell);   
         
        cell = new PdfPCell(new Phrase("Prénom",FontFactory.getFont("Comic Sans MS",16,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
        table.addCell(cell); 
         
        cell = new PdfPCell(new Phrase("DateNaiss",FontFactory.getFont("Comic Sans MS",16,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
        table.addCell(cell); 
         
        cell = new PdfPCell(new Phrase("Sexe",FontFactory.getFont("Comic Sans MS",16,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
        table.addCell(cell); 
     while (rs.next()) {                
            
                Enfant g=new Enfant();
                g.setNom(rs.getString(1));
                 g.setPrenom(rs.getString(2));
                  g.setDateNaiss(rs.getString(3));
                   g.setSexe(rs.getString(4));
                  
               cell = new PdfPCell(new Phrase(String.valueOf(g.getNom()),FontFactory.getFont("Comic Sans MS",14)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
                    cell = new PdfPCell(new Phrase(String.valueOf(g.getPrenom()),FontFactory.getFont("Comic Sans MS",14)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
              
                    cell = new PdfPCell(new Phrase(String.valueOf(g.getDateNaiss()),FontFactory.getFont("Comic Sans MS",14)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
              
                    cell = new PdfPCell(new Phrase(String.valueOf(g.getSexe()),FontFactory.getFont("Comic Sans MS",14)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
              
              
        
     }    doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Liste Grp.pdf")); 
    }}      