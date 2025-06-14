import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;

public class CardUtils {
    private static final String ACCEPTED_CARDS_FILE = "resources/carduri.csv";

    // Verifica algoritmul Luhn
    public static boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    // Verifica daca cardul este prezent in carduri.csv
    public static boolean isAcceptedCard(String cardNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCEPTED_CARDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(cardNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read card file: " + e.getMessage());
        }
        return false;
    }

    // Verifica daca data de expirare este in viitor
    public static boolean isNotExpired(int month, int year) {
        try {
            YearMonth cardDate = YearMonth.of(year, month);
            return cardDate.isAfter(YearMonth.now());
        } catch (Exception e) {
            return false;
        }
    }

    // Verifica formatul IBAN (simplificat)
    public static boolean isValidIBAN(String iban) {
        iban = iban.replaceAll("\\s+", ""); // elimina spatii
        if (iban.length() < 15 || iban.length() > 34) {
            return false;
        }
        if (!iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$")) {
            return false;
        }
        return true;
    }
}
