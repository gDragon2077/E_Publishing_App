// Payment.java - interfata pentru metodele de plata, include validare generica

public interface Payment {
    // Metoda care trebuie implementata pentru a autoriza plata
    boolean authorize();

    // Verificare simpla folosita de implementari (ex: lungime numar card/cont, nu null etc.)
    default boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    default boolean isNumeric(String input) {
        return input != null && input.matches("\\d+");
    }

    default boolean isLength(String input, int expected) {
        return input != null && input.length() == expected;
    }
}
