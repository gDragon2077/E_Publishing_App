import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class CatalogPanel extends JPanel {
    private Order cart;
    private MainFrame mainFrame;
    private JPanel container;
    private List<Book> books;
    private int cardWidth = 220;

    public CatalogPanel(List<Book> books, Order cart, MainFrame mainFrame) {
        this.books = books;
        this.cart = cart;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new GridLayout(0, 1, 10, 10)); // initial 1 coloana
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        for (Book book : books) {
            container.add(createBookCard(book));
        }

        // listener pentru redimensionare
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateGridColumns();
            }
        });

        updateGridColumns();
    }

    private void updateGridColumns() {
        int width = getWidth();
        int columns = Math.max(1, width / (cardWidth + 20));
        GridLayout layout = (GridLayout) container.getLayout();
        layout.setColumns(columns);
        container.revalidate();
    }

    private JPanel createBookCard(Book book) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(cardWidth, 250));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("<html><center>" + book.getTitle() + "</center></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel authorLabel = new JLabel("<html><center>" + book.getAuthor() + "</center></html>");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + book.getPrice() + " RON");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel yearLabel = new JLabel("Year: " + book.getYear());
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Optional: eliminat complet recenziile, daca sunt inutile
        JTextArea reviewsArea = new JTextArea();
        reviewsArea.setEditable(false);
        reviewsArea.setBackground(new Color(245, 245, 245));
        reviewsArea.setFont(new Font("Arial", Font.ITALIC, 10));
        reviewsArea.setLineWrap(true);
        reviewsArea.setWrapStyleWord(true);
        reviewsArea.setBorder(null);
        reviewsArea.setMaximumSize(new Dimension(180, 70));
        reviewsArea.setText(buildReviewText(book.getReviews()));

        JButton addButton = new JButton("Add to cart");
        addButton.setFont(new Font("Arial", Font.PLAIN, 11));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(e -> {
            cart.addBook(book);
            // mainFrame.updateCartCount(...); // daca ai metoda, o poti reactiva
            JOptionPane.showMessageDialog(this, "Book added: " + book.getTitle());
        });

        card.add(Box.createVerticalStrut(5));
        card.add(titleLabel);
        card.add(authorLabel);
        card.add(priceLabel);
        card.add(yearLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(reviewsArea);
        card.add(Box.createVerticalStrut(5));
        card.add(addButton);

        return card;
    }

    private String buildReviewText(List<String> reviews) {
        if (reviews == null || reviews.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("Reviews:\n");
        for (String review : reviews) {
            if (!review.isBlank()) {
                sb.append("• ").append(review).append("\n");
            }
        }
        return sb.toString();
    }
}
