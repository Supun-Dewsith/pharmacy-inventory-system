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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.dto.*;
import model.entity.Customer;
import model.tm.CartTM;
import model.tm.CustomerTM;
import model.tm.MedicineTM;
import org.w3c.dom.ls.LSOutput;
import service.ServiceFactory;
import service.custom.BillingService;
import service.custom.CustomerManagementService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BillingController implements Initializable {


    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCartMedCode;

    @FXML
    private TableColumn<?, ?> colCartPrice;

    @FXML
    private TableColumn<CartTM,Integer> colCartQTY;

    @FXML
    private TableColumn<?, ?> colCartTotal;

    @FXML
    private TableColumn<?, ?> colCategary;

    @FXML
    private TableColumn<?, ?> colCuatName;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMedCode;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?,?> colQTY;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableView<MedicineTM> tblMedicine;

    @FXML
    private JFXTextField txtSearchByName;

    @FXML
    private JFXTextField txtSearchByName1;

    private final BillingService billingService = ServiceFactory.getInstance().getServiceType(ServiceType.BILLING);

    private final CustomerManagementService customerManagementService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private CustomerTM selectedCustomer;

    @FXML
    void btnClearCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        addNewOrder();
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
        mapCustomerTable();
        mapCartTable();
        loadTable();
        loadCustomerTable();
        tblCart.setItems(cartList);
        initializeQTYColumn();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                setSelectedCustomer(newValue);
            }
        });

    }

    private void addNewOrder(){
        if(getSelectedCustomer()==null){
            new Alert(Alert.AlertType.ERROR, "Select a Customer!").show();
            return;
        }
        if(cartList.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Cart is empty!").show();
            return;
        }
        BuyerOrderSaveRequestDTO buyerOrderSaveRequestDTO = new BuyerOrderSaveRequestDTO();
        buyerOrderSaveRequestDTO.setCustId(getSelectedCustomer().getId());
        buyerOrderSaveRequestDTO.setTotalPrice(calculateTotal());

        LocalDate today = LocalDate.now();
        buyerOrderSaveRequestDTO.setDate(today);

        LocalTime timeNow = LocalTime.now();
        buyerOrderSaveRequestDTO.setTime(timeNow);

        ArrayList<BuyerOrderItemDTO> buyerOrderItemDTOS = new ArrayList<>();
        cartList.forEach(cartTM -> buyerOrderItemDTOS.add(mapCartTMtoBuyerOrderItemDTO(cartTM)));
        buyerOrderSaveRequestDTO.setCart(buyerOrderItemDTOS);

        try {
            boolean isSaved = billingService.saveOrder(buyerOrderSaveRequestDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Order Saved Successfully!").show();
                loadTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Saving failed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    private BuyerOrderItemDTO mapCartTMtoBuyerOrderItemDTO(CartTM cartTM){
        return new BuyerOrderItemDTO(
          cartTM.getMedId(),
          cartTM.getMedCode(),
          cartTM.getQty(),
          cartTM.getTotal()
        );
    }

    private Double calculateTotal(){
        return cartList.stream()
                .mapToDouble(cartItem -> cartItem.getTotal())
                .sum();
    }

    private void calculateTotal(ObservableList<CartTM> cartTMS){
        lblTotal.setText(billingService.calculateTotal(cartTMS).toString());
    }

    private void addToCart(MedicineTM medicineTM){
        cartList.add(new CartTM(
                medicineTM.getId(),
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
            throw new RuntimeException(e);
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

    private void mapTable(){
        colMedCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCategary.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    private void mapCartTable(){
        colCartMedCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
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
