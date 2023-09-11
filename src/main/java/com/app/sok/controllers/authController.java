package com.app.sok.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import com.app.sok.Application;
import com.app.sok.models.employeeModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class authController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button authButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    void onAuthButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
        employeeModel employee = new employeeModel(loginTextField.getText(), passwordPasswordField.getText());
        if (employee.exists()) {
            String user = String.format("%s %s.%s.", employee.getLAST_NAME(), employee.getNAME().charAt(0), employee.getFATHERS_NAME().charAt(0));
            Application.setCURRENT_USER(user);
            Application.CURRENT_EMPLOYEE = employee;
            Application.switchScene("mainView.fxml", "StOKad");
        } else {
            authButton.setText("Ошибка");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> authButton.setText("Войти"));
                }
            }, 1000L);
        }
    }

    @FXML
    void initialize() {
        Application.getCURRENT_STAGE().setResizable(false);
        assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'authView.fxml'.";
        assert authButton != null : "fx:id=\"authButton\" was not injected: check your FXML file 'authView.fxml'.";
        assert passwordPasswordField != null : "fx:id=\"passwordPasswordField\" was not injected: check your FXML file 'authView.fxml'.";
    }
}

