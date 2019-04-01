/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
public class AddClassViewController implements Initializable
{

    @FXML
    private JFXTextField txtClassName;
    
    AAModel aaModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            aaModel = new AAModel();
        } catch (IOException ex)
        {
            Logger.getLogger(AddClassViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnAddClass(ActionEvent event)
    {
        if (txtClassName.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out the name field!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
 
        String className = this.txtClassName.getText();

        System.out.println("new class");
        
        SchoolClass newClass= new SchoolClass(0, className);
        aaModel.addClass(newClass);
        
        
    }
    
}