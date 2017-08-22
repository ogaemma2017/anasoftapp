package main.view.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.model.Project;
import main.view.utils.AlertsDialog;
import org.json.simple.JSONObject;

public class IbcrFormController {


    public static final String TOTAL_COST = "total_cost";
    public static final String TOTAL_BENEFIT = "total_benefit";


    @FXML
    private JFXTextField IBCRTextField;

    @FXML
    private JFXTextField totalBenefitOfSolarPvSystemTextField;

    @FXML
    private JFXTextField totalBenefitOfDieselGeneratorSystemTextField;
    public static final String TOTAL_BENEFIT_DIESEL = "total_benefit_diesel";

    @FXML
    private JFXTextField totalCostOfDieselGeneratorSystemTextField;


    Project project;
    public static final String TOTAL_BENEFIT_PV = "total_benefit_pv";
    public static final String TOTAL_COST_DIESEL = "total_cost_diesel";
    public static final String TOTAL_COST_PV = "total_cost_pv";
    public static final String IBCR_VALUE = "ibcr";
    public static final String MOST_BENEFICIAL = "most_beneficial";
    @FXML
    private JFXTextField totalCostOfSolarPvSystemTextField;
    private double totalBenefitDiesel = 0;
    private double totalBenefitPv = 0;
    private double totalCostDisel = 0;
    private double totalCostPv = 0;
    private double IBCR = 0;
    private String mostBeneficial = "";

    @FXML
    void findIBCR(ActionEvent event) {
        if (totalCostOfSolarPvSystemTextField.getText().isEmpty() ||
                totalCostOfDieselGeneratorSystemTextField.getText().isEmpty() ||
                totalBenefitOfSolarPvSystemTextField.getText().isEmpty() ||
                totalBenefitOfDieselGeneratorSystemTextField.getText().isEmpty()) {
            AlertsDialog.showErrorDialog("some fields are empty, You have to find all the fields first");

        } else {
            IBCR = (totalBenefitDiesel - totalBenefitPv) / (totalCostDisel - totalCostPv);
            if (IBCR >= 1) {
                mostBeneficial = (totalCostDisel > totalCostPv) ? "DIESEL" : "PV";
            } else {
                mostBeneficial = (totalCostDisel < totalCostPv) ? "DIESEL" : "PV";
            }

            IBCRTextField.setText(Double.toString(IBCR));
            saveToFile();
        }

    }

    @FXML
    void findTotalBenefitOfDieselGenerator(ActionEvent event) {
        totalBenefitDiesel = (double) project.getBcr_diesel().get(TOTAL_BENEFIT);
        totalBenefitOfDieselGeneratorSystemTextField.setText(Double.toString(totalBenefitPv));
    }

    @FXML
    void findTotalBenefitOfPvSystem(ActionEvent event) {
        totalBenefitPv = (double) project.getBcr_pv().get(TOTAL_BENEFIT);
        totalBenefitOfSolarPvSystemTextField.setText(Double.toString(totalBenefitPv));
    }

    @FXML
    void findTotalCostOfDieselGenerator(ActionEvent event) {
        totalCostDisel = (double) project.getBcr_diesel().get(TOTAL_COST);
        totalCostOfDieselGeneratorSystemTextField.setText(Double.toString(totalCostDisel));
    }

    @FXML
    void findTotalCostOfPvSystem(ActionEvent event) {
        totalCostPv = (double) project.getBcr_pv().get(TOTAL_COST);
        totalCostOfSolarPvSystemTextField.setText(Double.toString(totalCostPv));
    }

    @FXML
    void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
/*
        Alert confirmation = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to close the IBCR analysis window?");

        confirmation.initModality(Modality.APPLICATION_MODAL);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        confirmation.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == yes) {
            stage.close();
        } else {
            confirmation.close();
        }*/
        stage.close();
    }

    public void setProject(Project project) {

        this.project = project;
        readFromFile();

    }

    public void saveToFile() {
        JSONObject ibcr = new JSONObject();

        ibcr.put(TOTAL_BENEFIT_DIESEL, totalBenefitDiesel);
        ibcr.put(TOTAL_BENEFIT_PV, totalBenefitPv);
        ibcr.put(TOTAL_COST_DIESEL, totalCostDisel);
        ibcr.put(TOTAL_COST_PV, totalCostPv);
        ibcr.put(IBCR_VALUE, IBCR);
        ibcr.put(MOST_BENEFICIAL, mostBeneficial);


        project.setIbcr(ibcr);
    }


    public void readFromFile() {
        JSONObject ibcr = project.getIbcr();

        if (!(ibcr == null || ibcr.isEmpty())) {

            try {
                totalBenefitOfDieselGeneratorSystemTextField.setText(Double.toString((double) ibcr.get(TOTAL_BENEFIT_DIESEL)));
                totalBenefitOfSolarPvSystemTextField.setText(Double.toString((double) ibcr.get(TOTAL_BENEFIT_PV)));
                totalCostOfDieselGeneratorSystemTextField.setText(Double.toString((double) ibcr.get(TOTAL_COST_DIESEL)));
                totalCostOfSolarPvSystemTextField.setText(Double.toString((double) ibcr.get(TOTAL_COST_PV)));
                IBCRTextField.setText(Double.toString((double) ibcr.get(IBCR_VALUE)));
            } catch (Exception e) {
                AlertsDialog.showErrorDialog("Error reading IBCR from file");
                return;
            }
        }
    }
}
