/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Teacher;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXButton;
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
public class AddTeacherViewController implements Initializable
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
    private JFXButton btnSaveTeacher;

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
     * Takes the user input from the textfields
     * and uses that to add a new teacher 
     * @param event 
     */
    @FXML
    private void btnAddTeacher(ActionEvent event)
    {
        Stage primeStage = (Stage) btnSaveTeacher.getScene().getWindow();
        
        if (txtName.getText().length() == 0 || txtLastName.getText().length() == 0 || txtEmail.getText().length() == 0 || txtPassword.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out all fields!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
 
        String name = this.txtName.getText() + " " + this.txtLastName.getText();
        String email = this.txtEmail.getText();
        String password = this.txtPassword.getText();
      
        System.out.println("new teacher");
        
        Teacher newTeacher = new Teacher(0, name, email, password);
        aaModel.addTeacher(newTeacher);

        primeStage.close();
    }
}
