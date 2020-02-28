

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
import entities.Salle;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PdfSalle {
    Connection cn2;
    Statement st;
    
    public PdfSalle() {
         cn2 = Myconnection.getInstance().getCnx();
    }
    public void pdfsalle() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        st=cn2.createStatement();
     
        ResultSet rs=st.executeQuery("SELECT nom_salle,capacite FROM salles");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Salles.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste des salles:  ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("Numéro salle",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Capacité",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell);
        
     
        
        
     while (rs.next()) {                
            
                Salle s =new Salle();
                s.setNom_salle(rs.getString(1));
                s.setCapacite(rs.getString(2));
               
                  
           
               
               cell = new PdfPCell(new Phrase(String.valueOf(s.getNom_salle()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
              
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(s.getCapacite(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               
               table.addCell(cell);

                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Liste Salles.pdf"));
            }}