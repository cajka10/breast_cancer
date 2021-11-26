package Controllers;

import Core.Entity.Enum.UserRole;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {
    private UserService userService;

    @FXML
    private TextField userNameTextField;

    @FXML
    private ComboBox<UserRole> roleComboBox;

    public NewUserController() {
        this.userService = new UserService();
    }

    public void confirmButtonOnAction(ActionEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (this.userService.addUser(userNameTextField.getText(), this.roleComboBox.getValue()) == 1) {
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

        Stage stage = (Stage)roleComboBox.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.roleComboBox.getItems().addAll(UserRole.values());
    }
}
