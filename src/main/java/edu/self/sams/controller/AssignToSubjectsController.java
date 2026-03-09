package edu.self.sams.controller;

import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.dto.LecturerDto;
import edu.self.sams.dto.SubjectDto;
import edu.self.sams.dto.SubjectLecturerDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.LecturerService;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssignToSubjectsController implements Initializable {
    @FXML
    private AnchorPane ancAssignToSubjects;
    @FXML
    private ComboBox comboSubject;
    @FXML
    private ComboBox comboLecturer;
    @FXML
    private TableView<SubjectLecturerDto> tblSubjectLecturer;
    @FXML
    private TableColumn<SubjectLecturerDto,String> colSubjectCode;
    @FXML
    private TableColumn<SubjectLecturerDto,String> colSubjectName;
    @FXML
    private TableColumn<SubjectLecturerDto,String> colUserId;
    @FXML
    private TableColumn<SubjectLecturerDto,String> colLecturerName;
    @FXML
    private ArrayList<SubjectLecturerDto> subjectLecturers = new ArrayList<>();

    private SubjectService subjectService = (SubjectService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBJECT);
    private LecturerService lecturerService = (LecturerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LECTURER);

    public static void loadAssignToSubjects() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(AssignToCoursesController.class.getResource("/view/AssignToSubjects.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Assign To Subjects");
        Scene scene = new  Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void backToLecturer(ActionEvent actionEvent) throws IOException {
        LecturerController.loadLecture();
        Stage preScene = (Stage) ancAssignToSubjects.getScene().getWindow();
        preScene.close();
    }

    public void searchSubject(ActionEvent actionEvent) {
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        try{
            ArrayList<SubjectLecturerDto> subjectLecturerDtos = subjectService.findLecturersBySubjectCode(subjectCode);
            tblSubjectLecturer.getItems().clear();
            if(!subjectLecturerDtos.isEmpty()){
                for(SubjectLecturerDto subjectLecturerDto:subjectLecturerDtos){
                    tblSubjectLecturer.getItems().add(subjectLecturerDto);
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION,"No assigned lecturers found for this subject").show();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error loading lecturers: " + e.getMessage()).show();
        }
        comboLecturer.getSelectionModel().clearSelection();
    }

    public void searchLecturer(ActionEvent actionEvent) {
        String userId = comboLecturer.getValue().toString().substring(0,4);
        try{
            ArrayList<SubjectLecturerDto> subjectLecturerDtos = lecturerService.findSubjectsByUserId(userId);
            tblSubjectLecturer.getItems().clear();
            if(!subjectLecturerDtos.isEmpty()){
                for(SubjectLecturerDto subjectLecturerDto:subjectLecturerDtos){
                    tblSubjectLecturer.getItems().add(subjectLecturerDto);
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION,"No assigned subjects found for this lecturer").show();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error loading subjects: " + e.getMessage()).show();
        }
        comboSubject.getSelectionModel().clearSelection();
    }

    public void clickAdd(ActionEvent actionEvent) {
        String subjectCode =  comboSubject.getValue().toString().substring(0,6);
        String subjectName = comboSubject.getValue().toString().substring(7).trim();
        String userId = comboLecturer.getValue().toString().substring(0,4);
        String lecturerName = comboLecturer.getValue().toString().substring(5).trim();

        SubjectLecturerDto subjectLecturerDto = new SubjectLecturerDto(subjectCode, subjectName, userId, lecturerName);
        tblSubjectLecturer.getItems().add(subjectLecturerDto);
        subjectLecturers.add(subjectLecturerDto);
    }

    public void clickRemove(ActionEvent actionEvent) {
        SubjectLecturerDto selectedItem = tblSubjectLecturer.getSelectionModel().getSelectedItem();
        tblSubjectLecturer.getItems().remove(selectedItem);
        subjectLecturers.remove(selectedItem);
    }

    public void clickAssign(ActionEvent actionEvent) {
        if(subjectLecturers.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"No lecturers to assign").show();
            return;
        }
        try{
            int successCount = 0;
            int failCount = 0;
            StringBuilder failedAssignments = new  StringBuilder();
            for(SubjectLecturerDto subjectLecturerDto:subjectLecturers){
                boolean resp = subjectService.assignLecturer(subjectLecturerDto.getSubjectCode(),subjectLecturerDto.getUserId());
                if(resp){
                    successCount++;
                }else{
                    failCount++;
                    failedAssignments.append("\n").append(subjectLecturerDto.getSubjectCode()).append(" - ").append(subjectLecturerDto.getUserId());
                }
            }
            if(failCount == 0){
                new Alert(Alert.AlertType.INFORMATION,"Success! All " + successCount + " Lecturers assigned.").showAndWait();
                tblSubjectLecturer.getItems().clear();
                subjectLecturers.clear();
            }else{
                new Alert(Alert.AlertType.ERROR,"Assigned: " + successCount + ", Failed: " + failCount).showAndWait();
                System.out.println("Failed assignments:" + failedAssignments.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        comboSubject.getSelectionModel().clearSelection();
        comboLecturer.getSelectionModel().clearSelection();
    }

    public void clickUnassign(ActionEvent actionEvent) {
        if(subjectLecturers.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"No lecturers to Unassign").show();
            return;
        }
        try{
            int successCount = 0;
            int failCount = 0;
            StringBuilder failedAssignments = new  StringBuilder();
            for(SubjectLecturerDto subjectLecturerDto:subjectLecturers){
                boolean resp = subjectService.unassignLecturer(subjectLecturerDto.getSubjectCode(),subjectLecturerDto.getUserId());
                if(resp){
                    successCount++;
                }else{
                    failCount++;
                    failedAssignments.append("\n").append(subjectLecturerDto.getSubjectCode()).append(" - ").append(subjectLecturerDto.getUserId());
                }
            }
            if(failCount == 0){
                new Alert(Alert.AlertType.INFORMATION,"Success! All " + successCount + " Lecturers Unassigned.").showAndWait();
                tblSubjectLecturer.getItems().clear();
                subjectLecturers.clear();
            }else{
                new Alert(Alert.AlertType.ERROR,"Unassigned: " + successCount + ", Failed: " + failCount).showAndWait();
                System.out.println("Failed unassignments:" + failedAssignments.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        comboSubject.getSelectionModel().clearSelection();
        comboLecturer.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colLecturerName.setCellValueFactory(new PropertyValueFactory<>("lecturerName"));

        try{
            ArrayList<SubjectDto> subjects = subjectService.getSubjects();
            if(subjects != null){
                for(SubjectDto subject : subjects){
                    comboSubject.getItems().add(subject.getSubjectCode()+"-"+subject.getSubjectName());
                }
            }
            ArrayList<LecturerDto> lecturers = lecturerService.getLecturers();
                if(lecturers != null){
                    for(LecturerDto lecturer : lecturers){
                        comboLecturer.getItems().add(lecturer.getUserId()+"-"+lecturer.getName());
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
