import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String BOOKS_FILE = "resources/books.csv";
    private static final String CLIENTS_FILE = "resources/clients.txt";

    // Incarca cartile din fisier CSV cu 5 recenzii incluse
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // sarim peste antet
                    continue;
                }

                // Se presupune format: titlu,autor,an,pagini,pret,zileLivrare,rev1,rev2,rev3,rev4,rev5
                String[] parts = line.split(",", -1); // -1 pentru a nu ignora campuri goale
                if (parts.length >= 12) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    int pages = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    int deliveryDays = Integer.parseInt(parts[5].trim());
                    List<String> reviews = new ArrayList<>();
                    for (int i = 6; i < 11; i++) {
                        reviews.add(parts[i].trim());
                    }
                    String path = "resources/covers/" + parts[11].trim() + ".jpg"; // presupunem ca ultimul camp este calea catre fisier
                    books.add(new Book(title, author, year, pages, price, deliveryDays, reviews, path));
                }
            }
        } catch (IOException | NumberFormatException e) {
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
