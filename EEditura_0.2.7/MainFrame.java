import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class MainFrame extends JFrame {
    private Client currentClient;
    private Order cart;
    private int itemCount = 0;

    private JPanel catalogGrid;
    private JButton checkoutButton;

    public MainFrame(Client client) {
        this.currentClient = client;
        this.cart = new Order();

        setTitle("E-Editura - Welcome " + client.getUsername());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createTopBar(), BorderLayout.NORTH);
        add(createCatalogArea(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout(20, 30));
        topBar.setBackground(Color.BLACK);

        // LOGO
        JButton logoButton;
        try {
            URL logoUrl = getClass().getResource("/icons/logo2.png");
            if (logoUrl == null) throw new Exception("logo2.png not found");
            Image scaledLogo = new ImageIcon(logoUrl).getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
            logoButton = new JButton(new ImageIcon(scaledLogo));
        } catch (Exception e) {
            logoButton = new JButton("E-Editura");
        }

        logoButton.setBackground(Color.BLACK);
        logoButton.setBorderPainted(false);
        logoButton.setFocusPainted(false);
        logoButton.setContentAreaFilled(false);
        logoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoButton.setPreferredSize(new Dimension(182, 105));
        topBar.add(logoButton, BorderLayout.WEST);

        // RIGHT PANEL cu Cart si Logout
        checkoutButton = new JButton("Cart (0)");
        checkoutButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setContentAreaFilled(false);
        checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(checkoutButton);
        rightPanel.add(logoutButton);
        topBar.add(rightPanel, BorderLayout.EAST);

        // TITLU SECTIUNE
        JLabel sectionTitle = new JLabel("VOLUME");
        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setFont(new Font("Monospaced", Font.PLAIN, 40));
        topBar.add(sectionTitle, BorderLayout.SOUTH);

        // ACTIUNI
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        checkoutButton.addActionListener(e -> {
            new CheckoutFrame(currentClient, cart);
        });

        return topBar;
    }

    private JScrollPane createCatalogArea() {
        catalogGrid = new JPanel();
        catalogGrid.setLayout(new GridLayout(0, 4, 20, 20));
        catalogGrid.setBackground(Color.BLACK);
        catalogGrid.setBorder(new EmptyBorder(20, 20, 20, 20));

        List<Book> books = DataManager.loadBooks();
        for (Book book : books) {
            catalogGrid.add(createBookCard(book));
        }

        JScrollPane scrollPane = new JScrollPane(catalogGrid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        return scrollPane;
    }

    private JPanel createBookCard(Book book) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(200, 280));

        // TITLU
        JLabel title = new JLabel("<html><center><b>" + book.getTitle() + "</b></center></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));

        // AUTOR
        JLabel author = new JLabel("<html><center>" + book.getAuthor() + "</center></html>");
        author.setHorizontalAlignment(SwingConstants.CENTER);
        author.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // RECENZII
        JTextArea reviews = new JTextArea();
        reviews.setEditable(false);
        reviews.setBackground(new Color(245, 245, 245));
        reviews.setFont(new Font("SansSerif", Font.ITALIC, 10));
        reviews.setLineWrap(true);
        reviews.setWrapStyleWord(true);
        reviews.setBorder(null);
        StringBuilder reviewText = new StringBuilder("Reviews:\n");
        for (String rev : book.getReviews()) {
            if (!rev.isBlank()) reviewText.append("• ").append(rev).append("\n");
        }
        reviews.setText(reviewText.toString());
        reviews.setPreferredSize(new Dimension(180, 60));

        // ADAUGARE SI PRET
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        JLabel price = new JLabel(String.format(" %.2f RON", book.getPrice()));
        price.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bottomPanel.add(price, BorderLayout.WEST);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        bottomPanel.add(quantitySpinner, BorderLayout.CENTER);

        JButton addToCart = new JButton("Add");
        addToCart.setBackground(new Color(33, 150, 243));
        addToCart.setForeground(Color.WHITE);
        addToCart.setFont(new Font("SansSerif", Font.PLAIN, 12));
        addToCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bottomPanel.add(addToCart, BorderLayout.EAST);

        addToCart.addActionListener(e -> {
            int qty = (int) quantitySpinner.getValue();
            for (int i = 0; i < qty; i++) {
                cart.addBook(book);
                itemCount++;
            }
            checkoutButton.setText("Cart (" + itemCount + ")");
        });

        // ADAUGARE IN CARD
        card.add(title, BorderLayout.NORTH);
        card.add(author, BorderLayout.CENTER);
        card.add(reviews, BorderLayout.SOUTH);
        card.add(bottomPanel, BorderLayout.PAGE_END);

        return card;
    }
}
