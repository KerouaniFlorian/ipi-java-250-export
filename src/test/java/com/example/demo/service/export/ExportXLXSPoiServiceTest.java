package com.example.demo.service.export;

import com.example.demo.entity.Client;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportXLXSPoiServiceTest {

    @Test
    public void export() throws IOException {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        client1.setNom("PETRILLO");
        client1.setPrenom("Alexandre");
        clients.add(client1);

        File tempFile = new File("./target/clientS.xlsx");
        tempFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(tempFile);

        ExportXLSXService exportXLSXService = new ExportXLSXService();
        exportXLSXService.exportClients(fos, clients);

        fos.close();
    }
}