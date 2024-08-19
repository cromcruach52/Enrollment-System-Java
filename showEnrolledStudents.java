import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class showEnrolledStudents {
    private static final String ENROLLED_STUDENTS_FILE = "enrolled_student_details.txt";

    public static void display() {
        clearScreen();
        System.out.println("Enrolled Students:");
        System.out.println("**************************************************************************************************************************************");
        System.out.println("ID            Name                 Gender      Address      Birthday         Age   Grade Level   Due Cost     Date Registered");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        try {
            File file = new File(ENROLLED_STUDENTS_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                String id = rowData[0];
                String name = rowData[1];
                String gender = rowData[2];
                String address = rowData[3];
                String birthday = rowData[4];
                String age = rowData[5];
                String gradeLevel = rowData[6];
                String dueCost = rowData[8];
                String date = rowData[9];

                System.out.printf("%-13s %-20s %-11s %-12s %-16s %-5s %-13s %-12s %-13s\n", id, name, gender, address, birthday, age, gradeLevel, dueCost, date);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading enrolled student details file.");
            e.printStackTrace();
        }

        System.out.println("**************************************************************************************************************************************");
    }

    public static void searchById(String id) {
        try {
            File file = new File(ENROLLED_STUDENTS_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                String enrolledId = rowData[0];

                if (enrolledId.equals(id)) {
                    String name = rowData[1];
                    String gender = rowData[2];
                    String address = rowData[3];
                    String birthday = rowData[4];
                    String age = rowData[5];
                    String gradeLevel = rowData[6];
                    String dueCost = rowData[8];
                    String date = rowData[9];
                    System.out.println("**************************************************************************************************************************************");
                    System.out.println("ID            Name                 Gender      Address      Birthday         Age   Grade Level   Due Cost     Date Registered");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.printf("%-13s %-20s %-11s %-12s %-16s %-5s %-13s %-12s %-13s\n", enrolledId, name, gender, address, birthday, age, gradeLevel, dueCost, date);
                    
                    System.out.println("**************************************************************************************************************************************");
                    
                    scanner.close();
                    return;
                }
               
            }

            scanner.close();
            System.out.println("No student with ID " + id + " found.");
        } catch (IOException e) {
            System.out.println("Error reading enrolled student details file.");
            e.printStackTrace();
        }
    }

    public static void searchByName(String name) {
        try {
            File file = new File(ENROLLED_STUDENTS_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                String enrolledName = rowData[1];

                if (enrolledName.equalsIgnoreCase(name.trim())) {
                    String id = rowData[0];
                    String gender = rowData[2];
                    String address = rowData[3];
                    String birthday = rowData[4];
                    String age = rowData[5];
                    String gradeLevel = rowData[6];
                    String dueCost = rowData[8];
                    String date = rowData[9];

                    System.out.println("**************************************************************************************************************************************");
                    System.out.println("ID            Name                 Gender      Address      Birthday         Age   Grade Level   Due Cost     Date Registered");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.printf("%-13s %-20s %-11s %-12s %-16s %-5s %-13s %-12s %-13s\n", id, enrolledName, gender, address, birthday, age, gradeLevel, dueCost, date);
                    
                    System.out.println("**************************************************************************************************************************************");
                   
                    scanner.close();
                    return;
                }
            }

            scanner.close();
            System.out.println("No student with name " + name + " found.");
        } catch (IOException e) {
            System.out.println("Error reading enrolled student details file.");
            e.printStackTrace();
        }
    }

    public static void Menu() {
        display();
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Edit Student Details");
        System.out.println("4. Back to Admin Menu");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                System.out.println("Enter the ID: ");
                String id = scanner.nextLine();
                searchById(id);
                break;
            case 2:
                System.out.println("Enter the Name: ");
                String name = scanner.nextLine();
                searchByName(name);
                break;
            case 3:
                editStudent.display();
                break;
            case 4:
                adminMenu.display();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                Menu();
                break;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
