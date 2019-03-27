/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Person;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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

    private Person user;

    AAModel aaModel;
    @FXML
    private Label lblLoggedInAs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            aaModel = new AAModel();
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

        try
        {
            loadMainView();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        showCurrentDate();
        buildPieChart();
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

        if (attendance.getSelectedToggle() == rdBtnPresent && dPickerTo.getValue() != null)
        {
            informationAlert("Attendance for the chosen date set to present");
            return;
        } else if (attendance.getSelectedToggle() == rdBtnAbsent && dPickerTo.getValue() != null)
        {
            informationAlert("Attendance for the chosen date set to absent");
            return;
        }
        Alert newAlert = new Alert(Alert.AlertType.ERROR, "You either did not set a date to change or a state to change to");
        newAlert.showAndWait();
    }

    private void handleAbsenceVisibility(ActionEvent event)
    {
        if (attendance.getSelectedToggle() != rdBtnAbsent)
        {
            lblAReason.setVisible(false);
            txtAReason.setVisible(false);
        } else if (attendance.getSelectedToggle() == rdBtnAbsent)
        {
            lblAReason.setVisible(true);
            txtAReason.setVisible(true);
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
        if (attendance.getSelectedToggle() == rdBtnPresent)
        {
            lblAttendance.setText("Present");
            lblAttendance.setVisible(true);
            lblAbsent.setVisible(false);
            return;
        } else if (attendance.getSelectedToggle() == rdBtnAbsent)
        {
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
        Calendar currentDate = Calendar.getInstance();
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
    private PieChart buildPieChart()
    {
        ObservableList<PieChart.Data> classChart = FXCollections.observableArrayList(
                new PieChart.Data("Present", 90),
                new PieChart.Data("Absent", 10));

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
        // Define category axises
        studentBarChart.getXAxis().setLabel("Days of absence");
        studentBarChart.getYAxis().setLabel("Hours of absence");
        studentBarChart.setTitle("Overview of days absent (monthly)");
        XYChart.Series dataSet = new XYChart.Series();
        dataSet.setName("Absence");

        dataSet.getData().add(new XYChart.Data<>("Monday", aaModel.getAbsenceSumById(user.getId())));
        dataSet.getData().add(new XYChart.Data<>("Tuesday", aaModel.getAbsenceSumById(user.getId())));
        dataSet.getData().add(new XYChart.Data<>("Wednesday", aaModel.getAbsenceSumById(user.getId())));
        dataSet.getData().add(new XYChart.Data<>("Thursday", aaModel.getAbsenceSumById(user.getId())));
        dataSet.getData().add(new XYChart.Data<>("Friday", aaModel.getAbsenceSumById(user.getId())));

//        dataSet.getData().add(new XYChart.Data("Monday", 25));
//        dataSet.getData().add(new XYChart.Data("Tuesday", 2));
//        dataSet.getData().add(new XYChart.Data("Wednesday", 0));
//        dataSet.getData().add(new XYChart.Data("Thursday", 0));
//        dataSet.getData().add(new XYChart.Data("Friday", 20));
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
    }

    @FXML
    private void handleStudentLogout(ActionEvent event)
    {
        System.exit(0);
    }

}
