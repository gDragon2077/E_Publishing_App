// BankPayment.java - implementare pentru plata prin cont bancar

public class BankPayment implements Payment {
    private String bankName;
    private String accountNumber;

    public BankPayment(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean authorize() {
        // Comentariu: Se valideaza simplu existenta datelor introduse pentru simplitate
        return bankName != null && !bankName.isEmpty()
                && accountNumber != null && !accountNumber.isEmpty();
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
