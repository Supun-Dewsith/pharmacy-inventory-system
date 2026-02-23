package controller.medicine;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import model.dto.MedicineDTO;
import model.tm.MedicineTM;
import service.ServiceFactory;
import service.custom.MedicineManagementService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineManagementController implements Initializable {
    @FXML
    private JFXComboBox<?> cmbSearchBy;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<MedicineTM, String> colCode;

    @FXML
    private TableColumn<?, ?> colExpiryDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableView<MedicineTM> tblMedicine;

    @FXML
    private JFXTextField txtSearch;

    @Getter
    private MedicineTM selectedRow;

    @FXML
    void btnAddNewMedicineOnAction(ActionEvent event) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null  &&  GridPane.getColumnIndex(node) == 1
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_new_medicine_form.fxml"));
        try {
            Parent screen = loader.load();
            AddNewMedicineFormController controller = loader.getController();
            controller.setMedicineManagementController(this);
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        gridPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node)==1
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit_med_form.fxml"));
        try {
            Parent screen = loader.load();
            EditMedFormController controller = loader.getController();
            controller.setMedicineManagementController(this);
            controller.updateTxtFields();
            gridPane.add(screen,1,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private GridPane gridPane;

    private final MedicineManagementService medicineManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.MEDICINEMANAGEMENT);

    public boolean addNewMedicine(MedicineDTO medicineDTO){
        return medicineManagementService.addNewMedicine(medicineDTO);
    }

    public boolean updateMedicine(MedicineDTO medicineDTO){
        return medicineManagementService.updateMedicine(medicineDTO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapTable();
        loadTable();

        tblMedicine.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{
            if(newValue!=null){
                System.out.println(newValue.getMedCode());
                updateDetailView(newValue);
                updateSelectedRow(newValue);
            }
        } );
    }

    private void updateSelectedRow(MedicineTM medicineTM){
        selectedRow=medicineTM;
    }


    private void updateDetailView(MedicineTM newValue){

        gridPane.getChildren().removeIf(node ->
            GridPane.getColumnIndex(node) != null  &&  GridPane.getColumnIndex(node) == 1
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/medicine_Info_form.fxml"));
        try {
            Parent screen = loader.load();
            MedicineInfoFormController controller = loader.getController();
            controller.setMedData(newValue);
            gridPane.add(screen,1,0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadTable(){
        try {
            List<MedicineDTO> allMed = medicineManagementService.getAllMed();
            List< MedicineTM> medicineTMS = new ArrayList<>();

            allMed.forEach(medicineDTO -> {
                medicineTMS.add(new MedicineTM(
                        medicineDTO.getItemCode(),
                        medicineDTO.getMedName(),
                        medicineDTO.getBrand(),
                        medicineDTO.getBatchNumber(),
                        medicineDTO.getDescription(),
                        medicineDTO.getCategory(),
                        medicineDTO.getUnitPrice(),
                        medicineDTO.getBuyingPrice(),
                        medicineDTO.getStock(),
                        medicineDTO.getMinLevel(),
                        medicineDTO.getPackSize(),
                        medicineDTO.getExpiryDate(),
                        medicineDTO.getSupplierId()
                ));
            });

            tblMedicine.setItems(FXCollections.observableArrayList(medicineTMS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapTable(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

}
