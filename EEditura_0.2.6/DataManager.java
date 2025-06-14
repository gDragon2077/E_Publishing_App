import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String BOOKS_FILE = "resources/books.txt";
    private static final String CLIENTS_FILE = "resources/clients.txt";

    // Incarca cartile din fisier
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: titlu;autor;an;pagini;pret;zileLivrare
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String title = parts[0];
                    String author = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int pages = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    int deliveryDays = Integer.parseInt(parts[5]);
                    books.add(new Book(title, author, year, pages, price, deliveryDays));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load books: " + e.getMessage());
        }
        return books;
    }

    // Incarca clientii din fisier
    public static List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: prenume;nume;adresa;username;parolaCriptata
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    clients.add(new Client(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load clients: " + e.getMessage());
        }
        return clients;
    }

    // Salveaza clientul nou in fisier
    public static void saveClient(Client client) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLIENTS_FILE, true))) {
            String line = client.getFirstName() + ";" + client.getLastName() + ";" + client.getAddress() + ";" +
                          client.getUsername() + ";" + client.getPasswordHash();
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Could not save client: " + e.getMessage());
        }
    }
}
