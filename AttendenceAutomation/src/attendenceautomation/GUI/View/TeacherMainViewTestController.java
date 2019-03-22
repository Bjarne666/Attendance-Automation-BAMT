/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.View;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TeacherMainViewTestController implements Initializable
{

    @FXML
    private AnchorPane ancTeacherView;
    @FXML
    private AnchorPane ancStudentView;
    @FXML
    private PieChart studentPieChart;
    @FXML
    private BarChart<?, ?> studentBarChart;
    @FXML
    private AnchorPane ancClassView;
    @FXML
    private PieChart classPieChart;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colEmail;
    @FXML
    private TableColumn<Student, String> colAbsence;
    @FXML
    private JFXComboBox<SchoolClass> comboClassList;
    @FXML
    private Label lblDateTeacher;
    
    AAModel aaModel;
    
    private Student chosenStudent;
    
    @FXML
    private TableView<Student> tbViewStudents;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lblStudentOverview;
    @FXML
    private Label lblTeacherOverview;

    public TeacherMainViewTestController() throws IOException
    {
        aaModel = new AAModel();
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

        ancClassView.setVisible(false);
        ancStudentView.setVisible(false);

        

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
        showCurrentDate();
    }    
    
    /**
     * handles exiting the program while in teacher view
     * @param event 
     */
    @FXML
    private void handleTeacherLogout(ActionEvent event)
    {
        System.exit(0);
    }
    
    /**
     * The piechart showing overall class attendance
     * @return 
     */
    public PieChart classPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", 65),
                new PieChart.Data("Absent", 35));

        classPieChart.setData(classChart);
        classPieChart.setLegendVisible(false);

        return classPieChart;

    }
    
    /**
     * The piechart showing student attendance
     * @return 
     */
    public PieChart StudentPieChart()
    {
        ObservableList<PieChart.Data> studentChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", 90),
                new PieChart.Data("Absent", 10));

        studentPieChart.setData(studentChart);
        studentPieChart.setLegendVisible(false);

        return studentPieChart;
    }
    
     /**
     * The number of hours absent any given day for a monthly basis
     * @return 
     */
    public BarChart studentBarChart()
    {
        // Define category axises
        studentBarChart.getXAxis().setLabel("Days of absence");
        studentBarChart.getYAxis().setLabel("Hours of absence");

        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");

        dataSet.getData().add(new XYChart.Data("Monday", 25));
        dataSet.getData().add(new XYChart.Data("Tuesday", 2));
        dataSet.getData().add(new XYChart.Data("Wednesday", 0));
        dataSet.getData().add(new XYChart.Data("Thursday", 0));
        dataSet.getData().add(new XYChart.Data("Friday", 20));

        //add dataset to chart
        studentBarChart.getData().add(dataSet);
        studentBarChart.setLegendVisible(false);

        return studentBarChart;
    }
    
    /**
     * Changes the stackpane currently shown and clears the combobox selection
     * to allow returning to the same class overview.
     * @param event 
     */
    @FXML
    private void handlePaneSwitch(MouseEvent event)
    {
        {
            if (!tbViewStudents.getSelectionModel().isEmpty())
            {
                chosenStudent = tbViewStudents.getSelectionModel().getSelectedItem();
                comboClassList.getSelectionModel().clearSelection();
                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    if (event.getClickCount() == 1)
                    {
//                        ancTeacherView.getChildren().clear();
                        ancClassView.setVisible(false);

                        ancStudentView.toFront();
                        ancStudentView.setVisible(true);
//                        ancTeacherView.getChildren().add(ancStudentView);
                    }
                }
            }
        }
    }
    
    /**
     * Gets the image and does a method call when said image is clicked
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickImage(MouseEvent event) throws IOException
    {
        System.out.println("picture clicked");
        Stage mainStage = (Stage) imgLogo.getScene().getWindow();
        loadMainView();
        ancClassView.setVisible(false);
        ancStudentView.setVisible(false);
    }
    
    /**
     * Returns to the frontpage of the teacher's view
     * @throws IOException 
     */
    private void loadMainView() throws IOException
    {
//        mainAnchorPane.getChildren().clear();
//        ancTeacherView.getChildren().add(ancTeacherView);
        ancTeacherView.setVisible(true);
        ancTeacherView.toFront();

    }
    
    /**
     * Chooses which class to show
     * and shows the students currently associated with the selected class
     * @param event 
     */
    @FXML
    private void showClassStatistics(ActionEvent event)
    {
        
        if (!comboClassList.getSelectionModel().isEmpty())
        {
            System.out.println("clicked");
            tbViewStudents.setItems(aaModel.getStudentsInClass(comboClassList.getSelectionModel().getSelectedItem()));

//            mainAnchorPane.getChildren().clear();
//            ancTeacherView.setVisible(false);
            ancStudentView.setVisible(false);
            ancClassView.setVisible(true);
            ancClassView.toFront();
//            ancTeacherView.getChildren().add(ancClassView);
        }
    }
    
    private void showCurrentDate()
    {
        Calendar currentDate = Calendar.getInstance();
        int day = currentDate.get(Calendar.DATE);
        int month = currentDate.get(Calendar.MONTH);
        int year = currentDate.get(Calendar.YEAR);
        lblDateTeacher.setText(Integer.toString(day)+ "/" + Integer.toString(month+1) +"-"+Integer.toString(year));
    }
    
}
