package CreateFIle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CreateItemFIle {
    public static void main(String[] args) {
        // File name for item records
        String fileName = "item_records.txt";

        try {
            // Create an empty file
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            // Scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Set to store used item numbers
            Set<Integer> usedItemNumbers = new HashSet<>();

            // FileWriter to write into the file
            FileWriter writer = new FileWriter(file);

            // Accept user input to populate the file
            while (true) {
                System.out.println("Enter item details (Item Number, Description), or type 'exit' to quit:");

                // Read user input
                String input = scanner.nextLine();

                // Check if the user wants to exit
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Split input by comma
                String[] details = input.split(",");

                // Validate input format
                if (details.length != 2) {
                    System.out.println("Invalid input format. Please enter in the format: Item Number,Description");
                    continue;
                }

                // Parse Item Number
                int itemNumber;
                try {
                    itemNumber = Integer.parseInt(details[0].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Item Number. Please enter a valid number.");
                    continue;
                }

                // Validate Item Number uniqueness
                if (usedItemNumbers.contains(itemNumber)) {
                    System.out.println("Item number already exists. Please choose a different item number.");
                    continue;
                }

                // Ensure description is up to 20 characters long
                String description = details[1].trim();
                if (description.length() > 20) {
                    System.out.println("Description is too long. Truncating.");
                    description = description.substring(0, 20);
                }

                // Write item details to the file
                writer.write(String.format("%03d %-20s\n", itemNumber, description));

                // Add Item Number to used Item Numbers set
                usedItemNumbers.add(itemNumber);
            }

            // Close FileWriter
            writer.close();

            System.out.println("Item records saved successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}