package Controllers;

import Entity.Enum.UserRole;
import Entity.Enum.WindowMode;
import Entity.PatientRecord;
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
    @FXML
    private  Button patientDetailButton;

    private LoginService loginService;
    private UserService userService;
    private MainService mainService;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private TableView<PatientRecord> patientView;

    @FXML
    private TableView<PatientRecord> patientRecordsTableView;

    @FXML
    private TableView usersTableView;

    @FXML
    private Tab adminTab;

    @FXML
    private Tab patientsTab;

    @FXML
    private Tab usersTab;


    public MainWindowController() {
        this.loginService = new LoginService();
        this.userService = new UserService();
        this.mainService = new MainService();

    }

    public void setUserEnvironment(UserRole role){
        if (role.equals(UserRole.DOCTOR)){
            adminTab.setDisable(true);
            usersTab.setDisable(true);
        }
    }

    public void newUserOnAction(ActionEvent event) {
        try {
            Stage newStage = new Stage();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/Screens/NewUser.fxml")));
            newStage.setScene(newScene);
            newStage.showAndWait();
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
        this.reloadPatientRecords();
        this.reloadUsers();
    }

    private void reloadPatients() {
        this.patientView.getItems().clear();
        TableView patientTable = this.mainService.getPatients();
        this.patientView.getColumns().addAll(patientTable.getColumns());
        this.patientView.setItems(patientTable.getItems());
        this.patientView.refresh();

    }

    private void reloadPatientRecords() {
        this.patientRecordsTableView.getItems().clear();
        TableView patientTable = this.mainService.getPatientsRecords();
        this.patientRecordsTableView.getColumns().addAll(patientTable.getColumns());
        this.patientRecordsTableView.setItems(patientTable.getItems());
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

    public void importPatientButtonOnAction(ActionEvent event) {
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addPatientByDoctorButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Screens/NewPatientRecord.fxml"
                    )
            );
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            NewPatientRecordController patientRecordController = loader.getController();
            patientRecordController.init(null, WindowMode.NEW);

            newStage.showAndWait();
            this.reloadPatients();

        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nepodarilo sa otvoriť okno.");

            alert.showAndWait();
        }
    }

    public void patientDetailButtonOnAction(ActionEvent event) {
        TablePosition pos = this.patientView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        Object ob = this.patientView.getColumns().get(0).getCellObservableValue(row).getValue();

        int patientId = (new Double(ob.toString())).intValue();
        PatientRecord record = this.mainService.getPatientById(patientId);

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Screens/NewPatientRecord.fxml"
                    )
            );
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            NewPatientRecordController patientRecordController = loader.getController();
            patientRecordController.init(record, WindowMode.DETAIL);

            newStage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Nepodarilo sa otvoriť okno.");

            alert.showAndWait();
        }
    }
}
