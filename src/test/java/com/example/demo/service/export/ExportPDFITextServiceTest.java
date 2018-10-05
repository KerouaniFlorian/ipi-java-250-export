package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.itextpdf.text.DocumentException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportPDFITextServiceTest {

    @Test
    public void test() throws IOException, DocumentException {
        Client client = new Client();
        client.setNom("PETRILLO");
        client.setPrenom("Alexandre");

        Article article = new Article();
        article.setLibelle("Carte mère");
        article.setPrix(79.90);

        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(1);

        Facture facture = new Facture();
        facture.setClient(client);
        facture.getLigneFactures().add(ligneFacture1);

        ExportPDFITextService exportPDFITextService = new ExportPDFITextService();
        FileOutputStream fos = new FileOutputStream("./target/facture-itext.pdf");
        exportPDFITextService.export(fos, facture);
        fos.close();
    }
}