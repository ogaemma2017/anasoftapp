package main;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.view.controller.HomePageController;
import main.view.utils.Navigation;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigation.HOME));

        //Parent root = FXMLLoader.load(getClass().getResource("main.view/fxml/home_page.fxml"));

        AnchorPane root = loader.load();
        JFXDecorator decorator = new JFXDecorator(primaryStage, root, false, false, true);
        decorator.setCustomMaximize(true);
        decorator.setMaximized(true);
        decorator.setBorder(Border.EMPTY);

        HomePageController controller = loader.getController();
        controller.setWindow(primaryStage);

        primaryStage.setScene(new Scene(decorator));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setIconified(false);
        primaryStage.show();
    }
}
