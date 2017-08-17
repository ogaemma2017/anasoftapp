package main.view.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.view.utils.AlertsDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class BcrPvController implements Initializable {
    @FXML
    private JFXTextField productionTextField;

    @FXML
    private JFXTextField adminExpensesTextField;

    @FXML
    private JFXTextField researchTextField;

    @FXML
    private JFXTextField maintenanceTextField;

    @FXML
    private JFXTextField taxpaymentTextField;

    @FXML
    private JFXTextField totalCostTextField;

    @FXML
    private JFXTextField governmentGrantTextField;

    @FXML
    private JFXTextField governmentSubsidiesTextField;

    @FXML
    private JFXTextField investorFundTextField;

    @FXML
    private JFXTextField productionRevenueTextField;

    @FXML
    private JFXTextField incomeTaxBenefitTextField;

    @FXML
    private JFXTextField totalBenefitTextField;

    @FXML
    private JFXTextField discountTextField;

    @FXML
    private JFXTextField bcrInPresentWorkValueTextField;

    @FXML
    private JFXComboBox<Integer> yearsCombo;
    private double productionCost = 0;
    private double adminExpsenses = 0;
    private double research = 0;
    private double maintenance = 0;
    private double taxPayment = 0;
    private double totalCost = 0;

    private double governmentGrant = 0;
    private double governmentSubsidies = 0;
    private double investorFund = 0;
    private double productionRevenue = 0;
    private double incomeTaxBenefit = 0;
    private double totalBenefit = 0;

    int year = 1;
    double discountRate = 0;
    private double projectsCost = 0;
    private double projectBenefit = 0;
    private double BCR = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> years = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 27, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        yearsCombo.setItems(years);
        yearsCombo.getSelectionModel().selectFirst();


    }


    @FXML
    void selectYear(ActionEvent event) {
        year = yearsCombo.getSelectionModel().getSelectedItem();

    }

    @FXML
    void findBCRinPresentWorkValue(ActionEvent event) {
        if(discountTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter a discount value");
            return;
        }

        if(totalCostTextField.getText().isEmpty() || totalBenefitTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the total cost and total benefit first");
            return;
        }

        try{
            discountRate = Double.parseDouble(discountTextField.getText());
            projectsCost = Math.pow(totalCost/(1 + discountRate), year);
            projectBenefit = Math.pow(totalBenefit/(1 + discountRate), year);

            BCR = totalBenefit/totalCost;
            bcrInPresentWorkValueTextField.setText(Double.toString(BCR));

        }catch (Exception c){
            AlertsDialog.showErrorDialog("You entered an invalid discount value");
        }


    }

    @FXML
    void findTotalBenefit(ActionEvent event) {

        if(governmentGrantTextField.getText().isEmpty() || governmentSubsidiesTextField.getText().isEmpty() ||
                investorFundTextField.getText().isEmpty() || productionRevenueTextField.getText().isEmpty() ||
                incomeTaxBenefitTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Some fileds are empty\nCheck if all fields has been filled correctly");
            return;
        }

        try {
            governmentGrant = Double.parseDouble(governmentGrantTextField.getText());
            governmentSubsidies = Double.parseDouble(governmentSubsidiesTextField.getText());
            investorFund = Double.parseDouble(investorFundTextField.getText());
            productionRevenue = Double.parseDouble(productionRevenueTextField.getText());
            incomeTaxBenefit = Double.parseDouble(incomeTaxBenefitTextField.getText());

            totalBenefit = governmentGrant + governmentSubsidies + investorFund + productionRevenue + incomeTaxBenefit;
            totalBenefitTextField.setText(Double.toString(totalBenefit));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Some of the fields contains invalid characters.\n" +
                    "Only numbers are required in the inputs fields.");
        }

    }

    @FXML
    void findTotalCost(ActionEvent event) {
        if(productionTextField.getText().isEmpty() || adminExpensesTextField.getText().isEmpty() ||
                researchTextField.getText().isEmpty() || maintenanceTextField.getText().isEmpty() ||
                taxpaymentTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Some fileds are empty\nCheck if all fields has been filled correctly");
            return;
        }

        try {
            productionCost = Double.parseDouble(productionTextField.getText());
            adminExpsenses = Double.parseDouble(adminExpensesTextField.getText());
            research = Double.parseDouble(researchTextField.getText());
            maintenance = Double.parseDouble(maintenanceTextField.getText());
            taxPayment = Double.parseDouble(taxpaymentTextField.getText());

            totalCost = productionCost + adminExpsenses + research + maintenance + taxPayment;
            totalCostTextField.setText(Double.toString(totalCost));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Some of the fields contains invalid characters.\n" +
                    "Only numbers are required in the inputs fields.");
        }

    }
}
