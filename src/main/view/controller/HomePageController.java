package main.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.model.Project;
import main.view.utils.AlertsDialog;
import main.view.utils.Navigation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class HomePageController implements Initializable {

    @FXML
    private AnchorPane optionPane;

    @FXML
    private AnchorPane newProjectPane;


    private FileChooser fileChooser = null;
    private File selectedFile = null;
    private Stage window;

    private Project project;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = Project.getInstance();

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Anasoft project file", "*.asf"));
        fileChooser.setTitle("Open Project");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }


    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void analysis(ActionEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigation.ANALYSIS));

        //Parent root = FXMLLoader.load(getClass().getResource("main.view/fxml/home_page.fxml"));

        try {
            AnchorPane root = loader.load();

            AnalysisController controller = loader.getController();
            controller.setProject(project);

            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setIconified(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
       /* JFXDecorator decorator = new JFXDecorator(primaryStage, root, false, false, true);
        decorator.setCustomMaximize(true);
        decorator.setMaximized(true);
        decorator.setBorder(Border.EMPTY);*/


    }

    @FXML
    void decision(ActionEvent event) {

        try {
            project.getBcr_pv().get(BcrPvController.TOTAL_BCR);
            project.getBcr_diesel().get(BcrDieselController.TOTAL_BCR);
            project.getLcca_diesel().get(LccaDieselController.TOTAL_LCCA);
            project.getLcca_pv().get(LccaPvController.TOTAL_LCCA);
        } catch (Exception e) {
            AlertsDialog.showErrorDialog("You cannot make a decision until you have performed:\n\n" +
                    "1.   BCR analysis for diesel generator\n" +
                    "2.   BCR analysis for PV system\n" +
                    "3.   LCCA analysis for diesel generator\n" +
                    "4.   LCCA analysis for pv system\n" +
                    "\n" +
                    "Please complete the analysis and try again");

            return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigation.DECISIOIN));

        //Parent root = FXMLLoader.load(getClass().getResource("main.view/fxml/home_page.fxml"));

        try {
            AnchorPane root = loader.load();

            DecisionController controller = loader.getController();
            controller.setProject(project);

            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setIconified(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void exit(ActionEvent event) {

        Alert confirmation = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you to exit?");

        confirmation.initModality(Modality.APPLICATION_MODAL);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        confirmation.getButtonTypes().

                setAll(yes, no);

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == yes) {
            System.exit(0);
        } else {
            confirmation.close();
        }

    }

    @FXML
    void openExistingProject(ActionEvent event) {

        selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {
            project.openProject(selectedFile.getAbsolutePath());

            newProjectPane.setVisible(false);
            optionPane.setVisible(true);
        }


    }

    @FXML
    void startNewProject(ActionEvent event) {

        selectedFile = fileChooser.showSaveDialog(window);

        if (selectedFile != null) {
            project.createNewFile(selectedFile.getAbsolutePath());
            AlertsDialog.showSuccessDialog("New project created");

            newProjectPane.setVisible(false);
            optionPane.setVisible(true);
        }

    }

    public void setWindow(Stage window) {
        this.window = window;
    }


}
