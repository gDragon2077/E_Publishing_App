// MainFrame.java - interfata principala dupa autentificare

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private Client client;
    private Catalog catalog;
    private ShoppingCart cart;
    private JTextArea catalogArea;
    private JButton addButton;
    private JButton checkoutButton;
    private JTextField titleField;

    public MainFrame(Client client) {
        this.client = client;
        this.catalog = (Catalog) DataManager.loadData("catalog.dat");
        if (catalog == null) catalog = new Catalog();
        this.cart = new ShoppingCart();

        setTitle("Welcome, " + client.getFirstName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        catalogArea = new JTextArea();
        catalogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(catalogArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        titleField = new JTextField(20);
        addButton = new JButton("Add to Cart");
        checkoutButton = new JButton("Checkout");
        bottomPanel.add(new JLabel("Book title:"));
        bottomPanel.add(titleField);
        bottomPanel.add(addButton);
        bottomPanel.add(checkoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        populateCatalog();

        addButton.addActionListener(e -> addBookToCart());
        checkoutButton.addActionListener(e -> checkout());

        setVisible(true);
    }

    private void populateCatalog() {
        StringBuilder sb = new StringBuilder();
        for (Book book : catalog.getBooks()) {
            sb.append(book.toString()).append("\n");
        }
        catalogArea.setText(sb.toString());
    }

    private void addBookToCart() {
        String title = titleField.getText();
        Book book = catalog.getBookByTitle(title);
        if (book != null) {
            cart.addBook(book);
            JOptionPane.showMessageDialog(this, "Book added to cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkout() {
        CheckoutDialog dialog = new CheckoutDialog(this);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            DeliveryType deliveryType = DeliveryType.valueOf(dialog.getSelectedDelivery());
            Payment payment = null;

            if ("CREDIT_CARD".equals(dialog.getSelectedPayment())) {
                String type = JOptionPane.showInputDialog(this, "Card Type:");
                String number = JOptionPane.showInputDialog(this, "Card Number (16 digits):");
                String date = JOptionPane.showInputDialog(this, "Valid Until (YYYY-MM-DD):");
                payment = new CreditCardPayment(type, number, java.time.LocalDate.parse(date));
            } else {
                String bank = JOptionPane.showInputDialog(this, "Bank Name:");
                String acc = JOptionPane.showInputDialog(this, "Account Number:");
                payment = new BankPayment(bank, acc);
            }

            if (payment != null && payment.authorize()) {
                Order order = new Order(client, cart.getBooks(), deliveryType, payment);
                List<Order> orders = DataManager.loadList("orders.dat");
                if (orders == null) orders = new java.util.ArrayList<>();
                orders.add(order);
                DataManager.saveData(orders, "orders.dat");
                JOptionPane.showMessageDialog(this, "Order placed successfully.");
                cart.clear();
            } else {
                JOptionPane.showMessageDialog(this, "Payment failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
