package CreateFIle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerItemOrder {
    public static void main(String[] args) {
        // File names for customer and item records
        String customerFileName = "customer_records.txt";
        String itemFileName = "item_records.txt";

        // Load customer records into a map
        Map<Integer, String> customerRecords = loadRecords(customerFileName);

        // Load item records into a map
        Map<Integer, String> itemRecords = loadRecords(itemFileName);

        if (customerRecords == null || itemRecords == null) {
            System.out.println("Failed to load records.");
            return;
        }

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Accept customer number and item ordered
        System.out.println("Enter customer number:");
        int customerNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter item number:");
        int itemNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if customer number exists
        if (!customerRecords.containsKey(customerNumber)) {
            System.out.println("Customer number does not exist.");
            return;
        }

        // Check if item number exists
        if (!itemRecords.containsKey(itemNumber)) {
            System.out.println("Item number does not exist.");
            return;
        }

        // Display customer information and item information
        System.out.println("Customer Information:");
        System.out.println(customerNumber + " " + customerRecords.get(customerNumber));

        System.out.println("Item Information:");
        System.out.println(itemNumber + " " + itemRecords.get(itemNumber));
    }

    // Method to load records from a file into a map
    private static Map<Integer, String> loadRecords(String fileName) {
        Map<Integer, String> records = new HashMap<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+", 2);
                if (parts.length >= 2) {
                    int key = Integer.parseInt(parts[0]);
                    String value = parts[1];
                    records.put(key, value);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return null;
        }
        return records;
    }
}
