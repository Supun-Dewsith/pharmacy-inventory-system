package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.MedicineDTO;
import model.tm.ExpiringMedTM;
import model.tm.LowStockMedTM;
import model.tm.MedicineTM;
import service.ServiceFactory;
import service.custom.ExpiryStockAlertsService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ExpiryStockAlertsController implements Initializable {
    private final ExpiryStockAlertsService expiryStockAlertsService = ServiceFactory.getInstance().getServiceType(ServiceType.EXPIRYANDSTOCKALERTS);

    @FXML
    private TableColumn<?, ?> colExpiredBrand;

    @FXML
    private TableColumn<?, ?> colExpiredCategory;

    @FXML
    private TableColumn<?, ?> colExpiredMedCode;

    @FXML
    private TableColumn<?, ?> colExpiredName;

    @FXML
    private TableColumn<?, ?> colExpiredPrice;

    @FXML
    private TableColumn<?, ?> colExpiringDate;

    @FXML
    private TableColumn<?, ?> colLowStockBrand;

    @FXML
    private TableColumn<?, ?> colLowStockCategury;

    @FXML
    private TableColumn<?, ?> colLowStockMedCode;

    @FXML
    private TableColumn<?, ?> colLowStockName;

    @FXML
    private TableColumn<?, ?> colMinStock;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableView<ExpiringMedTM> tblExpiringItems;

    @FXML
    private TableView<LowStockMedTM> tblLowStockItems;


    List<MedicineDTO> medicineDTOS;
    {
        try {
            medicineDTOS = expiryStockAlertsService.getAllMed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadLowStockTable(){
        List<LowStockMedTM> lowStockMedTMS = new ArrayList<>();

        medicineDTOS.forEach(medicineDTO -> {
            lowStockMedTMS.add(new LowStockMedTM(
                    medicineDTO.getItemCode(),
                    medicineDTO.getMedName(),
                    medicineDTO.getBrand(),
                    medicineDTO.getCategory(),
                    medicineDTO.getMinLevel(),
                    medicineDTO.getStock()
            ));
        });

        List<LowStockMedTM> sortedList = lowStockMedTMS.stream()
                .sorted(Comparator.comparing(LowStockMedTM::getStock))
                .collect(Collectors.toList());

        tblLowStockItems.setItems(FXCollections.observableArrayList(sortedList));
    }

    private void loadExpiringTable(){
        List<ExpiringMedTM> expiringMedTMS = new ArrayList<>();

        medicineDTOS.forEach(medicineDTO -> {
            expiringMedTMS.add(new ExpiringMedTM(
                    medicineDTO.getItemCode(),
                    medicineDTO.getMedName(),
                    medicineDTO.getBrand(),
                    medicineDTO.getCategory(),
                    medicineDTO.getBuyingPrice(),
                    medicineDTO.getExpiryDate()
            ));
        });

        List<ExpiringMedTM> sortedList = expiringMedTMS.stream()
                .sorted(Comparator.comparing(ExpiringMedTM::getExpiryDate))
                .collect(Collectors.toList());

        tblExpiringItems.setItems(FXCollections.observableArrayList(sortedList));
    }

    private void colouringRows(){
        tblExpiringItems.setRowFactory(tv->new TableRow<ExpiringMedTM>(){
            @Override
            protected void updateItem(ExpiringMedTM expiringMedTM, boolean empty){
                super.updateItem(expiringMedTM,empty);

                if(expiringMedTM == null || empty){
                    setStyle("");
                }else{
                    LocalDate now = LocalDate.now();
                    LocalDate expiryDate = expiringMedTM.getExpiryDate();

                    if(expiryDate.isBefore(now)){
                        setStyle("-fx-background-color: #ff7675;");
                    }else if(expiryDate.isBefore(now.plusMonths(3))){
                        setStyle("-fx-background-color: #fab1a0;");
                    }else if(expiryDate.isBefore(now.plusMonths(6))){
                        setStyle("-fx-background-color: #ffeaa7;");
                    }else{
                        setStyle("");
                    }
                }
            }
        });

        tblLowStockItems.setRowFactory(tv->new TableRow<LowStockMedTM>(){
            @Override
            protected void updateItem(LowStockMedTM lowStockMedTM,boolean empty){
                super.updateItem(lowStockMedTM,empty);

                if(lowStockMedTM==null||empty){
                    setStyle("");
                }else{
                    Integer minStock = lowStockMedTM.getMinStock();
                    Integer currentStock = lowStockMedTM.getStock();
                    double stockPresentage = currentStock/(double)minStock*100;

                    if(stockPresentage<50){
                        setStyle("-fx-background-color: #ff7675;");
                    }else if(stockPresentage<100){
                        setStyle("-fx-background-color: #fab1a0;");
                    }else{
                        setStyle("");
                    }
                }
            }
        });
    }

    private void mapExpiringTable(){
        colExpiredMedCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colExpiredName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colExpiredBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colExpiredCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colExpiredPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colExpiringDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
    }

    private void mapLowStockTable(){
        colLowStockMedCode.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colLowStockName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colLowStockBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colLowStockCategury.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colMinStock.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapExpiringTable();
        mapLowStockTable();
        colouringRows();
        loadExpiringTable();
        loadLowStockTable();

    }
}
