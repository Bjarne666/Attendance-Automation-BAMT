/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class EditStudentAttendanceViewController implements Initializable
{

    @FXML
    private JFXDatePicker attDateChooser;
    @FXML
    private ToggleGroup editAttGrp;

    AAModel aaModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
    }
    
    @FXML
    private void saveAttendance(ActionEvent event)
    {
    }
    
}
