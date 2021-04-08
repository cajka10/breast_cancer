package Services;

import Core.PsswdUtils;
import Entity.User;
import Repository.LoginRepository;
import javafx.scene.control.Alert;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService() {
        this.loginRepository =  new LoginRepository();
    }

    public boolean validateLogin(String userName, String psswd) throws InvalidKeySpecException {
        PsswdUtils psswdUtils = new PsswdUtils();
        String hash = "";
        if (!userName.equals("admin"))
            hash = psswdUtils.hashPassword(psswd);

        User loggedUser = this.loginRepository.login(userName, hash);
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
