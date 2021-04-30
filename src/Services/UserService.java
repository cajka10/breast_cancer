package Services;

import Core.PsswdUtils;
import Entity.Enum.UserRole;
import Entity.User;
import Repository.UserRepository;
import javafx.scene.control.TableView;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public int addUser(String username, UserRole role) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PsswdUtils psswdUtils = new PsswdUtils();
        String passwd = psswdUtils.generatePassword();
        System.out.println("heslo je: " + passwd);
        return this.userRepository.addUser(username, passwd, role.getI());
    }

    public TableView getUsers(){
        return userRepository.getUserColumns();
    }

    public User getUserById(int userId){
        return this.userRepository.getUserById(userId);
    }

}
