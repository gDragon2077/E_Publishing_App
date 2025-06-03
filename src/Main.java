// Main.java - punctul de intrare in aplicatia E-Editura, adaptat la structura finala

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Seteaza look and feel modern pentru Swing
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Porneste aplicatia cu interfata de login
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
