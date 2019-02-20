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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class AttendenceStudentViewController implements Initializable {

    @FXML
    private Label lblUser;
    @FXML
    private RadioButton rdBtnAbsent;
    @FXML
    private RadioButton rdBtnPresent;
    @FXML
    private Label lblAttendance;
    @FXML
    private ToggleGroup attendance;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEducation;

    public AttendenceStudentViewController() {
        lblAttendance.setVisible(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleAttendance(ActionEvent event) {
        if (attendance.getSelectedToggle() == rdBtnPresent) {
            lblAttendance.setText("Present");
        } else if (attendance.getSelectedToggle() == rdBtnAbsent) {
            lblAttendance.setText("Absent");
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select either absent or present", ButtonType.OK);
        alert.showAndWait();
        return;
    }

}
