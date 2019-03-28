/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Controller;

import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import attendenceautomation.GUI.Model.AAModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asvør
 */
public class AdminViewController implements Initializable
{

    @FXML
    private TableView<Student> tbViewStudent;
    @FXML
    private TableColumn<Student, String> colStudentName;
    @FXML
    private TableColumn<Student, String> colStudentEmail;
    @FXML
    private TableColumn<Student, String> colStudentAbsence;
    @FXML
    private TableView<Teacher> tbViewTeacher;
    @FXML
    private TableColumn<Teacher, String> colTeacherName;
    @FXML
    private TableColumn<Teacher, String> colTeacherEmail;
    @FXML
    private TableView<SchoolClass> tbViewClass;
    @FXML
    private TableColumn<SchoolClass, String> colClassName;
    @FXML
    private AnchorPane ancClassView;
    @FXML
    private AnchorPane ancAdminView;
    @FXML
    private AnchorPane ancStudentView;
    @FXML
    private AnchorPane ancTeacherView;
    @FXML
    private ImageView imgLogo;
    
    private Person user;
    
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
            tbViewStudent.setItems(aaModel.getAllStudents());
            tbViewTeacher.setItems(aaModel.getAllTeachers());
            tbViewClass.setItems(aaModel.getAllClasses());
            loadMainView();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(attendenceautomation.GUI.Controller.AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Student data for tableview
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colStudentAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
        
        //Teacher data for tableview
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeacherEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        //School Class data for tableview
        colClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
    }    

    @FXML
    private void clickImage(MouseEvent event) throws IOException
    {
        System.out.println("picture clicked");
        Stage mainStage = (Stage) imgLogo.getScene().getWindow();
        loadMainView();
        ancClassView.setVisible(false);
        ancStudentView.setVisible(false);
        
        tbViewStudent.getItems().clear();
    }
    
    private void loadMainView() throws IOException
    {
        ancAdminView.setVisible(false);
        ancClassView.setVisible(false);
        ancStudentView.setVisible(false);
        ancAdminView.setVisible(true);
        ancAdminView.toFront();

    }

    @FXML
    private void studentOverview(ActionEvent event)
    {
        ancTeacherView.setVisible(false);
        ancClassView.setVisible(false);
        ancStudentView.setVisible(true);
        ancStudentView.toFront();
    }

    @FXML
    private void teacherOverview(ActionEvent event)
    {
        ancStudentView.setVisible(false);
        ancClassView.setVisible(false);
        ancTeacherView.setVisible(true);
        ancTeacherView.toFront();
    }

    @FXML
    private void classOverview(ActionEvent event)
    {
        ancStudentView.setVisible(false);
        ancTeacherView.setVisible(false);
        ancClassView.setVisible(true);
        ancClassView.toFront();
    }

    @FXML
    private void adminLogout(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void addStudent(ActionEvent event)
    {
    }

    @FXML
    private void deleteStudent(ActionEvent event)
    {
    }

    @FXML
    private void editStudent(ActionEvent event)
    {
    }

    @FXML
    private void addTeacher(ActionEvent event)
    {
    }

    @FXML
    private void deleteTeacher(ActionEvent event)
    {
    }

    @FXML
    private void editTeacher(ActionEvent event)
    {
    }

    @FXML
    private void addClass(ActionEvent event)
    {
    }

    @FXML
    private void deleteClass(ActionEvent event)
    {
    }

    @FXML
    private void editClass(ActionEvent event)
    {
    }
    
    public void setUser(Person userToSet)
    {
        user = userToSet;
    }
}
