package controller;

import controller.medicine.MedicineManagementController;
import controller.suplier.SuplierManagementFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ScreenSelectorDashboardController {

    @FXML
    private BorderPane borderPane;

    public void btnDashboardFormOnAction(ActionEvent actionEvent) {

        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/view/main_dashboard_form.fxml"));
            borderPane.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnMedicineManagementOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/medicine_management_form.fxml"));
            Parent screen = loader.load();
            MedicineManagementController controller = loader.getController();
            controller.setAddNewMedForm();
            borderPane.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBillingOnAction(ActionEvent actionEvent) {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/view/billing_form.fxml"));
            borderPane.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnExpiryAndStockAlertsOnAction(ActionEvent actionEvent) {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/view/expiry_and_stock_alerts_form.fxml"));
            borderPane.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSuplierManagementOnAction(ActionEvent actionEvent) {
        try {
            //Parent screen = FXMLLoader.load(getClass().getResource("/view/suplier_management_form.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/suplier_management_form.fxml"));
            Parent screen = loader.load();
            SuplierManagementFormController controller = loader.getController();
            controller.setAddNewSuplierForm();
            borderPane.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReportsAndAnalyticsOnAction(ActionEvent actionEvent) {
    }
}
