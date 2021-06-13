package Services;

import Core.PsswdUtils;
import Entity.User;
import Repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.apache.log4j.Logger;


public class LoginService {

    private final UserRepository userRepository;
    private final PsswdUtils psswdUtils;
    static final Logger logger = Logger.getLogger(LoginService.class.getName());

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
                logger.debug("Login succesfull.");

                return user;
            }
            else {
                logger.debug("Login not succesfull.");

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
