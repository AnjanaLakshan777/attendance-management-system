package edu.self.sams.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginController {

    public TextField txtUserId;
    public TextField txtPassword;
    public ComboBox comboRole;

    public void clickLogin(ActionEvent actionEvent) {
        System.out.println(txtUserId.getText());
        System.out.println(txtPassword.getText());
        System.out.println(comboRole.getValue());

    }
}