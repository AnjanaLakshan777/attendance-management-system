package edu.self.sams.controller;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    private TextField txtCourseCode;
    @FXML
    private TextField txtCourseName;
    @FXML
    private TextField txtDuration;
    @FXML
    private AnchorPane ancCourse;
    @FXML
    private TableView<CourseDto> tblCourse;
    @FXML
    private TableColumn<CourseDto,String> colCourseCode;
    @FXML
    private TableColumn<CourseDto,String> colCourseName;
    @FXML
    private TableColumn<CourseDto,String> colDuration;

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);

    public static void loadCourse() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(CourseController.class.getResource("/view/Course.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Courses");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException {
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException {
        StudentController.loadStudent();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException {
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException {
        ScheduleClassController.loadScheduleClass();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void goToReports(ActionEvent actionEvent) throws IOException {
        ReportController.loadReport();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        LoginController.loadLogin();
        Stage preScene = (Stage)ancCourse.getScene().getWindow();
        preScene.close();
    }

    public void clickSave() throws Exception {
        String courseCode = txtCourseCode.getText().trim();
        String courseName = txtCourseName.getText().trim();
        String duration = txtDuration.getText().trim();

        CourseDto courseDto = new CourseDto(courseCode, courseName, duration);

        try{
            String resp = courseService.saveCourse(courseDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickUpdate() throws Exception {
        String courseCode = txtCourseCode.getText().trim();
        String courseName = txtCourseName.getText().trim();
        String duration = txtDuration.getText().trim();

        CourseDto courseDto = new CourseDto(courseCode, courseName, duration);

        try{
            String resp = courseService.updateCourse(courseDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickDelete() throws Exception {
        String courseCode = txtCourseCode.getText().trim();
        try{
            String resp = courseService.deleteCourse(courseCode);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        loadTable();
    }

    private void loadTable() {
        try{
            ArrayList<CourseDto> courseDtos = courseService.getCourses();
            ObservableList<CourseDto> list = FXCollections.observableArrayList(courseDtos);
            tblCourse.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtCourseCode.clear();
        txtCourseName.clear();
        txtDuration.clear();
    }

    public void onTableClick(MouseEvent mouseEvent) {
        CourseDto selectedItem = tblCourse.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtCourseCode.setText(selectedItem.getCourseCode());
            txtCourseName.setText(selectedItem.getCourseName());
            txtDuration.setText(selectedItem.getDuration());
        }
    }

    public void clickSearch(ActionEvent actionEvent) {
        String courseCode = txtCourseCode.getText().trim();
        try{
            CourseDto courseDto = courseService.getCourse(courseCode);
            if(courseDto!=null){
                txtCourseCode.setText(courseDto.getCourseCode());
                txtCourseName.setText(courseDto.getCourseName());
                txtDuration.setText(courseDto.getDuration());
            }else{
                new Alert(Alert.AlertType.ERROR,"Course not found").showAndWait();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
