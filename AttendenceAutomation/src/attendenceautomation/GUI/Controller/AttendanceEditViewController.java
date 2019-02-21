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
        setReasonVisibility(false);
    }

    @FXML
    private void handleEditAttendance(ActionEvent event)
    {
        handleButtons();
    }

    public void handleButtons()
    {
        if (editAttendance.getSelectedToggle() == rdBtnPresent)
        {

            return;
        } else if (editAttendance.getSelectedToggle() == rdBtnAbsent)
        {
            setReasonVisibility(true);
            return;
        }
        Alert newAlert = new Alert(Alert.AlertType.ERROR, "You have to select either absent or present");
        newAlert.showAndWait();
    }
    
    public void setReasonVisibility(boolean visible)
    {
      lblAReason.setVisible(visible);
      txtAReason.setVisible(visible);   
    }
    
    @FXML
    private void handleAbsenceVisibility(ActionEvent event)
    {
        if (editAttendance.getSelectedToggle() != rdBtnAbsent)
        {
            setReasonVisibility(false);
        }
        else if (editAttendance.getSelectedToggle() == rdBtnAbsent)
        {
            setReasonVisibility(true);
        }
    }

}
