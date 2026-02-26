package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.dto.*;
import model.entity.Medicine;
import model.tm.ExpiryWatchListTM;
import model.tm.LowStockTM;
import model.tm.RecentAcitvityLogTM;
import service.ServiceFactory;
import service.custom.MainDashBoardService;
import util.ServiceType;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        loadDateAndTime();
        mapTblLowStock();
        mapTblExpiryWatchlist();
        mapRecntActivityTable();
        loadLowStockTable();
        loadExpiryWatchListTable();
        loadRecentActivityTable();
        loadStockPieCharts();
        loadSalsePieChart();
        loadBusyHourAnalysistBarChart();
        setPrescriptionBottlneckTrackerBarChart();
        setSalesPerfomanceAreaChart();
        loadCriticleAlerts();
    }

    private void loadCriticleAlerts(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event -> {
            lblPendingValidations.setText(""+new Random().nextInt(100));
            lblOutOfStockCount.setText(""+new Random().nextInt(100));
            double temp = 20.0+ new Random().nextDouble(5);
            lblColdChainTempratireAlert.setText(String.format("%.1f°C",temp));
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //lblDate.setText(simpleDateFormat.format(date));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            //lblTime.setText(now.format(dateTimeFormatter));
            lblDateTime.setText(simpleDateFormat.format(date)+" | "+now.format(dateTimeFormatter));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

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
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        List<ByerOrderDTO> byerOrderDTOS = null;
        try {
            byerOrderDTOS = mainDashBoardService.getAllByerOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<LocalDate, Double> revanuePerDay = byerOrderDTOS.stream()
                .collect(Collectors.groupingBy(
                        ByerOrderDTO::getDate, Collectors.summingDouble(s->s.getTotalPrice())
                ));

        List<ChartAreaDTO> chartAreaDTOS = revanuePerDay.entrySet().stream()
                .map(entry -> new ChartAreaDTO(entry.getKey().toString(), entry.getValue()))
                .sorted(Comparator.comparing(ChartAreaDTO::getPeriod))
                .collect(Collectors.toList());

        chartAreaDTOS.forEach(chartAreaDTO -> {
            series.getData().add(new XYChart.Data<>(chartAreaDTO.getPeriod(),chartAreaDTO.getTotalSales()));
        });
        salesPerfomanceAreaChart.setLegendVisible(false);
        salesPerfomanceAreaChart.setCreateSymbols(false);
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

    public void loadStockPieCharts(){
        ObservableList<PieChart.Data> piChartData = FXCollections.observableArrayList();
        try {
            List<MedicineDTO> medicineDTOS = mainDashBoardService.getAll();
            Map<String, Integer> collection = medicineDTOS.stream()
                    .collect(Collectors.groupingBy(
                            MedicineDTO::getCategory,
                            Collectors.summingInt(stock -> stock.getStock())
                    ));
            collection.entrySet().stream()
                    .forEach(entry -> piChartData.add(new PieChart.Data(entry.getKey().toString(),entry.getValue())));
            categuryBreakdownPieChart.setData(piChartData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        categuryBreakdownPieChart.setLegendVisible(false);
        categuryBreakdownPieChart.setData(piChartData);
    }

    private void loadSalsePieChart(){
        try {
            List<ByerOrderDTO> allSalseData = mainDashBoardService.getAllByerOrders();
            LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
            Map<String, Long> categoryCount = allSalseData.stream()
                    //i used !isBefore because isAfter exclude last day
                    .filter(order -> !order.getDate().isBefore(sevenDaysAgo))
                    .flatMap(byerOrderDTO -> byerOrderDTO.getCart().stream())
                    .collect(Collectors.groupingBy(
                            Medicine::getCategory,Collectors.counting()
                    ));

            ObservableList<PieChart.Data> piChartData1 = FXCollections.observableArrayList();
            categoryCount.entrySet().stream()
                    .forEach(entry -> piChartData1.add(new PieChart.Data(entry.getKey().toString(),entry.getValue())));
            categuryBreakdownPieChartInSales.setData(piChartData1);
            categuryBreakdownPieChartInSales.setLegendVisible(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
