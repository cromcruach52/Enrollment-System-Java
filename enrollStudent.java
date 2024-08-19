import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class enrollStudent {
    private static final String STUDENT_DETAILS_FILE = "student_details.txt";

    public static void Menu(){
        clearScreen();
        System.out.println("Enroll a Student:");
        display();
    }

    public static void userMenu(){
        clearScreen();
        System.out.println("Enroll now:");
        display();
    }
    public static void display() {
        boolean continueEnrollment = true;
        while (continueEnrollment) {
            String id = generateID();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Birthday (dd/mm/yyyy): ");
            String birthday = scanner.nextLine();
            System.out.print("Grade Level: ");
            int gradeLevel = scanner.nextInt();
            scanner.nextLine();

            int age = calculateAge(birthday);

            double enrollmentCost = getCost.readEnrollmentCost();
            double dueCost = enrollmentCost;

            System.out.println("Enrollment Cost: $" + enrollmentCost);
            System.out.println("Amount Due: $" + dueCost);

            System.out.print("Payment: ");
            double payment = scanner.nextDouble();

            dueCost -= payment;

            System.out.println("Your registration ID: " + id);

            saveStudentDetails(id, name, gender, address, birthday, age, gradeLevel, payment, dueCost);

            System.out.println("Student registered for enrollment successfully!");

            System.out.print("Enroll another student? (Y/N): ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("N")) {
                adminMenu.display();
                continueEnrollment = false;
            }

            clearScreen();
        }
    }

    private static String generateID() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Random random = new Random();
        int randomDigits = random.nextInt(9000) + 1000;
        return "s" + currentYear + randomDigits;
    }

    private static int calculateAge(String birthday) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date birthDate = sdf.parse(birthday);
            Date currentDate = new Date();

            long diffInMillies = Math.abs(currentDate.getTime() - birthDate.getTime());
            long diff = diffInMillies / (24 * 60 * 60 * 1000);
            int age = (int) (diff / 365);

            return age;
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return 0;
        }
    }

    private static void saveStudentDetails(String id, String name, String gender, String address, String birthday, int age,
            int gradeLevel, double payment, double dueCost) {
        try {
            FileWriter fileWriter = new FileWriter(STUDENT_DETAILS_FILE, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String enrollmentTime = sdf.format(new Date());

            String studentDetails = String.format("%s\t%s\t%s\t%s\t%s\t%d\t%d\t%.2f\t%.2f\t%s", id, name, gender, address, birthday,
                    age, gradeLevel, payment, dueCost, enrollmentTime);
            printWriter.println(studentDetails);

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving student details to file.");
            e.printStackTrace();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
