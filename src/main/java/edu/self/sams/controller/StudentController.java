package edu.self.sams.controller;

import edu.self.sams.dto.StudentDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.StudentService;
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

public class StudentController implements Initializable {

    @FXML
    private TextField txtRegNo;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTeleNo;
    @FXML
    private TableView<StudentDto> tblStudent;
    @FXML
    private TableColumn<StudentDto,String> colRegNo;
    @FXML
    private TableColumn<StudentDto,String> colName;
    @FXML
    private TableColumn<StudentDto,String> colEmail;
    @FXML
    private TableColumn<StudentDto,String> colTeleNo;
    @FXML
    private AnchorPane ancStudent;

    private StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);

    public static void loadStudent() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(StudentController.class.getResource("/view/Student.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Student");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException{
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException{
        CourseController.loadCourse();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException{
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException{
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException{
        ScheduleClassController.loadScheduleClass();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();

    }

    public void goToReports(ActionEvent actionEvent) throws IOException{
        ReportController.loadReport();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();

    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        LoginController.loadLogin();
        Stage preScene = (Stage)ancStudent.getScene().getWindow();
        preScene.close();
    }

    public void clickSave(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String teleNo = txtTeleNo.getText().trim();

        StudentDto studentDto = new StudentDto(regNo,name,email,teleNo);

        try{
            String resp = studentService.saveStudent(studentDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickUpdate(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String teleNo = txtTeleNo.getText().trim();

        StudentDto studentDto = new StudentDto(regNo,name,email,teleNo);

        try{
            String resp = studentService.updateStudent(studentDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickDelete(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText().trim();
        try{
            String resp = studentService.deleteStudent(regNo);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickSearch(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText().trim();
        try{
            StudentDto studentDto = studentService.getStudent(regNo);
            if(studentDto!=null){
                txtRegNo.setText(studentDto.getRegNo());
                txtName.setText(studentDto.getName());
                txtEmail.setText(studentDto.getEmail());
                txtTeleNo.setText(studentDto.getTeleNo());
            }else{
                new Alert(Alert.AlertType.ERROR,"Student not found").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTeleNo.setCellValueFactory(new PropertyValueFactory<>("teleNo"));

        loadTable();
    }

    private void loadTable(){
        try{
            ArrayList<StudentDto> studentDtos = studentService.getStudents();
            ObservableList<StudentDto> list = FXCollections.observableArrayList(studentDtos);
            tblStudent.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtRegNo.clear();
        txtName.clear();
        txtEmail.clear();
        txtTeleNo.clear();
    }


    public void onTableClick(MouseEvent mouseEvent) {
        StudentDto selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtRegNo.setText(selectedItem.getRegNo());
            txtName.setText(selectedItem.getName());
            txtEmail.setText(selectedItem.getEmail());
            txtTeleNo.setText(selectedItem.getTeleNo());
        }
    }
}
