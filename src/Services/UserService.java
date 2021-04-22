package Services;

import Core.PsswdUtils;
import Entity.Enum.UserRole;
import Repository.UserRepository;
import javafx.scene.control.TableView;

import java.security.spec.InvalidKeySpecException;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public int addPatient(String username, UserRole role) throws InvalidKeySpecException {
        PsswdUtils psswdUtils = new PsswdUtils();
        return this.userRepository.addUser(username, psswdUtils.generatePassword(), role.getI());
    }

    public TableView getUsers(){
        return userRepository.getUserColumns();
    }

}
