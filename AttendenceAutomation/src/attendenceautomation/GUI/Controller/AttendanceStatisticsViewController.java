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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;


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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        buildPieChart();
        studentBarChart();
    }    
    
    public BarChart studentBarChart()
    {
        // Define category axises
        CategoryAxis daysAxis = new CategoryAxis();
        daysAxis.setLabel("Days of absence");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Hours of absence");
        
        BarChart bChart = new BarChart(daysAxis, yAxis);
        studentBarChart.setTitle("Days of absence this week");
        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");
        
        dataSet.getData().add(new XYChart.Data("Monday", 2300));
        dataSet.getData().add(new XYChart.Data("Tuesday", 1000));
        dataSet.getData().add(new XYChart.Data("Wednesday", 986));
        dataSet.getData().add(new XYChart.Data("Thursday", 870));
        dataSet.getData().add(new XYChart.Data("Friday", 870));

        //add dataset to chart
        studentBarChart.getData().add(dataSet);
        
        return studentBarChart;
        
        
    }
}
