import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class userMenu {
    private static final String STUDENT_DETAILS_FILE = "student_details.txt";
    private static final String ENROLLED_STUDENT_DETAILS_FILE = "enrolled_student_details.txt";

    public static void display() {
        clearScreen();
        System.out.println("User Menu:");
        System.out.println("1. Enroll Now");
        System.out.println("2. View Enrollment Status");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        
        switch (choice) {
            case 1:
                enrollStudent.display();
                break;
            case 2:
                showEnrollmentStatus();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    public static void showEnrollmentStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String status = getEnrollmentStatus(studentId, name);
        double dueCost = getCurrentDueCost(studentId);

        System.out.println("Enrollment Status:");
        System.out.println("------------------");
        System.out.println("ID: " + studentId);
        System.out.println("Status: " + status);
        System.out.println("Due Cost: $" + dueCost);
        System.out.println();
        System.out.println("------------------");

        
        System.out.print("Press Enter to go back to the User Menu...");
        scanner.nextLine();

        display();
    }

    private static String getEnrollmentStatus(String studentId, String studentName) {
        try {
            File enrolledStudentsFile = new File(ENROLLED_STUDENT_DETAILS_FILE);
            File studentDetailsFile = new File(STUDENT_DETAILS_FILE);
            Scanner enrolledScanner = new Scanner(enrolledStudentsFile);
            Scanner studentScanner = new Scanner(studentDetailsFile);
    
            while (enrolledScanner.hasNextLine()) {
                String enrolledLine = enrolledScanner.nextLine();
                String[] enrolledData = enrolledLine.split("\t");
                if (enrolledData.length > 1 && enrolledData[0].equals(studentId) && enrolledData[1].equals(studentName)) {
                    enrolledScanner.close();
                    studentScanner.close();
                    return "Enrolled";
                }
            }
            enrolledScanner.close();
    
            while (studentScanner.hasNextLine()) {
                String studentLine = studentScanner.nextLine();
                String[] studentData = studentLine.split("\t");
                if (studentData.length > 1 && studentData[0].equals(studentId) && studentData[1].equals(studentName)) {
                    studentScanner.close();
                    return "Enrollment in Progress";
                }
            }
            studentScanner.close();
        } catch (IOException e) {
            System.out.println("Error reading enrollment status.");
            e.printStackTrace();
        }
    
        return "Unregistered";
    }

    private static double getCurrentDueCost(String studentId) {
        try {
            File studentDetailsFile = new File(STUDENT_DETAILS_FILE);
            Scanner scanner = new Scanner(studentDetailsFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                if (rowData.length > 0 && rowData[0].equals(studentId)) {
                    double dueCost = Double.parseDouble(rowData[7]);
                    scanner.close();
                    return dueCost;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading due cost.");
            e.printStackTrace();
        }

        return 0.0;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
