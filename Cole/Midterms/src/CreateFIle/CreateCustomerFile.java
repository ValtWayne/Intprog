package CreateFIle;

import java.io.*;

public class CreateCustomerFile {
    public static void main(String[] args) {
        // File name for customer records
        String fileName = "customer_records.txt";

        try {
            // Create an empty file
            File file = new File(fileName);

            // Check if the file exists, if not, create it and write initial values
            if (!file.exists()) {
                file.createNewFile();
                FileWriter initialWriter = new FileWriter(file);
                initialWriter.write("000,------,XXXXX\n");
                for (int i = 1; i <= 999; i++) {
                    initialWriter.write(String.format("%03d,------,XXXXX\n", i));
                }
                initialWriter.close();
                System.out.println("File created with initial values: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            // Scanner for user input
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] lines = new String[1000];
            int lineNumber = 0;

            // Read the initial values from the file
            String line;
            while ((line = reader.readLine()) != null) {
                lines[lineNumber++] = line;
            }
            reader.close();

            // Accept user input to populate the file
            while (true) {
                System.out.println("Enter customer details (ID, Last Name, Zip Code), or type 'exit' to quit:");

                // Read user input
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String input = bufferedReader.readLine();

                // Check if the user wants to exit
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Split input by comma
                String[] details = input.split(",");

                // Validate input format
                if (details.length != 3) {
                    System.out.println("Invalid input format. Please enter in the format: ID,Last Name,Zip Code");
                    continue;
                }

                // Parse ID
                int id;
                try {
                    id = Integer.parseInt(details[0].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please enter a valid number.");
                    continue;
                }

                // Validate ID range
                if (id < 0 || id > 999) {
                    System.out.println("ID must be between 0 and 999.");
                    continue;
                }

                // Update the line corresponding to the entered ID
                lines[id] = String.format("%03d,%s,%s", id, details[1].trim(), details[2].trim());

                // Write all lines back to the file
                FileWriter writer = new FileWriter(file);
                writer.write(lines[0] + "\n"); // Write the initial line first
                for (int i = 1; i < 1000; i++) {
                    writer.write(lines[i] != null ? lines[i] + "\n" : "000,------,XXXXX\n");
                }
                writer.close();

                System.out.println("Customer record updated successfully.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



