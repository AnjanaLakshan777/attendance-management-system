package edu.self.sams.controller;

import edu.self.sams.dto.*;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import edu.self.sams.service.custom.LecturerService;
import edu.self.sams.service.custom.ScheduleClassService;
import edu.self.sams.service.custom.SubjectService;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleClassesController implements Initializable {

    @FXML
    private Label lblClassId;
    @FXML
    private AnchorPane ancScheduleClass;
    @FXML
    private ComboBox comboCourse;
    @FXML
    private ComboBox comboSubject;
    @FXML
    private ComboBox comboLecturer;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtEndTime;
    @FXML
    private TextField txtBatch;
    @FXML
    private TableView<ScheduleClassDto> tblScheduleClass;
    @FXML
    private TableColumn<ScheduleClassDto,String> colClassId;
    @FXML
    private TableColumn<ScheduleClassDto,String> colCourse;
    @FXML
    private TableColumn<ScheduleClassDto,String> colSubject;
    @FXML
    private TableColumn<ScheduleClassDto,String> colLecturer;
    @FXML
    private TableColumn<ScheduleClassDto,String> colDate;
    @FXML
    private TableColumn<ScheduleClassDto,String> colStartTime;
    @FXML
    private TableColumn<ScheduleClassDto,String> colEndTime;
    @FXML
    private TableColumn<ScheduleClassDto,Integer> colBatch;
    @FXML
    private TableColumn<ScheduleClassDto,String> colStatus;

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);
    private SubjectService subjectService = (SubjectService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBJECT);
    private LecturerService lecturerService = (LecturerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LECTURER);
    private ScheduleClassService scheduleClassService = (ScheduleClassService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SCHEDULECLASS);

    public static void loadScheduleClass() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(ScheduleClassesController.class.getResource("/view/ScheduleClasses.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Schedule Class");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException{
        CourseController.loadCourse();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException{
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException{
        StudentController.loadStudent();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException{
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void goToReports(ActionEvent actionEvent) throws IOException{
        ReportController.loadReport();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();

    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        LoginController.loadLogin();
        Stage preScene = (Stage)ancScheduleClass.getScene().getWindow();
        preScene.close();
    }

    public void clickSave(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String courseName = comboCourse.getValue().toString().substring(5).trim();
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        String subjectName = comboSubject.getValue().toString().substring(7).trim();
        String userId = comboLecturer.getValue().toString().substring(0,4);
        String date = txtDate.getText().toString().trim();
        String startTime = txtStartTime.getText().toString().trim();
        String endTime = txtEndTime.getText().toString().trim();
        int batch = Integer.parseInt(txtBatch.getText());


        boolean isDateTimeValid = dateTimeValidation(date,startTime,endTime);

        if(isDateTimeValid){
            try{
                ScheduleClassDto scheduleClassDto = new ScheduleClassDto(null,courseCode,courseName,subjectCode,subjectName,userId,date,startTime,endTime,batch,"scheduled");
                String resp = scheduleClassService.saveScheduleClass(scheduleClassDto);
                new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
                loadTable();
                clearFields();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please enter date and time in valid format").showAndWait();
        }
    }

    public void clickUpdate(ActionEvent actionEvent) {
        String classId = lblClassId.getText().toString();
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String courseName = comboCourse.getValue().toString().substring(5).trim();
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        String subjectName = comboSubject.getValue().toString().substring(7).trim();
        String userId = comboLecturer.getValue().toString().substring(0,4);
        String date = txtDate.getText().toString().trim();
        String startTime = txtStartTime.getText().toString().trim();
        String endTime = txtEndTime.getText().toString().trim();
        int batch = Integer.parseInt(txtBatch.getText());

        boolean isDateTimeValid = dateTimeValidation(date,startTime,endTime);

        if(isDateTimeValid){
            try{
                ScheduleClassDto scheduleClassDto = new ScheduleClassDto(classId,courseCode,courseName,subjectCode,subjectName,userId,date,startTime,endTime,batch,"scheduled");
                String resp = scheduleClassService.updateScheduleClass(scheduleClassDto);
                new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
                loadTable();
                clearFields();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please enter date and time in valid format").showAndWait();
        }
    }

    public void clickDelete(ActionEvent actionEvent) {
        String classId = lblClassId.getText().toString();
        try{
            String resp = scheduleClassService.deleteScheduleClass(classId);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void onTableClick(MouseEvent mouseEvent) throws Exception {
        ScheduleClassDto selectedItem = tblScheduleClass.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            lblClassId.setText(selectedItem.getClassId());
            comboCourse.setValue(selectedItem.getCourseCode()+"-"+courseService.getCourse(selectedItem.getCourseCode()).getCourseName());
            comboSubject.setValue(selectedItem.getSubjectCode()+"-"+subjectService.getSubject(selectedItem.getSubjectCode()).getSubjectName());
            comboLecturer.setValue(selectedItem.getUserId()+"-"+lecturerService.getLecturer(selectedItem.getUserId()).getName());
            txtDate.setText(selectedItem.getDate());
            txtStartTime.setText(selectedItem.getStartTime());
            txtEndTime.setText(selectedItem.getEndTime());
            txtBatch.setText(String.valueOf(selectedItem.getBatch()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        colLecturer.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try{
            ArrayList<CourseDto> courses = courseService.getCourses();
            if(courses != null){
                for(CourseDto course : courses){
                    comboCourse.getItems().add(course.getCourseCode()+"-"+course.getCourseName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        loadTable();

    }

    public void clickCourseFill(ActionEvent actionEvent) {
        comboSubject.getItems().clear();
        try{
            String courseCode = comboCourse.getValue().toString().substring(0,4);
            ArrayList<CourseSubjectDto> courseSubjects = courseService.findSubjectsByCourseCode(courseCode);
            if(courseSubjects != null){
                for(CourseSubjectDto courseSubject : courseSubjects){
                    comboSubject.getItems().add(courseSubject.getSubjectCode()+"-"+courseSubject.getSubjectName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void clickSubjectFill(ActionEvent actionEvent) {
        comboLecturer.getItems().clear();
        try{
            String subjectCode = comboSubject.getValue().toString().substring(0,6);
            ArrayList<SubjectLecturerDto> subjectLecturers = subjectService.findLecturersBySubjectCode(subjectCode);
            if(subjectLecturers != null){
                for(SubjectLecturerDto subjectLecturer : subjectLecturers){
                    comboLecturer.getItems().add(subjectLecturer.getUserId()+"-"+subjectLecturer.getLecturerName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean dateTimeValidation(String date,String startTime,String endTime){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter startTimeFormatter = DateTimeFormatter.ofPattern("HH.mm").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter endTimeFormatter = DateTimeFormatter.ofPattern("HH.mm").withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDate.parse(date, dateFormatter);
            LocalTime.parse(startTime, startTimeFormatter);
            LocalTime.parse(endTime, endTimeFormatter);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void loadTable(){
        try{
            ArrayList<ScheduleClassDto> scheduleClassDtos = scheduleClassService.getAllScheduleClass();
            ObservableList<ScheduleClassDto> list = FXCollections.observableArrayList(scheduleClassDtos);
            tblScheduleClass.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields(){
        lblClassId.setText("");
        comboCourse.setValue(null);
        comboSubject.setValue(null);
        comboLecturer.setValue(null);
        txtDate.clear();
        txtStartTime.clear();
        txtEndTime.clear();
        txtBatch.clear();
    }
}
