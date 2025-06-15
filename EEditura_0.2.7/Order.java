import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Client client;
    private List<CartItem> items;
    private DeliveryType delivery;
    private Payment payment;
    private Date date;
    private Date orderDate;

    // Constructorul primeste toate informatiile despre comanda
    public Order(Client client, List<CartItem> items, DeliveryType delivery, Payment payment, Date date) {
        this.client = client;
        this.items = items;
        this.delivery = delivery;
        this.payment = payment;
        this.date = date;
    }
    
    public Order() {
        this.items = new ArrayList<>();
    }

    // Returneaza clientul care a facut comanda
    public Client getClient() {
        return client;
    }

    public Date getOrderDate() {
        return orderDate;
    }
        

    // Returneaza lista de CartItem (carte + cantitate)
    public List<CartItem> getItems() {
        return items;
    }

    // Returneaza tipul de livrare
    public DeliveryType getDelivery() {
        return delivery;
    }

    // Returneaza obiectul Payment asociat
    public Payment getPayment() {
        return payment;
    }

    // Returneaza data comenzii
    public Date getDate() {
        return date;
    }

    // Calculeaza totalul comenzii (carti + livrare)
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getBook().getPrice() * item.getQuantity();
        }

        // Adauga taxa suplimentara daca este livrare EXPRESS
        if (delivery == DeliveryType.EXPRESS) {
            total += 5.0;
        }

        return total;
    }

    // Goleste cosul
    public void clear() {
        items.clear();
    }

    // Adauga cartea in cos; daca exista deja, creste cantitatea
    public void addBook(Book book) {
        for (CartItem item : items) {
            if (item.getBook().getTitle().equals(book.getTitle())) {
                item.incrementQuantity();
                return;
            }
        }
        items.add(new CartItem(book));
    }
}
