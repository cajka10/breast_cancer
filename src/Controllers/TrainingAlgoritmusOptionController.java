package Controllers;

import Core.Entity.Enum.ClassifierType;
import Core.Entity.TrainedClassifier;
import Screens.TrainingOutputWindowController;
import Services.ModelService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainingAlgoritmusOptionController implements Initializable {
    @FXML
    private ComboBox<ClassifierType> algoritmusComboBox;

    private ModelService modelService;

    public  TrainingAlgoritmusOptionController(){
        modelService = new ModelService();
    }

    public void ConfirmButtonOnAction(ActionEvent event) {
        TrainedClassifier classifier = this.modelService.train(this.algoritmusComboBox.getValue());

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Screens/TrainingOutputWindow.fxml"
                    )
            );
            Stage newStage = new Stage();
            newStage.setTitle("TrainingOutputWindowController window");
            newStage.setScene(new Scene(loader.load()));
            TrainingOutputWindowController rainingOutputWindowController = loader.getController();
            rainingOutputWindowController.init(classifier.getEvaluation());

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.algoritmusComboBox.getItems().addAll(ClassifierType.values());
    }
}
