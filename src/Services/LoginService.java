package Services;

import Entity.User;
import Repository.LoginRepository;
import javafx.scene.control.Alert;

public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService() {
        this.loginRepository =  new LoginRepository();
    }

    public boolean validateLogin(String userName, String psswd) {
        User loggedUser = this.loginRepository.login(userName, psswd);
        if (loggedUser != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Prihlasenie uspesne.");

            alert.showAndWait();
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
