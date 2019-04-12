/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Person;
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
    
    private Person user;
    
    /**
     * Constructor for controller.
     * Makes a new instance of the model
     * which is then used throughout the application
     * @throws IOException 
     */
    public AttendenceLoginViewController() throws IOException
    {
        aModel = new AAModel();
    }

    //handles student login
    private void handleStudentLogin() throws IOException
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/StudentView.fxml"));
            AnchorPane pane = loader.load();

            StudentViewController sViewController = loader.getController();
            sViewController.setModel(aModel);
            sViewController.setUser(user);
            sViewController.setLabels();
            sViewController.studentBarChart();
            sViewController.buildPieChart();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));

            stage.show();

            //Close this window
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
    }

    
    /**
     * Handles login and chooses a view to open depending on the type of user
     * @param event
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException 
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException, InterruptedException, SQLException
    {
        user = aModel.login(txtUserName.getText(), txtPassword.getText());
        int student = 1;
        int teacher = 2;
        int admin = 3;
        
        if (user == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect user name or password");
            alert.showAndWait();
            return;
        }
         if (user.isAUser() == student)
        {
            handleStudentLogin();
        }
        if (user.isAUser() == teacher)
        {
            handleTeacherLogin();
        }   
        if (user.isAUser() == admin)
        {
            handleAdminLogin();
        }   
    }
    
    /**
     * Handles the teacher part of login
     * @throws IOException 
     */
    private void handleTeacherLogin() throws IOException
    {
        //handles teacher login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/TeacherView.fxml"));
            AnchorPane pane = loader.load();
            
            //sets up the view
            TeacherViewController tViewController = loader.getController();
            tViewController.setModel(aModel);
            tViewController.setUser(user);
            tViewController.setLabels();
            tViewController.setClassCombo();
    
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));

            stage.show();

            //Close this window
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
    }
    
    /**
     * Handles login for an admin
     * @throws IOException 
     */
    private void handleAdminLogin() throws IOException
    {
            //handles admin login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/AdminView.fxml"));
            AnchorPane pane = loader.load();

            AdminViewController aViewController = loader.getController();
            aViewController.setModel(aModel);
            aViewController.setUser(user);
            aViewController.setLabels();
            aViewController.setComboBoxItems();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));

            stage.show();

            //Close this window
            Stage currentWindows = (Stage) btnLogin.getScene().getWindow();
            currentWindows.close();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
