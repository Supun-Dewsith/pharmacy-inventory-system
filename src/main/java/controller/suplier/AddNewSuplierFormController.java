package controller.suplier;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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



}
