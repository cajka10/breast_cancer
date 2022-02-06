package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TrainingOutputWindowController {

    @FXML
    public TextArea outputTextArea;

    public void init(String evaluation) {
        this.outputTextArea.setText(evaluation);
    }
}
