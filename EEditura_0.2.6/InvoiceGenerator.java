import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceGenerator {
    private static final String FACTURI_FOLDER = "resources/facturi/";

    public static File generateInvoice(Order order) {
        File dir = new File(FACTURI_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "factura_" + order.getClient().getUsername() + "_" + timestamp + ".pdf";
        File invoiceFile = new File(FACTURI_FOLDER + filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(invoiceFile))) {
            writer.println("=====================================");
            writer.println("               E-Editura             ");
            writer.println("            FACTURA FISCALA          ");
            writer.println("=====================================");
            writer.println("Client: " + order.getClient().getFirstName() + " " + order.getClient().getLastName());
            writer.println("Adresa: " + order.getClient().getAddress());
            writer.println("Data:   " + new SimpleDateFormat("dd/MM/yyyy").format(order.getOrderDate()));
            writer.println("-------------------------------------");
            writer.println("Produse:");

            List<CartItem> items = order.getItems();
            for (CartItem item : items) {
                Book book = item.getBook();
                int qty = item.getQuantity();
                double totalLine = qty * book.getPrice();
                writer.printf("- %s x%d - %.2f RON\n", book.getTitle(), qty, totalLine);
            }

            // Livrare
            writer.println("-------------------------------------");
            writer.println("Tip livrare: " + order.getDeliveryType());
            if (order.getDeliveryType() == DeliveryType.EXPRESS) {
                writer.println("Taxa livrare rapida: 5.00 RON");
            }

            writer.println("-------------------------------------");
            writer.printf("Total de plata: %.2f RON\n", order.getTotal());

            // Informatii plata
            writer.println("-------------------------------------");
            if (order.getPayment() instanceof BankPayment) {
                BankPayment bp = (BankPayment) order.getPayment();
                writer.println("Plata prin transfer bancar:");
                writer.println("Banca: " + bp.getBankName());
                writer.println("IBAN:  " + bp.getAccountNumber());
                writer.println("Termen de plata: 30 zile");
            } else if (order.getPayment() instanceof CreditCardPayment) {
                CreditCardPayment cp = (CreditCardPayment) order.getPayment();
                String card = cp.getCardNumber();
                String last4 = card.substring(card.length() - 4);
                writer.println("Plata cu card:");
                writer.println("Titular: " + order.getClient().getFirstName() + " " + order.getClient().getLastName());
                writer.println("Card: **** **** **** " + last4);
            }

            writer.println("=====================================");
            writer.println("Multumim pentru comanda!");
        } catch (Exception e) {
            System.out.println("Eroare la generarea facturii: " + e.getMessage());
            return null;
        }

        return invoiceFile;
    }
}
