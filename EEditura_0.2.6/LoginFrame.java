import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - E-Editura");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Elemente UI
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Adaugare butoane
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel, BorderLayout.CENTER);

        // Actiune login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                List<Client> clients = DataManager.loadClients();
                for (Client c : clients) {
                    if (c.getUsername().equals(user) &&
                        PasswordUtils.checkPassword(pass, c.getPasswordHash())) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login reusit!");
                        dispose(); // inchide fereastra de login
                        new MainFrame(c); // deschide aplicatia
                        return;
                    }
                }

                JOptionPane.showMessageDialog(LoginFrame.this, "Username sau parola invalida.");
            }
        });

        // Actiune register
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrationFrame(); // deschide fereastra de inregistrare
            }
        });

        setVisible(true);
    }
}
