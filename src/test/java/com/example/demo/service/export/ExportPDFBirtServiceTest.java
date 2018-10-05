package com.example.demo.service.export;

import com.example.demo.entity.Facture;
import com.itextpdf.text.DocumentException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportPDFBirtServiceTest {

    @Test
    public void generate() throws IOException, EngineException, DocumentException {
        Facture facture = new Facture();

        File tempFile = new File("./target/facture-birt.csv");
        tempFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(tempFile);
        new ExportPDFBirtService().export(fos, facture);

        fos.close();
    }
}