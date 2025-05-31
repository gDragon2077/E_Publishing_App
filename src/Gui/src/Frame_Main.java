import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame_Main extends JFrame implements ActionListener {
    JButton shop;
    JButton login;
    JButton cautare;
    JTextField textField;
    ImageIcon searchIcon;
    ImageIcon shopIcon = new ImageIcon(getClass().getResource("/Iconuri/shop.png"));
    ImageIcon userIcon = new ImageIcon(getClass().getResource("/Iconuri/user.png"));
    ImageIcon sideIcon = new ImageIcon(getClass().getResource("/Iconuri/sidebar.png"));
    ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Iconuri/Iconita.png"));
    JComboBox<String> produseBox;

    private final Color drawerGray = new Color(70, 70, 70);
    private final Color buttonHover = new Color(255, 160, 0);
    private final Font mainFont = new Font("", Font.PLAIN, 16);

    Frame_Main() {
        // Configurare frame principal
        this.setTitle("Carturaresti");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(960, 540);
        this.setMinimumSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());

        Image image = new ImageIcon(getClass().getResource("/Iconuri/Iconita.png")).getImage();
        this.setIconImage(image);
        this.getContentPane().setBackground(new Color(26, 26, 26));

        searchIcon = new ImageIcon(getClass().getResource("/Iconuri/search.png"));
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledSearchIcon);

        Image scaledShopIcon = shopIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        shopIcon = new ImageIcon(scaledShopIcon);

        Image scaledUserIcon = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(scaledUserIcon);

        Image scaledSideIcon = sideIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        sideIcon = new ImageIcon(scaledSideIcon);

        Image scaledLogoIcon = logoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledLogoIcon);

        // Crearea barii superioare
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(62, 62, 62));
        topBar.setPreferredSize(new Dimension(960, 42));

        // Panel pentru butoanele din stanga
        JPanel leftButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        leftButtonsPanel.setOpaque(false);

        leftButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing

        JButton logoButton = new JButton(logoIcon);
        logoButton.setBackground(new Color(62, 62, 62));
        logoButton.setForeground(Color.WHITE);
        logoButton.setBorderPainted(false);
        logoButton.setFocusPainted(false);
        logoButton.setPreferredSize(new Dimension(30, 30));
        logoButton.addActionListener(e -> {
            System.out.println("Navigating to main page...");
        });
        logoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoButton.setBackground(new Color(80, 80, 80));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                logoButton.setBackground(new Color(62, 62, 62));
            }
        });
        leftButtonsPanel.add(logoButton);

        leftButtonsPanel.add(Box.createRigidArea(new Dimension(50, 0))); // Spacing

        String[] categorii = {"All", "manga", "fantezie", "romanesti"};
        produseBox = new JComboBox<>(categorii);
        produseBox.setBackground(drawerGray);
        produseBox.setForeground(Color.WHITE);
        produseBox.setFont(mainFont);
        produseBox.setPreferredSize(new Dimension(160, 30));
        produseBox.setFocusable(false);

        UIManager.put("ComboBox.popupBackground", drawerGray);
        UIManager.put("ComboBox.selectionBackground", buttonHover);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

        leftButtonsPanel.add(produseBox);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        textField.setText("Search");
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Search")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Search");
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        topBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (textField.hasFocus()) textField.transferFocus();
            }
        });

        cautare = new JButton(searchIcon);
        cautare.setPreferredSize(new Dimension(30, 30));
        cautare.setBackground(Color.orange);
        cautare.setBorderPainted(false);
        cautare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                cautare.setBackground(new Color(255, 160, 0));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                cautare.setBackground(Color.ORANGE);
            }
        });

        leftButtonsPanel.add(textField);
        leftButtonsPanel.add(cautare);

        shop = new JButton(shopIcon);
        shop.setPreferredSize(new Dimension(34, 34));
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        rightButtonsPanel.setOpaque(false);
        rightButtonsPanel.add(shop);
        shop.setBackground(new Color(62, 62, 62));
        shop.setBorderPainted(false);
        shop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                shop.setBackground(new Color(80, 80, 80));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                shop.setBackground(new Color(62, 62, 62));
            }
        });

        login = new JButton(userIcon);
        login.setPreferredSize(new Dimension(34, 34));
        login.setBackground(new Color(62, 62, 62));
        login.setBorderPainted(false);
        rightButtonsPanel.add(login);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                login.setBackground(new Color(80, 80, 80));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                login.setBackground(new Color(62, 62, 62));
            }
        });

        topBar.add(leftButtonsPanel, BorderLayout.WEST);
        topBar.add(rightButtonsPanel, BorderLayout.EAST);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(new Color(26, 26, 26));

        // Helper method to create sections
        mainContent.add(createSection("Most Popular Books"));
        mainContent.add(createSection("Most Hot Books"));
        mainContent.add(createSection("Books on Sale"));
        mainContent.add(createSection("Books for Preorder"));

        // Add the topBar and mainContent inside a wrapper panel
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.add(topBar, BorderLayout.NORTH);
        contentWrapper.add(new JScrollPane(mainContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

        this.add(contentWrapper, BorderLayout.CENTER);

        shop.addActionListener(this);
        login.addActionListener(this);
        cautare.addActionListener(this);
        produseBox.addActionListener(this);

        this.setVisible(true);
    }

    // Method to create each section panel with a label and horizontal scrolling content
    private JPanel createSection(String title) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setMaximumSize(new Dimension(900, 180));
        sectionPanel.setOpaque(false);

        JLabel sectionLabel = new JLabel(title);
        sectionLabel.setForeground(Color.WHITE);
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));

        sectionPanel.add(sectionLabel, BorderLayout.NORTH);

        // Container for books horizontally scrollable
        JPanel booksPanel = new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.X_AXIS));
        booksPanel.setOpaque(false);

        // Add placeholder book panels
        for (int i = 1; i <= 10; i++) {
            booksPanel.add(createBookPlaceholder(title + " " + i));
            booksPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(900, 140));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        sectionPanel.add(scrollPane, BorderLayout.CENTER);

        return sectionPanel;
    }

    // Method to create a placeholder for a book item
    private JPanel createBookPlaceholder(String bookTitle) {
        JPanel bookPanel = new JPanel();
        bookPanel.setPreferredSize(new Dimension(100, 130));
        bookPanel.setBackground(new Color(45, 45, 45));
        bookPanel.setLayout(new BorderLayout());
        bookPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 1, true));

        // Placeholder image area (colored rectangle)
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(80, 80, 80));
        imagePanel.setPreferredSize(new Dimension(100, 90));
        bookPanel.add(imagePanel, BorderLayout.NORTH);

        // Book title label
        JLabel titleLabel = new JLabel("<html><center>" + bookTitle + "</center></html>", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 3, 5, 3));
        bookPanel.add(titleLabel, BorderLayout.CENTER);

        return bookPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shop) {
            System.out.println("Buton cos de cumparaturi apasat");
        } else if (e.getSource() == login) {
            System.out.println("Buton login apasat");
        } else if (e.getSource() == cautare) {
            System.out.println("Ai cautat: " + textField.getText());
        } else if (e.getSource() == produseBox) {
            System.out.println("Categorie selectata: " + produseBox.getSelectedItem());
        }
    }
}

