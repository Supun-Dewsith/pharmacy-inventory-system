package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import lombok.Setter;
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
    void btnAddCustomerOnAction(ActionEvent event) {

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
