package io.github.bluething.java;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please specify one of: SJ");
            System.exit(1);
        }

        String mode = args[0];
        JsonParser jsonParser;
        CSVReader csvReader;
        String input;
        switch (mode) {
            case "SJ":
                jsonParser = new SimpleJson();
                input = """
                        {
                            "name": "Alice",
                            "age": 30,
                            "email": "alice@example.com",
                            "active": true
                        }
                        """;
                jsonParser.parse(input);
                break;
            case "FJ":
                jsonParser = new JsonFile();
                jsonParser.parse("small-json.json");
                break;
            case "ARRJ":
                jsonParser = new JsonArray();
                input = """
                        [
                            {"id": 1, "name": "Product A", "price": 29.99},
                            {"id": 2, "name": "Product B", "price": 49.99},
                            {"id": 3, "name": "Product C", "price": 19.99}
                        ]
                        """;
                jsonParser.parse(input);
                break;
            case "NJ":
                jsonParser = new NestedJson();
                input = """
                        {
                            "user": {
                                "id": 123,
                                "name": "John Doe",
                                "address": {
                                    "street": "123 Main St",
                                    "city": "Springfield",
                                    "zipcode": "12345"
                                },
                            "orders": [
                                {"orderId": "A001", "total": 99.99},
                                {"orderId": "A002", "total": 149.99}
                                ]
                            }
                        }
                        """;
                jsonParser.parse(input);
                break;
            case "SCSV":
                csvReader = new SimpleCSV();
                csvReader.read("small-csv.csv");;
                break;
            case "WHCSV":
                csvReader = new CSVWithoutHeader();
                csvReader.read("small-csv.csv");
                break;
            case "DCSV":
                csvReader = new DelimiterCSV();
                csvReader.read("small-csv.csv");
                break;
            case "QCSV":
                csvReader = new QuotedCSV();
                input = """
                        name,description,price
                        "Product A","A simple product",29.99
                        "Product B","Contains ""quotes"" and, commas",49.99
                        "Product C","Line 1
                        Line 2",19.99
                        """;
                csvReader.read(input);
                break;
            case "MFCSV":
                csvReader = new MissingFieldCSV();
                input = """
                        id,name,email,phone
                        1,Alice,alice@example.com,555-1234
                        2,Bob,,555-5678
                        3,Charlie,charlie@example.com,
                        4,,,555-9999
                        """;
                csvReader.read(input);
                break;
        }
    }
}
