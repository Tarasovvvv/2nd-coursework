package com.app.sok.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addAcademicRecordController {

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf5;

    @FXML
    private Button addButton;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private ListView<String> ListView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button addButton2;

    private searcherModel searcher = new searcherModel();
    ArrayList<UZModel> lastSearchResult = null;

    @FXML
    void onKeyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (!searchTextField.getText().equals("")) {
            ArrayList<UZModel> foundUZs = searcher.searchUZData(searchTextField.getText());
            lastSearchResult = foundUZs;
            if (foundUZs != null) {
                ObservableList<String> results = FXCollections.observableArrayList();
                for (UZModel UZ : foundUZs) results.add(UZ.getNAME());
                ListView.setItems(results);
            }
        } else if (!ListView.getItems().isEmpty())
            ListView.getItems().clear();
    }

    UZModel selectedUZ;

    @FXML
    void ViewListClicked(MouseEvent event) {
        if (lastSearchResult != null) {
            int index = ListView.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                selectedUZ = lastSearchResult.get(index);
                if (!selectedUZ.getTYPE().equals("1")) {
                    tf3.setDisable(false);
                    tf4.setDisable(false);
                } else {
                    tf3.setDisable(true);
                    tf4.setDisable(true);
                }
                employeeModel selectedUZDirector = selectedUZ.getDIRECTOR();
                l1.setText(selectedUZ.getNAME());
                l2.setText("" +
                        selectedUZ.getREGION() + " обл., г. " +
                        selectedUZ.getPUNCT() + ", ул. " +
                        selectedUZ.getSTREET() + ", д. " +
                        selectedUZ.getHOUSE());
                l3.setText("" +
                        selectedUZDirector.getLAST_NAME() + " " +
                        selectedUZDirector.getNAME() + " " +
                        selectedUZDirector.getFATHERS_NAME() + "  " +
                        selectedUZDirector.getPHONE() + "  " +
                        selectedUZDirector.getEMAIL());
            } else {
                l1.setText("...");
                l2.setText("...");
                l3.setText("...");
                tf3.setDisable(true);
                tf4.setDisable(true);
            }
        }
    }

    @FXML
    void onAddButton2Clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addUZView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление сведений об учебном заведении");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        new academicRecordModel(tf1.getText(), Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(),
                (selectedUZ.getTYPE().equals("1") ? "1" : (selectedUZ.getTYPE().equals("2") ? "2" : "3")),
                selectedUZ.getID(), (tf3.getText().equals("") ? "0" : tf3.getText()),
                (tf4.getText().equals("") ? "0" : tf4.getText()), tf5.getText()).insert();

        Stage st = (Stage) addButton2.getScene().getWindow();
        st.close();
    }

    @FXML
    void initialize() {
        tf3.setDisable(true);
        tf4.setDisable(true);
        assert l1 != null : "fx:id=\"l1\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert l2 != null : "fx:id=\"l2\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert l3 != null : "fx:id=\"l3\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert tf4 != null : "fx:id=\"tf4\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert tf5 != null : "fx:id=\"tf5\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert addButton2 != null : "fx:id=\"addButton2\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'addAcademicRecordView.fxml'.";

    }
}
