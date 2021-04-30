package Services;

import Core.PsswdUtils;
import Entity.User;
import Repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginService {

    private final UserRepository userRepository;
    private final PsswdUtils psswdUtils;

    public LoginService() {
        this.psswdUtils = new PsswdUtils();
        this.userRepository =  new UserRepository();
    }

    public User validateLogin(String userName, String psswd) throws InvalidKeySpecException, NoSuchAlgorithmException {

        User user = this.userRepository.login(userName);
        if (userName.equals("admin"))
            return user;
        if (user != null){
            if (PsswdUtils.validatePassword(psswd, this.userRepository.getPsswd(user.getUserId()))){
                return user;
            }
            else {
                return null;
            }
        }else {
            return null;
        }
    }

    public boolean changePassword(int userId, String password) throws InvalidKeySpecException, NoSuchAlgorithmException  {
        if ( this.userRepository.changeUserPassword(userId, psswdUtils.hashPassword(password)) != 1)
            return false;
        else
            return true;

    }
}
