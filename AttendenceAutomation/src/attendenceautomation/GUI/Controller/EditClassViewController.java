/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class EditClassViewController implements Initializable
{

    @FXML
    private JFXTextField txtClassName;
    
    private SchoolClass chosenClass;
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
    private void btnEditClass(ActionEvent event)
    {
        
        if (txtClassName.getText().length() == 0)
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "You have to fill out name field!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
 
        String className = this.txtClassName.getText();
      
        aaModel.editSchoolClassName(className,chosenClass.getId() );
    }
    public void setChosenClass(SchoolClass chosenClass)
    {
     this.chosenClass = chosenClass;   
    }
}
