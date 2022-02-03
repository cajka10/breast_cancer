package Controllers;

import Core.Entity.Enum.ClassifierType;
import Core.Entity.Enum.UserRole;
import Core.Entity.Enum.WindowMode;
import Core.Entity.PatientRecord;
import Core.Entity.TrainedClassifier;
import Core.Entity.User;
import Screens.TrainingOutputWindowController;
import Services.LoginService;
import Services.MainService;
import Services.ModelService;
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


public class MainWindowController{
    @FXML
    private Button patientDetailButton;

    private LoginService loginService;
    private UserService userService;
    private MainService mainService;
    private ModelService modelService;
    private User loggedUser;

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
        this.modelService = new ModelService();
        this.loggedUser = new User();
    }

    public void setUserEnvironment(User user) {
        this.loggedUser = user;
        if (user.getRole().equals(UserRole.DOCTOR)){
            this.reloadPatients();
        }
        else{
            this.reloadPatientRecords();
            this.reloadUsers();
        }

        if (user.getRole().equals(UserRole.DOCTOR)) {
            adminTab.setDisable(true);
            usersTab.setDisable(true);
        } else {
            patientsTab.setDisable(true);
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

    private void reloadPatients() {
        this.patientView.getItems().clear();
        TableView patientTable = this.mainService.getPatients(loggedUser.getUserId());
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
        this.reloadPatients();
    }

    public void importPatientButtonOnAction(ActionEvent event) {
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addPatientByDoctorButtonOnAction(ActionEvent event) {
        this.openPatientWindow(WindowMode.NEW);
        this.reloadPatients();
    }

    public void patientDetailButtonOnAction(ActionEvent event) {
        this.openPatientWindow(WindowMode.DETAIL);
    }

    public void patientEditButtonOnAction(ActionEvent event) {
        this.openPatientWindow(WindowMode.EDIT);
        this.reloadPatients();
    }

    private void openPatientWindow(WindowMode mode) {
        PatientRecord record = null;
        if (!mode.equals(WindowMode.NEW)) {
            TablePosition pos = this.patientView.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Object ob = this.getTableColumnByName(patientView, "record_id").getCellObservableValue(row).getValue();

            int patientId = (new Double(ob.toString())).intValue();
            record = this.mainService.getPatientById(patientId);
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Screens/PatientWindow.fxml"
                    )
            );
            Stage newStage = new Stage();
            newStage.setTitle("Patient window");
            newStage.setScene(new Scene(loader.load()));
            PatientWindowController patientRecordController = loader.getController();
            assert record != null;
            patientRecordController.init(record, mode, loggedUser);

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

    public void trainModelButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Screens/TrainingAlgoritmusOptionWindow.fxml"
                    )
            );
            Stage newStage = new Stage();
            newStage.setTitle("Training settings");
            newStage.setScene(new Scene(loader.load()));
//            TrainingAlgoritmusOptionController trainingAlgoritmusOptionController = loader.getController();

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

    private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }

}
