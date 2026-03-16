package edu.self.sams.controller;

import edu.self.sams.dto.AttendanceDto;
import edu.self.sams.dto.ScheduleClassDto;
import edu.self.sams.dto.StudentDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.AttendanceService;
import edu.self.sams.service.custom.ScheduleClassService;
import edu.self.sams.service.custom.StudentService;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceMarkController implements Initializable {
    @FXML
    private AnchorPane ancAttendance;
    @FXML
    private Label lblAttendance;
    @FXML
    private TableView<StudentDto> tblAttendance;
    @FXML
    private TableColumn<StudentDto,String> colStudentCode;
    @FXML
    private TableColumn<StudentDto,String> colStudentName;
    @FXML
    private TableColumn<StudentDto,String> colStatus;
    @FXML
    private TableColumn<StudentDto,String> colRemark;

    private ScheduleClassDto  scheduleClass;

    private StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);
    private AttendanceService attendanceService = (AttendanceService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ATTENDANCE);
    private ScheduleClassService scheduleClassService = (ScheduleClassService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SCHEDULECLASS);

    public void setScheduleClass(ScheduleClassDto scheduleClass) {
        this.scheduleClass = scheduleClass;
        lblAttendance.setText(scheduleClass.getCourseName()+"\n"+scheduleClass.getSubjectName());
        loadTable();
    }

    public void backToLectuturer(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LecturerDashboard.fxml"));
        AnchorPane anchorPane = loader.load();
        LecturerDashboardController controller = loader.getController();
        controller.setUserId(scheduleClass.getUserId());

        Stage stage = new Stage();
        stage.setTitle("Lecture Dashboard");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();

        Stage preScene =  (Stage) ancAttendance.getScene().getWindow();
        preScene.close();
    }

    public void clickOnSubmit(ActionEvent actionEvent) {
        try {
            ObservableList<StudentDto> students = tblAttendance.getItems();
            ArrayList<AttendanceDto> attendanceList = new ArrayList<>();

            for (StudentDto student : students) {
                if (student.getStatus() != null) {
                    AttendanceDto attendanceDto = new AttendanceDto(
                        scheduleClass.getClassId(),
                        student.getRegNo(),
                        student.getName(),
                        student.getStatus(),
                        student.getRemark() != null ? student.getRemark() : ""
                    );
                    attendanceList.add(attendanceDto);
                }
            }

            if (attendanceList.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No Attendance Marked", "Please mark attendance for at least one student.");
                return;
            }

            String result = attendanceService.saveAttendanceList(attendanceList);
            showAlert(Alert.AlertType.INFORMATION, "Attendance Submission", result);
            scheduleClass.setStatus("done");
            String resp = scheduleClassService.updateScheduleClass(scheduleClass);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save attendance: " + e.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblAttendance.setEditable(true);

        colStudentCode.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));

        colStatus.setCellFactory(ComboBoxTableCell.forTableColumn("Present", "Absent", "Late"));
        colStatus.setOnEditCommit(event -> {
            StudentDto student = event.getRowValue();
            student.setStatus(event.getNewValue());
        });

        colRemark.setCellFactory(TextFieldTableCell.forTableColumn());
        colRemark.setOnEditCommit(event -> {
            StudentDto student = event.getRowValue();
            student.setRemark(event.getNewValue());
        });
    }

    private void loadTable() {
        try{
            ArrayList<StudentDto> studentDtos = studentService.getStudentsByCourseAndBatch(scheduleClass.getCourseCode(),scheduleClass.getBatch());
            ObservableList<StudentDto> list = FXCollections.observableArrayList(studentDtos);
            tblAttendance.setItems(list);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
