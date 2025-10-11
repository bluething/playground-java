package io.github.bluething.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleCSV implements CSVReader {
    @Override
    public void read(String input) {
        try (Reader reader = Files.newBufferedReader(Path.of(input))) {
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(false)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .get();
            for (CSVRecord record : format.parse(reader)) {
                System.out.println("Record: " + record);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
