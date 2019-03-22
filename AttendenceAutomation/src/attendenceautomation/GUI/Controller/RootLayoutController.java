/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class RootLayoutController implements Initializable
{
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnEdit;
    @FXML
    private Pane stackLogout;
    @FXML
    private Pane stackEdit;
    @FXML
    private Pane stackCal;
    @FXML
    private StackPane stackAll;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button btnStatistics;
    @FXML
    private ImageView imgLogo;
    @FXML
    private AnchorPane anchorAll;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            loadMainView();    
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }   
    
    
    /**
    * Load the main scene. 
    */
    private void loadMainView() throws IOException
    {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/AttendenceStudentView.fxml"));
        anchorAll.getChildren().clear();
        anchorAll.getChildren().add(pane1);
    }         
    
    
    /**
    * Load scenes according to which button is pressed.
    */
    @FXML
    private void loadView(ActionEvent event) throws IOException
    {
        if (event.getSource() == btnStatistics)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/AttendanceStatisticsView.fxml"));
            anchorAll.getChildren().clear();
            anchorAll.getChildren().add(pane);
        }
         if (event.getSource() == btnEdit)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/AttendanceEditView.fxml"));
            anchorAll.getChildren().clear();
            anchorAll.getChildren().add(pane);
        }

    }

    /**
     * Adds a mouseEvent to change scenes back to main scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickImage(MouseEvent event) throws IOException
    {
        Stage mainStage = (Stage) imgLogo.getScene().getWindow();
        loadMainView();
    }

    @FXML
    private void handleStudentLogout(ActionEvent event)
    {
        System.exit(0);
    }
    
    
    
}
