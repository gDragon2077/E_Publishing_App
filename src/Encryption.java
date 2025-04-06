import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
    public static String hashPassword(String password) throws RuntimeException {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("The Password cannot be hashed!", e);
        }
    }

    public static boolean passwordVerify(String password, String hashPassword){
        return hashPassword(password).equals(hashPassword); // If the password is same return 1 or 0 if is not
    }
}
