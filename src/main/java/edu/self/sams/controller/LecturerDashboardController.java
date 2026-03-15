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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class LecturerDashboardController implements Initializable {
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

    private ScheduleClassService scheduleClassService = (ScheduleClassService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SCHEDULECLASS);
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

    public void clickOnMarkAttendance(ActionEvent actionEvent) throws IOException {
        ScheduleClassDto selectedItem = tblScheduleClass.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AttendanceMark.fxml"));
            AnchorPane anchorPane = loader.load();
            AttendanceMarkController controller = loader.getController();
            controller.setScheduleClass(selectedItem);

            Stage stage = new Stage();
            stage.setTitle("Attendance Mark");
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();

            Stage preScene =  (Stage) ancLecturerDash.getScene().getWindow();
            preScene.close();
        }else{
            new Alert(Alert.AlertType.ERROR, "Please select a scheduled class").showAndWait();
        }
    }

    public void clickOnDone(ActionEvent actionEvent) throws Exception {
        ScheduleClassDto selectedItem = tblScheduleClass.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            selectedItem.setStatus("done");
            String resp = scheduleClassService.updateScheduleClass(selectedItem);
            new Alert(Alert.AlertType.INFORMATION,resp).showAndWait();
            loadTable(userId);
        }
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
            ArrayList<ScheduleClassDto> scheduleClassDtos = scheduleClassService.getScheduleClassByUserId(userId);
            ObservableList<ScheduleClassDto> list = FXCollections.observableArrayList(scheduleClassDtos);
            tblScheduleClass.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
