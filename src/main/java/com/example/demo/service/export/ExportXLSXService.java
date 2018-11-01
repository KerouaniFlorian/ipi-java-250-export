package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {

    public static final int HEADER_SIZE = 1;

    public void export(OutputStream os, List<ClientDTO> clients) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");

		//Header Style 
		CellStyle style = workbook.createCellStyle();
        XSSFFont defaultFont = workbook.createFont();
        defaultFont.setFontHeightInPoints((short) 10);
        defaultFont.setFontName("Calibri");
        defaultFont.setBold(true);
        defaultFont.setItalic(true);
		style.setFont(defaultFont);
		
		//Header
        XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("NOM");
        header.getCell(0).setCellStyle(style);
    	header.createCell(1).setCellValue("PRENOM");
        header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("AGE");
        header.getCell(2).setCellStyle(style);


        int rowNum = 1;
        for (ClientDTO client : clients) {
            XSSFRow row1 = sheet.createRow(rowNum);

            XSSFCell cellNom = row1.createCell(0);
            cellNom.setCellValue(client.getNom());

            XSSFCell cellPrenom = row1.createCell(1);
            cellPrenom.setCellValue(client.getNom());
			
			XSSFCell cellAge = row1.createCell(2);
            cellAge.setCellValue(client.getAge());

            rowNum++;
        }


        workbook.write(os);
        workbook.close();
    }

    public void exportFacturesDUnClient(ServletOutputStream outputStream, List<FactureDTO> factures) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");
		
		//Header Style 
		CellStyle style = workbook.createCellStyle();
        XSSFFont defaultFont = workbook.createFont();
        defaultFont.setFontHeightInPoints((short) 10);
        defaultFont.setFontName("Calibri");
        defaultFont.setBold(true);
        defaultFont.setItalic(true);
		style.setFont(defaultFont);	
		
		
		//Header
        XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("NOM");
        header.getCell(0).setCellStyle(style);
    	header.createCell(1).setCellValue("PRENOM");
        header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("AGE");
        header.getCell(2).setCellStyle(style);
		
		Integer i = 1;
		Double Total = 0.0;
		Double LinePrice = 0.0;

		
		for (FactureDTO facture : factures) {
			XSSFRow headerRow = sheet.createRow(1);
    		XSSFCell cellNom = headerRow.createCell(0);
    		cellNom.setCellValue(facture.getClient().getNom());
    		XSSFCell cellPrenom = headerRow.createCell(1);
    		cellPrenom.setCellValue(facture.getClient().getPrenom());
			XSSFCell cellAge = headerRow.createCell(1);
    		cellAge.setCellValue(facture.getClient().getAge());
    		
			XSSFSheet sheet2 = workbook.createSheet("Facture " + String.valueOf(facture.getId()));	
    		// Header
        	XSSFRow header2 = sheet2.createRow(0);
        	header2.createCell(0).setCellValue("Libellé Article");
        	header2.getCell(0).setCellStyle(style);
        	header2.createCell(1).setCellValue("Prix Unitaire");
        	header2.getCell(1).setCellStyle(style);
        	header2.createCell(2).setCellValue("Quantité");
        	header2.getCell(2).setCellStyle(style);
        	header2.createCell(3).setCellValue("Montant Ligne");
        	header2.getCell(3).setCellStyle(style);
        	

        	Integer j = 1;
        	
        	for (LigneFactureDTO lf : facture.getLigneFactures()) {
        		XSSFRow Row = sheet2.createRow(j);
        		XSSFCell cellLib = Row.createCell(0);
        		cellLib.setCellValue(lf.getDesignation());
        		XSSFCell cellPriceU = Row.createCell(1);
        		cellPriceU.setCellValue(lf.getPrixUnitaire() + " €");
        		XSSFCell cellQty = Row.createCell(2);
        		cellQty.setCellValue(lf.getQuantite());
        		
        		LinePrice = lf.getQuantite() * lf.getPrixUnitaire();
        		Total += LinePrice;
        		
        		XSSFCell cellMntLigne = Row.createCell(3);
        		cellMntLigne.setCellValue(String.valueOf(LinePrice)  + " €");
        		
        		j++;
        	}
        	
        	// Line Total
        	XSSFRow RowTotal = sheet2.createRow(j + 1);
        	RowTotal.createCell(0).setCellValue("Total");
        	RowTotal.createCell(1).setCellValue("");
        	RowTotal.createCell(2).setCellValue("");
        	RowTotal.createCell(3).setCellValue(String.valueOf(Total) + " €");
    		
    		i++;
        }
		
        workbook.write(outputStream);
        workbook.close();
    }
}
