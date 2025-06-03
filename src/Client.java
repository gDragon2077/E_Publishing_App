// Client.java - reprezinta un utilizator al aplicatiei

import java.io.Serializable;
//import util.PasswordUtils;

// Este necesar sa fie compilata impreuna cu PasswordUtils.java
// Daca este in alt pachet, adauga: import path.to.PasswordUtils;

public class Client implements Serializable {
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    private String hashedPassword; // parola este stocata criptat

    public Client(String firstName, String lastName, String address, String username, String hashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    // Verifica daca parola introdusa corespunde celei criptate
    public boolean checkPassword(String password) {
        return PasswordUtils.verifyPassword(password, hashedPassword);
    }
}
