package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.medicine.AddNewMedicineFormController;
import controller.medicine.MedicineInfoFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import service.ServiceFactory;
import service.custom.impl.CustomerManagementServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerManagementFormController {
    @FXML
    private JFXComboBox<?> cmbSearchBy;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCuatName;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private JFXTextField txtSearch;

    CustomerManagementServiceImpl customerManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {
        setAddNewCustomerForm();
    }

    public void setAddNewCustomerForm(){
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null  &&  GridPane.getColumnIndex(node) == 1
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_new_customer_form.fxml"));
        try {
            Parent screen = loader.load();
            AddNewCustomerFormController controller = loader.getController();
            controller.setCustomerManagementFormController(this);
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void addnewCustomer(CustomerSaveRequestDTO customerSaveRequestDTO){
        try {
            customerManagementService.addNewCustomer(customerSaveRequestDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO){
        try {
            customerManagementService.updateCustomer(customerUpdateRequestDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

}
