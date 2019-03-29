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
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    @FXML
    private JFXButton btnAddStudent;

    private Person user;

    AAModel aaModel;
    @FXML
    private JFXButton btnAddTeacher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
//            aaModel = new AAModel();
            loadMainView();
        } catch (IOException ex)
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
        tbViewStudent.setItems(aaModel.getAllStudents());
        ancTeacherView.setVisible(false);
        ancClassView.setVisible(false);
        ancStudentView.setVisible(true);
        ancStudentView.toFront();
    }

    @FXML
    private void teacherOverview(ActionEvent event)
    {
        tbViewTeacher.setItems(aaModel.getAllTeachers());
        ancStudentView.setVisible(false);
        ancClassView.setVisible(false);
        ancTeacherView.setVisible(true);
        ancTeacherView.toFront();
    }

    @FXML
    private void classOverview(ActionEvent event)
    {
        tbViewClass.setItems(aaModel.getAllClasses());
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
    private void addStudent(ActionEvent event) throws IOException
    {
        Stage primeStage = (Stage) btnAddStudent.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/addStudentView.fxml"));
        Parent root = loader.load();

        AddStudentViewController addStudentController = loader.getController();
        addStudentController.setModel(aaModel);
        addStudentController.setComboBox();
        Stage stageAddMovie = new Stage();
        stageAddMovie.setScene(new Scene(root));

        stageAddMovie.initModality(Modality.WINDOW_MODAL);
        stageAddMovie.initOwner(primeStage);
        stageAddMovie.show();
    }

    @FXML
    private void deleteStudent(ActionEvent event)
    {
        if (tbViewStudent.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a student to delete", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected student?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            aaModel.deleteStudent(tbViewStudent.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void editStudent(ActionEvent event)
    {

    }

    @FXML
    private void addTeacher(ActionEvent event) throws IOException
    {
        Stage primeStage = (Stage) btnAddTeacher.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/AddTeacherView.fxml"));
        Parent root = loader.load();

        AddTeacherViewController addTeacherController = loader.getController();
        addTeacherController.setModel(aaModel);

        Stage stageAddMovie = new Stage();
        stageAddMovie.setScene(new Scene(root));

        stageAddMovie.initModality(Modality.WINDOW_MODAL);
        stageAddMovie.initOwner(primeStage);
        stageAddMovie.show();
    }

    @FXML
    private void deleteTeacher(ActionEvent event)
    {
        if (tbViewTeacher.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a teacher to delete", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected teacher?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            aaModel.deleteTeacher(tbViewTeacher.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void editTeacher(ActionEvent event)
    {
    }

    @FXML
    private void addClass(ActionEvent event) throws IOException
    {
        Stage primeStage = (Stage) btnAddStudent.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendenceautomation/GUI/View/AddClassView.fxml"));
        Parent root = loader.load();

        Stage stageAddMovie = new Stage();
        stageAddMovie.setScene(new Scene(root));

        stageAddMovie.initModality(Modality.WINDOW_MODAL);
        stageAddMovie.initOwner(primeStage);
        stageAddMovie.show();
    }

    @FXML
    private void deleteClass(ActionEvent event)
    {
        if (tbViewClass.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a class to delete", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected class?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            aaModel.deleteClass(tbViewClass.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void editClass(ActionEvent event)
    {
        if (!tbViewClass.getSelectionModel().isEmpty())
        {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Edit Class Name");
            dialog.setContentText("Enter class name");

            Optional<String> result = dialog.showAndWait();

            String className = "";

            if (result.isPresent())
            {
                if (className.equals("") || className.equals(" "))
                {
                    return;
                }
                tbViewClass.getSelectionModel().getSelectedItem();
                className = result.get();
                System.out.println("new class");
                SchoolClass editClass = new SchoolClass(0, className);
                aaModel.editSchoolClassName(editClass);
            }

        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a class to edit", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setUser(Person userToSet)
    {
        user = userToSet;
    }

    void setModel(AAModel aModel)
    {
        aaModel = aModel;
    }
}
