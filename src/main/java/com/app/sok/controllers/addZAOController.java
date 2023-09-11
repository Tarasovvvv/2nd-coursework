package com.app.sok.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.ZAO2Model;
import com.app.sok.models.ZAOModel;
import com.app.sok.models.searcherModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addZAOController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf2;

    @FXML
    private Button addButton;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private MenuButton chooseReason;

    @FXML
    private MenuItem mi4;

    @FXML
    private MenuItem mi5;

    @FXML
    private MenuButton chooseZType;

    @FXML
    private MenuItem mi1;

    @FXML
    private MenuItem mi2;

    @FXML
    private MenuItem mi3;

    @FXML
    private MenuItem mi6;

    @FXML
    private MenuItem mi7;

    String selectedReason = "1";
    String selectedZtype = "1";

    @FXML
    void mi1Clicked(ActionEvent event) {
        chooseZType.setText("Уход в академический отпуск");
        selectedZtype = "5";
        chooseReason.setDisable(false);
        chooseReason.setText("Причина ухода");
        tf1.setDisable(true);
    }

    @FXML
    void mi2Clicked(ActionEvent event) {
        chooseZType.setText("Продление академического отпуска");
        selectedZtype = "6";
        chooseReason.setDisable(true);
        chooseReason.setText("Причина ухода");
        tf1.setDisable(false);
        tf3.setDisable(true);
        tf4.setDisable(true);
    }

    @FXML
    void mi3Clicked(ActionEvent event) {
        chooseZType.setText("Выход из академического отпуска");
        selectedZtype = "7";
        chooseReason.setDisable(true);
        chooseReason.setText("Причина ухода");
        tf1.setDisable(false);
        tf3.setDisable(true);
        tf4.setDisable(true);
    }

    @FXML
    void mi4Clicked(ActionEvent event) {
        chooseReason.setText("Семейные обстоятельства");
        selectedReason = "1";
        tf3.setDisable(false);
        tf4.setDisable(false);
    }

    @FXML
    void mi5Clicked(ActionEvent event) {
        chooseReason.setText("Медицинские показания");
        selectedReason = "2";
        tf3.setDisable(false);
        tf4.setDisable(false);
    }

    @FXML
    void mi6Clicked(ActionEvent event) {
        chooseReason.setText("Военная служба");
        selectedReason = "3";
        tf3.setDisable(false);
        tf4.setDisable(false);
    }

    @FXML
    void mi7Clicked(ActionEvent event) {
        chooseReason.setText("Иная причина");
        selectedReason = "4";
        tf3.setDisable(false);
        tf4.setDisable(false);
    }

    @FXML
    void onAddButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (selectedZtype.equals("5")) {
            new ZAOModel(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(), selectedReason,
                    tf3.getText(), tf4.getText(), tf2.getText()).insert();
            Stage st = (Stage) addButton.getScene().getWindow();
            st.close();

        } else if (selectedZtype.equals("6") || selectedZtype.equals("7")) {
            new ZAO2Model(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(), tf1.getText(), tf2.getText()).insert();
            Stage st = (Stage) addButton.getScene().getWindow();
            st.close();
        }
    }

    @FXML
    void initialize() {
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert tf4 != null : "fx:id=\"tf4\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert chooseReason != null : "fx:id=\"chooseReason\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi4 != null : "fx:id=\"mi4\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi5 != null : "fx:id=\"mi5\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert chooseZType != null : "fx:id=\"chooseZType\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi1 != null : "fx:id=\"mi1\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi2 != null : "fx:id=\"mi2\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi3 != null : "fx:id=\"mi3\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi6 != null : "fx:id=\"mi1\" was not injected: check your FXML file 'addZAOView.fxml'.";
        assert mi7 != null : "fx:id=\"mi2\" was not injected: check your FXML file 'addZAOView.fxml'.";
        chooseReason.setDisable(true);
        tf1.setDisable(true);
        tf3.setDisable(true);
        tf4.setDisable(true);

    }
}
