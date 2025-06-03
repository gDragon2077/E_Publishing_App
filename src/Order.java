// Order.java - reprezinta o comanda finalizata a unui client

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private Client client;
    private List<Book> books;
    private DeliveryType deliveryType;
    private Payment payment;

    public Order(Client client, List<Book> books, DeliveryType deliveryType, Payment payment) {
        this.client = client;
        this.books = books;
        this.deliveryType = deliveryType;
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public List<Book> getBooks() {
        return books;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Order by: " + client.getUsername() + ", books: " + books.size() + ", delivery: " + deliveryType;
    }
}
