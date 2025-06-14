import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegistrationFrame() {
        setTitle("Register - E-Editura");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campuri de input
        formPanel.add(new JLabel("First name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        formPanel.add(registerButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        // Inregistrare
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String address = addressField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()
                        || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationFrame.this, "All fields are required.");
                    return;
                }

                String hashedPassword = PasswordUtils.hashPassword(password);
                Client newClient = new Client(firstName, lastName, address, username, hashedPassword);
                DataManager.saveClient(newClient);

                JOptionPane.showMessageDialog(RegistrationFrame.this, "Account created. You can now login.");
                dispose();
                new LoginFrame();
            }
        });

        // Intoarcere la login
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        setVisible(true);
    }
}
