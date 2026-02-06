package edu.self.sams.controller;

import edu.self.sams.service.ServiceFactory;
import edu.self.sams.service.custom.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    public TextField txtUserId;
    public PasswordField txtPassword;
    public ComboBox comboRole;
    public AnchorPane ancLogin;

    private UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER);

    public void clickLogin(ActionEvent actionEvent) throws Exception {

        String userId = txtUserId.getText().trim();
        String password = txtPassword.getText().trim();
        String role = comboRole.getValue().toString().trim();

        if(role.equals("Admin")){
            if(userService.userLogin(userId,password,role)){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Admin Dashboard");
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.show();

                Stage preScene = (Stage)ancLogin.getScene().getWindow();
                preScene.close();
            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid UserID or Password.").showAndWait();
            }
        } else if (role.equals("Lecture")) {
            if(userService.userLogin(userId,password,role)){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LectureDashboard.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Lecture Dashboard");
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.show();

                Stage preScene = (Stage)ancLogin.getScene().getWindow();
                preScene.close();
            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid UserID or Password.").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Please fill all").showAndWait();
        }
    }
}