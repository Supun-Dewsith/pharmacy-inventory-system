package controller.medicine;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lombok.Setter;
import model.dto.MedicineDTO;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddNewMedicineFormController {

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
    private DatePicker dateExpiryDate;

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



    public MedicineDTO getMedData(){
        return new MedicineDTO(
                null,
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

    public void btnAddMedicineOnAction(ActionEvent actionEvent) throws SQLException {
        if(!isInputValid()){
            return;
        }
        medicineManagementController.addNewMedicine(getMedData());

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
