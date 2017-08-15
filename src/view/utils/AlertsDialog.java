package view.utils;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertsDialog {
    public static void showErrorDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

}
