package edu.self.sams.controller;

import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.CourseService;
import edu.self.sams.service.custom.SubjectService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssignCoursesController implements Initializable {

    @FXML
    private AnchorPane ancAssignCourses;
    @FXML
    private ComboBox comboCourse;
    @FXML
    private ComboBox comboSubject;
    @FXML
    private TableView<CourseSubjectDto> tblCourseSubject;
    @FXML
    private TableColumn <CourseSubjectDto,String> colCourseCode;
    @FXML
    private TableColumn <CourseSubjectDto,String> colCourseName;
    @FXML
    private TableColumn <CourseSubjectDto,String> colSubjectCode;
    @FXML
    private TableColumn <CourseSubjectDto,String> colSubjectName;
    @FXML
    private ArrayList<CourseSubjectDto> courseSubjects = new ArrayList<>();

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);
    private SubjectService subjectService = (SubjectService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBJECT);

    public static void loadAssignCourses() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(AssignCoursesController.class.getResource("/view/AssignCourses.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Assign Subjects to Courses");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void backToSubject(ActionEvent actionEvent) throws IOException {
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancAssignCourses.getScene().getWindow();
        preScene.close();

    }

    public void searchCourse(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        try{
            ArrayList<CourseSubjectDto> courseSubjectDtos = courseService.findSubjectsByCourseCode(courseCode);

            tblCourseSubject.getItems().clear();
            
            if(!courseSubjectDtos.isEmpty()){
                for(CourseSubjectDto courseSubjectDto:courseSubjectDtos){
                    tblCourseSubject.getItems().add(courseSubjectDto);
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION,"No assigned subjects found for this course").show();
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error loading subjects: " + e.getMessage()).show();
        }
        comboSubject.getSelectionModel().clearSelection();
    }

    public void searchSubject(ActionEvent actionEvent) {
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        try{
            ArrayList<CourseSubjectDto> courseSubjectDtos = subjectService.findCoursesBySubjectCode(subjectCode);
            tblCourseSubject.getItems().clear();
            if(!courseSubjectDtos.isEmpty()){
                for(CourseSubjectDto courseSubjectDto:courseSubjectDtos){
                    tblCourseSubject.getItems().add(courseSubjectDto);
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION,"No assigned courses found for this subject").show();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error loading subjects: " + e.getMessage()).show();
        }
        comboCourse.getSelectionModel().clearSelection();
    }

    public void clickAdd(ActionEvent actionEvent) {
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String courseName = comboCourse.getValue().toString().substring(5).trim();
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        String subjectName = comboSubject.getValue().toString().substring(7).trim();

        CourseSubjectDto courseSubjectDto = new CourseSubjectDto(courseCode, courseName, subjectCode, subjectName);
        tblCourseSubject.getItems().add(courseSubjectDto);
        courseSubjects.add(courseSubjectDto);
    }

    public void clickRemove(ActionEvent actionEvent) {
        CourseSubjectDto selectedItem = tblCourseSubject.getSelectionModel().getSelectedItem();
        tblCourseSubject.getItems().remove(selectedItem);
        courseSubjects.remove(selectedItem);
    }

    public void clickAssign(ActionEvent actionEvent) {
        if(courseSubjects.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"No subjects to assign").showAndWait();
            return;
        }
        try{
            int successCount = 0;
            int failCount = 0;
            StringBuilder failedAssignments = new StringBuilder();

            for(CourseSubjectDto courseSubjectDto : courseSubjects){
                String result = courseService.assignSubject(courseSubjectDto.getCourseCode(), courseSubjectDto.getSubjectCode());
                if(result.equals("Subject Assigned to Course")){
                    successCount++;
                } else {
                    failCount++;
                    failedAssignments.append("\n").append(courseSubjectDto.getCourseCode()).append(" - ").append(courseSubjectDto.getSubjectCode());
                }
            }
            if(failCount == 0){
                new Alert(Alert.AlertType.INFORMATION,"Success! All " + successCount + " subjects assigned.").showAndWait();
                tblCourseSubject.getItems().clear();
                courseSubjects.clear();
            } else {
                new Alert(Alert.AlertType.ERROR,"Assigned: " + successCount + ", Failed: " + failCount).showAndWait();
                System.out.println("Failed assignments:" + failedAssignments.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        comboCourse.getSelectionModel().clearSelection();
        comboSubject.getSelectionModel().clearSelection();
    }

    public void clickUnassign(ActionEvent actionEvent) {
        if(courseSubjects.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"No subjects to unassign").showAndWait();
            return;
        }
        try{
            int successCount = 0;
            int failCount = 0;
            StringBuilder failedUnassignments = new StringBuilder();

            for(CourseSubjectDto courseSubjectDto : courseSubjects){
                String result = courseService.unassignSubject(courseSubjectDto.getCourseCode(), courseSubjectDto.getSubjectCode());

                if(result.equals("Subject Unassigned from Course")){
                    successCount++;
                } else {
                    failCount++;
                    failedUnassignments.append("\n").append(courseSubjectDto.getCourseCode()).append(" - ").append(courseSubjectDto.getSubjectCode());
                }
            }
            if(failCount == 0){
                new Alert(Alert.AlertType.INFORMATION,"Success! All " + successCount + " subjects unassigned.").showAndWait();
                tblCourseSubject.getItems().clear();
                courseSubjects.clear();
            } else {
                new Alert(Alert.AlertType.ERROR,"Unassigned: " + successCount + ", Failed: " + failCount).showAndWait();
                System.out.println("Failed unassignments:" + failedUnassignments.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));

        try{
            ArrayList<CourseDto> courses = courseService.getCourses();
            if(courses != null){
                for(CourseDto course : courses){
                    comboCourse.getItems().add(course.getCourseCode()+"-"+course.getCourseName());
                }
            }
            ArrayList<SubjectDto> subjects = subjectService.getSubjects();
            if(subjects != null){
                for(SubjectDto subject : subjects){
                    comboSubject.getItems().add(subject.getSubjectCode()+"-"+subject.getSubjectName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
