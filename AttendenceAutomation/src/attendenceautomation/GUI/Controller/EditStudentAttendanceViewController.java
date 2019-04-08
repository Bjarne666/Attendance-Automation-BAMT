/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Attendance;
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
import javafx.scene.control.Button;
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
    @FXML
    private Button btnSaveAtt;
    
    private Calendar currentDate;
    
    private Student chosenStudent;
    
    AAModel aaModel;
    @FXML
    private JFXDatePicker dpNewDate;
    

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
    
    public void saveEditAttendance()
    {
        Attendance changedAttendance;
        
        Date date = tbViewAttendance.getSelectionModel().getSelectedItem().getCurrentDate();
        
        if (tbViewAttendance.getSelectionModel().getSelectedItem() != null)
            {
                dpNewDate.setVisible(false);
                
                if (editAttGrp.getSelectedToggle() == rdBtnPresent && tbViewAttendance.getSelectionModel().getSelectedItem() != null)
        {
            changedAttendance = new Attendance(date, true);
            aaModel.editAttendance(chosenStudent.getId(), changedAttendance);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance for the chosen date set to present");
            alert.showAndWait();
        } 
        else if (editAttGrp.getSelectedToggle() == rdBtnAbsent && tbViewAttendance.getSelectionModel().getSelectedItem() != null)
        {
            tbViewAttendance.getSelectionModel().getSelectedItem();
            changedAttendance = new Attendance(date, false);
            aaModel.setAttendance(changedAttendance, chosenStudent.getId());
            
            dpNewDate.setVisible(false);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance for the chosen date set to absent");
            alert.showAndWait();
        }
            }
        
        
    }
    
    @FXML
    private void saveAttendance(ActionEvent event)
    {
        if (tbViewAttendance.getSelectionModel().getSelectedItem() != null && dpNewDate.getValue() == null)
        {
            saveEditAttendance();
            
            
            
            return;
        } 
        if (dpNewDate.getValue() != null && tbViewAttendance.getSelectionModel().getSelectedItem() == null)
        {
            saveNewAttendance();
            
            
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select either present or absent");
            alert.showAndWait();
        }
    }
    
    public void saveNewAttendance()
    {
        Attendance newAttendance;
        
        Date datePicker = java.sql.Date.valueOf(dpNewDate.getValue());
                
        if (dpNewDate.getValue() != null && editAttGrp.getSelectedToggle() == rdBtnPresent)
        {
            newAttendance = new Attendance(datePicker, true);
            aaModel.setAttendance(newAttendance, chosenStudent.getId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance for the chosen date set to present");
            alert.showAndWait();
        }     
        else if (dpNewDate.getValue() != null && editAttGrp.getSelectedToggle() == rdBtnAbsent)
        {
            newAttendance = new Attendance(datePicker, false);
            aaModel.setAttendance(newAttendance, chosenStudent.getId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance for the chosen date set to absent");
            alert.showAndWait();
        }  
    }
}
