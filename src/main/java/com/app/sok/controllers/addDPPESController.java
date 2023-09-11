package com.app.sok.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.app.sok.models.DPPESModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addDPPESController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private Button addButton;

    @FXML
    void tf1Typed(KeyEvent event) {
        checkStatus();
    }

    @FXML
    void tf2Typed(KeyEvent event) {
        checkStatus();
    }

    @FXML
    void tf3Typed(KeyEvent event) {
        checkStatus();
    }

    private void checkStatus() {
        if (tf1.getText().matches("[0-9]{0,6}"))
            if (tf2.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$"))
                if (tf3.getText().matches("[1-5]")) addButton.setDisable(false);
                else addButton.setDisable(true);
            else addButton.setDisable(true);
        else addButton.setDisable(true);
    }

    @FXML
    void onAddButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        new DPPESModel(tf1.getText(), tf2.getText(), tf3.getText()).insert();
        Stage st = (Stage) addButton.getScene().getWindow();
        st.close();
    }

    @FXML
    void initialize() {
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addDPPESView.fxml'.";
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'addDPPESView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addDPPESView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'addDPPESView.fxml'.";
        addButton.setDisable(true);

    }
}
