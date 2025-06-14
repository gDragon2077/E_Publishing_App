import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InvoiceViewFrame extends JFrame {
    public InvoiceViewFrame(File invoiceFile) {
        setTitle("Invoice - E-Editura");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea invoiceArea = new JTextArea();
        invoiceArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton printButton = new JButton("Print Invoice");
        add(printButton, BorderLayout.SOUTH);

        // Citeste continutul facturii si afiseaza in JTextArea
        try (BufferedReader reader = new BufferedReader(new FileReader(invoiceFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                invoiceArea.append(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could not read invoice: " + e.getMessage());
        }

        // Actiune buton print
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    invoiceArea.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(InvoiceViewFrame.this, "Print failed: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
