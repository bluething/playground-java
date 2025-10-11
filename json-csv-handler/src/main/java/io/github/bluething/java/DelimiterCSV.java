package io.github.bluething.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class DelimiterCSV implements CSVReader {
    @Override
    public void read(String input) {
        try (Reader reader = Files.newBufferedReader(Path.of(input))) {
            CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT.builder()
                    .setDelimiter(',')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .get());

            System.out.println("Header: " + parser.getHeaderNames());

            for (CSVRecord record : parser) {
                System.out.println(record);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
