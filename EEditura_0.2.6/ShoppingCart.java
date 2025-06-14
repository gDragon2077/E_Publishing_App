import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    // Adauga o carte in cos
    public void addBook(Book book) {
        for (CartItem item : items) {
            if (item.getBook().equals(book)) {
                item.incrementQuantity();
                return;
            }
        }
        items.add(new CartItem(book, 1));
    }

    // Sterge o carte complet din cos
    public void removeBook(Book book) {
        items.removeIf(item -> item.getBook().equals(book));
    }

    // Returneaza toate cartile din cos
    public List<CartItem> getItems() {
        return items;
    }

    // Calculeaza totalul
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getBook().getPrice() * item.getQuantity();
        }
        return total;
    }

    // Goleste cosul
    public void clear() {
        items.clear();
    }
}
