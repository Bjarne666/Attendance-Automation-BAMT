/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.View;

import attendenceautomation.AttendenceAutomation;
import attendenceautomation.GUI.Controller.AttendenceMainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class TestViewController implements Initializable
{

    
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCalender;
    @FXML
    private Pane stackLogout;
    @FXML
    private Pane stackEdit;
    @FXML
    private Pane stackCal;
    @FXML
    private StackPane stackAll;
    @FXML
    private AnchorPane anchorTest;
    
    AttendenceAutomation mainApp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void loadView(ActionEvent event) throws IOException
    {
        if (event.getSource() == btnCalender)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/AttendanceCalenderView.fxml"));
            anchorTest.getChildren().clear();
            anchorTest.getChildren().add(pane);
        }
         if (event.getSource() == btnEdit)
        {
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/AttendenceMainView1.fxml"));
            anchorTest.getChildren().clear();
            anchorTest.getChildren().add(pane1);    
        }
    }
    
    
    
}
