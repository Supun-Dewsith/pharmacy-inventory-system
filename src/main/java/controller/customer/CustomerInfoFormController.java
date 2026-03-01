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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.dto.BuyerOrderDTO;
import model.dto.BuyerOrderItemDTO;
import model.dto.CustomerDTO;
import model.tm.BuyerOrderDetailsTM;
import model.tm.BuyerOrderItemTM;
import model.tm.CustomerTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerInfoFormController implements Initializable{
    @FXML
    private TableColumn<?, ?> colMedCode;

    @FXML
    private TableColumn<?, ?> colMedName;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderCode;

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

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private CustomerTM selectedCustomer;

    public void setCustomerData(CustomerTM selectedCustomer) {
        if (selectedCustomer == null) return;
        setSelectedCustomer(selectedCustomer);

        lblMedName.setText(selectedCustomer.getName());

        ObservableList<BuyerOrderDetailsTM> orderList = FXCollections.observableArrayList();

        for (BuyerOrderDTO order : selectedCustomer.getOrders()) {
            orderList.add(new BuyerOrderDetailsTM(
                    order.getId(),
                    order.getCode(),
                    order.getDate(),
                    order.getTotalPrice()
            ));
        }
        tblOrderDetails.setItems(orderList);
    }

    private List<BuyerOrderItemTM> getOrderItemTMs(Long orderId){
        BuyerOrderDTO buyerOrderDTO = getSelectedCustomer().getOrders().stream()
                .filter(order -> order.getId()==orderId)
                .findFirst()
                .orElse(null);
        ArrayList<BuyerOrderItemTM> buyerOrderItemTMSTMS = new ArrayList<>();
        buyerOrderDTO.getCart().forEach(buyerOrderItemDTO -> {
            buyerOrderItemTMSTMS.add(new BuyerOrderItemTM(
                    buyerOrderItemDTO.getMedCode(),
                    null,
                    buyerOrderItemDTO.getQty()
            ));
        });
        return buyerOrderItemTMSTMS;
    }

    private void loadMedAndQualityTable(Long orderId){
        tblMedAndQuality.setItems(FXCollections.observableArrayList(getOrderItemTMs(orderId)));
    }

    private void mapOrderDetailsTable(){
        colOrderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
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
        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                loadMedAndQualityTable(newValue.getOrderId());
            }
        });
    }
}
