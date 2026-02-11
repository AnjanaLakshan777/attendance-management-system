package edu.self.sams.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LecturerController {

    public AnchorPane ancLecturer;

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
}
