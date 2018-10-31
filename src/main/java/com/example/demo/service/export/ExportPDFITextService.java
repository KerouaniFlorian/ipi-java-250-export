package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Phrase;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.awt.Color;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
		document.setMargins(100, 100, 100, 100);
        document.open();

		List<LigneFactureDTO> lignesFactures = facture.getLigneFactures();
    	Double Total = 0.0;
    	Double PrixLigne = 0.0;
    	
    	Paragraph Head = new Paragraph();
    	Head.add("Je ne suis pas sur \n Quelque pars \n numero SIRET");
    	Head.add("\n \n \n");
    	Head.setAlignment(Element.ALIGN_LEFT);
    	
    	document.add(Head);
    	
    	Paragraph Client = new Paragraph();
    	
    	Client.add(facture.getClient().getNom() + " " + facture.getClient().getPrenom() + "\n");
    	Client.add("2000 Rue d'ici \n");
    	Client.add("00000 Quelque parsr");
    	Client.add("\n \n");
    	
    	Client.setAlignment(Element.ALIGN_RIGHT);
    	
    	document.add(Client);
    	
    	Paragraph Title = new Paragraph();
    	Title.add("INVOICE n° " + facture.getId());
    	Title.setAlignment(Element.ALIGN_CENTER);
    	document.add(Title);
    	
    	Paragraph paragraph = new Paragraph();
    	paragraph.add("\n \n \n Récapitulatif de la Facture : \n");
    	
    	PdfPTable pdfTable = new PdfPTable(4);
    	pdfTable.setWidthPercentage(100.0f);
    	pdfTable.setSpacingBefore(10);
    	
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setBackgroundColor(Color.GRAY);
        pdfCell.setPadding(5);
        
     // En Tête pdfTableau
        pdfCell.setPhrase(new Phrase("Libellé Article", font));
        pdfTable.addCell(pdfCell);
		
        pdfCell.setPhrase(new Phrase("Prix Unitaire", font));
        pdfTable.addCell(pdfCell);
 
        pdfCell.setPhrase(new Phrase("Quantité", font));
        pdfTable.addCell(pdfCell);
         
        pdfCell.setPhrase(new Phrase("Montant", font));
        pdfTable.addCell(pdfCell);
        
        // Ligne pdfTableau
        for (LigneFactureDTO lf : lignesFactures) {
            pdfTable.addCell(lf.getDesignation());
            pdfTable.addCell(String.valueOf(lf.getPrixUnitaire()) + " €");
            pdfTable.addCell(String.valueOf(lf.getQuantite()));
            
            PrixLigne = lf.getQuantite() * lf.getPrixUnitaire();
    		Total += PrixLigne;
    		
            pdfTable.addCell(String.valueOf(PrixLigne) + " €");
        }
        
        // Ligne Total
        pdfCell.setPhrase(new Phrase("", font));
        pdfTable.addCell(pdfCell);
        pdfTable.addCell(pdfCell);
        pdfTable.addCell(pdfCell);
        pdfTable.addCell("Total : " + String.valueOf(Total) + " €");
        
        paragraph.add(pdfTable);
		document.add(paragraph);
        document.close();
    }
}
