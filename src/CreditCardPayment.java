// CreditCardPayment.java - implementare pentru plata cu cardul de credit

import java.time.LocalDate;

public class CreditCardPayment implements Payment {
    private String cardType;
    private String cardNumber;
    private LocalDate validUntil;

    public CreditCardPayment(String cardType, String cardNumber, LocalDate validUntil) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validUntil = validUntil;
    }

    @Override
    public boolean authorize() {
        // Comentariu: verifica daca informatiile cardului sunt valide si cardul nu este expirat
        return cardType != null && !cardType.isEmpty()
                && cardNumber != null && cardNumber.matches("\\d{16}")
                && validUntil != null && validUntil.isAfter(LocalDate.now());
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }
}
