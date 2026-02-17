package edu.self.sams.controller;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.SubjectService;
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

public class SubjectController implements Initializable {

    @FXML
    private TextField txtSubjectCode;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private AnchorPane ancSubject;
    @FXML
    private TableView<SubjectDto> tblSubject;
    @FXML
    private TableColumn<SubjectDto,String> colSubjectCode;
    @FXML
    private TableColumn<SubjectDto,String> colSubjectName;

    private SubjectService subjectService = (SubjectService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBJECT);

    public static void loadSubject() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(SubjectController.class.getResource("/view/Subject.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Subjects");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException{
        CourseController.loadCourse();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException{
        StudentController.loadStudent();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException{
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException{
        ScheduleClassController.loadScheduleClass();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void goToReports(ActionEvent actionEvent) throws IOException{
        ReportController.loadReport();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        LoginController.loadLogin();
        Stage preScene = (Stage)ancSubject.getScene().getWindow();
        preScene.close();
    }


    public void clickSave(ActionEvent actionEvent) {
        String subjectCode = txtSubjectCode.getText().trim();
        String subjectName = txtSubjectName.getText().trim();

        SubjectDto subjectDto = new SubjectDto(subjectCode, subjectName);

        try{
            String resp = subjectService.saveSubject(subjectDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickUpdate(ActionEvent actionEvent) {
        String subjectCode = txtSubjectCode.getText().trim();
        String subjectName = txtSubjectName.getText().trim();

        SubjectDto subjectDto = new SubjectDto(subjectCode, subjectName);

        try{
            String resp = subjectService.updateSubject(subjectDto);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void clickDelete(ActionEvent actionEvent) {
        String subjectCode = txtSubjectCode.getText().trim();
        try{
            String resp = subjectService.deleteSubject(subjectCode);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    private void loadTable(){
        try{
            ArrayList<SubjectDto> subjectDtos = subjectService.getSubjects();
            ObservableList<SubjectDto> list = FXCollections.observableArrayList(subjectDtos);
            tblSubject.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields(){
        txtSubjectCode.clear();
        txtSubjectName.clear();
    }

    public void clickSearch(ActionEvent actionEvent) {
        String subjectCode = txtSubjectCode.getText().trim();
        try{
            SubjectDto subjectDto = subjectService.getSubject(subjectCode);
            if(subjectDto != null){
                txtSubjectCode.setText(subjectDto.getSubjectCode());
                txtSubjectName.setText(subjectDto.getSubjectName());
            }else{
                new Alert(Alert.AlertType.ERROR,"Subject not found").showAndWait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        loadTable();
    }

    public void onTableClick(MouseEvent mouseEvent) {
        SubjectDto selectedItem = tblSubject.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtSubjectCode.setText(selectedItem.getSubjectCode());
            txtSubjectName.setText(selectedItem.getSubjectName());
        }
    }

}
