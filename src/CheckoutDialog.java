// CheckoutDialog.java - dialog pentru selectarea metodei de livrare si plata

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckoutDialog extends JDialog {
    private JComboBox<String> deliveryBox;
    private JComboBox<String> paymentBox;
    private JButton confirmButton;
    private boolean confirmed = false;

    private String selectedDelivery;
    private String selectedPayment;

    public CheckoutDialog(Frame owner) {
        super(owner, "Checkout", true);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Delivery method:"));
        deliveryBox = new JComboBox<>(new String[]{"STANDARD", "EXPRESS"});
        add(deliveryBox);

        add(new JLabel("Payment method:"));
        paymentBox = new JComboBox<>(new String[]{"CREDIT_CARD", "BANK"});
        add(paymentBox);

        confirmButton = new JButton("Confirm order");
        add(confirmButton);

        confirmButton.addActionListener(e -> {
            selectedDelivery = (String) deliveryBox.getSelectedItem();
            selectedPayment = (String) paymentBox.getSelectedItem();
            confirmed = true;
            dispose();
        });

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedDelivery() {
        return selectedDelivery;
    }

    public String getSelectedPayment() {
        return selectedPayment;
    }
}
