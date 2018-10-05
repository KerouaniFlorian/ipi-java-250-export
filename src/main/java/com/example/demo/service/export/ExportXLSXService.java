package com.example.demo.service.export;

import com.example.demo.entity.Client;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {

    public void exportClients(OutputStream os, List<Client> clients) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");

        workbook.write(os);
        workbook.close();
    }

}
