import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Date;

public class CheckoutFrame extends JFrame {
    private Client client;
    private ShoppingCart cart;

    private JComboBox<String> deliveryBox;
    private JComboBox<String> paymentBox;

    // Card panel + fields
    private JPanel cardPanel;
    private JTextField cardNumberField;
    private JTextField expMonthField;
    private JTextField expYearField;

    // Bank panel + fields
    private JPanel bankPanel;
    private JTextField bankNameField;
    private JTextField ibanField;

    public CheckoutFrame(Client client, ShoppingCart cart) {
        this.client = client;
        this.cart = cart;

        setTitle("Checkout - E-Editura");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabel cos
        String[] columns = {"Title", "Quantity", "Price (RON)", "Line Total"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (CartItem item : cart.getItems()) {
            Object[] row = {
                item.getBook().getTitle(),
                item.getQuantity(),
                String.format("%.2f", item.getBook().getPrice()),
                String.format("%.2f", item.getBook().getPrice() * item.getQuantity())
            };
            model.addRow(row);
        }
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Zona de optiuni
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        deliveryBox = new JComboBox<>(new String[]{"STANDARD", "EXPRESS"});
        paymentBox = new JComboBox<>(new String[]{"Card", "Bank Transfer"});

        JPanel deliveryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deliveryPanel.add(new JLabel("Delivery method:"));
        deliveryPanel.add(deliveryBox);

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.add(new JLabel("Payment method:"));
        paymentPanel.add(paymentBox);

        optionsPanel.add(deliveryPanel);
        optionsPanel.add(paymentPanel);

        // Card panel
        cardPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        cardNumberField = new JTextField();
        expMonthField = new JTextField();
        expYearField = new JTextField();
        cardPanel.add(new JLabel("Card number:"));
        cardPanel.add(cardNumberField);
        cardPanel.add(new JLabel("Expiration month (MM):"));
        cardPanel.add(expMonthField);
        cardPanel.add(new JLabel("Expiration year (YYYY):"));
        cardPanel.add(expYearField);
        optionsPanel.add(cardPanel);

        // Bank panel
        bankPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        bankNameField = new JTextField();
        ibanField = new JTextField();
        bankPanel.add(new JLabel("Bank name:"));
        bankPanel.add(bankNameField);
        bankPanel.add(new JLabel("IBAN:"));
        bankPanel.add(ibanField);
        optionsPanel.add(bankPanel);

        JButton confirmBtn = new JButton("Confirm order");
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(confirmBtn);

        add(optionsPanel, BorderLayout.SOUTH);

        // Ascunde panels initial
        togglePaymentPanels();

        // Schimbare selectie metoda de plata
        paymentBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                togglePaymentPanels();
            }
        });

        // Confirmare
        confirmBtn.addActionListener(e -> processOrder());

        setVisible(true);
    }

    private void togglePaymentPanels() {
        String paymentMethod = (String) paymentBox.getSelectedItem();
        cardPanel.setVisible("Card".equals(paymentMethod));
        bankPanel.setVisible("Bank Transfer".equals(paymentMethod));
        revalidate();
        repaint();
    }

    private void processOrder() {
        String deliveryStr = (String) deliveryBox.getSelectedItem();
        DeliveryType delivery = DeliveryType.valueOf(deliveryStr);
        Payment payment = null;

        if ("Card".equals(paymentBox.getSelectedItem())) {
            String cardNumber = cardNumberField.getText().trim();
            int month, year;
            try {
                month = Integer.parseInt(expMonthField.getText().trim());
                year = Integer.parseInt(expYearField.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid expiration date.");
                return;
            }

            if (!CardUtils.isValidLuhn(cardNumber)) {
                JOptionPane.showMessageDialog(this, "Invalid card number (Luhn check failed).");
                return;
            }

            if (!CardUtils.isAcceptedCard(cardNumber)) {
                JOptionPane.showMessageDialog(this, "Insufficient funds (card not accepted).");
                return;
            }

            if (!CardUtils.isNotExpired(month, year)) {
                JOptionPane.showMessageDialog(this, "Card expired.");
                return;
            }

            payment = new CreditCardPayment(cart.getTotal(), cardNumber, month, year);
        } else {
            String bankName = bankNameField.getText().trim();
            String iban = ibanField.getText().trim();

            if (bankName.isEmpty() || iban.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete bank payment details.");
                return;
            }

            if (!CardUtils.isValidIBAN(iban)) {
                JOptionPane.showMessageDialog(this, "Invalid IBAN format.");
                return;
            }

            payment = new BankPayment(cart.getTotal(), bankName, iban);
        }

        Order order = new Order(client, cart.getItems(), delivery, payment, new Date());
        File invoice = InvoiceGenerator.generateInvoice(order);

        if (invoice != null) {
            JOptionPane.showMessageDialog(this, "Invoice generated.");
            cart.clear();
            dispose();
            new InvoiceViewFrame(invoice);
        } else {
            JOptionPane.showMessageDialog(this, "Could not generate invoice.");
        }
    }
}
