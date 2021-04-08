package Services;

import Core.PsswdUtils;
import Entity.Enum.UserRole;
import Repository.LoginRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserService {

    private final LoginRepository loginRepository;

    public UserService() {
        this.loginRepository = new LoginRepository();
    }

    public int addPatient(String username, UserRole role) throws InvalidKeySpecException {
        PsswdUtils psswdUtils = new PsswdUtils();
        return this.loginRepository.addUser(username, psswdUtils.generatePassword(), role.getI());
    }

}
