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
    @FXML
    private ToggleGroup editAttGrp;
    @FXML
    private JFXRadioButton rdBtnPresent;
    @FXML
    private JFXRadioButton rdBtnAbsent;
    @FXML
    private TableView<Attendance> tbViewAttendance;
    @FXML
    private TableColumn<Attendance, String> colAttendance;
    @FXML
    private TableColumn<Attendance, Date> colDate;
    
    private Calendar currentDate;
    
    private Student chosenStudent;
    
    AAModel aaModel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("present"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("currentDate"));
    }    
    
    public void populateTableView()
    {
        tbViewAttendance.setItems(aaModel.getAttendance(chosenStudent));
    }

    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
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
        Date date = tbViewAttendance.getSelectionModel().getSelectedItem().getCurrentDate();
        
        if (editAttGrp.getSelectedToggle() == rdBtnPresent && tbViewAttendance.getSelectionModel().getSelectedItem() != null)
        {
            changedAttendance = new Attendance(date, true);
            aaModel.editAttendance(chosenStudent.getId(), changedAttendance);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Attendance for the chosen date set to present");
            alert.showAndWait();
            return;
        } 
        else if (editAttGrp.getSelectedToggle() == rdBtnAbsent && tbViewAttendance.getSelectionModel().getSelectedItem() != null)
        {
            tbViewAttendance.getSelectionModel().getSelectedItem();
            changedAttendance = new Attendance(date, false);
            aaModel.setAttendance(changedAttendance, chosenStudent.getId());
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR, "Attendance for the chosen date set to absent");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select either present or absent");
        alert.showAndWait();
    }
}
