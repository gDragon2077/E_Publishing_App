// RegistrationFrame.java - interfata pentru inregistrarea unui nou utilizator

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationFrame() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerButton = new JButton("Register");
        add(registerButton);

        // Actiune la apasarea butonului
        registerButton.addActionListener(e -> handleRegister());

        setVisible(true);
    }

    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()
                || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Client> clients = DataManager.loadList("clients.dat");
        if (clients == null) clients = new ArrayList<>();

        for (Client c : clients) {
            if (c.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String hashedPassword = PasswordUtils.hashPassword(password);
        Client client = new Client(firstName, lastName, address, username, hashedPassword);
        clients.add(client);
        DataManager.saveData(clients, "clients.dat");

        JOptionPane.showMessageDialog(this, "Registration successful.");
        dispose();
        new LoginFrame();
    }
}
