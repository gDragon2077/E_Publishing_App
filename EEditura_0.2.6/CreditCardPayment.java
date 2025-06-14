import java.time.YearMonth;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private int expMonth;
    private int expYear;

    // Constructor
    public CreditCardPayment(double amount, String cardNumber, int expMonth, int expYear) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    @Override
    public boolean processPayment() {
        // Cardul trebuie sa inceapa cu 4 sau 5
        if (!(cardNumber.startsWith("4") || cardNumber.startsWith("5"))) {
            return false;
        }

        // Verifica algoritmul Luhn
        if (!CardUtils.isValidLuhn(cardNumber)) {
            return false;
        }

        // Verifica daca data de expirare e in viitor
        YearMonth current = YearMonth.now();
        YearMonth cardDate = YearMonth.of(expYear, expMonth);
        if (cardDate.isBefore(current)) {
            return false;
        }

        // Verifica daca cardul este acceptat (exista in CSV)
        return CardUtils.isAcceptedCard(cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public int getExpYear() {
        return expYear;
    }
}
