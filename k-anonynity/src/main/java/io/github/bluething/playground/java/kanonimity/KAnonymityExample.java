package io.github.bluething.playground.java.kanonimity;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class KAnonymityExample {
    static class Patient {
        String id;
        int age;
        String gender;
        String zipCode;
        String disease;

        public Patient(String id, int age, String gender, String zipCode, String disease) {
            this.id = id;
            this.age = age;
            this.gender = gender;
            this.zipCode = zipCode;
            this.disease = disease;
        }

        @Override
        public String toString() {
            return String.join(",", id, String.valueOf(age), gender, zipCode, disease);
        }
    }

    // Generate dummy patient data
    public static List<Patient> generateDummyData(int count) {
        List<Patient> patients = new ArrayList<>();
        String[] genders = {"M", "F"};
        String[] diseases = {"Diabetes", "Hypertension", "Asthma", "Arthritis", "Migraine"};
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String id = "P" + String.format("%03d", i + 1);
            int age = rand.nextInt(70) + 20; // age between 20 and 89
            String gender = genders[rand.nextInt(genders.length)];
            String zipCode = String.format("%05d", rand.nextInt(100000));
            String disease = diseases[rand.nextInt(diseases.length)];

            patients.add(new Patient(id, age, gender, zipCode, disease));
        }

        return patients;
    }

    // Write data to CSV
    public static void writeToCSV(List<Patient> patients, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            // Write header
            writer.println("ID,Age,Gender,ZipCode,Disease");

            // Write data
            for (Patient patient : patients) {
                writer.println(patient.toString());
            }
        }
    }

    // Apply k-anonymity by generalizing age and zip code
    public static List<Patient> applyKAnonymity(List<Patient> patients, int k) {
        List<Patient> anonymizedPatients = new ArrayList<>();

        // Group patients by age ranges and first 3 digits of zip code
        Map<String, List<Patient>> groups = patients.stream()
                .collect(Collectors.groupingBy(p -> {
                    int ageGroup = (p.age / 10) * 10; // Group ages by decades
                    String zipPrefix = p.zipCode.substring(0, 3); // Take first 3 digits
                    return ageGroup + "-" + zipPrefix;
                }));

        // Process each group
        for (Map.Entry<String, List<Patient>> entry : groups.entrySet()) {
            List<Patient> group = entry.getValue();

            if (group.size() < k) {
                // If group is too small, generalize further
                for (Patient p : group) {
                    int ageGroup = (p.age / 10) * 10;
                    String anonymizedZip = p.zipCode.substring(0, 2) + "***";
                    anonymizedPatients.add(new Patient(
                            p.id,
                            ageGroup,
                            p.gender,
                            anonymizedZip,
                            p.disease
                    ));
                }
            } else {
                // If group is large enough, use current generalization
                for (Patient p : group) {
                    int ageGroup = (p.age / 10) * 10;
                    String anonymizedZip = p.zipCode.substring(0, 3) + "**";
                    anonymizedPatients.add(new Patient(
                            p.id,
                            ageGroup,
                            p.gender,
                            anonymizedZip,
                            p.disease
                    ));
                }
            }
        }

        return anonymizedPatients;
    }

    public static void main(String[] args) {
        try {
            // Generate 200 dummy patient records
            List<Patient> patients = generateDummyData(200);

            // Write original data to CSV
            writeToCSV(patients, "original_patients.csv");

            // Apply k-anonymity with k=3
            List<Patient> anonymizedPatients = applyKAnonymity(patients, 3);

            // Write anonymized data to CSV
            writeToCSV(anonymizedPatients, "anonymized_patients.csv");

            System.out.println("Files generated successfully!");

            // Print sample of original and anonymized data
            System.out.println("\nSample Original Data (first 5 records):");
            patients.stream().limit(5).forEach(System.out::println);

            System.out.println("\nSample Anonymized Data (first 5 records):");
            anonymizedPatients.stream().limit(5).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
