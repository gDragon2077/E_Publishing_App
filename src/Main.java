import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Selectați o opțiune:");
            System.out.println("1. Adaugă utilizator");
            System.out.println("2. Autentificare utilizator");
            System.out.println("3. Schimbă parola utilizator");
            System.out.println("4. Șterge utilizator");
            System.out.println("5. Ieși");
            System.out.print("Alegere: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (choice) {
                case 1:
                    System.out.print("Introduceți username: ");
                    String username = scanner.nextLine();
                    System.out.print("Introduceți parola: ");
                    String password = scanner.nextLine();
                    System.out.print("Introduceți nume: ");
                    String name = scanner.nextLine();
                    System.out.print("Introduceți adresă: ");
                    String address = scanner.nextLine();
                    System.out.print("Introduceți email: ");
                    String email = scanner.nextLine();
                    try {
                        userManagement.inregistrareUser(username, password, name, address, email);
                        System.out.println("Utilizator adăugat cu succes!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Introduceți username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Introduceți parola: ");
                    String loginPassword = scanner.nextLine();
                    try {
                        User user = userManagement.authUser(loginUsername, loginPassword);
                        System.out.println("Autentificare reușită! Bun venit, " + user.getName() + "!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Introduceți username: ");
                    String changeUsername = scanner.nextLine();
                    System.out.print("Introduceți parola veche: ");
                    String oldPassword = scanner.nextLine();
                    System.out.print("Introduceți parola nouă: ");
                    String newPassword = scanner.nextLine();
                    try {
                        userManagement.changePassword(changeUsername, oldPassword, newPassword);
                        System.out.println("Parola schimbată cu succes!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Introduceți username pentru ștergere: ");
                    String deleteUsername = scanner.nextLine();
                    try {
                        userManagement.deleteUser(deleteUsername);
                        System.out.println("Utilizator șters cu succes!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
        scanner.close();
        System.out.println("Program închis.");
    }
}
