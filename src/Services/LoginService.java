package Services;

import Core.PsswdUtils;
import Entity.User;
import Repository.UserRepository;
import javafx.scene.control.Alert;

import java.security.spec.InvalidKeySpecException;

public class LoginService {

    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository =  new UserRepository();
    }

    public boolean validateLogin(String userName, String psswd) throws InvalidKeySpecException {
        PsswdUtils psswdUtils = new PsswdUtils();
        String hash = "";
        if (!userName.equals("admin"))
            hash = psswdUtils.hashPassword(psswd);

        User loggedUser = this.userRepository.login(userName, hash);
        if (loggedUser != null) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nesprávne meno alebo heslo.");

            alert.showAndWait();
            return false;

        }


    }
}
