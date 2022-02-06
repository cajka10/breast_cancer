package Controllers;

import Core.Entity.TrainedClassifier;
import Services.MLModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TrainingOutputWindowController {

    @FXML
    public TextArea outputTextArea;

    private MLModel mlModel;

    private TrainedClassifier classifier;
    public TrainingOutputWindowController() {
        this.mlModel = new MLModel();
    }

    public void init(TrainedClassifier classifier) {
        this.outputTextArea.setText(classifier.getEvaluation());
        this.classifier = classifier;
    }

    public void saveButtonOnAction(ActionEvent event) {
//        System.getProperty("user.dir") + "\\Models\\Model_ " + LocalDateTime.now().toString() + ".bin"
        String path = System.getProperty("user.dir") + "\\Models\\Model_" + LocalTime.now().getNano() +".bin";
        File file = new File(path);
        mlModel.saveModel(classifier.getClassifier(), System.getProperty("user.dir") +"\\Models\\Rand.bin" );
        mlModel.saveModel(classifier.getClassifier(), path );
    }
}
