module edu.self.sams.studentattendancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.self.sams to javafx.fxml;
    exports edu.self.sams;
    exports edu.self.sams.controller;
    opens edu.self.sams.controller to javafx.fxml;
}