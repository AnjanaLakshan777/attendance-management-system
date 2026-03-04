package edu.self.sams.controller;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.EnrollmentDto;
import edu.self.sams.dto.StudentDto;
import edu.self.sams.dto.TblEnrollmentDto;
import edu.self.sams.entity.CourseEntity;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import edu.self.sams.service.custom.EnrollmentService;
import edu.self.sams.service.custom.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private TableView<TblEnrollmentDto> tblEnrollment;
    @FXML
    private TableColumn<TblEnrollmentDto, String> colRegNo;
    @FXML
    private TableColumn<TblEnrollmentDto, String> colStudentName;
    @FXML
    private TableColumn<TblEnrollmentDto, String> colCourseName;
    @FXML
    private TableColumn<TblEnrollmentDto, Integer> colBatch;
    @FXML
    private TableColumn<TblEnrollmentDto, String> colStatus;

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
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        try{
            ArrayList<TblEnrollmentDto> tblEnrollmentDtos = enrollmentService.findEnrollmentsByCourseCode(courseCode);
            ObservableList<TblEnrollmentDto> list = FXCollections.observableArrayList(tblEnrollmentDtos);
            tblEnrollment.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void searchStudent(ActionEvent actionEvent) {
        String regNo = comboStudent.getValue().toString().substring(0,5);
        try{
            ArrayList<TblEnrollmentDto> tblEnrollmentDtos = enrollmentService.findEnrollmentsByStudentRegNo(regNo);
            ObservableList<TblEnrollmentDto> list = FXCollections.observableArrayList(tblEnrollmentDtos);
            tblEnrollment.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            loadTable();
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
            loadTable();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();

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
            loadTable();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    private void loadTable(){
        try{
            ArrayList<TblEnrollmentDto> tblEnrollmentDtos = enrollmentService.getAllEnrollments();
            ObservableList<TblEnrollmentDto> list = FXCollections.observableArrayList(tblEnrollmentDtos);
            tblEnrollment.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onTableClick(MouseEvent mouseEvent) {
        TblEnrollmentDto selectedItem = tblEnrollment.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            comboCourse.getSelectionModel().select(selectedItem.getCourseCode()+"-"+selectedItem.getCourseName());
            comboStudent.getSelectionModel().select(selectedItem.getRegNo()+"-"+selectedItem.getStudentName());
            txtBatch.setText(String.valueOf(selectedItem.getBatch()));
            comboStatus.getSelectionModel().select(selectedItem.getStatus());
        }
    }
}
