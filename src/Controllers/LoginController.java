package Controllers;

import Entity.User;
import Services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginController {

    private boolean newUser = false;

    private User user;

    private LoginService loginService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField psswdTextField;

    @FXML
    private PasswordField psswdVerifyTextField;

    @FXML
    private Label verifyPsswdLabel;

    public LoginController() {
        loginService = new LoginService();
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (newUser) {
            this.changePasswd(user.getUserId());
            this.openMainWindow();
        } else {
            if (userNameTextField.getText().isBlank() == false && psswdTextField.getText().isBlank() == false) {
                this.user = loginService.validateLogin(userNameTextField.getText(), psswdTextField.getText());
                if (user != null) {

                    if (user.isNew()) {
                        this.setVerifyPasswordVisible();
                        this.newUser = user.isNew();
                    } else {
                        this.openMainWindow();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Nesprávne meno alebo heslo.");

                    alert.showAndWait();
                }
            }
        }
    }

    private void setVerifyPasswordVisible() {
        this.psswdTextField.setText("");
        this.userNameTextField.setDisable(true);
        this.psswdVerifyTextField.setVisible(true);
        this.verifyPsswdLabel.setVisible(true);
    }

    private void openMainWindow() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            Stage newStage = new Stage();
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

    private boolean changePasswd(int userId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (!psswdVerifyTextField.getText().isBlank() && psswdTextField.getText().equals((psswdVerifyTextField.getText()))) {
            return loginService.changePassword(userId, psswdVerifyTextField.getText());
        } else {
            return false;
        }
    }


}
