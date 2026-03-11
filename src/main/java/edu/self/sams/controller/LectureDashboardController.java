package edu.self.sams.controller;

import edu.self.sams.dto.LecturerDto;
import edu.self.sams.dto.ScheduleClassDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.LecturerService;
import edu.self.sams.service.custom.ScheduleClassService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LectureDashboardController implements Initializable {
    @FXML
    private AnchorPane ancLecturerDash;
    @FXML
    private Label lblTitle;
    @FXML
    private TableView<ScheduleClassDto> tblScheduleClass;
    @FXML
    private TableColumn<ScheduleClassDto,String> colDate;
    @FXML
    private TableColumn<ScheduleClassDto,String> colStartTime;
    @FXML
    private TableColumn<ScheduleClassDto,String> colEndTime;
    @FXML
    private TableColumn<ScheduleClassDto,String> colSubjectName;
    @FXML
    private TableColumn<ScheduleClassDto,String> colBatch;
    @FXML
    private TableColumn<ScheduleClassDto,String> colCourseName;

    private String userId;

    private ScheduleClassService scheduleClassService = (ScheduleClassService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SHEDULECLASS);
    private LecturerService lecturerService = (LecturerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LECTURER);

    public void setUserId(String userId) throws Exception {
        this.userId = userId;
        LecturerDto lecturerDto = lecturerService.getLecturer(userId);
        String lecturerName = lecturerDto.getName();
        lblTitle.setText("Hello, "+userId+"-"+ lecturerName);
        loadTable(userId);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        LoginController.loadLogin();
        Stage preScene = (Stage) ancLecturerDash.getScene().getWindow();
        preScene.close();
    }

    public void clickOnMarkAttendance(ActionEvent actionEvent) {

    }

    public void clickOnDone(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    }

    private void loadTable(String userId) {
        try{
            ArrayList<ScheduleClassDto> ScheduleClassDtos = scheduleClassService.getScheduleClassByUserId(userId);
            ObservableList<ScheduleClassDto> list = FXCollections.observableArrayList(ScheduleClassDtos);
            tblScheduleClass.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
