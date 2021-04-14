package Controllers;

import Entity.Enum.UserRole;
import Services.LoginService;
import Services.MainService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.stream.Stream;


public class MainWindowController implements Initializable {

    private LoginService loginService;
    private UserService userService;
    private MainService mainService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private ComboBox<UserRole> roleComboBox;

    @FXML
    private TableView tableview;

    public MainWindowController() {
        this.loginService = new LoginService();
        this.userService = new UserService();
        this.mainService = new MainService();
    }

    public void newPatientOnAction(ActionEvent event)  {

    }

    public void confirmButtonOnAction(ActionEvent event) throws InvalidKeySpecException {
        if (this.userService.addPatient(userNameTextField.getText(), this.roleComboBox.getValue()) == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Užívatel bol úspešne vytvorený. \n Heslo je:");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Užívatel nebol vytvorený.");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.roleComboBox.getItems().addAll(UserRole.values());
    }

    public void importButtonOnAction(ActionEvent event) {
        this.mainService.importData((Stage) roleComboBox.getScene().getWindow());
    }
}
