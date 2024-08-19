import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class showEnrollmentRequests {
    private static final String STUDENT_DETAILS_FILE = "student_details.txt";

    public static void display() {
        clearScreen();
        System.out.println("Enrollment Requests:");
        System.out.println("**************************************************************************************************************************************");
        System.out.println("ID            Name                 Gender      Address      Birthday         Age   Grade Level   Due Cost     Date Registered");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    
        try {
            File file = new File(STUDENT_DETAILS_FILE);
            Scanner scanner = new Scanner(file);
    
            
            scanner.nextLine();
    
            
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
            System.out.println("Error reading student details file.");
            e.printStackTrace();
        }
    
        System.out.println("**************************************************************************************************************************************");
    
        acceptEnrollment();
    }
    

    public static void acceptEnrollment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1. Accept Enrollment");
        System.out.println("2. Back to Admin Menu");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                System.out.print("Enter the ID of the student to accept enrollment: ");
                String studentId = scanner.nextLine();
                acceptStudentEnrollment(studentId);
                break;
            case 2:
                adminMenu.display();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                acceptEnrollment();
                break;
        }

        scanner.close();
    }

    public static void acceptStudentEnrollment(String studentId) {
        try {
            File enrollmentRequestsFile = new File(STUDENT_DETAILS_FILE);
            File enrolledStudentsFile = new File("enrolled_student_details.txt");
    
            Scanner scanner = new Scanner(enrollmentRequestsFile);
            StringBuilder newData = new StringBuilder();
    
            
            String studentData = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(studentId)) {
                    studentData = line; 
                    continue; 
                }
                newData.append(line).append("\n");
            }
            scanner.close();
    
    
            FileWriter writer = new FileWriter(enrollmentRequestsFile);
            writer.write(newData.toString());
            writer.close();
    
            
            FileWriter enrolledWriter = new FileWriter(enrolledStudentsFile, true);
            enrolledWriter.write(studentData + "\n");
            enrolledWriter.close();
    
            System.out.println("Enrollment accepted for student with ID: " + studentId);
            display(); 
        } catch (IOException e) {
            System.out.println("Error accepting student enrollment.");
            e.printStackTrace();
        }
    }
    

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
