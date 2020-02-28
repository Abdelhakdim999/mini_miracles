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
import entities.Group;

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
public class PdfGroup {
Connection cn2;
    Statement st;    

 public PdfGroup() {
        cn2 = Myconnection.getInstance().getCnx();    }
    public void pdfGroup() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        st=cn2.createStatement();
     
        ResultSet rs=st.executeQuery("SELECT nom_group FROM groupe");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Group.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste des group:  ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(40);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("Nom du group",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
        table.addCell(cell);      
     while (rs.next()) {                
            
                Group g =new Group();
                g.setNom_group(rs.getString(1));
           
               cell = new PdfPCell(new Phrase(String.valueOf(g.getNom_group()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
              
        
     }    doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Liste Group.pdf")); 
    }}      