public enum DeliveryType {
    STANDARD("Standard"),
    EXPRESS("Express (+5 RON)");

    private final String label;

    DeliveryType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
