// Catalog.java - gestioneaza lista de carti disponibile in sistem

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Book> books;

    public Catalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        // Comentariu: adauga o carte noua in lista catalogului
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
