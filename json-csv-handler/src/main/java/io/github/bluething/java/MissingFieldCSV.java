package io.github.bluething.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MissingFieldCSV implements CSVReader {
    @Override
    public void read(String input) {
        try (Reader reader = new StringReader(input)) {
             CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(false)
                     .setNullString("")
                     .setTrim(true)
                     .get());

            for (CSVRecord record : parser) {
                String id = record.get("id");
                String name = record.get("name");
                String email = record.get("email");
                String phone = record.get("phone");

                System.out.printf("ID: %s, Name: %s, Email: %s, Phone: %s%n",
                        (id == null || id.isEmpty()) ? "N/A" : id,
                        (name == null || name.isEmpty()) ? "N/A" : name,
                        (email == null || email.isEmpty()) ? "N/A" : email,
                        (phone == null || phone.isEmpty()) ? "N/A" : phone
                );
            }

        } catch (IOException e) {
            System.err.println("Error parsing CSV: " + e.getMessage());
        }
    }
}
