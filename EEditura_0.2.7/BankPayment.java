public class BankPayment extends Payment {
    private String bankName;
    private String accountNumber;

    // Constructor
    public BankPayment(double amount, String bankName, String accountNumber) {
        super(amount);
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean processPayment() {
        // Pentru simplitate, se presupune ca plata prin transfer este mereu acceptata
        // Se poate modifica pentru a verifica daca banca IBAN-ul este in lista de acceptate
        return true;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
