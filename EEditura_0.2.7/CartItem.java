public class CartItem {
    private Book book;
    private int quantity;

    // Constructor ce initializeaza cartea si seteaza cantitatea 1
    public CartItem(Book book) {
        this.book = book;
        this.quantity = 1;
    }

    // Getter pentru carte
    public Book getBook() {
        return book;
    }

    // Getter pentru cantitate
    public int getQuantity() {
        return quantity;
    }

    // Creste cantitatea cu 1
    public void incrementQuantity() {
        quantity++;
    }
}
