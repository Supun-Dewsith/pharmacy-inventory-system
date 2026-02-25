package controller.suplier;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lombok.Setter;
import model.dto.OrderDTO;
import model.dto.SuplierDTO;

import java.util.ArrayList;

public class AddNewSuplierFormController {

    @FXML
    private JFXTextField txtContactPerson;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    void btnAddSuplierOnAction(ActionEvent event) {
        if(!isInputValid()){
            return;
        }
        getSuplierInfo();
    }

    @Setter
    private SuplierManagementFormController suplierManagementFormController;

    private void getSuplierInfo(){
        suplierManagementFormController.addNewSuplier(new SuplierDTO(
                (long)1.0,
                txtSupplierName.getText(),
                txtContactPerson.getText(),
                txtPhone.getText(),
                1,
                txtEmail.getText(),
                txtStatus.getText(),
                new ArrayList<OrderDTO>()
        ));
    }


    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        if (txtSupplierName.getText() == null || txtSupplierName.getText().trim().isEmpty()) {
            errorMessage.append("Supplier Name is required!\n");
        }
        if (txtContactPerson.getText() == null || txtContactPerson.getText().trim().isEmpty()) {
            errorMessage.append("Contact Person is required!\n");
        }
        if (txtStatus.getText() == null || txtStatus.getText().trim().isEmpty()) {
            errorMessage.append("Status is required!\n");
        }

        String phoneText = txtPhone.getText();
        if (phoneText == null || phoneText.trim().isEmpty()) {
            errorMessage.append("Phone number is required!\n");
        } else if (!phoneText.matches("\\d{10}")) {
            errorMessage.append("Phone number must be exactly 10 digits!\n");
        }

        String emailText = txtEmail.getText();
        if (emailText == null || emailText.trim().isEmpty()) {
            errorMessage.append("Email is required!\n");
        } else if (!emailText.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage.append("Please enter a valid email address!\n");
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }



}
