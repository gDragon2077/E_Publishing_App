import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {
    /// Metoda pentru a genera hash-ul unei parole folositd SHA-256

    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Eroare la hashing-ul parolei", e);
        }
    }

    /// Pentru a verifica daca hash-ul este corect
    public static boolean verifyPassword(String password, String hashedPassword){
        return hashPassword(password).equals(hashedPassword);
    }
}
