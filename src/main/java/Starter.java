import controller.ScreenSelectorDashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/screen_selector_dashboard_form.fxml"));
        Parent root = loader.load();
        ScreenSelectorDashboardController controller = loader.getController();
        controller.loadDashboard();
        stage.setScene(new Scene(root));
        stage.setTitle("Pharmacy Management System");
        stage.show();
    }
}
