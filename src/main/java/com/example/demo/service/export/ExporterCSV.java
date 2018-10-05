package com.example.demo.service.export;

import com.example.demo.entity.Client;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExporterCSV {

    private List<String> headers = new ArrayList<>();
    private List<Function<Client, String>> functions = new ArrayList<>();

    public void addColumnString(String headerName, Function<Client, String> function) {
        headers.add(headerName);
        functions.add(function);
    }

    public void addColumnInteger(String headerName, Function<Client, Integer> function) {
        headers.add(headerName);
        functions.add(function.andThen(integerValue -> integerValue == null ? "" : integerValue.toString()));
    }

    public void createCSV(Writer printWriter, List<Client> clients) throws IOException {
        for (String header : headers) {
            printWriter.write(header);
            printWriter.write(";");
        }
        printWriter.write("\n");

        for (Function<Client, String> function : functions) {
            for (Client client : clients) {
                String value = function.apply(client);
                printWriter.write(value);
                printWriter.write(";");
            }
            printWriter.write("\n");
        }
    }
}
