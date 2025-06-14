public abstract class Payment {
    protected double amount;

    // Constructor
    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    // Metoda abstracta care va fi implementata diferit in fiecare subclasa
    public abstract boolean processPayment();
}
