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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TeacherMainViewController implements Initializable
{

    @FXML
    private TableView<Student> tbViewStudents;
    @FXML
    private JFXComboBox<SchoolClass> comboClassList;

    AAModel aaModel = new AAModel();
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colEmail;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tbViewStudents.setItems(aaModel.getAllStudents());
        comboClassList.setItems(aaModel.getAllClasses());
        
//        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        colName.setCellValueFactory(cellData -> cellData.getValue().getName());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmail());
    }    
    
    public TeacherMainViewController()
    {
        
    }

    @FXML
    private void handleTeacherLogout(ActionEvent event)
    {
    }
    
    
    
}
