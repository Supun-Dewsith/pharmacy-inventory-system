package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import lombok.Setter;
import model.tm.CustomerTM;

public class EditCustomerFormController {
    @FXML
    private JFXComboBox<String> cmbTitle;

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
    void btnAddCustomerOnAction(ActionEvent event) {

    }

    private void updateTxtFields(){
        CustomerTM selectedRow = customerManagementFormController.getSelectedRow();
        if(selectedRow != null){
            txtCustomerName.setText(selectedRow.getName());
            txtAddress.setText(selectedRow.getAddress());
            txtEmail.setText(selectedRow.getEmail());
            txtPhone.setText(selectedRow.getPhone());
            dateDOB.setValue(selectedRow.getDob());
            ((JFXComboBox<String>) cmbTitle).setValue(selectedRow.getTitle());
        }
    }

}
