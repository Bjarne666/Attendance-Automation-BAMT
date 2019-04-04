/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXButton;
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
public class EditStudentViewController implements Initializable
{

    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnSaveEdit;

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
        
    }    
    
    public void setModel(AAModel aaModel) 
    {
        this.aaModel = aaModel;
    }

    @FXML
    private void btnEditStudent(ActionEvent event)
    {
        Stage primeStage = (Stage) btnSaveEdit.getScene().getWindow();
        
        if (txtFirstName.getText().length() == 0 || txtLastName.getText().length() == 0 || txtEmail.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out nall fields!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String fName = this.txtFirstName.getText();
        String lName = this.txtLastName.getText();
        String email = this.txtEmail.getText();
         
        aaModel.editPerson(fName, lName, email, chosenStudent.getId());
         
        primeStage.close();
    }

    public void setChosenStudent(Student chosenStudent)
    {
        this.chosenStudent = chosenStudent;
    }
}
