/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.DAL.PersonDBDAO;
import attendenceautomation.GUI.Model.AAModel;
import attendenceautomation.UTIL.DateConverter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TeacherViewController implements Initializable
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
    @FXML
    private TableView<Student> tbViewStudents;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lblStudentOverview;
    @FXML
    private Label lblTeacherOverview;
    @FXML
    private Label lblLoggedInUser;
    @FXML
    private Label lblClassName;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblTotalAbsence;
    @FXML
    private JFXButton btnEditAttendance;

    private Person user;
    
    private Student chosenStudent;
    
    private DateConverter dConverter;
    
    private ObservableList<LocalDate> localDates;
    
    SchoolClass chosenClass;
    
    AAModel aaModel;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    
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

        showCurrentDate();
    }

    /**
     * handles exiting the program while in teacher view
     *
     * @param event
     */
    @FXML
    private void handleTeacherLogout(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * The pie chart showing overall class attendance
     *
     * @return
     */
    public PieChart classPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", aaModel.getTotalClassPresence(chosenClass.getId())),
                new PieChart.Data("Absent", aaModel.getTotalClassAbsence(chosenClass.getId())));

        classPieChart.setData(classChart);
        classPieChart.setLegendVisible(false);

        return classPieChart;
    }


    /**
     * The pie chart showing student attendance
     *
     * @return
     */
    public PieChart StudentPieChart()
    {
        ObservableList<PieChart.Data> studentChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", aaModel.getStudentPresentPieChartData(chosenStudent.getId())),
                new PieChart.Data("Absent", aaModel.getStudentPieChartAbsenceData(chosenStudent.getId())));

        studentPieChart.setData(studentChart);
        studentPieChart.setLegendVisible(false);

        return studentPieChart;
    }

    /**
     * The number of hours absent any given day for a monthly basis
     *
     * @return
     */
    public BarChart studentBarChart()
    {
        // Define category axes
        studentBarChart.getYAxis().setLabel("Days of absence");
        XYChart.Series dataSet = new XYChart.Series();

        // Converting date to localDate and counting absense for weekdays
        dConverter = new DateConverter(aaModel.getAbsenceSumById(chosenStudent.getId()));
        localDates = dConverter.getDates();
        int mondayCount = 0;
        int tuesdayCount = 0;
        int wednesdayCount = 0;
        int thursdayCount = 0;
        int fridayCount = 0;
        for (LocalDate localDate : localDates)
        {
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            switch (dayOfWeek)
            {
                case MONDAY:
                    mondayCount++;
                    break;
                case TUESDAY:
                    tuesdayCount++;
                    break;
                case WEDNESDAY:
                    wednesdayCount++;
                    break;
                case THURSDAY:
                    thursdayCount++;
                    break;
                case FRIDAY:
                    fridayCount++;
                    break;
                default:
                    System.out.println("Not a weekday");
                    break;
            }
        }

        dataSet.setName("Absence");

        dataSet.getData().add(new XYChart.Data<>("Monday", mondayCount));
        dataSet.getData().add(new XYChart.Data<>("Tuesday", tuesdayCount));
        dataSet.getData().add(new XYChart.Data<>("Wednesday", wednesdayCount));
        dataSet.getData().add(new XYChart.Data<>("Thursday", thursdayCount));
        dataSet.getData().add(new XYChart.Data<>("Friday", fridayCount));

        //add dataset to chart
        studentBarChart.getData().add(dataSet);
        studentBarChart.setLegendVisible(false);
        
        return studentBarChart;
    }

    /**
     * Changes the stackpane currently shown and clears the combobox selection
     * to allow returning to the same class overview.
     *
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
                        studentBarChart.getData().clear();
                        studentBarChart();
                        StudentPieChart();
                        showTotalAbsence();
                        lblStudentName.setText(chosenStudent.getName());
                        ancClassView.setVisible(false);

                        ancStudentView.toFront();
                        ancStudentView.setVisible(true);
                    } 
                    
                    else if (event.getClickCount() == 2)
                    {
                        tbViewStudents.getSelectionModel().clearSelection();
                        ancClassView.setVisible(true);
                        studentBarChart.getData().clear();
                        ancStudentView.toBack();
                        ancStudentView.setVisible(false);
                    }
                }
            }
        }
    }

    /**
     * Gets the image and does a method call when said image is clicked
     *
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

        tbViewStudents.getItems().clear();
    }

    /**
     * Returns to the frontpage of the teacher's view
     *
     * @throws IOException
     */
    private void loadMainView() throws IOException
    {
        ancTeacherView.setVisible(true);
        ancTeacherView.toFront();
    }

    /**
     * Chooses which class to show and shows the students currently associated
     * with the selected class
     *
     * @param event
     */
    @FXML
    private void showClassStatistics(ActionEvent event)
    {
        if (!comboClassList.getSelectionModel().isEmpty())
        {
            System.out.println("clicked");
            SchoolClass currentClass = comboClassList.getSelectionModel().getSelectedItem();
            chosenClass = comboClassList.getSelectionModel().getSelectedItem();
            tbViewStudents.setItems(aaModel.getStudentsInClass(currentClass));
            lblClassName.setText(currentClass.getClassName());

            tbViewStudents.setItems(aaModel.getStudentsInClass(currentClass));
            lblClassName.setText(currentClass.getClassName());
            
            classPieChart();

            ancStudentView.setVisible(false);
            ancClassView.setVisible(true);
            ancClassView.toFront();
        }
    }
    
    /**
     * Gets the current date and sets it to the appropriate label
     */
    private void showCurrentDate()
    {
        Calendar currentDate = Calendar.getInstance();
        int day = currentDate.get(Calendar.DATE);
        int month = currentDate.get(Calendar.MONTH);
        int year = currentDate.get(Calendar.YEAR);
        lblDateTeacher.setText(Integer.toString(day) + "/" + Integer.toString(month + 1) + "-" + Integer.toString(year));
    }
    
    /**
     * Populates the combobox holding class objects
     */
    public void setClassCombo()
    {
        comboClassList.setItems(aaModel.getAllClasses());
    }
    
    /**
     * Sets the current user
     * @param userToSet 
     */
    public void setUser(Person userToSet)
    {
        user = userToSet;
    }
    
    /**
     * Sets the labels 
     */
    public void setLabels()
    {
        lblLoggedInUser.setText(user.getName());
    }
    
    /**
     * 
     * @param modelToSet 
     */
    void setModel(AAModel modelToSet)
    {
        aaModel = modelToSet;
    }
    
    /**
     * 
     * @param classToChoose 
     */
    public void setChosenClass(SchoolClass classToChoose)
    {
        chosenClass = classToChoose;
    }
    
    /**
     * Opens a window that lets the teacher edit a student's attendance
     * @param event
     * @throws IOException 
     */
    @FXML
    private void editAttendance(ActionEvent event) throws IOException
    {
        Stage primeStage = (Stage) btnEditAttendance.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/editStudentAttendanceView.fxml"));
        Parent root = loader.load();

        EditStudentAttendanceViewController editAttController = loader.getController();
        editAttController.setModel(aaModel);
        editAttController.setStudent(tbViewStudents.getSelectionModel().getSelectedItem());
        editAttController.populateTableView();

        Stage stageEditAtt = new Stage();
        stageEditAtt.setScene(new Scene(root));

        stageEditAtt.initModality(Modality.WINDOW_MODAL);
        stageEditAtt.initOwner(primeStage);
        stageEditAtt.show();
    }
    
    /**
     * Calculates total absence for the chosen student
     */
    public void showTotalAbsence()
    {
        double absence = aaModel.getStudentPieChartAbsenceData(chosenStudent.getId());
        double pressent = aaModel.getStudentPresentPieChartData(chosenStudent.getId());
        double totaldays = (absence + pressent); 
        double absenceProcent =(absence/totaldays)*100;
        DecimalFormat df = new DecimalFormat(".00");
        String formatProcent = df.format(absenceProcent);
        lblTotalAbsence.setText(formatProcent + "%");
    }
}
