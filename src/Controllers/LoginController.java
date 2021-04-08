package Controllers;

import Services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    private LoginService loginService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField psswdTextField;

    public LoginController() {
        loginService = new LoginService();
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (userNameTextField.getText().isBlank() == false && psswdTextField.getText().isBlank() == false) {
            if (loginService.validateLogin(userNameTextField.getText(), psswdTextField.getText())) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Stage newStage = new Stage();
                try {
                    Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/Screens/MainWindow.fxml")));
                    newStage.setScene(newScene);
                    newStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Nepodarilo sa spustiť aplikáciu.");

                    alert.showAndWait();
                }
            }
        } else {

        }
    }
}
