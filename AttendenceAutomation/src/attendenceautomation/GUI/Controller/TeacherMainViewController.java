/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TeacherMainViewController implements Initializable
{

    @FXML
    private TableView<Student> tbViewStudents;
    @FXML
    private JFXComboBox<SchoolClass> comboClassList;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colEmail;
    @FXML
    private PieChart studentPieChart;
    @FXML
    private PieChart CollectiveStudentChart;
    @FXML
    private Pane paneStudentView;
    @FXML
    private Pane paneMainView;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Pane paneClassView;
    @FXML
    private ImageView imgLogo;
    
    AAModel aaModel = new AAModel();
    
    private Student chosenClass;
    
    private SchoolClass classList;
    @FXML
    private BarChart<?, ?> barChartAbsence;
    

    public TeacherMainViewController()
    {
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tbViewStudents.setItems(aaModel.getAllStudents());
        comboClassList.setItems(aaModel.getAllClasses());
        
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
//        colName.setCellValueFactory(cellData -> cellData.getValue().getName());
//        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmail());
        classPieChart();
        StudentPieChart();
        studentBarChart();
        
       
    }    


    @FXML
    private void handleTeacherLogout(ActionEvent event)
    {
        System.exit(0);
    }
    
    public PieChart classPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
            new PieChart.Data("Present", 65),
            new PieChart.Data("Absent", 35));
        
        CollectiveStudentChart.setData(classChart);
        
        return CollectiveStudentChart;
        
    }
    
    public PieChart StudentPieChart()
    {
        ObservableList<PieChart.Data> studentChart = FXCollections.observableArrayList(
            new PieChart.Data("Present", 90),
            new PieChart.Data("Absent", 10));
        
        studentPieChart.setData(studentChart);
        
        
        return studentPieChart; 
    }
    
    public BarChart studentBarChart()
    {
        // Define category axises
        CategoryAxis daysAxis = new CategoryAxis();
        daysAxis.setLabel("Days of absence");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Hours of absence");
        
        BarChart bChart = new BarChart(daysAxis, yAxis);
        
        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");
        
        dataSet.getData().add(new XYChart.Data("Monday", 2300));
        dataSet.getData().add(new XYChart.Data("Tuesday", 1000));
        dataSet.getData().add(new XYChart.Data("Wednesday", 986));
        dataSet.getData().add(new XYChart.Data("Thursday", 870));
        dataSet.getData().add(new XYChart.Data("Friday", 870));

        //add dataset to chart
        barChartAbsence.getData().add(dataSet);
        
        return barChartAbsence;
        
        
    }

    @FXML
    private void handlePaneSwitch(MouseEvent event)
    {
        {
            if (!tbViewStudents.getSelectionModel().isEmpty())
            {
                chosenClass = tbViewStudents.getSelectionModel().getSelectedItem();
//                tbViewStudents.setItems(aaModel.getAllStudents());

                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    if (event.getClickCount() == 1)
                    {
                        mainAnchorPane.getChildren().clear();

                        paneStudentView.toFront();
                        mainAnchorPane.getChildren().add(paneStudentView);
                    }
                }
            }
        
        }
        

    }

    @FXML
    private void clickImage(MouseEvent event) throws IOException
    {
        Stage mainStage = (Stage) imgLogo.getScene().getWindow();
        loadMainView();
    }
    
     private void loadMainView() throws IOException
     {
         mainAnchorPane.getChildren().clear();
         paneMainView.toFront();
         mainAnchorPane.getChildren().add(paneMainView);
     }

    @FXML
    private void showClassStatistics(ActionEvent event)
    {
        if (!comboClassList.getSelectionModel().isEmpty())
        {
            classList = comboClassList.getSelectionModel().getSelectedItem();
            comboClassList.setItems(aaModel.getAllClasses());
            
            mainAnchorPane.getChildren().clear();

            paneClassView.toFront();
            mainAnchorPane.getChildren().add(paneClassView);
            
        }
    }

}
