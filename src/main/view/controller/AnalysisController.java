package main.view.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Project;
import main.view.utils.AlertsDialog;
import main.view.utils.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    Project project;
    @FXML
    private JFXComboBox<String> analysisTypeCombo;
    @FXML
    private JFXComboBox<String> powerOptionCombo;
    private String SOLAR_PV = "Solar PV";
    private String DIESEL_GEN = "Diesel Generator";


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void startAnalysis(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String analysis = analysisTypeCombo.getSelectionModel().getSelectedItem();
        String powerOption = powerOptionCombo.getSelectionModel().getSelectedItem();


        if (analysis == null) {
            AlertsDialog.showErrorDialog("Select the type of analysis you want to perform");
            return;
        }

        if (powerOption == null) {
            AlertsDialog.showErrorDialog("Select a power option for the analysis");
            return;
        }

        stage.close();

        switch (analysis.charAt(0)) {
            case 'B':
                if (powerOption.equals(SOLAR_PV)) {

                    FXMLLoader loader = showPage(Navigation.BCR_PV);

                    BcrPvController controller = loader.getController();
                    controller.setProject(project);

                } else {

                    FXMLLoader loader = showPage(Navigation.BCR_DIESEL);

                    BcrDieselController controller = loader.getController();
                    controller.setProject(project);

                }
                break;
            case 'I':
                if (project.getBcr_diesel() == null || project.getBcr_pv() == null
                        || project.getBcr_diesel().isEmpty() || project.getBcr_pv().isEmpty()) {

                    AlertsDialog.showErrorDialog("Please find the BCR for both PV and Diesel generator systems" +
                            "\nbefore attempting to find their IBCR");
                } else {
                    FXMLLoader loader = showPage(Navigation.IBCR);

                    IbcrFormController controller = loader.getController();
                    controller.setProject(project);
                }
            break;

            case 'L':
                if (powerOption.equals(SOLAR_PV)) {
                    FXMLLoader loader = showPage(Navigation.LCCA_PV);

                    LccaPvController controller = loader.getController();
                    controller.setProject(project);

                } else {

                    FXMLLoader loader = showPage(Navigation.LCCA_DIESEL);

                    LccaDieselController controller = loader.getController();
                    controller.setProject(project);

                }
                break;
        }


    }

    private FXMLLoader showPage(String location) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));

        //Parent root = FXMLLoader.load(getClass().getResource("main.view/fxml/home_page.fxml"));

        try {
            AnchorPane root = loader.load();

            primaryStage.setScene(new Scene(root));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setMaximized(true);
            primaryStage.setIconified(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> powerOption = FXCollections.observableArrayList(SOLAR_PV, DIESEL_GEN);
        powerOptionCombo.setItems(powerOption);

        ObservableList<String> analysisType = FXCollections.observableArrayList("BCR", "IBCR", "LCCA");
        analysisTypeCombo.setItems(analysisType);

    }


    public void setProject(Project project) {
        this.project = project;
    }
}
