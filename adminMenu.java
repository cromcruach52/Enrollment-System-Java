import java.util.Scanner;

public class adminMenu {
    public static void display() {
        clearScreen();

        
        System.out.println("Admin Menu:");
        System.out.println("1. Show Enrolled Students");
        System.out.println("2. Show Enrollment Requests");
        System.out.println("3. Enroll a Student");
        System.out.println("4. Edit Tuition Cost");
        System.out.println("5. Exit");

        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        
        switch (choice) {
            case 1:
                showEnrolledStudents.Menu();
                break;
            case 2:
                showEnrollmentRequests.display();
                break;
            case 3:
                enrollStudent.Menu();
                break;
            case 4:
                getCost.display();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
