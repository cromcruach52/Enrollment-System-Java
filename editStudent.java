import java.io.*;
import java.util.Scanner;

public class editStudent {
    private static final String STUDENT_DETAILS_FILE = "enrolled_student_details.txt";

    public static void display() {
        clearScreen();
        showEnrolledStudents.display();

        System.out.println("Edit Student Details:");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the student you want to edit: ");
        String studentId = scanner.nextLine();

        if (studentExists(studentId)) {
            System.out.println("Student found!");
            System.out.println("1. View Student Details");
            System.out.println("2. Update Student Details");
            System.out.println("3. Delete Student");
            System.out.println("4. Go Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewStudentDetails(studentId);
                    break;
                case 2:
                    updateStudentDetails(studentId);
                    break;
                case 3:
                    deleteStudent(studentId);
                    break;
                case 4:
                    adminMenu.display();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    scanner.nextLine(); 
                    showEnrolledStudents.Menu();
                    break;
            }
        } else {
            System.out.println("Student not found with ID: " + studentId);
            scanner.nextLine(); 
            showEnrolledStudents.Menu();
        }

        scanner.close();
    }

    private static boolean studentExists(String studentId) {
        try {
            File file = new File(STUDENT_DETAILS_FILE);
            Scanner scanner = new Scanner(file);
    
            
            scanner.nextLine();
    
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                if (rowData.length > 0 && rowData[0].equals(studentId)) {
                    scanner.close();
                    return true;
                }
            }
    
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading student details file.");
            editStudent.display();
        }
    
        return false;
    }

    private static void viewStudentDetails(String studentId) {
        try {
            File file = new File(STUDENT_DETAILS_FILE);
            Scanner scanner = new Scanner(file);
    
            
            scanner.nextLine();
    
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rowData = line.split("\t");
                if (rowData.length > 0 && rowData[0].equals(studentId)) {
                    System.out.println("Student Details:");
                    System.out.println("----------------");
                    System.out.println("ID: " + rowData[0]);
                    System.out.println("Name: " + rowData[1]);
                    System.out.println("Gender: " + rowData[2]);
                    System.out.println("Address: " + rowData[3]);
                    System.out.println("Birthday: " + rowData[4]);
                    System.out.println("Age: " + rowData[5]);
                    System.out.println("Grade Level: " + rowData[6]);
                    System.out.println("Due Cost: " + rowData[8]);
                    System.out.println("Date Applied: " + rowData[9]);
                    System.out.println();
                    System.out.println("----------------");
                    break;
                }
            }
    
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading student details file.");
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        showEnrolledStudents.display();
    }

    private static void updateStudentDetails(String studentId) {
        try {
            File file = new File(STUDENT_DETAILS_FILE);
            File tempFile = new File("temp_student_details.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("\t");
                if (rowData.length > 0 && rowData[0].equals(studentId)) {
                    
                    System.out.println("Update Student Details:");
                    System.out.println("-----------------------");

                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Birthday: ");
                    String birthday = scanner.nextLine();

                    System.out.print("Age: ");
                    String Age = scanner.nextLine();

                    System.out.print("Grade Level: ");
                    String gradeLevel = scanner.nextLine();

                    System.out.print("Due Cost: ");
                    String dueCost = scanner.nextLine();

                    
                    String updatedLine = studentId + "\t" + name + "\t" + gender + "\t" + address + "\t" + birthday + "\t" + Age + "\t" + gradeLevel + "\t" + rowData[7]+ "\t" + dueCost + "\t" +rowData[9];

                    writer.write(updatedLine);
                } else {
                    
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            
            if (file.delete()) {
                tempFile.renameTo(file);
                System.out.println("Student details updated successfully.");
            } else {
                System.out.println("Error updating student details.");
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing student details file.");
            e.printStackTrace();
        }

        showEnrolledStudents.Menu();
    }

    private static void deleteStudent(String studentId) {
        try {
            File file = new File(STUDENT_DETAILS_FILE);
            File tempFile = new File("temp_student_details.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("\t");
                if (rowData.length > 0 && rowData[0].equals(studentId)) {
                    continue; 
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            
            if (file.delete()) {
                tempFile.renameTo(file);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Error deleting student.");
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing student details file.");
            e.printStackTrace();
        }

        showEnrolledStudents.Menu();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
