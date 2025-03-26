import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User{
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String name;
    private String address;
    private String email;

    private boolean isInvalid(String value){
        return (value == null || value.trim().isEmpty());
    }
    private boolean validEmail(String email){
        String emailFormat = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailFormat);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    User(String userName, String password, String name, String address, String email){
        if(isInvalid(userName)){ throw new IllegalArgumentException("Username cannot be null or empty!");}
        if(isInvalid(password)){ throw new IllegalArgumentException("Password cannot be null or empty!");}
        if(isInvalid(name)){ throw new IllegalArgumentException("Name cannot be null or empty!");}
        if(isInvalid(address)){throw new IllegalArgumentException("Email cannot br null or empty!");}
        if(isInvalid(email)){throw new IllegalArgumentException("Email cannot br null or empty!");}
        if(validEmail(email)){throw new IllegalArgumentException("Invalid email format!");}

        this.userName = userName;
        this.password= password;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /// Getters
    public String getUserName (){return userName;}
    public String getPassword (){return password;}
    public String getName (){return name;}
    public String getAddress (){return address;}
    public String getEmail (){return email;}

    /// Setters
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public int hashCode(){
        return Objects.hash(userName);
    }

    @Override
    public String toString(){
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}