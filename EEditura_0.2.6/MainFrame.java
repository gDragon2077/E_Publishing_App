import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private Client currentClient;
    private ShoppingCart cart;

    public MainFrame(Client client) {
        this.currentClient = client;
        this.cart = new ShoppingCart();

        setTitle("E-Editura - Welcome " + client.getUsername());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bara de sus cu butoane
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        JButton checkoutButton = new JButton("Checkout");
        topPanel.add(checkoutButton);
        topPanel.add(logoutButton);
        add(topPanel, BorderLayout.NORTH);

        // Incarca cartile din fisier si le afiseaza prin CatalogPanel
        List<Book> books = DataManager.loadBooks();
        CatalogPanel catalogPanel = new CatalogPanel(books, cart);
        JScrollPane scrollPane = new JScrollPane(catalogPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Logout
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        // Checkout
        checkoutButton.addActionListener(e -> {
            new CheckoutFrame(currentClient, cart);
        });

        setVisible(true);
    }
}
