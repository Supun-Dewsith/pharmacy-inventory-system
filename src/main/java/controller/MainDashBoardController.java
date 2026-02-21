package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MedicineDTO;
import model.RecentActivityDTO;
import model.tm.ExpiryWatchListTM;
import model.tm.LowStockTM;
import model.tm.RecentAcitvityLogTM;
import service.ServiceFactory;
import service.custom.MainDashBoardService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainDashBoardController implements Initializable {
    private final MainDashBoardService mainDashBoardService = ServiceFactory.getInstance().getServiceType(ServiceType.MAINDASHBOARD);

    @FXML
    private BarChart<String, Number> bussyHourAnalysistBarChart;

    @FXML
    private PieChart categuryBreakdownPieChart;

    @FXML
    private PieChart categuryBreakdownPieChartInSales;

    @FXML
    private TableColumn<?, ?> colActivity;

    @FXML
    private TableColumn<?, ?> colBatch;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colExpiryDate;

    @FXML
    private TableColumn<LowStockTM, String> colMedicineCodeLowStock;

    @FXML
    private TableColumn<?, ?> colMinLevel;

    @FXML
    private TableColumn<ExpiryWatchListTM, String> colMedicineCodeExpiry;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableColumn<?, ?> colSuplier;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colValue;

    @FXML
    private Label lblColdChainTempratireAlert;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblOutOfStockCount;

    @FXML
    private Label lblPendingValidations;

    @FXML
    private BarChart<String, Number> prescriptionBottlneckTrackerBarChart;

    @FXML
    private AreaChart<String, Number> salesPerfomanceAreaChart;

    @FXML
    private TableView<ExpiryWatchListTM> tblExpiryWatchlist;

    @FXML
    private TableView<LowStockTM> tblLowStock;

    @FXML
    private TableView<RecentAcitvityLogTM> tblRecentActivityLog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapTblLowStock();
        mapTblExpiryWatchlist();
        mapRecntActivityTable();
        loadLowStockTable();
        loadExpiryWatchListTable();
        loadRecentActivityTable();
        loadPieCharts();
        loadBusyHourAnalysistBarChart();
        setPrescriptionBottlneckTrackerBarChart();
        setSalesPerfomanceAreaChart();
    }

    private void loadRecentActivityTable(){
        try {
            List<RecentActivityDTO> allActivity = mainDashBoardService.getAllActivity();
            List<RecentAcitvityLogTM> recentAcitvityLogTMS = new ArrayList<>();

            allActivity.forEach(recentActivityDTO -> {
                recentAcitvityLogTMS.add(new RecentAcitvityLogTM(
                        recentActivityDTO.getActicity(),
                        recentActivityDTO.getDate(),
                        recentActivityDTO.getTime()
                ));
            });
            tblRecentActivityLog.setItems(FXCollections.observableArrayList(recentAcitvityLogTMS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadExpiryWatchListTable(){
        try {
            List<MedicineDTO> all = mainDashBoardService.getAll();
            List<ExpiryWatchListTM> expiryWatchListTMS = new ArrayList<>();
            all.forEach(medicineDTO -> {
                expiryWatchListTMS.add(new ExpiryWatchListTM(
                        medicineDTO.getBatchNumber(),
                        medicineDTO.getItemCode(),
                        medicineDTO.getExpiryDate(),
                        medicineDTO.getUnitPrice()
                ));
            });
            tblExpiryWatchlist.setItems(FXCollections.observableArrayList(expiryWatchListTMS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLowStockTable(){
        try {
            List<MedicineDTO> all = mainDashBoardService.getAll();
            List<LowStockTM> lowStockTMS = new ArrayList<>();

            all.forEach(medicineDTO -> {
                lowStockTMS.add(new LowStockTM(
                        medicineDTO.getItemCode(),
                        medicineDTO.getStock(),
                        medicineDTO.getMinLevel(),
                        medicineDTO.getSupplierId()
                ));
            }
            );
            tblLowStock.setItems(FXCollections.observableArrayList(lowStockTMS));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    private void mapRecntActivityTable(){
        colActivity.setCellValueFactory(new PropertyValueFactory<>("acticity"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void mapTblExpiryWatchlist(){
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colMedicineCodeExpiry.setCellValueFactory(new PropertyValueFactory<>("medCode"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    private void mapTblLowStock(){
        colMedicineCodeLowStock.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colMinLevel.setCellValueFactory(new PropertyValueFactory<>("minLevel"));
        colSuplier.setCellValueFactory(new PropertyValueFactory<>("suplier"));
    }



    private void setSalesPerfomanceAreaChart(){
        //dummy data
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("Jan", 1200));
        series.getData().add(new XYChart.Data<>("Feb", 1800));
        series.getData().add(new XYChart.Data<>("Mar", 1500));
        series.getData().add(new XYChart.Data<>("Apr", 2500));
        series.getData().add(new XYChart.Data<>("May", 1200));
        series.getData().add(new XYChart.Data<>("Jun", 1800));
        series.getData().add(new XYChart.Data<>("Jul", 1500));
        series.getData().add(new XYChart.Data<>("Sep", 2500));
        series.getData().add(new XYChart.Data<>("Oct", 1200));
        series.getData().add(new XYChart.Data<>("Nov", 1800));
        series.getData().add(new XYChart.Data<>("Dec", 1500));
        series.getData().add(new XYChart.Data<>("Jan", 2500));
        salesPerfomanceAreaChart.setLegendVisible(false);
        salesPerfomanceAreaChart.getData().add(series);
    }

    private void setPrescriptionBottlneckTrackerBarChart(){
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Order Received",25));
        series.getData().add(new XYChart.Data<>("Verification", 80));
        series.getData().add(new XYChart.Data<>("Filling", 75));
        series.getData().add(new XYChart.Data<>("Final Check", 60));
        series.getData().add(new XYChart.Data<>("Ready", 60));
        prescriptionBottlneckTrackerBarChart.setLegendVisible(false);
        prescriptionBottlneckTrackerBarChart.getData().add(series);


    }

    private void loadBusyHourAnalysistBarChart(){
        //dummy data
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("6 AM",25));
        series.getData().add(new XYChart.Data<>("7 AM", 80));
        series.getData().add(new XYChart.Data<>("8 AM", 75));
        series.getData().add(new XYChart.Data<>("9 AM", 60));
        series.getData().add(new XYChart.Data<>("10 AM", 60));
        series.getData().add(new XYChart.Data<>("11 AM", 55));
        series.getData().add(new XYChart.Data<>("12 PM", 60));
        series.getData().add(new XYChart.Data<>("13 PM", 65));
        series.getData().add(new XYChart.Data<>("14 PM", 65));
        series.getData().add(new XYChart.Data<>("15 PM", 60));
        series.getData().add(new XYChart.Data<>("16 PM", 80));
        series.getData().add(new XYChart.Data<>("17 PM", 45));
        series.getData().add(new XYChart.Data<>("18 PM", 30));
        series.getData().add(new XYChart.Data<>("19 PM", 10));
        bussyHourAnalysistBarChart.setLegendVisible(false);
        bussyHourAnalysistBarChart.getData().add(series);
    }

    private void loadPieCharts(){
        ObservableList<PieChart.Data> piChartData1 = FXCollections.observableArrayList(
                //dummy data
                new PieChart.Data("ABC",1000),
                new PieChart.Data("DEF",5000),
                new PieChart.Data("HIJ",2000)

        );
        categuryBreakdownPieChart.setLegendVisible(false);
        categuryBreakdownPieChart.setData(piChartData1);

        ObservableList<PieChart.Data> piChartData2 = FXCollections.observableArrayList(
                //dummy data
                new PieChart.Data("ABC",200),
                new PieChart.Data("DEF",60),
                new PieChart.Data("HIJ",40)

        );
        categuryBreakdownPieChartInSales.setLegendVisible(false);
        categuryBreakdownPieChartInSales.setData(piChartData2);
    }

}
