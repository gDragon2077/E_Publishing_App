import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Cripteaza parola folosind SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 not available: " + e.getMessage());
            return null;
        }
    }

    // Verifica daca parola introdusa de utilizator corespunde cu cea criptata salvata
    public static boolean checkPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash != null && inputHash.equals(storedHash);
    }
}
