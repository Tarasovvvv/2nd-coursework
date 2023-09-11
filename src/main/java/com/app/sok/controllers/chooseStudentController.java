package com.app.sok.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.passportModel;
import com.app.sok.models.searcherModel;
import com.app.sok.models.studentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class chooseStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveButton;

    @FXML
    private ListView<String> studentsListView;

    @FXML
    private TextField searchTextField;

    private searcherModel searcher = new searcherModel();
    ArrayList<studentModel> lastSearchResult = null;

    @FXML
    void onKeyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (!searchTextField.getText().equals("")) {
            ArrayList<studentModel> foundStudents = searcher.searchStudentData(searchTextField.getText());
            lastSearchResult = foundStudents;
            if (foundStudents != null) {
                ObservableList<String> results = FXCollections.observableArrayList();
                for (studentModel student : foundStudents) {
                    passportModel latestPassport = student.getLatestPassport();
                    results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                }
                studentsListView.setItems(results);
            }
        } else if (!studentsListView.getItems().isEmpty())
            studentsListView.getItems().clear();
    }

    studentModel selectedStudent = null;

    @FXML
    void onStudentsListViewClicked(MouseEvent event) {
        if (lastSearchResult != null) {
            int index = studentsListView.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                selectedStudent = lastSearchResult.get(index);
                passportModel latestPassport = selectedStudent.getLatestPassport();
                searchTextField.setText(latestPassport.getFIO());
            }
        }
    }


    @FXML
    void save(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (selectedStudent != null) {
            Application.GLOBAL = selectedStudent.getId();
            Stage st = (Stage) saveButton.getScene().getWindow();
            st.close();
        }
    }

    @FXML
    void initialize() {
        Application.getCURRENT_STAGE().setResizable(false);
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'chooseStudentView.fxml'.";

    }
}
