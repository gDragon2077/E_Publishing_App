import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Se initializeaza catalogul cu carti default daca lipseste fisierul
        CatalogInitializer.initializeCatalog();

        // Se porneste interfata Swing pe thread-ul UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}
