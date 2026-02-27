package controller.customer;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.dto.BuyerOrderDTO;
import model.dto.BuyerOrderItemDTO;
import model.tm.BuyerOrderDetailsTM;
import model.tm.BuyerOrderItemTM;
import model.tm.CustomerTM;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CustomerInfoFormController implements Initializable{
    @FXML
    private TableColumn<?, ?> colMedCode;

    @FXML
    private TableColumn<?, ?> colMedName;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotalProce;

    @FXML
    private Label lblMedName;

    @FXML
    private TableView<BuyerOrderDetailsTM> tblOrderDetails;

    @FXML
    private TableView<BuyerOrderItemTM> tblMedAndQuality;

    public void setCustomerData(CustomerTM selectedCustomer) {
        if (selectedCustomer == null) return;
        lblMedName.setText(selectedCustomer.getName());

        ObservableList<BuyerOrderDetailsTM> orderList = FXCollections.observableArrayList();
        ObservableList<BuyerOrderItemTM> medicineList = FXCollections.observableArrayList();

        for (BuyerOrderDTO order : selectedCustomer.getOrders()) {
            orderList.add(new BuyerOrderDetailsTM(
                    order.getCode(),
                    order.getDate(),
                    order.getTotalPrice()
            ));

            for (BuyerOrderItemDTO item : order.getCart()) {
                medicineList.add(new BuyerOrderItemTM(
                        item.getMedCode(),
                        //item.getMedName(),
                        null,
                        item.getQty()
                ));
            }
        }
        tblOrderDetails.setItems(orderList);
        tblMedAndQuality.setItems(medicineList);
    }

    private void mapOrderDetailsTable(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalProce.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void mapMedAndQualityTable(){
        colMedCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colMedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapOrderDetailsTable();
        mapMedAndQualityTable();
    }
}
