package controller.medicine;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lombok.Setter;
import model.dto.MedicineDTO;
import model.tm.MedicineTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditMedFormController {
    @FXML
    private DatePicker dateExpiryDate;

    @FXML
    private JFXTextField txtBatchNumber;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtBuyingPrice;

    @FXML
    private JFXTextField txtCategury;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtMedCode;

    @FXML
    private JFXTextField txtMedName;

    @FXML
    private JFXTextField txtMinLevel;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtSuplierId;

    @FXML
    private JFXTextField txtUnitPrice;

    @Setter
    private MedicineManagementController medicineManagementController;

    public void btnEditMedicineOnAction(ActionEvent actionEvent) {
        if(!isInputValid()){
            return;
        }
        medicineManagementController.updateMedicine(getMedData());
    }

    public void updateTxtFields() {
        MedicineTM selectedRow = medicineManagementController.getSelectedRow();

        if (selectedRow != null) {
            txtMedCode.setText(selectedRow.getMedCode());
            txtMedName.setText(selectedRow.getMedName());
            txtBrand.setText(selectedRow.getBrand());
            txtBatchNumber.setText(selectedRow.getBatchNumber());
            txtDescription.setText(selectedRow.getDescription());
            txtCategury.setText(selectedRow.getCategory());
            txtPackSize.setText(selectedRow.getPackSize());
            txtSuplierId.setText(selectedRow.getSupplierId());

            txtUnitPrice.setText(String.valueOf(selectedRow.getUnitPrice()));
            txtBuyingPrice.setText(String.valueOf(selectedRow.getBuyingPrice()));
            txtStock.setText(String.valueOf(selectedRow.getStock()));
            txtMinLevel.setText(String.valueOf(selectedRow.getMinLevel()));

            dateExpiryDate.setValue(selectedRow.getExpiryDate());
        } else {
            System.out.println("No medicine selected to edit.");
        }
    }

    public MedicineDTO getMedData() {
        return new MedicineDTO(
                txtMedCode.getText(),
                txtMedName.getText(),
                txtBrand.getText(),
                txtBatchNumber.getText(),
                txtDescription.getText(),
                txtCategury.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtBuyingPrice.getText()),
                Integer.parseInt(txtStock.getText()),
                Integer.parseInt(txtMinLevel.getText()),
                txtPackSize.getText(),
                LocalDate.parse(dateExpiryDate.getValue().toString()),
                txtSuplierId.getText()
        );
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (txtMedCode.getText().isEmpty()) errorMessage += "Medicine Code is required!\n";
        if (txtMedName.getText().isEmpty()) errorMessage += "Medicine Name is required!\n";
        if (txtBrand.getText().isEmpty()) errorMessage += "Brand is required!\n";
        if (txtCategury.getText().isEmpty()) errorMessage += "Category is required!\n";
        if (txtBatchNumber.getText().isEmpty()) errorMessage += "Batch Number is required!\n";
        if (txtSuplierId.getText().isEmpty()) errorMessage += "Supplier ID is required!\n";
        if (txtDescription.getText().isEmpty()) errorMessage += "Description is required!\n";
        if (txtPackSize.getText().isEmpty()) errorMessage += "Pack Size is required!\n";

        if (dateExpiryDate.getValue() == null) {
            errorMessage += "Expiry Date is required!\n";
        } else if (dateExpiryDate.getValue().isBefore(LocalDate.now())) {
            errorMessage += "Expiry Date cannot be in the past!\n";
        }

        errorMessage += validateDouble(txtUnitPrice.getText(), "Unit Price");
        errorMessage += validateDouble(txtBuyingPrice.getText(), "Buying Price");

        errorMessage += validateInt(txtStock.getText(), "Stock");
        errorMessage += validateInt(txtMinLevel.getText(), "Minimum Level");

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return false;
        }
    }

    private String validateDouble(String input, String fieldName) {
        try {
            if (input.isEmpty()) return fieldName + " is required!\n";
            Double.parseDouble(input);
            return "";
        } catch (NumberFormatException e) {
            return "Invalid " + fieldName + " (must be a decimal number)!\n";
        }
    }

    private String validateInt(String input, String fieldName) {
        try {
            if (input.isEmpty()) return fieldName + " is required!\n";
            Integer.parseInt(input);
            return "";
        } catch (NumberFormatException e) {
            return "Invalid " + fieldName + " (must be a whole number)!\n";
        }
    }

}
