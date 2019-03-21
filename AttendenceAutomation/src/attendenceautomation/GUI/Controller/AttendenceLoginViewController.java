/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Person;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import attendenceautomation.GUI.Model.AAModel;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Button btnLogin;
    @FXML
    private JFXPasswordField txtPassword;
    
    private AAModel aModel;

    public AttendenceLoginViewController() throws IOException
    {
    aModel = new AAModel();
    }

    //handles student login
    private void handleStudentLogin() throws IOException
    {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/RootLayout.fxml"));
            FXMLLoader loader = new FXMLLoader();

            Stage stage = new Stage();
            stage.setScene(new Scene(pane));

            stage.show();

            //Close this window
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
        
    }

    

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, InterruptedException, SQLException
    {
        Person user = aModel.login(txtUserName.getText(), txtPassword.getText());
        if (user.IsAStudent())
        {
            handleStudentLogin();
            return;
        }
        if (!user.IsAStudent())
        {
            handleTeacherLogin();
            return;
        }
        else
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect user name or password");
            alert.showAndWait();
        }
    }

    private void handleTeacherLogin() throws IOException
    {
        //handles teacher login
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendenceautomation/GUI/View/teacherMainView.fxml"));
            FXMLLoader loader = new FXMLLoader();

            Stage stage = new Stage();
            stage.setScene(new Scene(pane));

            stage.show();

            //Close this window
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
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
