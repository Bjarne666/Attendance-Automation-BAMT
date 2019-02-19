/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.View;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TeacherMainViewController implements Initializable
{

    @FXML
    private JFXListView<Student> listStudents;
    @FXML
    private JFXComboBox<SchoolClass> comboClassList;

    AAModel aaModel = new AAModel();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listStudents.setItems(aaModel.getAllStudents());
        comboClassList.setItems(aaModel.getAllClasses());
    }    
    
}
