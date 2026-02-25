package controller.medicine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.tm.MedicineTM;

public class MedicineInfoFormController {
    @FXML
    private Label lblBatchNumber;

    @FXML
    private Label lblBuyingPrice;

    @FXML
    private Label lblCategury;

    @FXML
    private Label lblMedName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblExpiryDate;

    @FXML
    private Label lblMedCode;

    @FXML
    private Label lblMinLevel;

    @FXML
    private Label lblPackSize;

    @FXML
    private Label lblReoderLevel;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblSuplierId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblBrand;

    public void setMedData(MedicineTM medData){
        lblMedName.setText(medData.getMedName());
        lblMedCode.setText("Med Code : "+medData.getMedCode());
        lblExpiryDate.setText("Expiry Date : "+medData.getExpiryDate());
        lblBrand.setText("Brand : "+medData.getBrand());
        lblBatchNumber.setText("Batch Number : "+medData.getBatchNumber());
        lblDescription.setText("Description : "+medData.getDescription());
        lblCategury.setText("Categury : "+medData.getCategory());
        lblUnitPrice.setText("Unit Price : "+medData.getUnitPrice());
        lblBuyingPrice.setText("Buying Price : "+medData.getBuyingPrice());
        lblStock.setText("Stock : "+medData.getStock());
        lblMinLevel.setText("Min Level : "+medData.getMinLevel());
        lblPackSize.setText("Pack Size : "+medData.getPackSize());
        lblSuplierId.setText("Suplier ID : "+medData.getSupplierId());

    }


}
