/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class EditStudentAttendanceViewController implements Initializable
{

    private JFXDatePicker attDateChooser;
    @FXML
    private ToggleGroup editAttGrp;
    @FXML
    private JFXRadioButton rdBtnPresent;
    @FXML
    private JFXRadioButton rdBtnAbsent;
    @FXML
    private TableView<Attendance> tbViewAttendance;
    @FXML
    private TableColumn<Student, String> colAttendance;
    
    private Calendar currentDate;
    
    private Person user;
    
    private Student chosenStudent;
    
    AAModel aaModel;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("absence"));
    }    
    
    public void populateTableView()
    {
        tbViewAttendance.setItems(aaModel.getAttendance(chosenStudent));
    }

    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
    }
    
    public void setUser(Person userToSet)
    {
        user = userToSet;
    }
    
    public void setStudent (Student studentToGet)
    {
        chosenStudent = studentToGet;
    }
    
    @FXML
    private void saveAttendance(ActionEvent event)
    {
        Attendance changedAttendance;
        currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();
        if (editAttGrp.getSelectedToggle() == rdBtnPresent && attDateChooser.getValue() != null)
        {
            changedAttendance = new Attendance(date, true);
            aaModel.setAttendance(changedAttendance, user.getId());

            return;
        } 
        else if (editAttGrp.getSelectedToggle() == rdBtnAbsent&& attDateChooser.getValue() != null)
        {
            changedAttendance = new Attendance(date, false);
            aaModel.setAttendance(changedAttendance, user.getId());
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select either present or absent");
        alert.showAndWait();
    }
}
