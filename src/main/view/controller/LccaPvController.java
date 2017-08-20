package main.view.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Project;
import main.view.utils.AlertsDialog;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LccaPvController implements Initializable{

    @FXML
    private JFXComboBox<Integer> yearCombo;

    @FXML
    private JFXTextField wireEfficiencyTextField, batteryEfficiencyTextField, powerEfficiencyTextField,
            escalationRateTextField, discountRateTextField;

    @FXML
    private JFXTextField acLoad1TextField, acLoad2TextField, acLoad3TextField, acLoad4TextField, acLoad5TextField;

    @FXML
    private JFXComboBox<Integer> acLoad1DdcCombo, acLoad1WdcCombo, acLoad2DdcCombo, acLoad3DdcCombo,
            acLoad4DdcCombo, acLoad5DdcCombo, acLoad2WdcCombo, acLoad3WdcCombo, acLoad4WdcCombo, acLoad5WdcCombo;

    @FXML
    private JFXTextField dcLoad1TextField, dcLoad2TextField, dcLoad3TextField, dcLoad4TextField, dcLoad5TextField;

    @FXML
    private JFXComboBox<Integer> dcLoad1DdcCombo, dcLoad1WdcCombo,dcLoad2DdcCombo, dcLoad3DdcCombo, dcLoad4DdcCombo,
            dcLoad5DdcCombo, dcLoad2WdcCombo, dcLoad3WdcCombo, dcLoad4WdcCombo, dcLoad5WdcCombo;

    @FXML
    private JFXTextField moduleCapacityTextField;

    @FXML
    private JFXTextField moduleCapacityNumberTextField;

    @FXML
    private JFXTextField chargeControllerTextField;

    @FXML
    private JFXTextField chargeControllerNumberTextField;

    @FXML
    private JFXTextField batteryCapacityTextField;

    @FXML
    private JFXTextField batteryCapacityNumberTextField;

    @FXML
    private JFXTextField inverterCapacityTextField;

    @FXML
    private JFXTextField inverterCapacityNumberTextField;

    @FXML
    private JFXTextField capitalCostInitialCostTextField;

    @FXML
    private JFXTextField capitalCostLCC;

    @FXML
    private JFXTextField BatteryReplacementnoOfReplacementTextField;

    @FXML
    private JFXTextField batteryReplacementLCCATextField;

    @FXML
    private JFXTextField InverterReplacementNoOfReplacementTextField;

    @FXML
    private JFXTextField InverterReplacementLCCATextField;

    @FXML
    private JFXTextField controllerReplacementNoOfReplacementTextField;

    @FXML
    private JFXTextField controllerReplacementLCCATextField;

    @FXML
    private JFXTextField operationAndMaintenanceInitialCostTextField;

    @FXML
    private JFXTextField batteryReplacementCostInitialCostTextField;

    @FXML
    private JFXTextField inverterReplacementCostInitialCostTextField;

    @FXML
    private JFXTextField controllerReplacementCostInitialCostTextField;

    @FXML
    private JFXTextField operationAndMaintenanceLCC;

    @FXML
    private JFXTextField totalReplacementCostInitialCostTextField;

    @FXML
    private JFXTextField totalReplacementLCCTextField;

    @FXML
    private JFXTextField salvageValueTextField;

    @FXML
    private JFXTextField lccSalvageValueTextField;

    @FXML
    private JFXTextField totalLCCAOfAllComponentTextField;

    @FXML
    private JFXTextField totalLoadTextField;

    @FXML
    private JFXTextField norminalSystemVoltageTextField;

    @FXML
    private JFXTextField totalAmpHrLoadTextField;

    @FXML
    private JFXTextField correctedAmpHrLoadTextField;

    @FXML
    private JFXComboBox<String> locationCombo;

    @FXML
    private JFXTextField designCurrentTextField;

    @FXML
    private JFXTextField peakSunValueTextField;


    private double wireEfficiency = 0.98;
    private double batteryEfficiency = 0.9;
    private double powerConversionEfficiency = 0.9;
    private double escalationRate = 13.71;
    private double discountRate = 16.9;

    private double peakSunValue = 4.92;


    Formulas formulas = null;
    private double totalLoad = 0;
    private double norminalSystemVoltage = 0;


    double moduleCapacity = 0;
    double batteryNumberCapacity = 0;
    double chargeControllerCapacity = 0;
    double inverterNumberCapacity = 0;


    double capitalCost = 0;
    double operationAndMaintenance = 0;
    double batteryReplacement = 0;
    double inverterReplacement = 0;
    double controllerReplacement = 0;
    private Integer years = 0;

    private double totalReplacementInitialCost = 0;
    private double totalReplacementLCCACost = 0;
    private double LCCSalvageValue = 0;
    private double totalLCCA = 0;
    private double totalAmpereHrLoad = 0;
    private double correctedAmpHrLoad = 0;
    private double designCurrent = 0;

    public static final String YEARS = "years";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        formulas = new Formulas();

        wireEfficiencyTextField.setText(Double.toString(wireEfficiency));
        batteryEfficiencyTextField.setText(Double.toString(batteryEfficiency));
        powerEfficiencyTextField.setText(Double.toString(powerConversionEfficiency));
        escalationRateTextField.setText(Double.toString(escalationRate));
        discountRateTextField.setText(Double.toString(discountRate));

        peakSunValueTextField.setText(Double.toString(peakSunValue));

        //Initialize combo boxes
        ObservableList<Integer> years = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 ,35, 36, 37, 38, 39, 40);
        yearCombo.setItems(years);
        yearCombo.getSelectionModel().selectFirst();

        ObservableList<String> locations = FXCollections.observableArrayList("Aninri", "Agwu", "Enugu East", "Enugu North", "Enugu South", "Ezeagu", "Igbo-etit",
        "Igbo-eze North", "Igbo-eze South", "Isi Uzo", "Nkanu East", "Nkanu West", "Nsukka", "Oji River", "Udenu",
        "Udi", "Uzo-Uwani");
        locationCombo.setItems(locations);
        locationCombo.getSelectionModel().selectFirst();
        peakSunValueTextField.setText(Double.toString(peakSunValue));

        ObservableList<Integer> hours = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        acLoad1DdcCombo.setItems(hours);
        acLoad2DdcCombo.setItems(hours);
        acLoad3DdcCombo.setItems(hours);
        acLoad4DdcCombo.setItems(hours);
        acLoad5DdcCombo.setItems(hours);

        dcLoad1DdcCombo.setItems(hours);
        dcLoad2DdcCombo.setItems(hours);
        dcLoad3DdcCombo.setItems(hours);
        dcLoad4DdcCombo.setItems(hours);
        dcLoad5DdcCombo.setItems(hours);

        ObservableList<Integer> days = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);

        acLoad1WdcCombo.setItems(days);
        acLoad2WdcCombo.setItems(days);
        acLoad3WdcCombo.setItems(days);
        acLoad4WdcCombo.setItems(days);
        acLoad5WdcCombo.setItems(days);

        dcLoad1WdcCombo.setItems(days);
        dcLoad2WdcCombo.setItems(days);
        dcLoad3WdcCombo.setItems(days);
        dcLoad4WdcCombo.setItems(days);
        dcLoad5WdcCombo.setItems(days);


    }


    @FXML
    void selectYears(ActionEvent event) {
        years = yearCombo.getSelectionModel().getSelectedItem();

    }

    @FXML
    void findCorrectedAmpHrLoad(ActionEvent event) {
        if(totalAmpHrLoadTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the total ampere hour load first");
            return;
        }

        correctedAmpHrLoad = formulas.correctedAmpereHrLoad(totalAmpereHrLoad, wireEfficiency, batteryEfficiency);
        correctedAmpHrLoadTextField.setText(Double.toString(correctedAmpHrLoad));

    }

    @FXML
    void findDesignCurrent(ActionEvent event) {
        if(correctedAmpHrLoadTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the corrected ampere hour load first");
            return;
        }

        designCurrent = formulas.calculateDesignCurrent(correctedAmpHrLoad, peakSunValue);
        designCurrentTextField.setText(Double.toString(designCurrent));
    }

    @FXML
    void findLCCSalvageValue(ActionEvent event) {
        if(salvageValueTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("You need to find the salvage value first");

            return;
        }

        LCCSalvageValue = formulas.calculateLCCOfSalvageValue(capitalCost, escalationRate, discountRate, years);
        lccSalvageValueTextField.setText(Double.toString(LCCSalvageValue));
    }

    @FXML
    void findModuleCapacity(ActionEvent event) {

        if(norminalSystemVoltage == 0){
            AlertsDialog.showErrorDialog("find the norminal system voltage first");
            return;
        }
        if(moduleCapacityTextField.getText().isEmpty() || batteryCapacityTextField.getText().isEmpty()
                || chargeControllerTextField.getText().isEmpty() || inverterCapacityTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Some fields are empty\nPlease fill in all fields correctly");

            return;
        }

        try{
            moduleCapacity = Double.parseDouble(moduleCapacityTextField.getText())/36;
            batteryNumberCapacity = Double.parseDouble(batteryCapacityTextField.getText())/norminalSystemVoltage;
            chargeControllerCapacity = Double.parseDouble(chargeControllerTextField.getText())/norminalSystemVoltage;
            inverterNumberCapacity = Double.parseDouble(inverterCapacityTextField.getText())/norminalSystemVoltage;

            moduleCapacityNumberTextField.setText(Double.toString(moduleCapacity));
            batteryCapacityNumberTextField.setText(Double.toString(batteryNumberCapacity));
            chargeControllerNumberTextField.setText(Double.toString(chargeControllerCapacity));
            inverterCapacityNumberTextField.setText(Double.toString(inverterNumberCapacity));

        }catch (Exception e){
            AlertsDialog.showErrorDialog("Some fields contains invalid values");
        }

    }

    @FXML
    void findNorminalSystemVoltage(ActionEvent event) {
        if(totalLoadTextField.getText().isEmpty() || totalLoadTextField.getText().equals("")) {
            AlertsDialog.showErrorDialog("You have to find the total load in kw first");

            norminalSystemVoltage = 0;
            norminalSystemVoltageTextField.setText("");

            return;
        }

        norminalSystemVoltage = formulas.calculateNorminalSystemVolatage(totalLoad);
        norminalSystemVoltageTextField.setText(Double.toString(norminalSystemVoltage));

    }

    @FXML
    void findSalvageValue(ActionEvent event) {
        if(capitalCostInitialCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("You need to enter the initial capital cost first");

            return;
        }

        salvageValueTextField.setText(Double.toString(0.2 * capitalCost));

    }

    @FXML
    void findTotalAmpHrLoad(ActionEvent event) {

        if(norminalSystemVoltageTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("You need to find the norminal value first");
            return;
        }

        double totalDDC = getTotalDDC();
        double totalWDC = getTotalWDC();

        totalAmpereHrLoad = formulas.calculateTotalAmpereHrLoad(totalLoad, totalDDC, totalWDC,
                norminalSystemVoltage, powerConversionEfficiency);

        totalAmpHrLoadTextField.setText(Double.toString(totalAmpereHrLoad));

    }

    public static final String WIRE_EFFICIENCY = "wire_efficiency";

    @FXML
    void findTotalLoad(ActionEvent event) {

        double totalAcLaod = getTotalAcLoad();
        double totalDCLoad = getTotalDcLoad();


        if(totalAcLaod == 0 && totalDCLoad == 0) {
            AlertsDialog.showErrorDialog("You have not inputted any AC or DC load\n" +
                    "You must enter atleast one AC or DC load");
            totalLoadTextField.setText("");
            inverterCapacityTextField.setText("");
            return;
        }

        totalLoad = formulas.calculateTotalLoadInKw(totalAcLaod, totalDCLoad);

        totalLoadTextField.setText(Double.toString(totalLoad));
        inverterCapacityTextField.setText(Double.toString(totalLoad));

    }

    @FXML
    void findTotalReplacementCost(ActionEvent event) {
        if(capitalCostInitialCostTextField.getText().isEmpty() ||
                batteryReplacementCostInitialCostTextField.getText().isEmpty() ||
                operationAndMaintenanceInitialCostTextField.getText().isEmpty() ||
                inverterReplacementCostInitialCostTextField.getText().isEmpty() ||
                controllerReplacementCostInitialCostTextField.getText().isEmpty()){

            AlertsDialog.showErrorDialog("Some fileds are empty\nFill all fields correctly and try again");
            return;
        }

        try{
            double capitalcostInitialCost = Double.parseDouble(capitalCostInitialCostTextField.getText());
            double operationAndMaintenanceCostInitialCost = Double.parseDouble(operationAndMaintenanceInitialCostTextField.getText());
            double inverterControllerCostInitialCost = Double.parseDouble(inverterReplacementCostInitialCostTextField.getText());
            double batterReplacementCost = Double.parseDouble(batteryReplacementCostInitialCostTextField.getText());
            double controllerReplacementCost = Double.parseDouble(controllerReplacementCostInitialCostTextField.getText());

            operationAndMaintenance = formulas.calculateLCCOfMaintenance(operationAndMaintenanceCostInitialCost, escalationRate, discountRate, years);
            inverterReplacement = formulas.calculateInverterReplacement(inverterControllerCostInitialCost, escalationRate, discountRate, years);
            batteryReplacement = formulas.calculateLCCOfBattery(batterReplacementCost, escalationRate, discountRate, years);
            controllerReplacement = formulas.calculateLCCOfChargeController(controllerReplacementCost, escalationRate, discountRate, years);

            totalReplacementInitialCost = capitalcostInitialCost + operationAndMaintenanceCostInitialCost + inverterControllerCostInitialCost +
                    batterReplacementCost + controllerReplacementCost;

            totalReplacementLCCACost = capitalcostInitialCost + operationAndMaintenance + inverterReplacement +
                    batteryReplacement + controllerReplacement;

            capitalCostLCC.setText(Double.toString(capitalcostInitialCost));
            operationAndMaintenanceLCC.setText(Double.toString(operationAndMaintenance));
            batteryReplacementLCCATextField.setText(Double.toString(batteryReplacement));
            InverterReplacementLCCATextField.setText(Double.toString(inverterReplacement));
            controllerReplacementLCCATextField.setText(Double.toString(controllerReplacement));

            totalReplacementCostInitialCostTextField.setText(Double.toString(totalReplacementInitialCost));
            totalReplacementLCCTextField.setText(Double.toString(totalReplacementLCCACost));

        }catch (Exception e){
            AlertsDialog.showErrorDialog("Some fileds contains invalid characters\nFill all fields correctly and try again");
        }
    }

    public double getTotalAcLoad() {

        double totalAcLoad = 0;

        if(!acLoad1TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(acLoad1TextField.getText());


                double ddc = 1;
                if(!acLoad1DdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad1DdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad1DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!acLoad1WdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad1WdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad1WdcCombo.getSelectionModel().selectFirst();

                totalAcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("AC load 1 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!acLoad2TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(acLoad2TextField.getText());


                double ddc = 1;
                if(!acLoad2DdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad2DdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad2DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!acLoad2WdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad2WdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad2WdcCombo.getSelectionModel().selectFirst();

                totalAcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("AC load 2 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!acLoad3TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(acLoad3TextField.getText());


                double ddc = 1;
                if(!acLoad3DdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad3DdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad3DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!acLoad3WdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad3WdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad3WdcCombo.getSelectionModel().selectFirst();

                totalAcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("AC load 3 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!acLoad4TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(acLoad4TextField.getText());


                double ddc = 1;
                if(!acLoad4DdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad4DdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad4DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!acLoad4WdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad4WdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad4WdcCombo.getSelectionModel().selectFirst();

                totalAcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("AC load 4 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!acLoad5TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(acLoad5TextField.getText());


                double ddc = 1;
                if(!acLoad5DdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad5DdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad5DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!acLoad5WdcCombo.getSelectionModel().isEmpty())
                    ddc = acLoad5WdcCombo.getSelectionModel().getSelectedItem();
                else
                    acLoad5WdcCombo.getSelectionModel().selectFirst();

                totalAcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("AC load 5 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        return totalAcLoad;
    }

    public double getTotalDcLoad() {
        double totalDcLoad = 0;

        if(!dcLoad1TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(dcLoad1TextField.getText());


                double ddc = 1;
                if(!dcLoad1DdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad1DdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad1DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!dcLoad1WdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad1WdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad1WdcCombo.getSelectionModel().selectFirst();

                totalDcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("DC load 1 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!dcLoad2TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(dcLoad2TextField.getText());


                double ddc = 1;
                if(!dcLoad2DdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad2DdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad2DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!dcLoad2WdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad2WdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad2WdcCombo.getSelectionModel().selectFirst();

                totalDcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("DC load 2 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!dcLoad3TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(dcLoad3TextField.getText());


                double ddc = 1;
                if(!dcLoad3DdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad3DdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad3DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!dcLoad3WdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad3WdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad3WdcCombo.getSelectionModel().selectFirst();

                totalDcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("DC load 3 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!dcLoad4TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(dcLoad4TextField.getText());


                double ddc = 1;
                if(!dcLoad4DdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad4DdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad4DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!dcLoad4WdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad4WdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad4WdcCombo.getSelectionModel().selectFirst();

                totalDcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("DC load 4 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        if(!dcLoad5TextField.getText().isEmpty()){
            try{
                double value = Double.parseDouble(dcLoad5TextField.getText());


                double ddc = 1;
                if(!dcLoad5DdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad5DdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad5DdcCombo.getSelectionModel().selectFirst();


                double wdc = 1;
                if(!dcLoad5WdcCombo.getSelectionModel().isEmpty())
                    ddc = dcLoad5WdcCombo.getSelectionModel().getSelectedItem();
                else
                    dcLoad5WdcCombo.getSelectionModel().selectFirst();

                totalDcLoad += (value * ddc * wdc);

            }catch (Exception e){
                AlertsDialog.showErrorDialog("DC load 5 text field contains an invalid number" +
                        "\nPlease only digits(0-9) are required in the text boxes");

                return 0;
            }
        }

        return totalDcLoad;
    }

    public double getTotalDDC() {
        double totalDDC = 0;

        if(!acLoad1DdcCombo.getSelectionModel().isEmpty())
            totalDDC += acLoad1DdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad2DdcCombo.getSelectionModel().isEmpty())
            totalDDC += acLoad2DdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad3DdcCombo.getSelectionModel().isEmpty())
            totalDDC += acLoad3DdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad4DdcCombo.getSelectionModel().isEmpty())
            totalDDC += acLoad4DdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad5DdcCombo.getSelectionModel().isEmpty())
            totalDDC += acLoad5DdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad1DdcCombo.getSelectionModel().isEmpty())
            totalDDC += dcLoad1DdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad2DdcCombo.getSelectionModel().isEmpty())
            totalDDC += dcLoad2DdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad3DdcCombo.getSelectionModel().isEmpty())
            totalDDC += dcLoad3DdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad4DdcCombo.getSelectionModel().isEmpty())
            totalDDC += dcLoad4DdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad5DdcCombo.getSelectionModel().isEmpty())
            totalDDC += dcLoad5DdcCombo.getSelectionModel().getSelectedItem();

        return totalDDC;
    }

    public double getTotalWDC() {
        double totalWDC = 0;

        if(!acLoad1WdcCombo.getSelectionModel().isEmpty())
            totalWDC += acLoad1WdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad2WdcCombo.getSelectionModel().isEmpty())
            totalWDC += acLoad2WdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad3WdcCombo.getSelectionModel().isEmpty())
            totalWDC += acLoad3WdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad4WdcCombo.getSelectionModel().isEmpty())
            totalWDC += acLoad4WdcCombo.getSelectionModel().getSelectedItem();

        if(!acLoad5WdcCombo.getSelectionModel().isEmpty())
            totalWDC += acLoad5WdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad1WdcCombo.getSelectionModel().isEmpty())
            totalWDC += dcLoad1WdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad2WdcCombo.getSelectionModel().isEmpty())
            totalWDC += dcLoad2WdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad3WdcCombo.getSelectionModel().isEmpty())
            totalWDC += dcLoad3WdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad4WdcCombo.getSelectionModel().isEmpty())
            totalWDC += dcLoad4WdcCombo.getSelectionModel().getSelectedItem();

        if(!dcLoad5WdcCombo.getSelectionModel().isEmpty())
            totalWDC += dcLoad5WdcCombo.getSelectionModel().getSelectedItem();

        return totalWDC;
    }

    public static final String BATTERY_EFFICIENCY = "battery_efficiency";
    public static final String POWER_CONVERSION_EFFICIENCY = "power_conversion_efficiency";

    //============================================DATA PERSISTENCE ==================================//
    public static final String ESCALATION_RATE = "escalation_rate";
    public static final String DISCOUNT_RATE = "discount_rate";
    public static final String PEAK_SUN_VALUE = "peak_sun_value";
    public static final String TOTAL_LOAD = "total_load";
    public static final String NORMINAL_SYSTEM_VOLTAGE = "norminal_system_voltage";
    public static final String MODULE_CAPACITY = "module_capacity";
    public static final String BATTERY_NUMBER_CAPACITY = "battery_number_capacity";
    public static final String CHARGE_CONTROLLER_CAPACITY = "charge_controller_capacity";
    public static final String INVERTER_NUMBER_CAPACITY = "inverter_number_capacity";
    public static final String CAPITAL_COST = "capital_cost";
    public static final String OPERATION_AND_MAINTENANCE = "operation_and_maintenance";
    public static final String BATTERY_REPLACEMENT = "battery_replacement";
    public static final String INVERTER_REPLACEMENT = "inverter_replacement";
    public static final String CONTROLLER_REPLACEMENT = "controller_replacement";
    public static final String TOTAL_REPLACEMENT_INITIAL_COST = "total_replacement_initial_cost";
    public static final String TOTAL_REPLACEMENT_LCCA_COST = "total_replacement_lcca_cost";
    public static final String LCC_SALVAGE_VALUE = "lcc_salvage_value";
    public static final String TOTAL_LCCA = "total_lcca";
    public static final String TOTAL_AMPERE_HR_LOAD = "total_ampere_hr_load";
    public static final String CORRECTED_AMPERE_LOAD = "corrected_ampere_hr_load";
    public static final String DESIGN_CURRENT = "design_current";
    Project project;

    @FXML
    void findTotalLCCAOfAllComponent(ActionEvent event) {
        if (lccSalvageValueTextField.getText().isEmpty()) {
            AlertsDialog.showErrorDialog("You need to find the lcc salvage value first");
            return;
        }

        totalLCCA = totalReplacementInitialCost + operationAndMaintenance + batteryReplacement + inverterReplacement +
                controllerReplacement + LCCSalvageValue;

        totalLCCAOfAllComponentTextField.setText(Double.toString(totalLCCA));

        saveToFile();

    }

    public void setProject(Project project) {
        this.project = project;

        readFromFile();
    }

    @FXML
    void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert confirmation = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to close the LCCA solar pv analysis window?");

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

    public void saveToFile() {

        JSONObject lccaPv = new JSONObject();

        lccaPv.put(WIRE_EFFICIENCY, wireEfficiency);
        lccaPv.put(BATTERY_EFFICIENCY, batteryEfficiency);
        lccaPv.put(POWER_CONVERSION_EFFICIENCY, powerConversionEfficiency);
        lccaPv.put(ESCALATION_RATE, escalationRate);
        lccaPv.put(DISCOUNT_RATE, discountRate);

        lccaPv.put(PEAK_SUN_VALUE, peakSunValue);

        lccaPv.put(TOTAL_LOAD, totalLoad);
        lccaPv.put(NORMINAL_SYSTEM_VOLTAGE, norminalSystemVoltage);

        lccaPv.put(MODULE_CAPACITY, moduleCapacity);
        lccaPv.put(BATTERY_NUMBER_CAPACITY, batteryNumberCapacity);
        lccaPv.put(CHARGE_CONTROLLER_CAPACITY, chargeControllerCapacity);
        lccaPv.put(INVERTER_NUMBER_CAPACITY, inverterNumberCapacity);

        lccaPv.put(CAPITAL_COST, capitalCost);
        lccaPv.put(OPERATION_AND_MAINTENANCE, operationAndMaintenance);
        lccaPv.put(BATTERY_REPLACEMENT, batteryReplacement);
        lccaPv.put(CONTROLLER_REPLACEMENT, controllerReplacement);
        lccaPv.put(INVERTER_REPLACEMENT, inverterReplacement);
        lccaPv.put(YEARS, years);

        lccaPv.put(TOTAL_REPLACEMENT_INITIAL_COST, totalReplacementInitialCost);
        lccaPv.put(TOTAL_REPLACEMENT_LCCA_COST, totalReplacementLCCACost);
        lccaPv.put(LCC_SALVAGE_VALUE, LCCSalvageValue);
        lccaPv.put(TOTAL_LCCA, totalLCCA);
        lccaPv.put(TOTAL_AMPERE_HR_LOAD, totalAmpereHrLoad);
        lccaPv.put(CORRECTED_AMPERE_LOAD, correctedAmpHrLoad);
        lccaPv.put(DESIGN_CURRENT, designCurrent);

        project.setLcca_pv(lccaPv);

    }

    private void readFromFile() {
        JSONObject lccaPv = project.getLcca_pv();

        try {

            wireEfficiencyTextField.setText(Double.toString((double) lccaPv.get(WIRE_EFFICIENCY)));
            batteryEfficiencyTextField.setText(Double.toString((double) lccaPv.get(BATTERY_EFFICIENCY)));
            powerEfficiencyTextField.setText(Double.toString((double) lccaPv.get(POWER_CONVERSION_EFFICIENCY)));
            escalationRateTextField.setText(Double.toString((double) lccaPv.get(ESCALATION_RATE)));
            discountRateTextField.setText(Double.toString((double) lccaPv.get(DISCOUNT_RATE)));

            peakSunValueTextField.setText(Double.toString((double) lccaPv.get(PEAK_SUN_VALUE)));

            totalLoadTextField.setText(Double.toString((double) lccaPv.get(TOTAL_LOAD)));
            norminalSystemVoltageTextField.setText(Double.toString((double) lccaPv.get(NORMINAL_SYSTEM_VOLTAGE)));

            moduleCapacityNumberTextField.setText(Double.toString((double) lccaPv.get(MODULE_CAPACITY)));
            batteryCapacityNumberTextField.setText(Double.toString((double) lccaPv.get(BATTERY_NUMBER_CAPACITY)));
            chargeControllerNumberTextField.setText(Double.toString((double) lccaPv.get(CHARGE_CONTROLLER_CAPACITY)));
            inverterCapacityNumberTextField.setText(Double.toString((double) lccaPv.get(INVERTER_NUMBER_CAPACITY)));

            capitalCostLCC.setText(Double.toString((double) lccaPv.get(CAPITAL_COST)));
            operationAndMaintenanceLCC.setText(Double.toString((double) lccaPv.get(OPERATION_AND_MAINTENANCE)));
            batteryReplacementLCCATextField.setText(Double.toString((double) lccaPv.get(BATTERY_REPLACEMENT)));
            controllerReplacementLCCATextField.setText(Double.toString((double) lccaPv.get(CONTROLLER_REPLACEMENT)));
            inverterReplacementCostInitialCostTextField.setText(Double.toString((double) lccaPv.get(INVERTER_REPLACEMENT)));


            //yearCombo

            totalReplacementCostInitialCostTextField.setText(Double.toString((double) lccaPv.get(TOTAL_REPLACEMENT_INITIAL_COST)));
            totalReplacementLCCTextField.setText(Double.toString((double) lccaPv.get(TOTAL_REPLACEMENT_LCCA_COST)));
            lccSalvageValueTextField.setText(Double.toString((double) lccaPv.get(LCC_SALVAGE_VALUE)));
            totalLCCAOfAllComponentTextField.setText(Double.toString((double) lccaPv.get(TOTAL_LCCA)));
            totalAmpHrLoadTextField.setText(Double.toString((double) lccaPv.get(TOTAL_AMPERE_HR_LOAD)));
            correctedAmpHrLoadTextField.setText(Double.toString((double) lccaPv.get(CORRECTED_AMPERE_LOAD)));
            designCurrentTextField.setText(Double.toString((double) lccaPv.get(DESIGN_CURRENT)));

        } catch (Exception e) {
            AlertsDialog.showErrorDialog("there was an error getting the saved data");
        }


    }

    class Formulas{
        public double calculateTotalLoadInKw(double totalAcLoad, double totalDcLoads){
            return totalAcLoad + totalDcLoads;
        }

        public double calculateTotalAmpereHrLoad(double totalLoadInKw, double totalDDC, double totalWDC,
                                                 double norminalSystemVolatage, double powerCoversionEfficieny){
            return (totalLoadInKw * totalDDC * totalWDC * 52) / (365 * norminalSystemVolatage * powerCoversionEfficieny);
        }

        public double correctedAmpereHrLoad(double totalAmpereHrLoad, double wireEfficiency, double batteryEfficiency){
            return (totalAmpereHrLoad) / (wireEfficiency * batteryEfficiency);
        }

        public double calculateDesignCurrent(double correctedAmpHrLoad, double peakSunValue){
            return correctedAmpHrLoad/peakSunValue;
        }

        public double calculateNorminalSystemVolatage(double load){
            if(load < 1500)
                return 12;
            if(load < 3000)
                return 24;
            if(load < 5000)
                return 48;

            return 120;
        }

        public double calculateLCCOfMaintenance(double initialCostOfMaintenance, double escalationRate, double discountRate,
                                                double yearsOfAnalysis){
            return initialCostOfMaintenance * ((1 + escalationRate) / (discountRate - escalationRate)) *
                    (1 - Math.pow(((1 + escalationRate)/(1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLCCOfBattery(double initialCostOfBattery, double escalationRate, double discountRate,
                                            double yearsOfAnalysis) {
            return initialCostOfBattery * ((1 + escalationRate) / (discountRate - escalationRate)) *
                    (1 - Math.pow(((1 + escalationRate) / (1 + discountRate)), yearsOfAnalysis));

        }

        public double calculateInverterReplacement(double initialInverReplacement, double escalationRate, double discountRate,
                                                   double yearsOfAnalysis) {
            return initialInverReplacement * ((1 + escalationRate) / (discountRate - escalationRate)) *
                    (1 - Math.pow(((1 + escalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLCCOfChargeController(double initialCostOfChargeController, double escalationRate, double discountRate,
                                                     double yearsOfAnalysis) {
            return initialCostOfChargeController * ((1 + escalationRate) / (discountRate - escalationRate)) *
                    (1 - Math.pow(((1 + escalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLCCOfSalvageValue(double capitalCost, double escalationRate, double discountRate,
                                                 double yearsOfAnalysis) {

            double initialSalvageCost = 0.2 * capitalCost;

            return initialSalvageCost * ((1 + escalationRate) / (discountRate - escalationRate)) *
                    (1 - Math.pow(((1 + escalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateTotalLCC(double initialCostOfPvSystem, double lCCOfMaintenance, double lCCOfBattery,
                                        double lCCInverterReplacement, double lCCChargeController, double lCCSalvageValue){

            return initialCostOfPvSystem + lCCOfMaintenance + lCCOfBattery +
                    lCCInverterReplacement + lCCChargeController + lCCSalvageValue;
        }

    }


}
