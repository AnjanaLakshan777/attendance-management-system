package edu.self.sams.controller;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.EnrollmentDto;
import edu.self.sams.dto.StudentDto;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import edu.self.sams.service.custom.EnrollmentService;
import edu.self.sams.service.custom.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnrollToCoursesController implements Initializable {
    @FXML
    private ComboBox comboCourse;
    @FXML
    private ComboBox comboStudent;
    @FXML
    private TextField txtBatch;
    @FXML
    private ComboBox comboStatus;
    @FXML
    private AnchorPane ancEnrollToCourse;

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);
    private StudentService  studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);
    private EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ENROLLMENT);

    public static void loadEnrollToCourses() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(EnrollToCoursesController.class.getResource("/view/EnrollToCourses.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Enroll to Courses");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void backToStudent(ActionEvent actionEvent) throws IOException {
        StudentController.loadStudent();
        Stage preScene = (Stage) ancEnrollToCourse.getScene().getWindow();
        preScene.close();
    }

    public void searchCourse(ActionEvent actionEvent) {
    }

    public void searchStudent(ActionEvent actionEvent) {
    }

    public void clickEnroll(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String regNo = comboStudent.getValue().toString().substring(0,5);
        int batch = Integer.parseInt(txtBatch.getText());
        String status = comboStatus.getValue().toString();

        EnrollmentDto enrollmentDto = new EnrollmentDto(courseCode, regNo, batch, status);

        try{
            String resp = enrollmentService.enrollStudent(enrollmentDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickUnenroll(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String regNo = comboStudent.getValue().toString().substring(0,5);
        try{
            String resp = enrollmentService.deleteEnrollment(courseCode,regNo);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ArrayList<CourseDto> courses = courseService.getCourses();
            if(courses != null){
                for(CourseDto course : courses){
                    comboCourse.getItems().add(course.getCourseCode()+"-"+course.getCourseName());
                }
            }
            ArrayList<StudentDto> students = studentService.getStudents();
            if(students != null){
                for(StudentDto student : students){
                    comboStudent.getItems().add(student.getRegNo()+"-"+student.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickUpdate(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String regNo = comboStudent.getValue().toString().substring(0,5);
        int batch = Integer.parseInt(txtBatch.getText());
        String status = comboStatus.getValue().toString();

        EnrollmentDto enrollmentDto = new EnrollmentDto(courseCode, regNo, batch, status);

        try{
            String resp = enrollmentService.updateEnrollment(enrollmentDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }
}
