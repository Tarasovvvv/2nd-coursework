package com.app.sok.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.ZVModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addZVController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf1;

    @FXML
    private Button addButton;

    @FXML
    void onAddButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (tf1.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            new ZVModel(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(), tf1.getText()).insert();
            Stage st = (Stage) addButton.getScene().getWindow();
            st.close();
        }
    }

    @FXML
    void initialize() {
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addZVView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addZVView.fxml'.";

    }
}
