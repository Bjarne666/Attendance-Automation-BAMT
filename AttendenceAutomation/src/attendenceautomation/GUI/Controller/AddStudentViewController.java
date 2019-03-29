/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class AddStudentViewController implements Initializable
{

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;

    AAModel aaModel;
    @FXML
    private JFXButton btnSaveStudent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }   
    
    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
    }

    @FXML
    private void btnAddStudent(ActionEvent event)
    {
//        Stage primeStage = (Stage) btnAddStudent(event).getScene().getWindow();

        if (txtName.getText().length() == 0 || txtLastName.getText().length() == 0 || txtEmail.getText().length() == 0 || txtPassword.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out all fields!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        String name = this.txtName.getText() + " " + this.txtLastName.getText();
        String email = this.txtEmail.getText();
        String password = this.txtPassword.getText();
      
//        Student newStudent = new Student(0, name, email, password);
        System.out.println("new student");
        
        Student student = new Student(0, name, email, password);
        aaModel.addStudent(student);
        aaModel.getAllStudents();
//        primeStage.close();
    }
    
}
