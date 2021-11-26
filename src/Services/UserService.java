package Services;

import Core.PsswdUtils;
import Core.Entity.Enum.UserRole;
import Core.Entity.User;
import Repository.UserRepository;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserService {
    static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public int addUser(String username, UserRole role) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PsswdUtils psswdUtils = new PsswdUtils();
        String passwd = psswdUtils.generatePassword();
        System.out.println("heslo je: " + passwd);
        int count = this.userRepository.addUser(username, passwd, role.getI());
        if (count > 0)
            logger.debug("User: " + username + " has been added succesfully.");
        else
            logger.debug("Error - User: " + username + " has not been added.");

        return count;
    }

    public TableView getUsers() {
        return userRepository.getUserColumns();
    }

    public User getUserById(int userId) {
        return this.userRepository.getUserById(userId);
    }

}
