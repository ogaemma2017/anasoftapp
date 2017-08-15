package view.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.utils.AlertsDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class LccaDieselController implements Initializable{

    @FXML
    private JFXComboBox<Integer> yearsCombo;

    @FXML
    private JFXTextField generalEscalationRateTextField;

    @FXML
    private JFXTextField discountRateTextField;

    @FXML
    private JFXTextField powerFactorTextField;

    @FXML
    private JFXTextField fuelEscalationRateTextField;

    @FXML
    private JFXTextField maxLoadTextField;

    @FXML
    private JFXTextField generatorCapacityTextField;

    @FXML
    private JFXTextField fuelConsumptionTextField;

    @FXML
    private JFXTextField annualFuelCostTextField;

    @FXML
    private JFXTextField lifeFuelCostTextField;

    @FXML
    private JFXTextField annualServiceCostTextField;

    @FXML
    private JFXTextField LifeServiceCostTextField;

    @FXML
    private JFXTextField annualGeneratorReplacementCostTextField;

    @FXML
    private JFXTextField lifeGeneratorReplacementCostTextField;

    @FXML
    private JFXTextField annualTopCyclinderCostTextField;

    @FXML
    private JFXTextField lifeCylinderReplacementCostTextField;

    @FXML
    private JFXTextField annualEngineReplacementCostTextField;

    @FXML
    private JFXTextField lifeEngineReplacementCostTextField;

    @FXML
    private JFXTextField capitalCostTextField;

    @FXML
    private JFXTextField totalLCCAAnalysisOfAllComponentTextField;

    @FXML
    private JFXTextField totalLifeReplacementCostTextField;

    @FXML
    private JFXTextField salvageValueTextField;

    @FXML
    private JFXTextField lifeSalvageValueTextField;



    private double generalEscalationRate = 16.7;
    private double fuelEscalationRate = 0.2;
    private double discountRate = 16.9;
    private double powerFactor = 0.8;
    private int year = 1;


    private double maxLoad = 0;
    private double generatorCapacity = 0;
    private double fuelConsumptionPerHr = 0;
    private double annualFuelCost = 0;

    Formulas formulas = null;

    private double lifeFuelCost = 0;
    private double annualServiceCost = 0;
    private double lifeServiceCost = 0;
    private double annualGeneratorReplacementCost = 0;
    private double lifeGeneratorServiceCost = 0;
    private double annualTopCylinderReplacementCost = 0;
    private double lifeTopCylinderCost = 0;
    private double annualEngineReplacementCost = 0;
    private double lifeEngineReplacementCost = 0;
    private double totalLifeReplacementCost = 0;
    private double capitalCost = 0;
    private double salvageValue = 0;
    private double lifeSalvageValue = 0;
    private double totalLCCA = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        formulas = new Formulas();

        //Initialize combo boxes
        ObservableList<Integer> years = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 ,35, 36, 37, 38, 39, 40);
        yearsCombo.setItems(years);
        yearsCombo.getSelectionModel().selectFirst();

        generalEscalationRateTextField.setText(Double.toString(generalEscalationRate));
        fuelEscalationRateTextField.setText(Double.toString(fuelEscalationRate));
        discountRateTextField.setText(Double.toString(discountRate));
        powerFactorTextField.setText(Double.toString(powerFactor));
    }

    @FXML
    void selectYears(ActionEvent event){
        year = yearsCombo.getSelectionModel().getSelectedItem();
    }


    @FXML
    void findGeneratorCapacityFuelConsumption(ActionEvent event) {
        if(maxLoadTextField.getText().isEmpty() || powerFactorTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Please enter the generator capacity and fuel capacity");
            return;
        }

        try{
            maxLoad = Double.parseDouble(maxLoadTextField.getText().toString());
            generatorCapacity = maxLoad/powerFactor;

            //todo make this dynamic
            fuelConsumptionPerHr = 4.2;
            annualFuelCost = fuelConsumptionPerHr * 24 * 365 * 200;

            generatorCapacityTextField.setText(Double.toString(generatorCapacity));
            fuelConsumptionTextField.setText(Double.toString(fuelConsumptionPerHr));
            annualFuelCostTextField.setText(Double.toString(annualFuelCost));


        }catch (Exception e){
            AlertsDialog.showErrorDialog("invalid max load");
        }

    }

    @FXML
    void findLifeCylinderReplacementCost(ActionEvent event) {
        if(annualTopCyclinderCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter the annual generator replacement cost");
            return;
        }

        try{
            annualTopCylinderReplacementCost = Double.parseDouble(annualTopCyclinderCostTextField.getText());
            lifeTopCylinderCost = formulas.calculateReplacementCostOfGenerator(annualTopCylinderReplacementCost, generalEscalationRate, discountRate, year);
            lifeCylinderReplacementCostTextField.setText(Double.toString(lifeTopCylinderCost));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Please enter a valid annual generator replacement cost");
        }
    }

    @FXML
    void findLifeEngineReplacementCost(ActionEvent event) {

        if(annualEngineReplacementCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter the annual generator replacement cost");
            return;
        }

        try{
            annualEngineReplacementCost = Double.parseDouble(annualEngineReplacementCostTextField.getText());
            lifeEngineReplacementCost = formulas.calculateReplacementCostOfGenerator(annualEngineReplacementCost, generalEscalationRate, discountRate, year);
            lifeEngineReplacementCostTextField.setText(Double.toString(lifeEngineReplacementCost));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Please enter a valid annual generator replacement cost");
        }
    }

    @FXML
    void findLifeFuelCost(ActionEvent event) {
        if(annualFuelCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the generator capacity first");
            return;
        }

        lifeFuelCost = formulas.calculateLifeFuelCost(annualFuelCost, fuelEscalationRate, discountRate, year);
        lifeFuelCostTextField.setText(Double.toString(lifeFuelCost));


    }

    @FXML
    void findLifeGeneratorReplacementCost(ActionEvent event) {
        if(annualGeneratorReplacementCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter the annual generator replacement cost");
            return;
        }

        try{
            annualGeneratorReplacementCost = Double.parseDouble(annualGeneratorReplacementCostTextField.getText());
            lifeGeneratorServiceCost = formulas.calculateReplacementCostOfGenerator(annualGeneratorReplacementCost, generalEscalationRate, discountRate, year);
            lifeGeneratorReplacementCostTextField.setText(Double.toString(lifeGeneratorServiceCost));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Please enter a valid annual generator replacement cost");
        }

    }

    @FXML
    void findLifeSalvageValue(ActionEvent event) {
        if(salvageValueTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the salvage value first");
            return;
        }

        lifeSalvageValue = formulas.calculateAnnualSalvageValue(salvageValue, generalEscalationRate, discountRate,year);
        lifeSalvageValueTextField.setText(Double.toString(lifeSalvageValue));
    }

    @FXML
    void findLifeServiceCost(ActionEvent event) {
        if(annualServiceCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter the annual service cost");
            return;
        }

        try{
            annualServiceCost = Double.parseDouble(annualServiceCostTextField.getText());
            lifeServiceCost = formulas.calculateLifeServiceCost(annualServiceCost, generalEscalationRate, discountRate, year);
            LifeServiceCostTextField.setText(Double.toString(lifeServiceCost));
        }catch (Exception e){
            AlertsDialog.showErrorDialog("Please enter a valid annual service cost");
        }

    }

    @FXML
    void findSalvageValue(ActionEvent event) {
        if(capitalCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Enter the capital cost first");
            return;
        }

        try {
            capitalCost = Double.parseDouble(capitalCostTextField.getText());
            salvageValue = capitalCost * 0.2;

            salvageValueTextField.setText(Double.toString(salvageValue));

        }catch (Exception e){
            AlertsDialog.showErrorDialog("Invalid capital cost");
        }

    }

    @FXML
    void findTotalLCCAAnalysisOfAllComponent(ActionEvent event) {
        if(lifeSalvageValueTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find teh life salvage value first");
            return;
        }

        totalLCCA = capitalCost + lifeFuelCost + lifeServiceCost + totalLifeReplacementCost + lifeSalvageValue;
        totalLCCAAnalysisOfAllComponentTextField.setText(Double.toString(totalLCCA));

    }

    @FXML
    void findTotalReplacementCost(ActionEvent event) {
        if(lifeEngineReplacementCostTextField.getText().isEmpty()){
            AlertsDialog.showErrorDialog("Find the life engine replacement cost first");
            return;
        }

        totalLifeReplacementCost = lifeEngineReplacementCost + lifeTopCylinderCost + lifeEngineReplacementCost;
        totalLifeReplacementCostTextField.setText(Double.toString(totalLifeReplacementCost));
    }

    private class Formulas {
        public double calculateGeneratorCapacity(double maxLoad, double powerFactor) {
            return maxLoad / powerFactor;
        }

        public double calculateAnnualFuelCost(double fuelConsumptionPerLiter) {
            return fuelConsumptionPerLiter * 24 * 365 * 200;
        }

        public double calculateLifeFuelCost(double annualFuelCost, double fuelEscalationRate,
                                            double discountRate, double yearsOfAnalysis) {

            return annualFuelCost * ((1 + fuelEscalationRate) / (discountRate - fuelEscalationRate)) *
                    (1 - Math.pow(((1 + fuelEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLifeServiceCost(double anualServiceCost, double generalEscalationRate,
                                               double discountRate, double yearsOfAnalysis) {

            return anualServiceCost * ((1 + generalEscalationRate) / (discountRate - generalEscalationRate)) *
                    (1 - Math.pow(((1 + generalEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateReplacementCostOfGenerator(double annualReplacementOfGenerator, double generalEscalationRate,
                                                         double discountRate, double yearsOfAnalysis) {

            return annualReplacementOfGenerator * ((1 + generalEscalationRate) / (discountRate - generalEscalationRate)) *
                    (1 - Math.pow(((1 + generalEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLifeReplacementCostOfTopCylinder(double annualReplacementOfTopCylinder, double generalEscalationRate,
                                                                double discountRate, double yearsOfAnalysis) {

            return annualReplacementOfTopCylinder * ((1 + generalEscalationRate) / (discountRate - generalEscalationRate)) *
                    (1 - Math.pow(((1 + generalEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateLifeReplacementCostOfEngine(double annualReplacementCostOfEngine, double generalEscalationRate,
                                                           double discountRate, double yearsOfAnalysis) {

            return annualReplacementCostOfEngine * ((1 + generalEscalationRate) / (discountRate - generalEscalationRate)) *
                    (1 - Math.pow(((1 + generalEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateAnnualSalvageValue(double annualSalvageValue, double generalEscalationRate,
                                                  double discountRate, double yearsOfAnalysis) {

            return annualSalvageValue * ((1 + generalEscalationRate) / (discountRate - generalEscalationRate)) *
                    (1 - Math.pow(((1 + generalEscalationRate) / (1 + discountRate)), yearsOfAnalysis));
        }

        public double calculateTotalLifeReplacementCost(double lifeReplacementCostGenerator, double lifeReplacementCostTopCylinder,
                                                        double lifeReplacementCostEngine){
            return lifeReplacementCostGenerator + lifeReplacementCostTopCylinder + lifeReplacementCostEngine;
        }

        public double calculateTotalLCCAOfComponent(double capitalCost, double lifeFuelCost, double lifeServiceCost,
                                                    double lifeTotalReplacementCost, double lifeSalvageValue){
            return capitalCost + lifeFuelCost + lifeServiceCost + lifeTotalReplacementCost + lifeSalvageValue;
        }


    }
}
