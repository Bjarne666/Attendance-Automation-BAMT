/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML
    private JFXButton btnSaveStudent;
    @FXML
    private JFXComboBox<SchoolClass> cbClass;
    
    AAModel aaModel;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
    }   
    
    /**
     * Sets the model
     * @param aaModel 
     */
    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
    }
    
    /**
     * Fills the combobox with a list of all classes
     */
    public void setComboBox()
    {
         cbClass.setItems(aaModel.getAllClasses());
    }
    
    
     /**
     * Takes the user input from the textfields
     * and uses that to add a new student.
     * Furthermore, the student is added to the chosen school class
     * @param event 
     */
    @FXML
    private void btnAddStudent(ActionEvent event)
    {
        Stage primeStage = (Stage) btnSaveStudent.getScene().getWindow();
        
        String name = this.txtName.getText() + " " + this.txtLastName.getText();
        String email = this.txtEmail.getText();
        String password = this.txtPassword.getText();
        
        if (txtName.getText().length() == 0 || txtLastName.getText().length() == 0 || txtEmail.getText().length() == 0 || txtPassword.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out all fields!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (cbClass.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have choose a class!", ButtonType.OK);
            alert.showAndWait();
        }
        
        if(cbClass.getSelectionModel().getSelectedItem() != null)
        {
            System.out.println("new student");
            SchoolClass chosenClass = cbClass.getSelectionModel().getSelectedItem();
            Student student = new Student(0, name, email, password);
            aaModel.addStudent(student, chosenClass);
            aaModel.getAllStudents();
        }
        primeStage.close();
    }
    
}
