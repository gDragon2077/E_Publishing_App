import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogPanel extends JPanel {
    private ShoppingCart cart;
    private List<Book> books;

    public CatalogPanel(List<Book> books, ShoppingCart cart) {
        this.books = books;
        this.cart = cart;

        setLayout(new GridLayout(0, 3, 10, 10)); // randuri flexibile, 3 coloane
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Book book : books) {
            add(createBookBox(book));
        }
    }

    // Creeaza un panel pentru o singura carte
    private JPanel createBookBox(Book book) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("<html><center><b>" + book.getTitle() + "</b></center></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel authorLabel = new JLabel("by " + book.getAuthor());
        JLabel priceLabel = new JLabel(String.format("%.2f RON", book.getPrice()));
        JLabel yearLabel = new JLabel("Year: " + book.getYear());

        JButton addButton = new JButton("Add to cart");
        addButton.addActionListener(e -> {
            cart.addBook(book);
            JOptionPane.showMessageDialog(this, "Book added to cart.");
        });

        // Center alignment
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add spacing and components
        panel.add(Box.createVerticalStrut(10));
        panel.add(titleLabel);
        panel.add(authorLabel);
        panel.add(priceLabel);
        panel.add(yearLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(addButton);

        return panel;
    }
}
