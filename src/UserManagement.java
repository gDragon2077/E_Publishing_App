import java.util.HashMap;
import java.util.Map;

public class UserManagement {
    /// Se foloseste hashMap pentru gestionarea userilor
    private Map<String, User> users = new HashMap<>();

    public void addUser(User newUser){
        if(newUser == null){
            throw new IllegalArgumentException("User cannot be null!");
        }
        if(users.containsKey(newUser.getUserName())){
            throw new IllegalArgumentException("Username already taken!");
        }
        users.put(newUser.getUserName(),newUser);
    }

    /// Functii Obligatorii
    public User getUser(String username){
        return users.get(username);
    }
    public boolean existaUser(String username){
        return users.containsKey(username);
    }
    public void inregistrareUser(String userName, String password, String name, String address, String email){
        if(existaUser(userName)){
            throw new IllegalArgumentException("Username already taken!");
        }

        String hashedPassword = PasswordUtils.hashPassword(password);
        User newUser = new User(userName, hashedPassword, name, address, email);
        addUser(newUser);
    }
    public User authUser(String userName, String password){
        User user = getUser(userName);
        if(user != null && PasswordUtils.verifyPassword(password, user.getPassword())){
            /// TO DO - posibile probleme la memorarea parolei sau a utilizatorilor
            return user;
        }
        return null;
    }

    /// Functii Extra de la Mine
    public void changePassword(String userName, String oldPassword, String newPassword){
        if(oldPassword.equals(newPassword)){
            throw new IllegalArgumentException("Parolele nu trebuie sa coincida!");
        }

        User user = getUser(userName);
        if(user == null){
            throw new IllegalArgumentException("Utilizatorul nu exista!");
        }
        if(!PasswordUtils.verifyPassword(oldPassword,user.getPassword())){
            throw new IllegalArgumentException("Parola incorecta!");
        }
        user.setPassword(PasswordUtils.hashPassword(newPassword));
    }
    public void deleteUser(String userName){
        if(!existaUser(userName)){
            throw new IllegalArgumentException("Utilizatorul nu exista!");
        }else{
            users.remove(userName);
        }
    }
}
