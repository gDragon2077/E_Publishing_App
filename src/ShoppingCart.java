// ShoppingCart.java - gestioneaza cartile adaugate de un client pentru comanda

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    private List<Book> books;

    public ShoppingCart() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        // Adauga o carte in cos
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }

    public void clear() {
        books.clear();
    }
}
