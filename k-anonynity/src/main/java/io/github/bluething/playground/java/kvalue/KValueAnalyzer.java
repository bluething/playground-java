package io.github.bluething.playground.java.kvalue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class KValueAnalyzer {
    static class Record {
        Map<String, String> values;

        public Record(Map<String, String> values) {
            this.values = values;
        }

        // Get values only for specified quasi-identifiers
        public String getQuasiIdentifierKey(List<String> quasiIdentifiers) {
            return quasiIdentifiers.stream()
                    .map(qi -> values.get(qi))
                    .collect(Collectors.joining("|"));
        }
    }

    public static List<Record> readCSV(String filename) throws IOException {
        List<Record> records = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));

        if (lines.isEmpty()) {
            throw new IOException("Empty CSV file");
        }

        // Parse header
        String[] headers = lines.get(0).split(",");

        // Parse records
        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            Map<String, String> recordMap = new HashMap<>();

            for (int j = 0; j < headers.length && j < values.length; j++) {
                recordMap.put(headers[j].trim(), values[j].trim());
            }

            records.add(new Record(recordMap));
        }

        return records;
    }

    public static int findKValue(List<Record> records, List<String> quasiIdentifiers) {
        // Group records by quasi-identifier combinations
        Map<String, List<Record>> groups = records.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getQuasiIdentifierKey(quasiIdentifiers)
                ));

        // Find the smallest group size
        int minGroupSize = Integer.MAX_VALUE;
        String smallestGroup = "";

        for (Map.Entry<String, List<Record>> entry : groups.entrySet()) {
            int groupSize = entry.getValue().size();
            if (groupSize < minGroupSize) {
                minGroupSize = groupSize;
                smallestGroup = entry.getKey();
            }
        }

        // Print detailed analysis
        System.out.println("\nDetailed Analysis:");
        System.out.println("Total number of records: " + records.size());
        System.out.println("Number of unique combinations: " + groups.size());
        System.out.println("Smallest group size (k-value): " + minGroupSize);
        System.out.println("Quasi-identifier combination with smallest group: " + smallestGroup);

        // Print distribution of group sizes
        Map<Integer, Long> distribution = groups.values().stream()
                .collect(Collectors.groupingBy(
                        List::size,
                        Collectors.counting()
                ));

        System.out.println("\nDistribution of group sizes:");
        distribution.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("Groups of size %d: %d%n", e.getKey(), e.getValue()));

        return minGroupSize;
    }

    public static void main(String[] args) {
        try {
            String filename = "original_patients.csv"; // Change this to your CSV file
            List<String> quasiIdentifiers = Arrays.asList("Age", "Gender", "ZipCode"); // Adjust these based on your CSV columns

            System.out.println("Reading data from: " + filename);
            List<Record> records = readCSV(filename);

            System.out.println("Analyzing k-value for quasi-identifiers: " + quasiIdentifiers);
            int kValue = findKValue(records, quasiIdentifiers);

            System.out.println("\nSummary:");
            System.out.println("The dataset has a k-value of: " + kValue);
            if (kValue < 2) {
                System.out.println("Warning: This dataset does not provide k-anonymity (k should be at least 2)");
            } else {
                System.out.println("This means that each combination of quasi-identifiers appears at least " + kValue + " times");
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
