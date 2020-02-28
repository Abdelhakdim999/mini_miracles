/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Darius
 */
public class PdfEmploi {
Connection cn2;
    Statement st;    

 public PdfEmploi() {
        cn2 = Myconnection.getInstance().getCnx();
    }
 public void Pdfemp(String nomgr,String noms,String nomE,String tdate,String t1,String t2,String t3,String t4,String t5,String t6,String t7,String t8,String t9,String t10,String t11,String t12) throws SQLException, FileNotFoundException, DocumentException, IOException{
    Document doc = new Document();
        st=cn2.createStatement();
 
  PdfWriter.getInstance(doc, new FileOutputStream("./Emploi.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" Emploi du temps '"+nomE+"'du groupe '"+nomgr+"':  ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC) ));
        doc.add(new Paragraph(" Date début  '"+tdate+"'"));
        
         PdfPTable table = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
         PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
       
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("8h 12h",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        table.addCell(cell);
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        cell.disableBorderSide(2);
        table.addCell(cell);
         cell = new PdfPCell(new Phrase("14h 17h",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        table.addCell(cell);
        
          
          PdfPTable table2 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Lundi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
      cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t1.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
        else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("P",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
             cell.disableBorderSide(1);
                  cell.disableBorderSide(2);
                  cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t2.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
          PdfPTable table3 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Mardi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
      cell.setFixedHeight(45f);
        table.addCell(cell);
        
        if(t3.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("A",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
 
        cell.setFixedHeight(45f);
        cell.disableBorderSide(1);
                  cell.disableBorderSide(2);
        table.addCell(cell);
        if(t4.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
          PdfPTable table4 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Mercredi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
      cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t5.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("U",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        cell.setFixedHeight(45f);
        cell.disableBorderSide(1);
                  cell.disableBorderSide(2);
        table.addCell(cell);
        if(t6.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
          PdfPTable table5 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Jeudi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
      cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t7.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("S",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        cell.setFixedHeight(45f);
        cell.disableBorderSide(1);
                  cell.disableBorderSide(2);
        table.addCell(cell);
        if(t8.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
          PdfPTable table6 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Vendredi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
      cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t9.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("E",FontFactory.getFont("Comic Sans MS",20,Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        cell.disableBorderSide(1);
                  cell.disableBorderSide(2);
                  cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t10.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
          PdfPTable table7 = new PdfPTable(4);
         table.setWidths(new float[] { 30, 90,10,90 });
        
        
        cell = new PdfPCell(new Phrase("Samedi",FontFactory.getFont("Comic Sans MS",12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
     cell.setFixedHeight(45f);
        table.addCell(cell);
        if(t11.equals("1")){
        cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#A0A0A0"));
        cell.setFixedHeight(45f);
        cell.disableBorderSide(1);
                  
        table.addCell(cell);
        if(t12.equals("1")){
         cell = new PdfPCell(new Phrase("Salle'"+noms+"'",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(WebColors.getRGBColor("#EEA0A6"));
         cell.setFixedHeight(45f);
        table.addCell(cell);}
         else{
         cell = new PdfPCell(new Phrase("",FontFactory.getFont("Comic Sans MS",12)));
         cell.setFixedHeight(45f);
                 table.addCell(cell);
        }
        
        
        
          doc.add(table);
          
        doc.close();
            Desktop.getDesktop().open(new File ("./Emploi.pdf"));
}

}














