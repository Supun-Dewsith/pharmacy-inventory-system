package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDashBoardController implements Initializable {

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
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colExpiryDate;

    @FXML
    private TableColumn<?, ?> colMedication;

    @FXML
    private TableColumn<?, ?> colMinLevel;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

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
    private TableView<?> tblExpiryWatchlist;

    @FXML
    private TableView<?> tblLowStock;

    @FXML
    private TableView<?> tblRecentActivityLog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPieCharts();
        loadBusyHourAnalysistBarChart();
        setPrescriptionBottlneckTrackerBarChart();
        setSalesPerfomanceAreaChart();
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
