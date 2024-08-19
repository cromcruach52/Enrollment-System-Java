import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentSystem {
    private static final String ADMIN_CREDENTIALS_FILE = "admin_credentials.txt";
    private static final String USER_CREDENTIALS_FILE = "user_credentials.txt";
    private List<Credentials> adminCredentials;
    private List<Credentials> userCredentials;
    private boolean loggedIn;

    public EnrollmentSystem() {
        adminCredentials = new ArrayList<>();
        userCredentials = new ArrayList<>();
        loggedIn = false;
    }

    public static void main(String[] args) {
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();
        enrollmentSystem.loadCredentials();
        enrollmentSystem.run();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!loggedIn) {
            clearScreen();
            System.out.println("Enrollment System");
            System.out.println("1. Register User");
            System.out.println("2. Admin Login");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        registerUser(reader);
                        break;
                    case 2:
                        adminLogin(reader);
                        break;
                    case 3:
                        userLogin(reader);
                        break;
                    case 4:
                        System.out.println("Exiting the system.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading input: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while closing the input reader: " + e.getMessage());
        }
    }

    private void registerUser(BufferedReader reader) {
        try {
            System.out.print("Enter user username: ");
            String username = reader.readLine();
            System.out.print("Enter user password: ");
            String password = reader.readLine();

            userCredentials.add(new Credentials(username, password));
            saveCredentials(userCredentials, USER_CREDENTIALS_FILE);

            System.out.println("User registered successfully.");
            
        } catch (IOException e) {
            System.out.println("An error occurred while registering user: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void adminLogin(BufferedReader reader) {
        try {
            clearScreen();
            System.out.print("Enter admin username: ");
            String username = reader.readLine();
            System.out.print("Enter admin password: ");
            String password = reader.readLine();

            if (verifyCredentials(adminCredentials, username, password)) {
                System.out.println("Admin login successful.");
                loggedIn = true;
                adminMenu.display();
            } else {
                System.out.println("Invalid admin username or password.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        }
    }

    private void userLogin(BufferedReader reader) {
        try {
            System.out.print("Enter user username: ");
            String username = reader.readLine();
            System.out.print("Enter user password: ");
            String password = reader.readLine();

            if (verifyCredentials(userCredentials, username, password)) {
                System.out.println("User login successful.");
                loggedIn = true;
                userMenu.display();
            } else {
                System.out.println("Invalid user username or password.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        }
    }

    private boolean verifyCredentials(List<Credentials> credentialsList, String username, String password) {
        for (Credentials credentials : credentialsList) {
            if (credentials.getUsername().equals(username) && credentials.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveCredentials(List<Credentials> credentialsList, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Credentials credentials : credentialsList) {
                writer.println(credentials.getUsername() + "," + credentials.getPassword());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving credentials: " + e.getMessage());
        }
    }

    private void loadCredentials() {
        loadCredentials(adminCredentials, ADMIN_CREDENTIALS_FILE);
        loadCredentials(userCredentials, USER_CREDENTIALS_FILE);
    }

    private void loadCredentials(List<Credentials> credentialsList, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    credentialsList.add(new Credentials(username, password));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading credentials: " + e.getMessage());
        }
    }

    private static class Credentials {
        private String username;
        private String password;

        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
