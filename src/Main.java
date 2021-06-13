import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main extends Application {
    static final Logger logger = Logger.getLogger(Main.class.getName());
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Screens/Login.fxml"));

        logger.debug("Program started.");
        PropertyConfigurator.configure(getClass().getResource("log4j.properties"));
        primaryStage.setTitle("Breast cancer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
