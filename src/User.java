import java.util.Objects;

public class User {
    // Name, age cat si email sunt final deoarece aceastea nu o sa poata vreodata sa fie schimbate
    private final String name;
    private final String email;
    private final int age;
    // private String userName; // Deoarece nu mi se pare atat de interesant faptul de a avea user si parola in detrimentul emal si parola.
    private String password;
    private String address;

    User(String name, /*String userName,*/ String password, String address, String email, int age){
        this.name = name;
        // this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /// Getters
    public String getName(){
        return name;
    }
//    public String getUserName(){
//        return userName;
//    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public int getAge(){
        return age;
    }

    /// Setters
    public void setPassword(String password){
        this.password = password;
    }
    public void setAddress(String address){
        this.address = address;
    }

    @Override
    public int hashCode(){
        return Objects.hash(email, name);
    }

    @Override
    public String toString(){
        return "User{" +
                "Name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}
