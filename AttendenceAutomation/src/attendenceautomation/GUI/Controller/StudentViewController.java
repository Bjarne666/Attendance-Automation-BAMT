/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.GUI.Model.AAModel;
import attendenceautomation.UTIL.DateConverter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class StudentViewController implements Initializable
{

    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton btnStatistics;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private BarChart<?, ?> studentBarChart;
    @FXML
    private TextField txtAReason;
    @FXML
    private Label lblAReason;
    @FXML
    private DatePicker dPickerFrom;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEducation;
    @FXML
    private ToggleGroup attendance;
    @FXML
    private Label lblAbsent;
    @FXML
    private Label lblAttendance;
    @FXML
    private PieChart attendencePieChart;
    @FXML
    private AnchorPane ancStatisticView;
    @FXML
    private AnchorPane ancEditView;
    @FXML
    private AnchorPane ancStudentMainView;
    @FXML
    private DatePicker dPickerTo;
    @FXML
    private RadioButton rdBtnPresent;
    @FXML
    private RadioButton rdBtnAbsent;
    @FXML
    private Label lblLoggedInAs;
    @FXML
    private RadioButton editRdBtnAbsent;
    @FXML
    private ToggleGroup Editgrp;
    @FXML
    private RadioButton editRdBtnPresent;
    
    private Calendar currentDate;
    
    private Person user;
    
    private AAModel aaModel;
    private DateConverter dConverter;
    private ObservableList<LocalDate> localDates;
    @FXML
    private Label lblTotalAbsence;
    @FXML
    private JFXToggleButton btnToggleMultiple;
    @FXML
    private Label lblTo;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            aaModel = new AAModel();
            localDates = FXCollections.observableArrayList();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblAttendance.setVisible(false);
        lblAbsent.setVisible(false);

        lblAReason.setVisible(false);
        txtAReason.setVisible(false);

        ancEditView.setVisible(false);
        ancStatisticView.setVisible(false);
        
        lblTo.setVisible(false);
        dPickerTo.setVisible(false);

        try
        {
            loadMainView();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        showCurrentDate();
//        buildPieChart();
//        studentBarChart();
    }

    /**
     * Returns to the frontpage of the teacher's view
     *
     * @throws IOException
     */
    private void loadMainView() throws IOException
    {
        System.out.println("im loading");
//        mainAnchorPane.getChildren().clear();
        ancEditView.setVisible(false);
        ancStatisticView.setVisible(false);
        ancStudentMainView.setVisible(true);
        ancStudentMainView.toFront();

    }

    @FXML
    private void switchToEditWindow(ActionEvent event)
    {
        System.out.println("Edit");
//        ancStudentMainView.setVisible(false);
        ancStatisticView.setVisible(false);

        ancEditView.setVisible(true);
        ancEditView.toFront();

    }

    @FXML
    private void showStatisticWindow(ActionEvent event)
    {
        System.out.println("statistic");
//        ancStudentMainView.setVisible(false);
        ancEditView.setVisible(false);

        ancStatisticView.setVisible(true);
        ancStatisticView.toFront();

    }

    @FXML
    private void handleEditAttendance(ActionEvent event)
    {
        Attendance changedAttendance;
        currentDate = Calendar.getInstance();
        Date date = java.sql.Date.valueOf(dPickerFrom.getValue());
        if (Editgrp.getSelectedToggle() == editRdBtnPresent && dPickerFrom.getValue() != null)
        {
            changedAttendance = new Attendance(date, true);
            aaModel.editAttendance(changedAttendance, user.getId());
            
            informationAlert("Attendance for the chosen date set to present");
            return;
        } else if (Editgrp.getSelectedToggle() == editRdBtnAbsent && dPickerFrom.getValue() != null)
        {
            changedAttendance = new Attendance(date, false);
            aaModel.editAttendance(changedAttendance, user.getId());

            informationAlert("Attendance for the chosen date set to absent");
            return;
        }
        Alert newAlert = new Alert(Alert.AlertType.ERROR, "You either did not set a date to change or a state to change to");
        newAlert.showAndWait();
    }
    
    @FXML
    private void handleAbsenceVisibility(ActionEvent event)
    {
        if (Editgrp.getSelectedToggle() != editRdBtnAbsent)
        {
            lblAReason.setVisible(false);
            txtAReason.setVisible(false);
        } else if (Editgrp.getSelectedToggle() == editRdBtnAbsent)
        {
            lblAReason.setVisible(true);
            txtAReason.setVisible(true);
        }
        if (btnToggleMultiple.isSelected())
        {
            lblTo.setVisible(true);
            dPickerTo.setVisible(true);
        } else if(!btnToggleMultiple.isSelected())
        {
            lblTo.setVisible(false);
            dPickerTo.setVisible(false);
            
        }
        
    }

    /**
     * handles information messages
     *
     * @param message
     */
    public void informationAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    private void handleAttendance(ActionEvent event)
    {
        Attendance changedAttendance;
        currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();
        if (attendance.getSelectedToggle() == rdBtnPresent)
        {
            changedAttendance = new Attendance(date, true);
            aaModel.setAttendance(changedAttendance, user.getId());
            lblAttendance.setText("Present");
            lblAttendance.setVisible(true);
            lblAbsent.setVisible(false);
            return;
        } else if (attendance.getSelectedToggle() == rdBtnAbsent)
        {
            changedAttendance = new Attendance(date, false);
            aaModel.setAttendance(changedAttendance, user.getId());
            lblAbsent.setText("Absent");
            lblAbsent.setVisible(true);
            lblAttendance.setVisible(false);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select either present or absent");
        alert.showAndWait();
    }

    private void showCurrentDate()
    {
        currentDate = Calendar.getInstance();
        int day = currentDate.get(Calendar.DATE);
        int month = currentDate.get(Calendar.MONTH);
        int year = currentDate.get(Calendar.YEAR);
        lblDate.setText(Integer.toString(day) + "/" + Integer.toString(month + 1) + "-" + Integer.toString(year));

    }

    /**
     * sets the piechart data
     *
     * @return
     */
    public PieChart buildPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", aaModel.getStudentPresentPieChartData(user.getId())),
                new PieChart.Data("Absent", aaModel.getStudentPieChartData(user.getId())));
    
        attendencePieChart.setData(classChart);
        attendencePieChart.setLegendVisible(false);

        return attendencePieChart;
    }
   
    /**
     * defines the barChart and inserts data
     *
     * @return
     */
    public BarChart studentBarChart()
    {
        // Define category axes
        studentBarChart.getYAxis().setLabel("Days of absence");
        studentBarChart.setTitle("Overview of days absent (monthly)");
        XYChart.Series dataSet = new XYChart.Series();
        
        // Converting date to localDate and counting absense for weekdays
        dConverter = new DateConverter(aaModel.getAbsenceSumById(user.getId()));
        localDates = dConverter.getDates();
        int mondayCount = 0;
        int tuesdayCount = 0;
        int wednesdayCount = 0;
        int thursdayCount = 0;
        int fridayCount = 0;
        for (LocalDate localDate : localDates)
        {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        switch(dayOfWeek)
        {
            case MONDAY: mondayCount++;
            break;
            case TUESDAY: tuesdayCount++;   
            break;
            case WEDNESDAY: wednesdayCount++; 
            break;
            case THURSDAY: thursdayCount++;  
            break;
            case FRIDAY: fridayCount++;
            break;
            default: System.out.println("Not a weekday");
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

    @FXML
    private void clickImage(MouseEvent event) throws IOException
    {
        System.out.println("you clicked me");
        Stage mainStage = (Stage) imgLogo.getScene().getWindow();
        loadMainView();
    }

    public void setUser(Person userToSet)
    {
        user = userToSet;
    }

    public void setLabels()
    {
        lblName.setText(user.getName());
        lblLoggedInAs.setText(user.getName());
        lblEducation.setText(aaModel.getStudentClass(user.getId()));
        lblTotalAbsence.setText(Integer.toString(aaModel.getStudentPieChartAbsenceData(user.getId())));
    }

    @FXML
    private void handleStudentLogout(ActionEvent event)
    {
        System.exit(0);
    }

    void setModel(AAModel modelToSet)
    {
        aaModel = modelToSet;
    }

}
