package main.view.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.model.Project;
import main.view.utils.AlertsDialog;
import org.json.simple.JSONObject;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
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


    public static String TOTAL_BCR = "bcr";
    DecimalFormat df = new DecimalFormat("#.##");


    @FXML
    void selectYear(ActionEvent event) {
        year = yearsCombo.getSelectionModel().getSelectedItem();

    }

    private String GOVERNMENT_SUBSIDIES = "government_subsidies";
    private String INVESTOR_FUND = "investor_fund";
    private String PROUDCTION_REVENU = "production_revenue";
    private String INCOME_TAX_BENEFIT = "income_tax_benefit";
    private String TOTAL_BENEFIT = "total_benefit";
    private String YEAR = "year";
    private String DISCOUNT = "discount";
    private String PRODUCTION_COST = "production";
    private String ADMIN_EXPENSES = "admin_expenses";
    private String RESEARCH = "research";
    private String MAINTENANCE = "maintenance";
    private String TAX_PAYMENT = "tax_payment";
    private String TOTAL_COST = "total_cost";
    private String GOVERNEMNT_GRANT = "government_grant";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        df.setRoundingMode(RoundingMode.HALF_UP);

        ObservableList<Integer> years = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 27, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        yearsCombo.setItems(years);
        yearsCombo.getSelectionModel().selectFirst();


    }
    Project project;

    @FXML
    void findBCRinPresentWorkValue(ActionEvent event) {
        if (discountTextField.getText().isEmpty()) {
            AlertsDialog.showErrorDialog("Enter a discount value");
            return;
        }

        if (totalCostTextField.getText().isEmpty() || totalBenefitTextField.getText().isEmpty()) {
            AlertsDialog.showErrorDialog("Find the total cost and total benefit first");
            return;
        }

        if (totalCost == 0 || totalBenefit == 0) {
            AlertsDialog.showErrorDialog("Find the total cost and total benefit first");
            return;
        }

        try {
            discountRate = Double.parseDouble(discountTextField.getText()) / 100;
            projectsCost = totalCost / Math.pow((1 + discountRate), year);
            projectBenefit = totalBenefit / Math.pow((1 + discountRate), year);

            BCR = projectBenefit / projectsCost;
            bcrInPresentWorkValueTextField.setText(df.format(BCR));

            saveToFile();

        } catch (Exception c) {
            AlertsDialog.showErrorDialog("You entered an invalid discount value");
            c.printStackTrace();
        }


    }

    @FXML
    void findTotalBenefit(ActionEvent event) {

        if (governmentGrantTextField.getText().isEmpty() || governmentSubsidiesTextField.getText().isEmpty() ||
                investorFundTextField.getText().isEmpty() || productionRevenueTextField.getText().isEmpty() ||
                incomeTaxBenefitTextField.getText().isEmpty()) {
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
            totalBenefitTextField.setText(df.format(totalBenefit));


        } catch (Exception e) {
            AlertsDialog.showErrorDialog("Some of the fields contains invalid characters.\n" +
                    "Only numbers are required in the inputs fields.");
        }

    }

    @FXML
    void findTotalCost(ActionEvent event) {
        if (productionTextField.getText().isEmpty() || adminExpensesTextField.getText().isEmpty() ||
                researchTextField.getText().isEmpty() || maintenanceTextField.getText().isEmpty() ||
                taxpaymentTextField.getText().isEmpty()) {
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
            totalCostTextField.setText(df.format(totalCost));
        } catch (Exception e) {
            AlertsDialog.showErrorDialog("Some of the fields contains invalid characters.\n" +
                    "Only numbers are required in the inputs fields.");
        }

    }

    @FXML
    void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

