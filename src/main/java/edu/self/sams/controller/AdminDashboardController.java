package edu.self.sams.controller;

import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import edu.self.sams.service.custom.LecturerService;
import edu.self.sams.service.custom.StudentService;
import edu.self.sams.service.custom.SubjectService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private AnchorPane ancAdminDashboard;
    @FXML
    private Text txtTotStudents;
    @FXML
    private Text txtTotLecturers;
    @FXML
    private Text txtTotCourses;
    @FXML
    private Text txtTotSubjects;

    private StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);
    private LecturerService lecturerService = (LecturerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LECTURER);
    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);
    private SubjectService subjectService = (SubjectService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBJECT);

    public static void loadAdminDashboard() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(AdminDashboardController.class.getResource("/view/AdminDashboard.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Subjects");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException {
        CourseController.loadCourse();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException {
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException {
        StudentController.loadStudent();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException {
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException {
        ScheduleClassesController.loadScheduleClass();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToReports(ActionEvent actionEvent) throws IOException {
        ReportController.loadReport();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        LoginController.loadLogin();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int totalStudents = studentService.getTotalStudents();
            txtTotStudents.setText(String.valueOf(totalStudents));
        } catch (Exception e) {
            txtTotStudents.setText("0");
        }

        try {
            int totalLecturers = lecturerService.getTotalLecturers();
            txtTotLecturers.setText(String.valueOf(totalLecturers));
        } catch (Exception e) {
            txtTotLecturers.setText("0");
        }

        try {
            int totalCourses = courseService.getTotalCourses();
            txtTotCourses.setText(String.valueOf(totalCourses));
        } catch (Exception e) {
            txtTotCourses.setText("0");
        }

        try {
            int totalSubjects = subjectService.getTotalSubjects();
            txtTotSubjects.setText(String.valueOf(totalSubjects));
        } catch (Exception e) {
            txtTotSubjects.setText("0");
        }
    }
}
