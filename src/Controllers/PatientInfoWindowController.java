package Controllers;

import Core.Entity.Enum.UserRole;
import Core.Entity.PatientInfo;
import Services.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PatientInfoWindowController {
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField birthIdTextField;
    @FXML
    private TextField surNameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private DatePicker birthDateTimePicker;

    private MainService mainService;

    private PatientInfo info;
    public PatientInfoWindowController() {
        this.mainService = new MainService();
        info = new PatientInfo();
    }

    public void confirmButtonOnAction(ActionEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (!nameTextField.getText().trim().isBlank() &&
                !surNameTextField.getText().trim().isBlank()  &&
                !birthIdTextField.getText().trim().isBlank()  ) {
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

        Stage stage = (Stage)nameTextField.getScene().getWindow();
        stage.close();
    }

}