//        Alert confirmation = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
//        confirmation.setHeaderText(null);
//        confirmation.setContentText("Are you sure you want to close the BCR diesel generator analysis window?");
//
//        confirmation.initModality(Modality.APPLICATION_MODAL);
//
//        ButtonType yes = new ButtonType("Yes");
//        ButtonType no = new ButtonType("No");
//
//        confirmation.getButtonTypes().
//
//                setAll(new ButtonType[]{yes, no});
//
//        Optional<ButtonType> result = confirmation.showAndWait();
//
//        if (result.get() == yes) {
//            stage.close();
//        } else {
//            confirmation.close();
//        }

        stage.close();
    }

    private void saveToFile() {

        JSONObject BCRPV = new JSONObject();

        BCRPV.put(YEAR, yearsCombo.getSelectionModel().getSelectedItem());
        BCRPV.put(DISCOUNT, this.discountRate);
        BCRPV.put(PRODUCTION_COST, this.productionCost);
        BCRPV.put(ADMIN_EXPENSES, this.adminExpsenses);
        BCRPV.put(RESEARCH, this.research);
        BCRPV.put(MAINTENANCE, this.maintenance);
        BCRPV.put(TAX_PAYMENT, this.taxPayment);

        BCRPV.put(GOVERNEMNT_GRANT, this.governmentGrant);
        BCRPV.put(GOVERNMENT_SUBSIDIES, this.governmentSubsidies);
        BCRPV.put(INVESTOR_FUND, this.investorFund);
        BCRPV.put(PROUDCTION_REVENU, this.productionRevenue);
        BCRPV.put(INCOME_TAX_BENEFIT, this.incomeTaxBenefit);

        BCRPV.put(TOTAL_BENEFIT, this.totalBenefit);
        BCRPV.put(TOTAL_COST, this.totalCost);

        BCRPV.put(TOTAL_BCR, BCR);

        project.setBcr_pv(BCRPV);

    }

    private void readFromFile() {
        JSONObject bcr_pv = project.getBcr_pv();

        if (!(bcr_pv.isEmpty() || bcr_pv == null)) {

            try {

                //yearsCombo.getSelectionModel().select((year = (int) diesel.get(YEAR)));
                discountTextField.setText(df.format(discountRate = (double) bcr_pv.get(DISCOUNT)));
                productionTextField.setText(df.format(productionCost = (double) bcr_pv.get(PRODUCTION_COST)));
                adminExpensesTextField.setText(df.format(adminExpsenses = (double) bcr_pv.get(ADMIN_EXPENSES)));
                researchTextField.setText(df.format(research = (double) bcr_pv.get(RESEARCH)));
                maintenanceTextField.setText(df.format(maintenance = (double) bcr_pv.get(MAINTENANCE)));
                taxpaymentTextField.setText(df.format(taxPayment = (double) bcr_pv.get(TAX_PAYMENT)));
                totalCostTextField.setText(df.format(totalCost = (double) bcr_pv.get(TOTAL_COST)));

                governmentGrantTextField.setText(df.format(governmentGrant = (double) bcr_pv.get(GOVERNEMNT_GRANT)));
                governmentSubsidiesTextField.setText(df.format(governmentSubsidies = (double) bcr_pv.get(GOVERNMENT_SUBSIDIES)));
                investorFundTextField.setText(df.format(investorFund = (double) bcr_pv.get(INVESTOR_FUND)));
                productionRevenueTextField.setText(df.format(productionRevenue = (double) bcr_pv.get(PROUDCTION_REVENU)));
                incomeTaxBenefitTextField.setText(df.format(incomeTaxBenefit = (double) bcr_pv.get(INCOME_TAX_BENEFIT)));
                totalBenefitTextField.setText(df.format(totalBenefit = (double) bcr_pv.get(TOTAL_BENEFIT)));

                bcrInPresentWorkValueTextField.setText(df.format((double) bcr_pv.get(TOTAL_BCR)));

            } catch (Exception e) {
                AlertsDialog.showErrorDialog("there was an error getting the saved data");
            }
        }
    }

    public void setProject(Project project) {
        this.project = project;

        readFromFile();
    }
}
