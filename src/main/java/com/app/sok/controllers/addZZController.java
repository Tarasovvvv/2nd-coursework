package com.app.sok.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.app.sok.Application;
import com.app.sok.models.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class addZZController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DZZModel> table;

    @FXML
    private TableColumn<DZZModel, String> priority;

    @FXML
    private TableColumn<DZZModel, String> direction;

    @FXML
    private TableColumn<DZZModel, String> level;

    @FXML
    private TableColumn<DZZModel, String> form;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> list;

    @FXML
    private Button addDirection;

    @FXML
    private TextField tf2;

    @FXML
    private Button addButton;

    @FXML
    private MenuButton educationFormTF;

    @FXML
    private MenuItem mi1;

    @FXML
    private MenuItem mi2;

    @FXML
    private MenuItem mi3;

    @FXML
    private MenuButton priorityTF;

    @FXML
    private MenuItem mi4;

    @FXML
    private MenuItem mi5;

    @FXML
    private MenuItem mi6;

    private searcherModel searcher = new searcherModel();
    ArrayList<directionModel> lastSearchResult = null;

    public addZZController() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void keyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (!searchTextField.getText().equals("")) {
            ArrayList<directionModel> directions = searcher.searchDirectionData(searchTextField.getText());
            lastSearchResult = directions;
            if (directions != null) {
                ObservableList<String> results = FXCollections.observableArrayList();
                for (directionModel direction : directions) {
                    String id = direction.getID();
                    results.add((id.length() == 5 ? "0" + id : id) + " " + direction.getNAME());
                }
                list.setItems(results);
            }
        } else if (!list.getItems().isEmpty())
            list.getItems().clear();
    }

    @FXML
    void mi4Clicked(ActionEvent event) {
        priorityTF.setText(mi4.getText());
        if (!educationFormTF.getText().equals("Форма обучения")) addDirection.setDisable(false);
    }

    @FXML
    void mi5Clicked(ActionEvent event) {
        priorityTF.setText(mi5.getText());
        if (!educationFormTF.getText().equals("Форма обучения")) addDirection.setDisable(false);
    }

    @FXML
    void mi6Clicked(ActionEvent event) {
        priorityTF.setText(mi6.getText());
        if (!educationFormTF.getText().equals("Форма обучения")) addDirection.setDisable(false);
    }

    private String selectedEducationForm = "1";

    @FXML
    void mi1Clicked(ActionEvent event) {
        educationFormTF.setText(mi1.getText());
        selectedEducationForm = "1";
        if (!priorityTF.getText().equals("Приоритет")) addDirection.setDisable(false);
    }

    @FXML
    void mi2Clicked(ActionEvent event) {
        educationFormTF.setText(mi2.getText());
        selectedEducationForm = "2";
        if (!priorityTF.getText().equals("Приоритет")) addDirection.setDisable(false);
    }

    @FXML
    void mi3Clicked(ActionEvent event) {
        educationFormTF.setText(mi3.getText());
        selectedEducationForm = "3";
        if (!priorityTF.getText().equals("Приоритет")) addDirection.setDisable(false);
    }


    int selectedDZZindex;
    private boolean dirListFilled = false;
    private directionModel selectedDirection;
    private ArrayList<DZZModel> DZZs = new ArrayList<DZZModel>();


    private void checkRequiresToUnlockAddButton() {
        if (dirListFilled)
            if (tf2.getText().matches("^((19[6-9][0-9])|(20([01][0-9]|2[0-3])))-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$"))
                addButton.setDisable(false);
            else addButton.setDisable(true);
        else addButton.setDisable(true);
    }

    @FXML
    void addDirectionClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (DZZs.size() < 3) {
            dirListFilled = false;
            checkRequiresToUnlockAddButton();
            boolean selectedDZZNotAdded = true;
            for (DZZModel dzz : DZZs)
                if (dzz.getDIRECTION_ID().equals(selectedDirection.getID())) {
                    selectedDZZNotAdded = false;
                    break;
                }
            if (selectedDZZNotAdded) {
                DZZModel newDZZ = new DZZModel("0", priorityTF.getText(), selectedDirection.getID(), selectedEducationForm);
                DZZs.add(newDZZ);
                table.setItems(FXCollections.observableArrayList(DZZs));
                mi1.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '1' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi2.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '2' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi3.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '3' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi4.setDisable(false);
                mi5.setDisable(false);
                mi6.setDisable(false);
                for (DZZModel dzz : DZZs)
                    if (dzz.getPRIORITY().equals("1")) mi4.setDisable(true);
                    else if (dzz.getPRIORITY().equals("2")) mi5.setDisable(true);
                    else if (dzz.getPRIORITY().equals("3")) mi6.setDisable(true);
                priorityTF.setText("Приоритет");
                educationFormTF.setText("Форма обучения");
                addDirection.setDisable(true);
                if (DZZs.size() == 3) dirListFilled = true;
            } else {
                addDirection.setText("Направление уже добавлено");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> addDirection.setText("Выбрать"));
                    }
                }, 1000L);
            }
        } else {
            dirListFilled = true;
            checkRequiresToUnlockAddButton();
            addDirection.setText("Максимум 3 направления");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> addDirection.setText("Выбрать"));
                }
            }, 1000L);
        }

    }

    @FXML
    void tf2Typed() {
        checkRequiresToUnlockAddButton();
    }

    @FXML
    void onTableKeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            selectedDZZindex = table.getSelectionModel().getSelectedIndex();
            if (selectedDZZindex > -1) {
                DZZs.remove(selectedDZZindex);
                table.setItems(FXCollections.observableArrayList(DZZs));
            }
        }
    }

    @FXML
    void listClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (lastSearchResult != null) {
            educationFormTF.setText("Форма обучения");
            priorityTF.setText("Приоритет");
            int index = list.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                educationFormTF.setDisable(false);
                priorityTF.setDisable(false);
                selectedDirection = lastSearchResult.get(index);
                mi1.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '1' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi2.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '2' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi3.setDisable(searcher.getCount("Код формы обучения", "Направление-Форма_обучения",
                        String.format("`Код формы обучения` = '3' AND `Код направления` = '%s'", selectedDirection.getID())) == 0);
                mi4.setDisable(false);
                mi5.setDisable(false);
                mi6.setDisable(false);
                for (DZZModel dzz : DZZs) {
                    if (dzz.getPRIORITY().equals("1")) mi4.setDisable(true);
                    else if (dzz.getPRIORITY().equals("2")) mi5.setDisable(true);
                    else if (dzz.getPRIORITY().equals("3")) mi6.setDisable(true);
                }
            } else {
                addDirection.setDisable(true);
                educationFormTF.setDisable(true);
                mi1.setDisable(true);
                mi2.setDisable(true);
                mi3.setDisable(true);
                priorityTF.setDisable(true);
                mi4.setDisable(true);
                mi5.setDisable(true);
                mi6.setDisable(true);
            }
        }
    }

    @FXML
    void onAddButtonClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        String currentStudent = Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber();
        ArrayList<academicRecordModel> currentStudentAcademicRecords = searcher.searchAcademicRecordData(currentStudent);
        ZZModel newZZ = new ZZModel(currentStudent, currentStudentAcademicRecords.get(currentStudentAcademicRecords.size() - 1).getSERIES_NUMBER(), tf2.getText());
        newZZ.insert();
        for (DZZModel dzz : DZZs) {
            dzz.setZZ_ID(newZZ.getID());
            dzz.insert();
        }
        Stage st = (Stage) addButton.getScene().getWindow();
        st.close();
    }

    @FXML
    void initialize() {
        priority.setStyle("-fx-alignment: CENTER;");
        direction.setStyle("-fx-align: JUSTIFY;");
        level.setStyle("-fx-alignment: CENTER;");
        form.setStyle("-fx-alignment: CENTER;");
        priority.setCellValueFactory(new PropertyValueFactory<DZZModel, String>("PRIORITY"));
        direction.setCellValueFactory(new PropertyValueFactory<DZZModel, String>("DIRECTION_ID_NAME"));
        level.setCellValueFactory(new PropertyValueFactory<DZZModel, String>("ATTAINMENT_NAME"));
        form.setCellValueFactory(new PropertyValueFactory<DZZModel, String>("EDUCATION_FORM_NAME"));

        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert priority != null : "fx:id=\"priority\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert direction != null : "fx:id=\"direction\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert level != null : "fx:id=\"level\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert form != null : "fx:id=\"form\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert addDirection != null : "fx:id=\"addDirection\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert educationFormTF != null : "fx:id=\"educationFormTF\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi1 != null : "fx:id=\"mi1\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi2 != null : "fx:id=\"mi2\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi3 != null : "fx:id=\"mi3\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert priorityTF != null : "fx:id=\"priorityTF\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi4 != null : "fx:id=\"mi4\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi5 != null : "fx:id=\"mi5\" was not injected: check your FXML file 'addZZView.fxml'.";
        assert mi6 != null : "fx:id=\"mi6\" was not injected: check your FXML file 'addZZView.fxml'.";

        educationFormTF.setDisable(true);
        mi1.setDisable(true);
        mi2.setDisable(true);
        mi3.setDisable(true);
        priorityTF.setDisable(true);
        mi4.setDisable(true);
        mi5.setDisable(true);
        mi6.setDisable(true);
        addDirection.setDisable(true);
        addButton.setDisable(true);
    }
}