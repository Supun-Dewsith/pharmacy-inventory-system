package controller.suplier;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.Setter;
import model.dto.SuplierDTO;
import model.tm.SuplierTM;

public class EditSupplierFormController {
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

    @Setter
    private SuplierManagementFormController suplierManagementFormController;

    @FXML
    void btnAddSuplierOnAction(ActionEvent event) {

    }

    public void updateTxtFields(){
        SuplierTM suplierTM =  suplierManagementFormController.getSelectedRow();
        if(suplierTM!=null){
            txtContactPerson.setText(suplierTM.getContactPerson());
            txtEmail.setText(suplierTM.getEmail());
            txtPhone.setText(suplierTM.getPhone());
            txtStatus.setText(suplierTM.getStatus());
            txtSupplierName.setText(suplierTM.getName());
        }else {
            System.out.println("No medicine selected to edit.");
        }
    }
}
