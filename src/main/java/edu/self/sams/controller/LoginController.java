package edu.self.sams.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginController {

    public TextField txtUsername;
    public TextField txtPassword;
    public ComboBox comboRole;

    public void clickLogin(ActionEvent actionEvent) {
        System.out.println(txtUsername.getText());
        System.out.println(txtPassword.getText());
        System.out.println(comboRole.getValue());

    }
}