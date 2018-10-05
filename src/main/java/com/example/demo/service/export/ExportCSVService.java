package com.example.demo.service.export;

import com.example.demo.entity.Client;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<Client> clients) throws IOException {
        printWriter.write("Nom");
        printWriter.write(";");
        printWriter.write("Prenom;");

        for (Client client : clients) {

            printWriter.write(client.getNom());
            printWriter.write(";");
            printWriter.write(client.getPrenom());

            printWriter.write("\n");
        }
    }

    public void exportAmeliorer(Writer printWriter, List<Client> clients) throws IOException {
        ExporterCSV exporter = new ExporterCSV();
        exporter.addColumnString("Nom", client -> client.getNom());
        exporter.addColumnString("Prenom", Client::getPrenom);
        exporter.createCSV(printWriter, clients);
    }

}
