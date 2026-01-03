module edu.self.sams.studentattendancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.self.sams to javafx.fxml;
    exports edu.self.sams;
}