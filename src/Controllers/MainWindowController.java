package Controllers;

import Services.LoginService;
import Services.MainService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {

    private LoginService loginService;
    private UserService userService;
    private MainService mainService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private TableView patientView;

    @FXML
    private TableView usersTableView;

    public MainWindowController() {
        this.loginService = new LoginService();
        this.userService = new UserService();
        this.mainService = new MainService();

    }

    public void newPatientOnAction(ActionEvent event) {
        try {
            Stage newStage = new Stage();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/Screens/NewUser.fxml")));
            newStage.setScene(newScene);
            newStage.show();
            this.reloadUsers();
        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nepodarilo sa otvoriť okno.");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.reloadPatients();
        this.reloadUsers();
    }

    private void reloadPatients() {
        this.patientView.getItems().clear();
        TableView patientTable = this.mainService.getData();
        this.patientView.getColumns().addAll(patientTable.getColumns());
        this.patientView.setItems(patientTable.getItems());
        this.patientView.refresh();

    }

    private void reloadUsers() {
        this.usersTableView.getItems().clear();
        TableView userTable = this.userService.getUsers();
        this.usersTableView.getColumns().addAll(userTable.getColumns());
        this.usersTableView.setItems(userTable.getItems());
        this.usersTableView.refresh();

    }

    public void importButtonOnAction(ActionEvent event) {
        this.mainService.importData((Stage) patientView.getScene().getWindow());
    }

    public void refreshButtonOnAction(ActionEvent event) {
        this.patientView.refresh();
    }
}
