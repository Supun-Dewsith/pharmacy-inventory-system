package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lombok.Setter;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import model.tm.CustomerTM;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EditCustomerFormController implements Initializable {
    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @Setter
    private CustomerManagementFormController customerManagementFormController;

    @FXML
    void btnEditCustomerOnAction(ActionEvent event) {
        if(!isInputValid()){
            return;
        }
        getCustomerInfo();
    }

    private void getCustomerInfo() {
        customerManagementFormController.updateCustomer(new CustomerUpdateRequestDTO(
                null,
                cmbTitle.getValue().toString(),
                txtCustomerName.getText().trim(),
                dateDOB.getValue(),
                txtAddress.getText().trim(),
                txtPhone.getText().trim(),
                txtEmail.getText().trim()
        ));
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (cmbTitle.getSelectionModel().isEmpty()) {
            errorMessage += "No valid title selected.\n";
        }
        if (txtCustomerName.getText() == null || txtCustomerName.getText().trim().isEmpty()) {
            errorMessage += "No valid customer name.\n";
        }
        if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty()) {
            errorMessage += "No valid address.\n";
        }
        if (dateDOB.getValue() == null) {
            errorMessage += "No valid Date of Birth selected.\n";
        }

        if (!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage += "No valid email address.\n";
        }

        if (!txtPhone.getText().matches("\\d{10}")) {
            errorMessage += "No valid phone number (10 digits required).\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return false;
        }
    }


    void updateTxtFields(){
        CustomerTM selectedRow = customerManagementFormController.getSelectedRow();
        if(selectedRow != null){
            txtCustomerName.setText(selectedRow.getName());
            txtAddress.setText(selectedRow.getAddress());
            txtEmail.setText(selectedRow.getEmail());
            txtPhone.setText(selectedRow.getPhone());
            dateDOB.setValue(selectedRow.getDob());
            cmbTitle.setValue(selectedRow.getTitle());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbTitle.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Mr","Ms","Miss")));
    }
}
