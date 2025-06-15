public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    private String passwordHash;

    // Constructor
    public Client(String firstName, String lastName, String address, String username, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getter pentru prenume
    public String getFirstName() {
        return firstName;
    }

    // Getter pentru nume
    public String getLastName() {
        return lastName;
    }

    // Getter pentru adresa
    public String getAddress() {
        return address;
    }

    // Getter pentru username
    public String getUsername() {
        return username;
    }

    // Getter pentru parola criptata
    public String getPasswordHash() {
        return passwordHash;
    }

    // Setter pentru parola criptata
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // Afisare client ca text (doar username)
    @Override
    public String toString() {
        return username;
    }
}
