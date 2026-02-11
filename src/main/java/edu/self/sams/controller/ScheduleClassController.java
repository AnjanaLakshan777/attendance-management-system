package edu.self.sams.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleClassController {

    public AnchorPane ancScheduleClass;

    public static void loadScheduleClass() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(ScheduleClassController.class.getResource("/view/ScheduleClass.fxml"));
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
}
