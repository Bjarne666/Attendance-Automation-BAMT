/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Bjarne Helsinghof
 */
public class AttendanceStatisticsViewController implements Initializable
{

    @FXML
    private BarChart<?, ?> studentBarChart;
    @FXML
    private PieChart attendencePieChart;

    /**
     * sets the piechart data
     *
     * @return
     */
    private PieChart buildPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", 90),
                new PieChart.Data("Absent", 10));

        attendencePieChart.setData(classChart);

        return attendencePieChart;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        buildPieChart();
        studentBarChart();
    }

    /**
     * defines the barChart and inserts data
     *
     * @return
     */
    public BarChart studentBarChart()
    {
        // Define category axises
        studentBarChart.getXAxis().setLabel("Days of absence");
        studentBarChart.getYAxis().setLabel("Hours of absence");
        studentBarChart.setTitle("Overview of days absent (monthly)");
        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");

        dataSet.getData().add(new XYChart.Data("Monday", 25));
        dataSet.getData().add(new XYChart.Data("Tuesday", 2));
        dataSet.getData().add(new XYChart.Data("Wednesday", 0));
        dataSet.getData().add(new XYChart.Data("Thursday", 0));
        dataSet.getData().add(new XYChart.Data("Friday", 20));

        //add dataset to chart
        studentBarChart.getData().add(dataSet);

        return studentBarChart;

    }
}
