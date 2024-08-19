
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class getCost {
    private static final String ENROLLMENT_COST_FILE = "enrollment_cost.txt";

    public static void display() {
        double currentPrice = readEnrollmentCost();
        System.out.println("Current enrollment cost: $" + currentPrice);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new enrollment cost: $");
        double newPrice = scanner.nextDouble();

        writeEnrollmentCost(newPrice);
        System.out.println("Enrollment cost updated successfully.");
        scanner.nextLine();
        adminMenu.display();
    }

    public static double readEnrollmentCost() {
        double enrollmentCost = 0.0;
        try {
            File file = new File(ENROLLMENT_COST_FILE);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextDouble()) {
                enrollmentCost = scanner.nextDouble();
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading enrollment cost from file.");
            e.printStackTrace();
        }
        return enrollmentCost;
    }

    private static void writeEnrollmentCost(double price) {
        try {
            FileWriter fileWriter = new FileWriter(ENROLLMENT_COST_FILE);
            fileWriter.write(String.valueOf(price));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing enrollment cost to file.");
            e.printStackTrace();
        }
    }

}
