package controller.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerInfoFormController {
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
    private TableView<?> tblDelleveryDetails;

    @FXML
    private TableView<?> tblMedAndQuality;
}
