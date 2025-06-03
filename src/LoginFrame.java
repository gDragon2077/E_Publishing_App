// LoginFrame.java - interfata pentru autentificarea utilizatorilor

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        add(loginButton);
        add(registerButton);

        // Eveniment login
        loginButton.addActionListener(e -> handleLogin());

        // Eveniment register
        registerButton.addActionListener(e -> {
            dispose();
            new RegistrationFrame();
        });

        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        List<Client> clients = DataManager.loadList("clients.dat");
        if (clients != null) {
            for (Client client : clients) {
                if (client.getUsername().equals(username) && client.checkPassword(password)) {
                    dispose();
                    new MainFrame(client);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
