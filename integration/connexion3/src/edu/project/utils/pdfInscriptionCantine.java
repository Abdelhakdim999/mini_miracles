/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.project.utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entities.inscription_cantine;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import services.EnfantCrud;
import utils.DataBase;

/**
 *
 * @author User
 */
public class pdfInscriptionCantine {

    Connection con;
    Statement ste;

    public pdfInscriptionCantine() {
        con = DataBase.getInstance().getConnection();
    }

    public void pdfInscrcantine(int i) throws SQLException, FileNotFoundException, DocumentException, IOException {
        Document doc = new Document();

        ste = con.createStatement();

        ResultSet rs = ste.executeQuery("select * from inscription_cantine where id='" + i + "'");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste InscriptionCantine.pdf"));

        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" Inscription à la cantine  ", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC)));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(40);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Inscription à la cantine", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        while (rs.next()) {

            int ide = rs.getInt(2);
            EnfantCrud sere = new EnfantCrud();

            String nom = sere.readOne(ide).getNom() + " " + sere.readOne(ide).getPrenom();

            System.out.println(nom);

            inscription_cantine insccant = new inscription_cantine();

            int id = rs.getInt(1);

            int idc = rs.getInt(3);
            int nbj = rs.getInt(4);
            float pri = rs.getFloat(5);
            String dat = rs.getString(6);

            insccant.setNomenf(nom);
            insccant.setId(id);
            insccant.setDate_insc(dat);
            insccant.setNomenf(dat);
            insccant.setNbj(nbj);
            insccant.setPrix(pri);
            System.out.println(insccant.getNomenf());
            String sjk = Float.toString(insccant.getPrix());
            String snbj = Integer.toString(insccant.getNbj());

            //cell = new PdfPCell(new Phrase(String.valueOf(insccant.getNomenf()),FontFactory.getFont("Comic Sans MS",12)));
            // cell = new PdfPCell(new Phrase(String.valueOf(insccant.getDate_insc()),FontFactory.getFont("Comic Sans MS",12)));
            // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //table.addCell(cell);
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph("Inscription du :                                  " + insccant.getDate_insc(), FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph("Nom de l'enfant :                                 " + nom, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph("Le nombre de jour d'inscription :                  " + snbj, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC)));
            doc.add(new Paragraph("Le prix payé:                                      " + sjk + " Dinars", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC)));

        }   // doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("./Liste Inscriptioncantine.pdf"));
    }

}
