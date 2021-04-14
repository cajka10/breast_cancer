package Services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class MainService {
    private ObservableList<ObservableList> data;

    public void importData(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose csv file.");
        fileChooser.showOpenDialog(stage);
    }
}
