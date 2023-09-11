package com.app.sok.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.passportModel;
import com.app.sok.models.studentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addPassportController {

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
    private TextField tf4;

    @FXML
    private TextField tf5;

    @FXML
    private TextField tf7;

    @FXML
    private TextField tf8;

    @FXML
    private TextField tf9;

    @FXML
    private TextField tf10;

    @FXML
    private TextField tf11;

    @FXML
    private TextField tf12;

    @FXML
    private TextField tf13;

    @FXML
    private TextField tf14;

    @FXML
    private TextField tf15;

    @FXML
    private TextField tf16;

    @FXML
    private TextField tf17;

    @FXML
    private TextField tf18;

    @FXML
    private TextField tf19;

    @FXML
    private Button addButton;

    @FXML
    private MenuItem g1;

    @FXML
    private MenuItem g2;

    @FXML
    private MenuItem chooseStudentButton;

    @FXML
    private MenuButton gender;

    @FXML
    private MenuItem addNewStudentButton;

    @FXML
    private MenuButton studentID;

    @FXML
    void ong1Action(ActionEvent event) {
        gender.setText("муж.");
    }

    @FXML
    void ong2Action(ActionEvent event) {
        gender.setText("жен.");
    }

    private boolean addNewStudentVar = false;

    @FXML
    void addNewStudent(ActionEvent event) {
        addNewStudentVar = true;
        studentID.setText("Добавить новый");
    }

    @FXML
    void chooseStudent(ActionEvent event) throws IOException {
        addNewStudentVar = false;
        studentID.setText("Указать существующего");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("chooseStudentView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Выберете студента");
        tempStage.setScene(scene);
        tempStage.show();
    }

    @FXML
    void onAddButton1Clicked(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
        boolean requiresSatisfied = true;
        if (!tf1.getText().matches("^[0-9]{4}$")) {
            tf1.setText("");
            requiresSatisfied = false;
        }
        if (!tf2.getText().matches("^[0-9]{6}$")) {
            tf2.setText("");
            requiresSatisfied = false;
        }
        if (!tf3.getText().matches("^[А-Я][а-я]*$")) {
            tf3.setText("");
            requiresSatisfied = false;
        }
        if (!tf4.getText().matches("^[А-Я][а-я]*$")) {
            tf4.setText("");
            requiresSatisfied = false;
        }
        if (!tf5.getText().matches("^[А-Я][а-я]*$")) {
            tf5.setText("");
            requiresSatisfied = false;
        }
        if (!tf7.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            tf7.setText("");
            requiresSatisfied = false;
        }
        if (!tf8.getText().matches("^[0-9]{6}$")) {
            tf8.setText("");
            requiresSatisfied = false;
        }
        if (!tf9.getText().matches("^[^0-9]*$")) {
            tf9.setText("");
            requiresSatisfied = false;
        }
        if (!tf10.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            tf10.setText("");
            requiresSatisfied = false;
        }
        if (!tf11.getText().matches("^[А-Я][а-я]*$")) {
            tf11.setText("");
            requiresSatisfied = false;
        }
        if (!tf12.getText().matches("^[А-Я][а-я]*$")) {
            tf12.setText("");
            requiresSatisfied = false;
        }
        if (!tf13.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            tf13.setText("");
            requiresSatisfied = false;
        }
        if (!tf14.getText().matches("^[А-я .]*$")) {
            tf14.setText("");
            requiresSatisfied = false;
        }
        if (!tf15.getText().matches("^[А-я .]*$")) {
            tf15.setText("");
            requiresSatisfied = false;
        }
        if (!tf16.getText().matches("^[А-Я][а-я]*$")) {
            tf16.setText("");
            requiresSatisfied = false;
        }
        if (!tf17.getText().matches("^[А-я -]*$")) {
            tf17.setText("");
            requiresSatisfied = false;
        }
        if (!tf18.getText().matches("^[1-9]{1,2}[0-9]?[А-я]?$")) {
            tf18.setText("");
            requiresSatisfied = false;
        }
        if (!tf19.getText().matches("^[1-9]{1,2}[0-9]?$")) {
            tf19.setText("");
            requiresSatisfied = false;
        }
        if (requiresSatisfied) {
            passportModel passport = new passportModel(new String[]{
                    tf1.getText() + tf2.getText(), tf3.getText(), tf4.getText(),
                    tf5.getText(), gender.getText(), tf7.getText(), tf8.getText(), tf9.getText(),
                    tf10.getText(), tf11.getText(), tf12.getText(), tf13.getText(), tf14.getText(),
                    tf15.getText(),tf16.getText(), tf17.getText(), tf18.getText(), tf19.getText()});
            passport.insert();
            studentModel student = new studentModel(passport);
            if (!addNewStudentVar) student.setId(Application.GLOBAL);
            student.insert();
        }
    }

    @FXML
    void initialize() {
        Application.getCURRENT_STAGE().setResizable(false);
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf4 != null : "fx:id=\"tf4\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf5 != null : "fx:id=\"tf5\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf7 != null : "fx:id=\"tf7\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf8 != null : "fx:id=\"tf8\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf9 != null : "fx:id=\"tf9\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf10 != null : "fx:id=\"tf10\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf11 != null : "fx:id=\"tf11\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf13 != null : "fx:id=\"tf13\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf14 != null : "fx:id=\"tf14\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf15 != null : "fx:id=\"tf15\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf16 != null : "fx:id=\"tf16\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf17 != null : "fx:id=\"tf17\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf18 != null : "fx:id=\"tf18\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf19 != null : "fx:id=\"tf19\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert tf12 != null : "fx:id=\"tf12\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert studentID != null : "fx:id=\"studentID\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert chooseStudentButton != null : "fx:id=\"chooseStudentButton\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert addNewStudentButton != null : "fx:id=\"addNewStudentButton\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert g1 != null : "fx:id=\"g1\" was not injected: check your FXML file 'addPassportView.fxml'.";
        assert g2 != null : "fx:id=\"g2\" was not injected: check your FXML file 'addPassportView.fxml'.";
    }
}
