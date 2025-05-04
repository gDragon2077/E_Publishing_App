import javax.swing.*;
import javax.swing.border.LineBorder;
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
    JComboBox<String> produseBox;

    JButton toggleButton;
    int DRAWER_WIDTH = 250;
    int ANIMATION_STEPS = 25;
    int ANIMATION_DELAY = 5;

    Frame_Main() {
        // Configurare frame principal
        this.setTitle("Carturaresti");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(960, 540);
        this.setLayout(new BorderLayout());

        Image image = new ImageIcon(getClass().getResource("/Iconuri/Iconita.png")).getImage();
        this.setIconImage(image);
        this.getContentPane().setBackground(Color.white);

        searchIcon = new ImageIcon(getClass().getResource("/Iconuri/search.png"));
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledSearchIcon);

        Image scaledShopIcon = shopIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        shopIcon = new ImageIcon(scaledShopIcon);

        Image scaledUserIcon = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(scaledUserIcon);

        Image scaledSideIcon = sideIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        sideIcon = new ImageIcon(scaledSideIcon);

        // Crearea barii superioare
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(62, 62, 62));
        topBar.setPreferredSize(new Dimension(960, 42));

        // Panel pentru butoanele din stanga
        JPanel leftButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        leftButtonsPanel.setOpaque(false);

        // Panel pentru butoanele din dreapta
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        rightButtonsPanel.setOpaque(false);

        // Configurare buton toggle
        toggleButton = new JButton(sideIcon);
        toggleButton.setBackground(new Color(62, 62, 62));
        toggleButton.setBorderPainted(false);
        toggleButton.setPreferredSize(new Dimension(30, 30));
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(new LineBorder(Color.WHITE, 1, true));

        toggleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                toggleButton.setBorderPainted(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                toggleButton.setBorderPainted(false);
            }
        });

        leftButtonsPanel.add(toggleButton);
        leftButtonsPanel.add(Box.createRigidArea(new Dimension(75, 0)));

        // ComboBox pentru categorii----------------------------------------------------------

        String[] categorii = {"All", "manga", "fantezie", "romanesti"};

        produseBox = new JComboBox<>(categorii);
        produseBox.setBackground(Color.LIGHT_GRAY);
        produseBox.setForeground(Color.BLACK);

        produseBox.addActionListener(e -> {
            String selectedItem = (String) produseBox.getSelectedItem();
            if (selectedItem != null) {
                produseBox.setPrototypeDisplayValue(selectedItem);
            }
        });

        produseBox.setPrototypeDisplayValue("All");
        produseBox.setPreferredSize(null);
        produseBox.setFont(new Font("Comic Sans", Font.PLAIN, 16));


        leftButtonsPanel.add(produseBox);


        // TextField pentru cautare--------------------------------
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        leftButtonsPanel.add(textField);
        textField.setText("Search");
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Dacă textul este placeholder-ul default, golește câmpul și setează culoarea normală
                if (textField.getText().equals("Search")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Dacă utilizatorul nu a introdus text, setează din nou placeholder-ul
                if (textField.getText().isEmpty()) {
                    textField.setText("Search");
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        topBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (textField.hasFocus()) {
                    textField.transferFocus(); // Elimină focusul de pe textField
                }
            }
        });

        // Buton submit search
        cautare = new JButton(searchIcon);
        cautare.setPreferredSize(new Dimension(30, 30));
        leftButtonsPanel.add(cautare);
        cautare.setBackground(Color.orange);
        cautare.setBorderPainted(false);

        cautare.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cautare.setBackground(new Color(255, 160, 0));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cautare.setBackground(Color.ORANGE);
            }

        });

        // Buton cos cumparaturi
        shop = new JButton(shopIcon);
        shop.setPreferredSize(new Dimension(34, 34));
        rightButtonsPanel.add(shop);
        shop.setBackground(new Color(62, 62, 62));
        shop.setBorderPainted(false);
        shop.setContentAreaFilled(false);
        shop.setBorder(new LineBorder(Color.WHITE, 1, true));

        shop.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shop.setBorderPainted(true);

            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shop.setBorderPainted(false);
            }
        });

        // Buton login
        login = new JButton(userIcon);
        login.setPreferredSize(new Dimension(34, 34));
        login.setBackground(new Color(62, 62, 62));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setBorder(new LineBorder(Color.WHITE, 1, true));
        rightButtonsPanel.add(login);

        login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login.setBorderPainted(true);

            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login.setBorderPainted(false);
            }
        });


        // Adaugare panouri butoane in bara superioara
        topBar.add(leftButtonsPanel, BorderLayout.WEST);
        topBar.add(rightButtonsPanel, BorderLayout.EAST);

        // Crearea drawer menu
        JPanel drawerPanel = new JPanel();
        drawerPanel.setBackground(new Color(70, 70, 70));
        drawerPanel.setPreferredSize(new Dimension(DRAWER_WIDTH, drawerPanel.getPreferredSize().height));
        drawerPanel.setLayout(new BoxLayout(drawerPanel, BoxLayout.Y_AXIS));

        // Adaugare elemente meniu
        JLabel menuTitle = new JLabel("MENIU", SwingConstants.CENTER);
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 16));
        drawerPanel.add(menuTitle);
        drawerPanel.add(Box.createVerticalStrut(20));

        String[] menuItems = {"Acasă", "Setări", "Profil", "Ajutor", "Ieșire"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setMaximumSize(new Dimension(DRAWER_WIDTH-20, menuButton.getPreferredSize().height));
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(90, 90, 90));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            drawerPanel.add(menuButton);
            drawerPanel.add(Box.createVerticalStrut(10));
        }

        // Crearea continutului principal
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(new Color(26, 26, 26));

        // Adaugare bara superioara si continut
        mainContent.add(topBar, BorderLayout.NORTH);
        mainContent.add(new JLabel("Conținut principal", SwingConstants.CENTER), BorderLayout.CENTER);

        mainContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (textField.hasFocus()) {
                    textField.transferFocus();
                }
            }
        });

        // Crearea split pane pentru drawer
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawerPanel, mainContent);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(0); // Initial ascuns

        // Adaugare functionalitate buton toggle
        toggleButton.addActionListener(new ActionListener() {
            private boolean isOpen = false;
            private Timer animationTimer;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationTimer != null && animationTimer.isRunning()) {
                    return;
                }

                int targetLocation = isOpen ? 0 : DRAWER_WIDTH;
                int currentLocation = splitPane.getDividerLocation();
                int step = (targetLocation - currentLocation) / ANIMATION_STEPS;

                animationTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int newLocation = splitPane.getDividerLocation() + step;

                        if ((step > 0 && newLocation >= targetLocation) ||
                                (step < 0 && newLocation <= targetLocation)) {
                            newLocation = targetLocation;
                            ((Timer)e.getSource()).stop();
                        }

                        splitPane.setDividerLocation(newLocation);
                    }
                });

                animationTimer.start();
                isOpen = !isOpen;
            }
        });

        // Adaugare action listeners
        shop.addActionListener(this);
        login.addActionListener(this);
        cautare.addActionListener(this);
        produseBox.addActionListener(this);

        // Adaugare split pane in frame
        this.add(splitPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shop) {
            System.out.println("Buton cos de cumparaturi apasat");
        }
        else if (e.getSource() == login) {
            System.out.println("Buton login apasat");
        }
        else if (e.getSource() == cautare) {
            System.out.println("Ai cautat: " + textField.getText());
        }
        else if (e.getSource() == produseBox) {
            System.out.println("Categorie selectata: " + produseBox.getSelectedItem());
        }
    }

}
