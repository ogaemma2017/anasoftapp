package main.view.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Project;

import java.util.Optional;

public class IbcrFormController {


    @FXML
    private JFXTextField IBCRTextField;

    @FXML
    private JFXTextField totalBenefitOfSolarPvSystemTextField;

    @FXML
    private JFXTextField totalBenefitOfDieselGeneratorSystemTextField;

    @FXML
    private JFXTextField totalCostOfDieselGeneratorSystemTextField;

    Project project;

    @FXML
    void findIBCR(ActionEvent event) {

    }

    @FXML
    void findTotalBenefitOfDieselGenerator(ActionEvent event) {

    }

    @FXML
    void findTotalBenefitOfPvSystem(ActionEvent event) {

    }

    @FXML
    void findTotalCostOfDieselGenerator(ActionEvent event) {

    }

    @FXML
    void findTotalCostOfPvSystem(ActionEvent event) {

    }


    @FXML
    void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert confirmation = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to close the IBCR analysis window?");

        confirmation.initModality(Modality.APPLICATION_MODAL);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        confirmation.getButtonTypes().

                setAll(yes, no);

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == yes) {
            stage.close();
        } else {
            confirmation.close();
        }
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
