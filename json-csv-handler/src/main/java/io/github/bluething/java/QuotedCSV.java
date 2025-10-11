package io.github.bluething.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class QuotedCSV implements CSVReader {
    @Override
    public void read(String input) {
        try (Reader reader = new StringReader(input)) {
            CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(false)
                    .get());

            for (CSVRecord record : parser) {
                System.out.printf("Name: %s%n", record.get("name"));
                System.out.printf("Description: %s%n", record.get("description"));
                System.out.printf("Price: %s%n%n", record.get("price"));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
