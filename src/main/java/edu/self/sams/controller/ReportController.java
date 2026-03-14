package edu.self.sams.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import edu.self.sams.dto.AttendanceDto;
import edu.self.sams.dto.CourseDto;
import edu.self.sams.dto.CourseSubjectDto;
import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.AttendanceService;
import edu.self.sams.service.custom.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private AnchorPane ancReport;
    @FXML
    private ComboBox comboCourse;
    @FXML
    private ComboBox comboSubject;
    @FXML
    private TextField txtBatch;
    @FXML
    private TextField txtDate;
    @FXML
    private TableView<AttendanceDto> tblReports;
    @FXML
    private TableColumn<AttendanceDto,String> colRegNo;
    @FXML
    private TableColumn<AttendanceDto,String> colName;
    @FXML
    private TableColumn<AttendanceDto,String> colStatus;
    @FXML
    private TableColumn<AttendanceDto,String> colRemark;

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);
    private AttendanceService attendanceService = (AttendanceService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ATTENDANCE);

    public static void loadReport() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(ReportController.class.getResource("/view/Report.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Reports");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent actionEvent) throws IOException{
        AdminDashboardController.loadAdminDashboard();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void goToCourses(ActionEvent actionEvent) throws IOException{
        CourseController.loadCourse();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void goToSubjects(ActionEvent actionEvent) throws IOException{
        SubjectController.loadSubject();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void goToStudents(ActionEvent actionEvent) throws IOException{
        StudentController.loadStudent();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void goToLecturers(ActionEvent actionEvent) throws IOException{
        LecturerController.loadLecture();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void goToScheduleClass(ActionEvent actionEvent) throws IOException{
        ScheduleClassesController.loadScheduleClass();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        LoginController.loadLogin();
        Stage preScene = (Stage)ancReport.getScene().getWindow();
        preScene.close();
    }

    public void searchSubjects(ActionEvent actionEvent) {
        comboSubject.getItems().clear();
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        try{
            ArrayList<CourseSubjectDto> courseSubjectDtos = courseService.findSubjectsByCourseCode(courseCode);
            for(CourseSubjectDto courseSubjectDto : courseSubjectDtos){
                comboSubject.getItems().add(courseSubjectDto.getSubjectCode()+"-"+courseSubjectDto.getSubjectName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));

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
    }

    public void clickOnSearch(ActionEvent actionEvent) {
        tblReports.getItems().clear();
        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        int batch = Integer.parseInt(txtBatch.getText());
        String date = txtDate.getText();

        boolean isDateValid = dateValidation(date);
        if(isDateValid){
            try{
                ArrayList<AttendanceDto> attendanceDtos = attendanceService.getAttendanceList(courseCode, subjectCode, batch, date);
                ObservableList<AttendanceDto> list = FXCollections.observableArrayList(attendanceDtos);
                tblReports.setItems(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean dateValidation(String date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnDownload(ActionEvent actionEvent) {
        if (tblReports.getItems() == null || tblReports.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "No attendance report data to download").showAndWait();
            return;
        }

        if (comboCourse.getValue() == null || comboSubject.getValue() == null || txtBatch.getText().isBlank() || txtDate.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Please select course, subject, batch and date").showAndWait();
            return;
        }

        if (!dateValidation(txtDate.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid date format. Use yyyy-MM-dd").showAndWait();
            return;
        }

        Stage stage = (Stage) ancReport.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Attendance Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        fileChooser.setInitialFileName("attendance_report_" + courseCode + "_" + subjectCode + "_" + txtDate.getText() + ".pdf");

        File file = fileChooser.showSaveDialog(stage);
        if (file == null) {
            return;
        }

        try {
            generateAttendancePDF(tblReports.getItems(), file);
            new Alert(Alert.AlertType.INFORMATION, "Attendance report downloaded successfully").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate PDF: " + e.getMessage()).showAndWait();
        }
    }

    public void generateAttendancePDF(List<AttendanceDto> attendanceList, File file) throws Exception {

        String courseCode = comboCourse.getValue().toString().substring(0,4);
        String courseName = comboCourse.getValue().toString().substring(5).trim();

        String subjectCode = comboSubject.getValue().toString().substring(0,6);
        String subjectName = comboSubject.getValue().toString().substring(7).trim();

        PdfWriter writer = new PdfWriter(file.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Class Attendance Report"));
        document.add(new Paragraph("Course: " + courseName + " (ID: " + courseCode + ")"));
        document.add(new Paragraph("Subject: " + subjectName + " (ID: " + subjectCode + ")"));
        document.add(new Paragraph("Batch: " + txtBatch.getText()));
        document.add(new Paragraph("Date: " + txtDate.getText()));
        document.add(new Paragraph(" "));

        Table table = new Table(4);
        table.addCell("Reg No");
        table.addCell("Student Name");
        table.addCell("Status");
        table.addCell("Remark");

        for (AttendanceDto a : attendanceList) {
            table.addCell(a.getRegNo() == null ? "" : a.getRegNo());
            table.addCell(a.getName() == null ? "" : a.getName());
            table.addCell(a.getStatus() == null ? "" : a.getStatus());
            table.addCell(a.getRemark() == null ? "" : a.getRemark());
        }

        document.add(table);
        document.close();
    }
}
