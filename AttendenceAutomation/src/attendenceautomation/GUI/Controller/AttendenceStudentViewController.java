/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import java.net.URL;
import java.sql.Array;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Kokushi
 */
public class AttendenceStudentViewController implements Initializable
{

    @FXML
    private Label lblUser;
    @FXML
    private ToggleGroup attendance;
    @FXML
    private Label lblAttendance;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEducation;
    @FXML
    private RadioButton rdBtnAbsent;
    @FXML
    private RadioButton rdBtnPresent;
    @FXML
    private Label lblAbsent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lblUser.setText("Birger");
        //lblDate.setText("22-02-2019");
        showCurrentDate();
        lblEducation.setText("Computer Science");
        lblName.setText("Birger");
        lblAttendance.setVisible(false);
        lblAbsent.setVisible(false);
    }

    /**
     * Shows a label with the chosen state of attendance when the save button is
     * pressed.
     *
     * @param event
     */
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
    
    @FXML
    private void showCurrentDate()
    {
        Calendar currentDate = Calendar.getInstance();
        int day = currentDate.get(Calendar.DATE);
        int month = currentDate.get(Calendar.MONTH);
        int year = currentDate.get(Calendar.YEAR);
        lblDate.setText(Integer.toString(day)+ "/" + Integer.toString(month+1) +"-"+Integer.toString(year));
    
    }


}
