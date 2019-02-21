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
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private TableColumn<Student, String> colAbsence;
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
    @FXML
    private BarChart<?, ?> barChartAbsence;

    private Student chosenStudent;

    AAModel aaModel = new AAModel();

    public TeacherMainViewController()
    {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        comboClassList.setItems(aaModel.getAllClasses());

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));

        try
        {
            loadMainView();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

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
        CollectiveStudentChart.setLegendVisible(false);

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
        barChartAbsence.getXAxis().setLabel("Days of absence");
        barChartAbsence.getYAxis().setLabel("Hours of absence");

        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");

        dataSet.getData().add(new XYChart.Data("Monday", 25));
        dataSet.getData().add(new XYChart.Data("Tuesday", 2));
        dataSet.getData().add(new XYChart.Data("Wednesday", 0));
        dataSet.getData().add(new XYChart.Data("Thursday", 0));
        dataSet.getData().add(new XYChart.Data("Friday", 20));

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
                chosenStudent = tbViewStudents.getSelectionModel().getSelectedItem();
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

            tbViewStudents.setItems(aaModel.getStudentsInClass(comboClassList.getSelectionModel().getSelectedItem()));

            mainAnchorPane.getChildren().clear();
            paneClassView.toFront();
            mainAnchorPane.getChildren().add(paneClassView);
        }
    }
}
