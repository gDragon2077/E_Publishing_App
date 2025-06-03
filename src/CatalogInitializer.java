// CatalogInitializer.java - initializeaza catalogul cu carti exemplu

import java.util.*;
import java.io.*;

public class CatalogInitializer {
    public static void main(String[] args) {
        String filePath = "catalog.dat";
        File file = new File(filePath);

        if (!file.exists()) {
            List<String> authors1 = Arrays.asList("J.K. Rowling");
            List<String> authors2 = Arrays.asList("George Orwell");
            List<String> authors3 = Arrays.asList("Yuval Noah Harari");

            List<Review> reviews1 = Arrays.asList(
                    new Review("Alice", "Fantastic book!", 5),
                    new Review("Bob", "Magical experience", 4)
            );

            List<Review> reviews2 = Arrays.asList(
                    new Review("Carol", "Very relevant", 5),
                    new Review("Dan", "A bit dark but insightful", 4)
            );

            List<Review> reviews3 = Arrays.asList(
                    new Review("Eve", "Eye-opening and deep", 5)
            );

            Catalog catalog = new Catalog();
            catalog.addBook(new Book("Harry Potter", authors1, 1997, 320, 49.99, 5, reviews1));
            catalog.addBook(new Book("1984", authors2, 1949, 328, 39.90, 3, reviews2));
            catalog.addBook(new Book("Sapiens", authors3, 2011, 512, 59.50, 4, reviews3));

            DataManager.saveData(catalog, filePath);
            System.out.println("Catalog initialized with sample books.");
        } else {
            System.out.println("Catalog already exists. No action taken.");
        }
    }
}
