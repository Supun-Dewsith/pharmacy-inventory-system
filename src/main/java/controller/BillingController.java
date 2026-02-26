package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import model.dto.CartDTO;
import model.dto.MedicineDTO;
import model.tm.CartTM;
import model.tm.MedicineTM;
import org.w3c.dom.ls.LSOutput;
import service.ServiceFactory;
import service.custom.BillingService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BillingController implements Initializable {
    private final BillingService billingService = ServiceFactory.getInstance().getServiceType(ServiceType.BILLING);

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCartPrice;

    @FXML
    private TableColumn<?, ?> colCartMedCode;

    @FXML
    private TableColumn<CartTM, Integer> colCartQTY;

    @FXML
    private TableColumn<?, ?> colMedCode;

    @FXML
    private TableColumn<?, ?> colCartTotal;

    @FXML
    private TableColumn<?, ?> colCategary;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableView<MedicineTM> tblMedicine;

    @FXML
    private JFXTextField txtSearchByName;

    @FXML
    void btnClearCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintReceiptOnAction(ActionEvent event) {

    }

    @FXML
    void btnScanBarCodeOnAction(ActionEvent event) {

    }


    private ObservableList<CartTM> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapTable();
        mapCartTable();
        loadTable();
        //bind to cart data for auto update
        tblCart.setItems(cartList);
        initializeQTYColumn();

    }

    private void calculateTotal(ObservableList<CartTM> cartTMS){
        lblTotal.setText(billingService.calculateTotal(cartTMS).toString());
    }

    private void addToCart(MedicineTM medicineTM){
        cartList.add(new CartTM(
                medicineTM.getMedCode(),
                medicineTM.getUnitPrice(),
                1
        ));

        //tblCart.setItems(cartList);
    }

    private void loadTable(){
        try {
            List<MedicineDTO> allMed = billingService.getAllMed();
            List<MedicineTM> medicineTMS = new ArrayList<>();

            allMed.forEach(medicineDTO -> {
                medicineTMS.add(new MedicineTM(
                        medicineDTO.getId(),
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
        colMedCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCategary.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    private void mapCartTable(){
        colCartMedCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCartQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCartTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public void initializeQTYColumn(){
        colCartQTY.setCellFactory(tableColumn -> new TableCell<CartTM,Integer>() {
            private final JFXButton btnPlus = new JFXButton("+");
            private final JFXButton btnMinus = new JFXButton("-");
            private final Label lblQty = new Label();
            private final HBox container = new HBox(15, btnMinus, lblQty, btnPlus);

            {
                container.setAlignment(Pos.CENTER);
                btnPlus.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
                btnMinus.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

                btnPlus.setOnAction(event ->{
                    CartTM cartTM = getTableView().getItems().get(getIndex());
                    cartTM.setQty(cartTM.getQty()+1);
                    lblQty.setText(String.valueOf(cartTM.getQty()));
                    calculateTotal(cartList);
                });

                btnMinus.setOnAction(event ->{
                    CartTM cartTM = getTableView().getItems().get(getIndex());
                    if(cartTM.getQty()>1){
                        cartTM.setQty(cartTM.getQty()-1);
                        lblQty.setText(String.valueOf(cartTM.getQty()));
                    }
                    calculateTotal(cartList);
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty){
                super.updateItem(item,empty);
                if(empty){
                    setGraphic(null);
                }else{
                    CartTM cartTM = getTableView().getItems().get(getIndex());
                    lblQty.setText(String.valueOf(cartTM.getQty()));
                    setGraphic(container);
                }
            }

        });
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        MedicineTM selectedItem = tblMedicine.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            addToCart(selectedItem);
            calculateTotal(cartList);
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a medicine first!").show();
        }
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
        CartTM selectedItem = tblCart.getSelectionModel().getSelectedItem();
        cartList.remove(selectedItem);
    }
}
