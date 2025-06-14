import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CatalogInitializer {
    private static final String BOOKS_FILE = "resources/books.txt";

    public static void initializeCatalog() {
        File file = new File(BOOKS_FILE);
        if (!file.exists() || file.length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Scriem carti de test in formatul: titlu;autor;an;pagini;pret;zileLivrare
                writer.write("Effective Java;Joshua Bloch;2018;416;45.50;3");
                writer.newLine();
                writer.write("Clean Code;Robert C. Martin;2008;464;50.00;2");
                writer.newLine();
                writer.write("Design Patterns;Erich Gamma;1994;395;55.00;4");
                writer.newLine();
                writer.write("Thinking in Java;Bruce Eckel;2006;1150;60.00;5");
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Could not initialize catalog: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        initializeCatalog();
        System.out.println("Catalog initialized successfully.");
    }
}
