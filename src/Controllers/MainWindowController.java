package Controllers;

import Entity.Enum.UserRole;
import Services.LoginService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.stream.Stream;


public class MainWindowController implements Initializable {

    private LoginService loginService;
    private UserService userService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private ComboBox<UserRole> roleComboBox;

    public MainWindowController() {
        this.loginService = new LoginService();
        this.userService = new UserService();
    }

    public void newPatientOnAction(ActionEvent event){
        if (this.userService.addPatient(userNameTextField.getText(), this.roleComboBox.getValue()) == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Užívatel bol úspešne vytvorený.");

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

    public void confirmButtonOnAction(ActionEvent event) {

    }
}
