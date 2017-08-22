package main.view.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.model.Project;


public class DecisionController {
    double bcrPv = -1;
    double bcrDiesel = -1;
    double ibcr = -1;
    double lccaDiesel = -1;
    double lccaPv = -1;
    @FXML
    private JFXTextArea theMostCostEffectiveOptionTextField;

    @FXML
    private JFXTextArea theMostBeneficialOptionTextField;


    private Project project;

    @FXML
    void findTheMostBeneficialOption(ActionEvent event) {
        bcrPv = (double) project.getBcr_pv().get(BcrPvController.TOTAL_BCR);
        bcrDiesel = (double) project.getBcr_diesel().get(BcrDieselController.TOTAL_BCR);
        ibcr = (double) project.getIbcr().get(IbcrFormController.IBCR_VALUE);

        if (bcrPv > bcrDiesel) {
            theMostBeneficialOptionTextField.setText("PV system is more beneficial than Diesel system " +
                    "by a ration 2:1 and a factor of " + Double.toString(ibcr));
        } else if (bcrPv < bcrDiesel) {
            theMostBeneficialOptionTextField.setText("Diesel generator system is more beneficial than PV system " +
                    "by a ration 2:1 and a factor of " + Double.toString(ibcr));
        } else {
            theMostBeneficialOptionTextField.setText("Both systems are the same");
        }

    }

    @FXML
    void findTheMostCostEffectiveOption(ActionEvent event) {
        lccaDiesel = (double) project.getLcca_diesel().get(LccaDieselController.TOTAL_LCCA);
        lccaPv = (double) project.getLcca_pv().get(LccaPvController.TOTAL_LCCA);

        if (lccaPv < lccaDiesel) {
            theMostCostEffectiveOptionTextField.setText("Pv system is more cost effective having " +
                    Double.toString(lccaPv) + " as final cost while Diesel has " + Double.toString(lccaDiesel));
        } else if (lccaPv > lccaDiesel) {
            theMostCostEffectiveOptionTextField.setText("Diesel system is more cost effective having " +
                    Double.toString(lccaDiesel) + " as final cost while Pv has " + Double.toString(lccaPv));
        } else {
            theMostCostEffectiveOptionTextField.setText("The two systems are the same, both has a value of" +
                    Double.toString(lccaPv));
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }


    public void setProject(Project project) {
        this.project = project;
    }
}
