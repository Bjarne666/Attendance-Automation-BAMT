/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class AttendanceEditViewController implements Initializable
{

    @FXML
    private DatePicker dPickerFrom;
    @FXML
    private TextField txtAReason;
    @FXML
    private DatePicker dPickerTo;
    @FXML
    private ToggleGroup editAttendance;
    @FXML
    private RadioButton rdBtnPresent;
    @FXML
    private RadioButton rdBtnAbsent;
    @FXML
    private Label lblAReason;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lblAReason.setVisible(false);
        txtAReason.setVisible(false);
    }
    
    /**
     * handles changing the attendance based on the chosen buttons
     * or a message if nothing is chosen
     * @param event 
     */
    @FXML
    private void handleEditAttendance(ActionEvent event)
    {
         if (editAttendance.getSelectedToggle() == rdBtnPresent && dPickerTo.getValue() != null)
        {
            informationAlert("Attendance for the chosen date set to present");
            return;
        } else if (editAttendance.getSelectedToggle() == rdBtnAbsent && dPickerTo.getValue() != null)
        {
            informationAlert("Attendance for the chosen date set to absent");
            return;
        }
        Alert newAlert = new Alert(Alert.AlertType.ERROR, "You either did not set a date to change or a state to change to");
        newAlert.showAndWait();
    }


    /**
     * Changes visibility of the reason textfield depending on the radiobutton
     * chosen.
     *
     * @param event
     */
    @FXML
    private void handleAbsenceVisibility(ActionEvent event)
    {
        if (editAttendance.getSelectedToggle() != rdBtnAbsent)
        {
            lblAReason.setVisible(false);
            txtAReason.setVisible(false);
        } else if (editAttendance.getSelectedToggle() == rdBtnAbsent)
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
