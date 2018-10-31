package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
        String Header = "NOM;" + "PRENOM;" + "AGE;" + "\n";
    	printWriter.write(Header);
    	for(ClientDTO client : clients) {
    		printWriter.write(client.getNom().replace(";", "") + ";" + client.getPrenom().replace(";", "") + ";" + client.getAge().replace(";", "") + "\n");
    	}
    }
}
