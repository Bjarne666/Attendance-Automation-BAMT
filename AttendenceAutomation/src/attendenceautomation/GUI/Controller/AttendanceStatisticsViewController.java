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
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Bjarne Helsinghof
 */
public class AttendanceStatisticsViewController implements Initializable
{
    
    @FXML
    private PieChart AttendencePieChart;
	
private PieChart buildPieChart()
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Attendence", 90),
        new PieChart.Data("Absence", 10));
          
        
        AttendencePieChart.setData(pieChartData);        
    
        
        return AttendencePieChart;
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
    }    
    
    @FXML 
    private void handlePieChart(ActionEvent event) 
            {
                AnchorPane.getBottomAnchor(buildPieChart());
            }
}
