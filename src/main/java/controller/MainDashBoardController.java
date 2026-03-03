package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.dto.*;
import model.entity.Medicine;
import model.tm.*;
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
    private Label lblActiveSupliers;

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
        loadActiveSupliersLabel();
        mapTblLowStock();
        mapTblExpiryWatchlist();
        mapRecntActivityTable();
        colouringRows();
        loadLowStockTable();
        loadExpiryWatchListTable();
        loadRecentActivityTable();
        loadStockPieCharts();
        loadSalsePieChart();
        loadBusyHourAnalysistBarChart();
        setPrescriptionBottlneckTrackerBarChart();
        setSalesPerfomanceAreaChart();
        loadColdChainTemp();
    }

    private void loadColdChainTemp(){
        Random random = new Random();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event -> {
            double temp = 2.0 + random.nextDouble() * 6.0;
            lblColdChainTempratireAlert.setText(String.format("%.1f°C",temp));
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void loadActiveSupliersLabel(){
        List<SuplierDTO> suplierDTOS = null;
        try {
            suplierDTOS = mainDashBoardService.getallSupliers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
        int count = 0;
        for(SuplierDTO suplierDTO:suplierDTOS){
            count += "Active".equalsIgnoreCase(suplierDTO.getStatus()) ? 1 : 0;
        }
        lblActiveSupliers.setText(""+count);
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
            loadLowStock(all);
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

    private void loadLowStock(List<MedicineDTO> all){
        int stockCount = 0;

        for(MedicineDTO medicineDTO:all){
            stockCount+=(medicineDTO.getStock()==0)?1:0;
        }
        lblOutOfStockCount.setText(""+stockCount);
    }





    private void colouringRows(){
        tblExpiryWatchlist.setRowFactory(tv->new TableRow<ExpiryWatchListTM>(){
            @Override
            protected void updateItem(ExpiryWatchListTM expiringMedTM, boolean empty){
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

        tblLowStock.setRowFactory(tv->new TableRow<LowStockTM>(){
            @Override
            protected void updateItem(LowStockTM lowStockMedTM,boolean empty){
                super.updateItem(lowStockMedTM,empty);

                if(lowStockMedTM==null||empty){
                    setStyle("");
                }else{
                    Integer minStock = lowStockMedTM.getMinLevel();
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

        List<BuyerOrderDTO> byerOrderDTOS = null;
        try {
            byerOrderDTOS = mainDashBoardService.getAllBuyerOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<LocalDate, Double> revanuePerDay = byerOrderDTOS.stream()
                .collect(Collectors.groupingBy(
                        BuyerOrderDTO::getDate, Collectors.summingDouble(s->s.getTotalPrice())
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
            List<BuyerOrderDTO> allSalseData = mainDashBoardService.getAllBuyerOrders();
            List<MedicineDTO> allMed = mainDashBoardService.getAll();
            LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);

            Map<Long, String> medIdToCategory = allMed.stream()
                    .collect(Collectors.toMap(MedicineDTO::getId, MedicineDTO::getCategory));

            Map<String, Long> categoryCount = allSalseData.stream()
                    .filter(order -> !order.getDate().isBefore(sevenDaysAgo))
                    .flatMap(order -> order.getCart().stream())
                    .map(item -> medIdToCategory.getOrDefault(item.getMedId(), "Unknown"))
                    .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

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
