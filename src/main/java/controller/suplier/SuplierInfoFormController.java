package controller.suplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AccessLevel;
import lombok.Setter;
import model.dto.LotDTO;
import model.dto.SuplierOrderDTO;
import model.tm.DeleveryDetailsTM;
import model.tm.FinancialTM;
import model.tm.MedAndQualityTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SuplierInfoFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> colExpiryDates;

    @FXML
    private TableColumn<?, ?> colFBatchLotNum;

    @FXML
    private TableColumn<?, ?> colMBatchLotNum;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQualityInspection;

    @FXML
    private TableColumn<?, ?> colReceivedDate;

    @FXML
    private TableColumn<?, ?> colTotalCostIncrease;

    @FXML
    private TableColumn<?, ?> colUnitCost;

    @FXML
    private Label lblSupplierName;

    @FXML
    private TableView<DeleveryDetailsTM> tblDelleveryDetails;

    @FXML
    private TableView<FinancialTM> tblFinancialHistory;

    @FXML
    private TableView<MedAndQualityTM> tblMedAndQuality;

    void setLblSupplierName(String name){
        lblSupplierName.setText(name);
    }

    public void setDeleveryDetailsTable(List<SuplierOrderDTO> orderDTOS){
        ObservableList<DeleveryDetailsTM> deleveryDetailsTMS = FXCollections.observableArrayList();
        orderDTOS.forEach(orderDTO -> {
            deleveryDetailsTMS.add(new DeleveryDetailsTM(
                    orderDTO.getOrderID(),
                    orderDTO.getOrderDate(),
                    orderDTO.getReceivedDate(),
                    orderDTO.getLots()
            ));
        });
        tblDelleveryDetails.setItems(deleveryDetailsTMS);
    }

    private void loadMedAndQuantityTable(List<LotDTO> lotDTOS){
        ObservableList<MedAndQualityTM> medAndQualityTMS = FXCollections.observableArrayList();
        lotDTOS.forEach(lotDTO -> {
            medAndQualityTMS.add(new MedAndQualityTM(
                    lotDTO.getLotNumber(),
                    lotDTO.getExpiryDate(),
                    lotDTO.getQualityInspectionResults()
            ));
        });
        tblMedAndQuality.setItems(medAndQualityTMS);

    }

    private void loadFinancialHistoryTable(List<LotDTO> lotDTOS){
        ObservableList<FinancialTM> financialTMS = FXCollections.observableArrayList();
        lotDTOS.forEach(lotDTO -> {
            financialTMS.add(new FinancialTM(
                    lotDTO.getLotNumber(),
                    lotDTO.getUnitCost(),
                    0.0
            ));
        });
        tblFinancialHistory.setItems(financialTMS);
    }

    private void mapDeleveryDetailsTable(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colReceivedDate.setCellValueFactory(new PropertyValueFactory<>("receivedDate"));
    }

    private void mapMedAndQuantityTable(){
        colMBatchLotNum.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));
        colExpiryDates.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colQualityInspection.setCellValueFactory(new PropertyValueFactory<>("qualityInspectionStatus"));
    }

    private void mapFinancialHistoryTable(){
        colFBatchLotNum.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colUnitCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colTotalCostIncrease.setCellValueFactory(new PropertyValueFactory<>("totalCostIncrease"));
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapDeleveryDetailsTable();
        mapMedAndQuantityTable();
        mapFinancialHistoryTable();

        tblDelleveryDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                loadMedAndQuantityTable(newValue.getLotDTOS());
                loadFinancialHistoryTable(newValue.getLotDTOS());
            }
        });
    }
}
