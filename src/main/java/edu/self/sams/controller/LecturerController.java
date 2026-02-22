package edu.self.sams.controller;

import edu.self.sams.dto.LecturerDto;
import edu.self.sams.dto.StudentDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.LecturerService;
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

public class LecturerController implements Initializable {

    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTeleNo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane ancLecturer;
    @FXML
    private TableView<LecturerDto> tblLecturer;
    @FXML
    private TableColumn<LecturerDto, String> colUserId;
    @FXML
    private TableColumn<LecturerDto, String> colName;
    @FXML
    private TableColumn<LecturerDto, String> colEmail;
    @FXML
    private TableColumn<LecturerDto, String> colTeleNo;

    LecturerService lecturerService = (LecturerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LECTURER);

    public static void loadLecture() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(LecturerController.class.getResource("/view/Lecturer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Courses");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException{
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void goToCourse(ActionEvent actionEvent) throws IOException{
        CourseController.loadCourse();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void goToSubject(ActionEvent actionEvent) throws IOException{
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void goToStudent(ActionEvent actionEvent) throws IOException{
        StudentController.loadStudent();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException{
        ScheduleClassController.loadScheduleClass();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void goToReport(ActionEvent actionEvent) throws IOException{
        ReportController.loadReport();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        LoginController.loadLogin();
        Stage preScene = (Stage)ancLecturer.getScene().getWindow();
        preScene.close();
    }

    public void clickOnSave(ActionEvent actionEvent) {
        String userId = txtUserId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String teleNo = txtTeleNo.getText().trim();
        String password = txtPassword.getText().trim();

        LecturerDto lecturerDto = new LecturerDto(userId,name,email,teleNo,password);

        try{
            String resp = lecturerService.saveLecturer(lecturerDto);
            new Alert(Alert.AlertType.INFORMATION, resp, ButtonType.OK).show();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickOnUpdate(ActionEvent actionEvent) {
        String userId = txtUserId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String teleNo = txtTeleNo.getText().trim();
        String password = txtPassword.getText().trim();

        LecturerDto lecturerDto = new LecturerDto(userId,name,email,teleNo,password);

        try{
            String resp = lecturerService.updateLecturer(lecturerDto);
            new Alert(Alert.AlertType.INFORMATION, resp, ButtonType.OK).show();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickOnDelete(ActionEvent actionEvent) {
        String userId = txtUserId.getText().trim();
        try{
            String resp = lecturerService.deleteLecturer(userId);
            new Alert(Alert.AlertType.INFORMATION, resp, ButtonType.OK).show();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new  Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickOnSearch(ActionEvent actionEvent) {
        String userId = txtUserId.getText().trim();
        try{
            LecturerDto lecturerDto = lecturerService.getLecturer(userId);
            if(lecturerDto != null){
                txtUserId.setText(lecturerDto.getUserId());
                txtName.setText(lecturerDto.getName());
                txtEmail.setText(lecturerDto.getEmail());
                txtTeleNo.setText(lecturerDto.getTeleNo());
                txtPassword.setText(lecturerDto.getPassword());
            }else{
                new  Alert(Alert.AlertType.ERROR,"Lecturer not found").showAndWait();
            }
        } catch (Exception e) {
            new  Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTeleNo.setCellValueFactory(new PropertyValueFactory<>("teleNo"));

        loadTable();
    }

    private void loadTable(){
        try{
            ArrayList<LecturerDto> lecturerDtos = (ArrayList<LecturerDto>) lecturerService.getLecturers();
            ObservableList<LecturerDto> list = FXCollections.observableArrayList(lecturerDtos);
            tblLecturer.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields(){
        txtUserId.clear();
        txtName.clear();
        txtEmail.clear();
        txtTeleNo.clear();
        txtPassword.clear();
    }

    public void onTableClick(MouseEvent mouseEvent) {
        LecturerDto lecturerDto = tblLecturer.getSelectionModel().getSelectedItem();
        if(lecturerDto != null){
            txtUserId.setText(lecturerDto.getUserId());
            txtName.setText(lecturerDto.getName());
            txtEmail.setText(lecturerDto.getEmail());
            txtTeleNo.setText(lecturerDto.getTeleNo());
            txtPassword.setText(lecturerDto.getPassword());
        }
    }
}
