package edu.self.sams.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    public AnchorPane ancAdminDashboard;

    public static void loadAdminDashboard() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(AdminDashboardController.class.getResource("/view/AdminDashboard.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Subjects");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException {
        CourseController.loadCourse();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException {
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException {
        StudentController.loadStudent();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException {
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException {
        ScheduleClassController.loadScheduleClass();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void goToReports(ActionEvent actionEvent) throws IOException {
        ReportController.loadReport();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        LoginController.loadLogin();
        Stage preScene = (Stage)ancAdminDashboard.getScene().getWindow();
        preScene.close();
    }
}
