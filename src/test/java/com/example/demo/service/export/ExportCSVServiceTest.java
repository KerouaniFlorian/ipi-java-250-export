package com.example.demo.service.export;

import com.example.demo.entity.Client;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportCSVServiceTest {

    @Test
    public void export() throws IOException {
        ExportCSVService exportCSVService = new ExportCSVService();
        List<Client> clientS = new ArrayList<>();
        Client client1 = new Client();
        client1.setNom("PETRILLO");
        client1.setPrenom("Alexandre");
        clientS.add(client1);

        File tempFile = new File("./target/clientS.csv");
        tempFile.createNewFile();
        FileWriter writer = new FileWriter(tempFile);

        exportCSVService.export(writer, clientS);

        writer.close();
    }
}