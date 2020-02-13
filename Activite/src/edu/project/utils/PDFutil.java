/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.project.utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import edu.project.entities.Activite;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author Dimassi Abdelhak
 */
public class PDFutil {
    Connection cn2;
    Statement ste;
    
    public PDFutil() {
        cn2 = Myconnection.getInstance().getCnx();
    }
    public void FacturePdf() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        ste=cn2.createStatement();
        //ResultSet rs=ste.executeQuery("select participation.*,user.* from participation INNER JOIN user ON participation.iduser=user.iduser where participation.id_event='"+id+"'ORDER BY ranking");
        ResultSet rs=ste.executeQuery("SELECT * FROM activite");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Activité.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste des activités:  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("ID_Activite",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Libelle",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Description",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Duree",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("Date",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("IDenfants",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("IDanimateurs",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
     while (rs.next()) {                
            
               Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getString(4));
                a.setDate(rs.getString(5));
                a.setEnfants(rs.getString(6));
                a.setAnimateurs(rs.getString(7));
                  
               /*DateFormat df = new SimpleDateFormat("hh:mm:ss");
               String rec = df.format(record);
                String rank = Integer.toString(ranking);*/
               
               cell = new PdfPCell(new Phrase(String.valueOf(a.getId()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getLib(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getDescription(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
        
        
               cell = new PdfPCell(new Phrase(a.getDuree(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getDate(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getEnfants(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getAnimateurs(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Liste Activité.pdf"));
            }
}




/*Activite a = new Activite();
                a.setId(rs.getInt(1));
                a.setLib(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setDuree(rs.getString(4));
                a.setDate(rs.getString(5));
                String idList = rs.getString(6);
                String idList1 = rs.getString(7);
                mail +="L'activite numéro " + a.getId()+" :";
                mail +="\n  Libelle = " + a.getLib();
                mail +="\n  Description = " + a.getDescription();
                mail +="\n  Duree = " + a.getDuree();
                mail +="\n  Date = " + a.getDate();
                mail +="\n  Les enfants qui vont participer sont : ";
                List<Enfant> KidList = afficherEnfant();
                for (Enfant en : KidList) {
                    if (idList.contains(en.getIdEnfant())) {
                        mail +="\n      l'enfant " + en.getIdEnfant() + " " + en.getNom() + " " + en.getPrenom();
                    }
                }
                List<Animateur> animList = afficherAnimateur();
                mail +="\n  Les animateurs sont : ";
                for (Animateur an : animList) {
                    if (idList1.contains(an.getAnimID())) {
                        mail +="\n      l'animateur " + an.getAnimID() + " " + an.getNom() + " " + an.getPrenom();
                    }
                }*/