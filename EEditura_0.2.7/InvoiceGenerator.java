import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String facturaId = "FCT" + timestamp;
        String filename = "factura_" + order.getClient().getUsername() + "_" + timestamp + ".pdf";
        File invoiceFile = new File(FACTURI_FOLDER + filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(invoiceFile))) {
            DecimalFormat df = new DecimalFormat("0.00");

            // Siguranta: fallback data
            Date date = order.getOrderDate();
            if (date == null) date = new Date();

            // Siguranta: fallback livrare
            String deliveryType = (order.getDelivery() != null) ? order.getDelivery().toString() : "STANDARD";

            writer.println("===================================================================================");
            writer.println("                                E-Editura SRL                                      ");
            writer.println("                            FACTURA PROFORMA - " + facturaId);
            writer.println("===================================================================================");
            writer.println("Data emiterii: " + new SimpleDateFormat("dd.MM.yyyy").format(date));
            writer.println("Client: " + order.getClient().getFirstName() + " " + order.getClient().getLastName());
            writer.println("Adresa: " + order.getClient().getAddress());
            writer.println("-----------------------------------------------------------------------------------");

            // Tabel antet
            writer.printf("%-5s %-35s %-5s %-10s %-12s %-12s %-10s\n",
                    "Nr.", "Denumire produs", "UM", "Cantitate", "Pret unitar", "Valoare", "TVA 19%");
            writer.println("-----------------------------------------------------------------------------------");

            List<CartItem> items = order.getItems();
            double subtotal = 0.0;
            double tvaTotal = 0.0;
            int index = 1;

            for (CartItem item : items) {
                Book book = item.getBook();
                if (book == null) continue;

                int qty = item.getQuantity();
                double unitPrice = book.getPrice();
                double totalLine = unitPrice * qty;
                double tva = totalLine * 0.19;

                subtotal += totalLine;
                tvaTotal += tva;

                writer.printf("%-5d %-35s %-5s %-10d %-12s %-12s %-10s\n",
                        index++, book.getTitle(), "buc", qty,
                        df.format(unitPrice), df.format(totalLine), df.format(tva));
            }

            // Taxa EXPRESS (daca este cazul)
            double expressFee = 0.0;
            double tvaExpress = 0.0;
            if ("EXPRESS".equals(deliveryType)) {
                expressFee = 5.0;
                tvaExpress = expressFee * 0.19;
                subtotal += expressFee;
                tvaTotal += tvaExpress;

                writer.printf("%-5d %-35s %-5s %-10d %-12s %-12s %-10s\n",
                        index, "Taxa livrare EXPRESS", "buc", 1,
                        df.format(expressFee), df.format(expressFee), df.format(tvaExpress));
            }

            double totalFinal = subtotal + tvaTotal;

            writer.println("-----------------------------------------------------------------------------------");
            writer.printf("%65s %12s\n", "Total fara TVA:", df.format(subtotal));
            writer.printf("%65s %12s\n", "Total TVA 19%:", df.format(tvaTotal));
            writer.printf("%65s %12s\n", "TOTAL DE PLATA:", df.format(totalFinal));
            writer.println();

            // Tip livrare + cost
            writer.println("-----------------------------------------------------------------------------------");
            writer.println("Tip livrare: " + deliveryType);
            if ("EXPRESS".equals(deliveryType)) {
                writer.println("Taxa livrare: 5.00 RON");
            } else {
                writer.println("Taxa livrare: Gratuit");
            }

            // Plata
            writer.println();
            if (order.getPayment() instanceof CreditCardPayment) {
                CreditCardPayment cp = (CreditCardPayment) order.getPayment();
                String card = cp.getCardNumber();
                String last4 = card.length() >= 4 ? card.substring(card.length() - 4) : "XXXX";
                writer.println("Plata cu card:");
                writer.println("  Titular: " + order.getClient().getFirstName() + " " + order.getClient().getLastName());
                writer.println("  Card: **** **** **** " + last4);
            } else if (order.getPayment() instanceof BankPayment) {
                BankPayment bp = (BankPayment) order.getPayment();
                writer.println("Plata prin transfer bancar:");
                writer.println("  Banca: " + bp.getBankName());
                writer.println("  IBAN:  " + bp.getAccountNumber());
                writer.println("  Termen de plata: 30 zile");
                writer.println("  Cod referinta: " + facturaId);
            }

            writer.println("===================================================================================");
            writer.println("Multumim pentru comanda!");
            writer.println("===================================================================================");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return invoiceFile;
    }
}
