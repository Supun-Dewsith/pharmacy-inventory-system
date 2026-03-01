package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.medicine.AddNewMedicineFormController;
import controller.medicine.MedicineInfoFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.dto.CustomerDTO;
import model.dto.CustomerSaveRequestDTO;
import model.dto.CustomerUpdateRequestDTO;
import model.tm.CustomerTM;
import model.tm.MedicineTM;
import service.ServiceFactory;
import service.custom.impl.CustomerManagementServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class CustomerManagementFormController implements Initializable {
    @FXML
    private JFXComboBox<?> cmbSearchBy;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCuatName;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private JFXTextField txtSearch;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PRIVATE)
    private CustomerTM selectedRow;

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
            boolean isAdded =  customerManagementService.addNewCustomer(customerSaveRequestDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully!").show();
                loadCustomerTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    protected void updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO){
        customerUpdateRequestDTO.setId(selectedRow.getId());
        try {
            boolean isUpdated = customerManagementService.updateCustomer(customerUpdateRequestDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully!").show();
                loadCustomerTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerTM selectedCustomer = getSelectedRow();

        if (selectedCustomer == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Customer to delete!").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Customer?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = customerManagementService.deleteCustomer(selectedCustomer.getId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully!").show();
                    loadCustomerTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Delete failed! Customer might not exist.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null  &&  GridPane.getColumnIndex(node) == 1
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit_customer_form.fxml"));
        try {
            Parent screen = loader.load();
            EditCustomerFormController controller = loader.getController();
            controller.setCustomerManagementFormController(this);
            controller.updateTxtFields();
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapCustomerTable();
        loadCustomerTable();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                setSelectedRow(newValue);
                updateDetailView(newValue);
            }
        });
    }

    private void updateDetailView(CustomerTM newValue){

        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null  &&  GridPane.getColumnIndex(node) == 1
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_Info_form.fxml"));
        try {
            Parent screen = loader.load();
            CustomerInfoFormController controller = loader.getController();
            controller.setCustomerData(newValue);
            gridPane.add(screen,1,0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerTable(){
        try {
            List<CustomerDTO> customerDTOS = customerManagementService.getCustomerData();
            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
            customerDTOS.forEach(customerDTO -> {
                customerTMS.add(new CustomerTM(
                        customerDTO.getId(),
                        customerDTO.getTitle(),
                        customerDTO.getName(),
                        customerDTO.getDob(),
                        customerDTO.getAddress(),
                        customerDTO.getPhone(),
                        customerDTO.getEmail(),
                        customerDTO.getOrders()
                ));
            });
            tblCustomer.setItems(customerTMS);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    private void mapCustomerTable(){
        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCuatName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
