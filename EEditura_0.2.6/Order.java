import java.util.Date;
import java.util.List;

public class Order {
    private Client client;
    private List<CartItem> items;
    private DeliveryType deliveryType;
    private Payment payment;
    private Date orderDate;
    private double total;

    public Order(Client client, List<CartItem> items, DeliveryType deliveryType, Payment payment, Date orderDate) {
        this.client = client;
        this.items = items;
        this.deliveryType = deliveryType;
        this.payment = payment;
        this.orderDate = orderDate;
        this.total = calculateTotal();
    }

    // Calculeaza totalul inclusiv taxa de livrare daca este EXPRESS
    private double calculateTotal() {
        double sum = 0.0;
        for (CartItem item : items) {
            sum += item.getBook().getPrice() * item.getQuantity();
        }
        if (deliveryType == DeliveryType.EXPRESS) {
            sum += 5.0; // taxa suplimentara
        }
        return sum;
    }

    public Client getClient() {
        return client;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public Payment getPayment() {
        return payment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotal() {
        return total;
    }
}
