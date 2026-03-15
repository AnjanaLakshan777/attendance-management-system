module edu.self.sams.studentattendancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.xml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires kernel;
    requires layout;
    requires bcrypt;

    opens edu.self.sams to javafx.fxml;
    opens edu.self.sams.controller to javafx.fxml;
    opens edu.self.sams.entity to org.hibernate.orm.core;
    opens edu.self.sams.dto to javafx.base;

    exports edu.self.sams;
}