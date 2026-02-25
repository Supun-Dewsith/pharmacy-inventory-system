package controller.suplier;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.util.DnsSrv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import model.dto.SuplierDTO;
import model.tm.SuplierTM;
import service.ServiceFactory;
import service.custom.SuplierManagementService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SuplierManagementFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> cilStatus;

    @FXML
    private JFXComboBox<?> cmbSearchBy;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colLeadTime;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<SuplierTM> tblSuplier;

    @FXML
    private JFXTextField txtSearch;

    @Getter
    @Setter
    protected SuplierTM selectedRow;

    private final SuplierManagementService suplierManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPLIERMANAGEMENT);

    @FXML
    void btnAddNewSuplierOnAction(ActionEvent event) {
        setAddNewSuplierForm();
    }

    public void setAddNewSuplierForm(){
        gridPane.getChildren().removeIf(node -> {
            return GridPane.getColumnIndex(node)!=null && GridPane.getColumnIndex(node)==1;
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_new_suplier_form.fxml"));
        try {
            Parent screen = loader.load();
            AddNewSuplierFormController controller = loader.getController();
            controller.setSuplierManagementFormController(this);
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected void addNewSuplier(SuplierDTO suplierDTO){
        suplierManagementService.addNewSuplier(suplierDTO);
    }

    protected void updateSuplier(SuplierDTO updatedSuplierDTO){
        suplierManagementService.updateSuplier(updatedSuplierDTO);
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        gridPane.getChildren().removeIf(node->
            GridPane.getColumnIndex(node)!=null && GridPane.getColumnIndex(node)==1
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit_supplier_form.fxml"));
        try {
            Parent screen = loader.load();
            EditSupplierFormController controller = loader.getController();
            controller.setSuplierManagementFormController(this);
            controller.updateTxtFields();
            if (selectedRow==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid");
                alert.setHeaderText("Please select Supplier");
                alert.setContentText("Please select Supplier before pressing edit button!");
                alert.showAndWait();
                return;
            }
            controller.setCurrentSuplierId(getSelectedRow().getId());
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapSuplierTable();
        loadSuplierTable();

        tblSuplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                suplierDetailView(newValue);
                setSelectedRow(newValue);
            }
        });
    }

    private void suplierDetailView(SuplierTM suplierTM){
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == 1
        );

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/suplier_Info_form.fxml")));
        try {
            Parent screen = loader.load();
            SuplierInfoFormController controller = loader.getController();
            controller.setDeleveryDetailsTable(suplierTM.getOrders());
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSuplierTable(){
        List<SuplierDTO> suplierData = suplierManagementService.getSuplierData();
        ObservableList<SuplierTM> suplierTMS = FXCollections.observableArrayList();
        suplierData.forEach(suplierDTO -> {
            suplierTMS.add(new SuplierTM(
                    suplierDTO.getId(),
                    suplierDTO.getName(),
                    suplierDTO.getContactPerson(),
                    suplierDTO.getPhone(),
                    suplierDTO.getLeadTime(),
                    suplierDTO.getEmail(),
                    suplierDTO.getStatus(),
                    suplierDTO.getOrders()
            ));
        });

        tblSuplier.setItems(suplierTMS);

    }

    private void mapSuplierTable(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colLeadTime.setCellValueFactory(new PropertyValueFactory<>("leadTime"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cilStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

}
