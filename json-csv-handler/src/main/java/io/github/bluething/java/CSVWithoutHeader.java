package io.github.bluething.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVWithoutHeader implements CSVReader {
    @Override
    public void read(String input) {
        try (Reader reader = Files.newBufferedReader(Path.of(input))) {
            CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT);

            for (CSVRecord record : parser) {
                for (int i = 0; i < record.size(); i++) {
                    System.out.print(record.get(i));
                    if (i < record.size() - 1) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
