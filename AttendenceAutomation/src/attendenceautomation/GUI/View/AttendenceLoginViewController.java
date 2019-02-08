/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.View;

import attendenceautomation.AttendenceAutomation;
import com.sun.glass.ui.Window;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class AttendenceLoginViewController implements Initializable
{

    @FXML
    private TextField txtUserName;
    private AnchorPane anchorTest;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button btnLogin;
    

     public AttendenceLoginViewController()
     {
         
     }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, InterruptedException
    {
        if(txtUserName.getText().equals(" "))
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/testView.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AttendenceAutomation.class.getResource("/attendenceautomation/GUI/View/AttendenceMainView1.fxml"));
            anchorTest = (AnchorPane) loader.load();
              
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            
            stage.show();
            
            
            //Close this windows
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
        }
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }   
    
}
