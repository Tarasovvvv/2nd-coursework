package com.app.sok.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class orderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PZModel> table;

    @FXML
    private TableView<POModel> table1;

    @FXML
    private MenuItem r1;

    @FXML
    private MenuItem r2;

    @FXML
    private MenuItem r3;

    @FXML
    private MenuItem r4;

    @FXML
    private MenuItem r5;

    @FXML
    private TableView<PONPModel> table8;

    @FXML
    private TableView<PUAOModel> table2;

    @FXML
    private TableView<PPVAOModel> table3;

    @FXML
    private TableView<PPVAOModel> table4;

    @FXML
    private TableView<PVModel> table5;

    @FXML
    private TableView<PPGModel> table6;

    @FXML
    private TableView<PPCModel> table7;

    @FXML
    private Label signDate;

    @FXML
    private Label releaseDate;

    @FXML
    private Button releaseButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> list;

    @FXML
    private Button chooseStudent;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf2;

    @FXML
    private MenuButton chooseReason;

    @FXML
    private MenuButton chooseOrder;

    @FXML
    private MenuItem z;

    @FXML
    private MenuItem o;

    @FXML
    private MenuItem o2;

    @FXML
    private MenuItem uao;

    @FXML
    private MenuItem pao;

    @FXML
    private MenuItem vao;

    @FXML
    private MenuItem v;

    @FXML
    private MenuItem pg;

    @FXML
    private MenuItem pc;

    @FXML
    private ComboBox<String> chooseGroup;

    @FXML
    private ComboBox<String> chooseDirection;

    @FXML
    private ComboBox<String> chooseSigner;

    studentModel selectedStudent;
    employeeModel selectedSigner;
    directionModel selectedDirection;
    String selectedReasonID;
    String selectedGroupCourse;
    searcherModel searcher = new searcherModel();
    ArrayList<employeeModel> Signers = new ArrayList<employeeModel>();
    ArrayList<directionModel> directions = new ArrayList<directionModel>();
    ArrayList<PZModel> PZs = new ArrayList<PZModel>();
    ArrayList<POModel> POs = new ArrayList<POModel>();
    ArrayList<PONPModel> PONPs = new ArrayList<PONPModel>();
    ArrayList<PUAOModel> PUAOs = new ArrayList<PUAOModel>();
    ArrayList<PPVAOModel> PPVAOs = new ArrayList<PPVAOModel>();
    ArrayList<PVModel> PVs = new ArrayList<PVModel>();
    ArrayList<PPGModel> PPGs = new ArrayList<PPGModel>();
    ArrayList<PPCModel> PPCs = new ArrayList<PPCModel>();
    ArrayList<studentModel> lastSearchResult = new ArrayList<studentModel>();
    ArrayList<studentModel> pickedStudents = new ArrayList<studentModel>();
    String orderTypeId = "0";

    private void selectOrderType() {
        selectedStudent = null;
        PZs.clear();
        pickedStudents.clear();
        lastSearchResult.clear();
        list.setDisable(false);
        list.getItems().clear();
        searchTextField.setDisable(false);
        searchTextField.setText("");
        chooseGroup.getItems().clear();
        table.setVisible(chooseOrder.getText().equals("Зачисление"));
        table1.setVisible(chooseOrder.getText().equals("Отчисление"));
        table8.setVisible(chooseOrder.getText().equals("Отчисление по неуважительной причине"));
        table2.setVisible(chooseOrder.getText().equals("Уход в академический отпуск"));
        table3.setVisible(chooseOrder.getText().equals("Продление академического отпуска"));
        table4.setVisible(chooseOrder.getText().equals("Выход из академического отпуска"));
        table5.setVisible(chooseOrder.getText().equals("Восстановление"));
        table6.setVisible(chooseOrder.getText().equals("Перевод в другую группу"));
        table7.setVisible(chooseOrder.getText().equals("Перевод на следующий курс"));
        PZs.clear();
        POs.clear();
        PONPs.clear();
        PUAOs.clear();
        PPVAOs.clear();
        PVs.clear();
        PPGs.clear();
        PPCs.clear();
        table.getItems().clear();
        table1.getItems().clear();
        table2.getItems().clear();
        table3.getItems().clear();
        table4.getItems().clear();
        table5.getItems().clear();
        table6.getItems().clear();
        table7.getItems().clear();
        table8.getItems().clear();

    }

    private void checkRequiresToUnlockReleaseButton() {
        if (chooseSigner.getValue() != null && !chooseSigner.getValue().equals(""))
                releaseButton.setDisable(false);
        else releaseButton.setDisable(true);
    }

    @FXML
    void Zchoosed(ActionEvent event) {
        chooseOrder.setText("Зачисление");
        releaseDate.setText("Дата зачисления");
        chooseReason.setText("Выбрать основание отчисления");
        chooseDirection.setDisable(false);
        chooseReason.setDisable(true);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "1";
    }

    @FXML
    void Ochoosed(ActionEvent event) {
        chooseOrder.setText("Отчисление");
        releaseDate.setText("Дата отчисления");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "2";
    }

    @FXML
    void O2choosed(ActionEvent event) {
        chooseOrder.setText("Отчисление по неуважительной причине");
        releaseDate.setText("Дата отчисления");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(false);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "3";
    }

    @FXML
    void UAOchoosed(ActionEvent event) {
        chooseOrder.setText("Уход в академический отпуск");
        releaseDate.setText("Дата исполнения");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(true);
        tf2.setText(tf3.getText());
        selectOrderType();
        orderTypeId = "4";
    }

    @FXML
    void PAOchoosed(ActionEvent event) {
        chooseOrder.setText("Продление академического отпуска");
        releaseDate.setText("Дата исполнения");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(true);
        tf2.setText(tf3.getText());
        selectOrderType();
        orderTypeId = "5";
    }

    @FXML
    void VAOchoosed(ActionEvent event) {
        chooseOrder.setText("Выход из академического отпуска");
        releaseDate.setText("Дата исполнения");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(true);
        tf2.setText(tf3.getText());
        selectOrderType();
        orderTypeId = "6";
    }

    @FXML
    void Vchoosed(ActionEvent event) {
        chooseOrder.setText("Восстановление");
        releaseDate.setText("Дата восстановления");
        chooseGroup.setPromptText("Выбрать группу");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "7";
    }

    @FXML
    void PGchoosed(ActionEvent event) {
        chooseOrder.setText("Перевод в другую группу");
        releaseDate.setText("Дата перевода");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "8";
    }

    @FXML
    void PCchoosed(ActionEvent event) {
        chooseOrder.setText("Перевод на следующий курс");
        releaseDate.setText("Дата перевода");
        chooseDirection.setPromptText("Выбрать направление");
        chooseReason.setText("Выбрать основание отчисления");
        chooseGroup.setDisable(false);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);
        tf2.setDisable(false);
        selectOrderType();
        orderTypeId = "9";
    }

    @FXML
    void onGroupChoosing(ActionEvent event) {

    }

    @FXML
    void r1Clicked(ActionEvent event) {
        chooseReason.setText(r1.getText());
        selectedReasonID = "8";
    }

    @FXML
    void r2Clicked(ActionEvent event) {
        chooseReason.setText(r2.getText());
        selectedReasonID = "9";
    }

    @FXML
    void r3Clicked(ActionEvent event) {
        chooseReason.setText(r3.getText());
        selectedReasonID = "10";
    }

    @FXML
    void r4Clicked(ActionEvent event) {
        chooseReason.setText(r4.getText());
        selectedReasonID = "11";
    }

    @FXML
    void r5Clicked(ActionEvent event) {
        chooseReason.setText(r5.getText());
        selectedReasonID = "12";
    }

    @FXML
    void onDirectionChoosing(ActionEvent event) throws SQLException, ClassNotFoundException {
        chooseGroup.setDisable(false);
        if (chooseDirection.getSelectionModel().getSelectedIndex() > -1) {
            selectedDirection = directions.get(chooseDirection.getSelectionModel().getSelectedIndex());
            ObservableList<String> groups = FXCollections.observableArrayList();
            groups.addAll(searcher.searchGroupsToZZ(chooseDirection.getValue().substring(0,6)));
            chooseGroup.setItems(groups);
        }
    }

    @FXML
    void onChoosingSigner(ActionEvent event) {
        selectedSigner = Signers.get(chooseSigner.getSelectionModel().getSelectedIndex());
        checkRequiresToUnlockReleaseButton();
    }

    @FXML
    void onChoosingReason(ActionEvent event) {
        checkRequiresToUnlockReleaseButton();
    }

    @FXML
    void onTableKeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PZs.remove(index);
                table.setItems(FXCollections.observableArrayList(PZs));
            }
        }
    }

    @FXML
    void onTable1KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table1.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                POs.remove(index);
                table1.setItems(FXCollections.observableArrayList(POs));
            }
        }
    }

    @FXML
    void onTable8KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table8.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PONPs.remove(index);
                table8.setItems(FXCollections.observableArrayList(PONPs));
            }
        }
    }

    @FXML
    void onTable2KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table2.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PUAOs.remove(index);
                table2.setItems(FXCollections.observableArrayList(PUAOs));
            }
        }
    }

    @FXML
    void onTable3KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table3.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PPVAOs.remove(index);
                table3.setItems(FXCollections.observableArrayList(PPVAOs));
            }
        }
    }

    @FXML
    void onTable4KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table4.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PPVAOs.remove(index);
                table4.setItems(FXCollections.observableArrayList(PPVAOs));
            }
        }
    }

    @FXML
    void onTable5KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table5.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PVs.remove(index);
                table5.setItems(FXCollections.observableArrayList(PVs));
            }
        }
    }

    @FXML
    void onTable6KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table6.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PPGs.remove(index);
                table6.setItems(FXCollections.observableArrayList(PPGs));
            }
        }
    }

    @FXML
    void onTable7KeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            int index = table7.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                pickedStudents.remove(index);
                PPCs.remove(index);
                table7.setItems(FXCollections.observableArrayList(PPCs));

            }
        }
    }

    @FXML
    void tf3KeyTyped(KeyEvent event) {
        checkRequiresToUnlockReleaseButton();
        if (orderTypeId.matches("[456]"))
            tf2.setText(tf3.getText());
    }

    @FXML
    void tf2KeyTyped(KeyEvent event) {
        checkRequiresToUnlockReleaseButton();
    }

    @FXML
    void chooseStudentClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        switch (orderTypeId) {
            case "1" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    PZModel newPZ = new PZModel(selectedStudent.getLatestPassport().getSeriesNumber(), selectedStudent.getLatestPassport().getFIO(),
                            chooseDirection.getValue(), chooseGroup.getValue(), searcher.searchLatestZZ(selectedStudent.getId()).getID(), selectedDirection.getID());
                    PZs.add(newPZ);
                    table.setItems(FXCollections.observableArrayList(PZs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "2" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZOModel latestZO = searcher.searchLatestZO(selectedStudent.getId());
                    POModel newPO = new POModel(selectedStudent.getLatestPassport().getSeriesNumber(), selectedStudent.getLatestPassport().getFIO(),
                            searcher.searchOReason(latestZO.getREASON_ID()), latestZO.getID());
                    POs.add(newPO);
                    table1.setItems(FXCollections.observableArrayList(POs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "3" -> {
                if (!chooseReason.getText().equals("Выбрать основание отчисления"))
                    if (!pickedStudents.contains(selectedStudent)) {
                        PONPModel newPONP = new PONPModel(selectedStudent.getLatestPassport().getSeriesNumber(), selectedReasonID,
                                selectedStudent.getLatestPassport().getFIO(), chooseReason.getText());
                        PONPs.add(newPONP);
                        table8.setItems(FXCollections.observableArrayList(PONPs));
                        pickedStudents.add(selectedStudent);
                    }
            }
            case "4" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZAOModel latestZAO = searcher.searchLatestZUAO(selectedStudent.getId());
                    PUAOModel newPUAO = new PUAOModel(latestZAO.getID(),
                            selectedStudent.getLatestPassport().getSeriesNumber(), selectedReasonID, latestZAO.getSTART_DATE(), latestZAO.getEND_DATE(),
                            selectedStudent.getLatestPassport().getFIO(), chooseReason.getText());
                    PUAOs.add(newPUAO);
                    table2.setItems(FXCollections.observableArrayList(PUAOs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "5" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZAO2Model latestZAO2 = searcher.searchLatestZPVAO(selectedStudent.getId());
                    PPVAOModel newPPVAO = new PPVAOModel(latestZAO2.getID(),
                            selectedStudent.getLatestPassport().getSeriesNumber(), latestZAO2.getEXIT_DATE(),
                            selectedStudent.getLatestPassport().getFIO());
                    PPVAOs.add(newPPVAO);
                    table3.setItems(FXCollections.observableArrayList(PPVAOs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "6" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZAO2Model latestZAO2 = searcher.searchLatestZPVAO(selectedStudent.getId());
                    PPVAOModel newPPVAO = new PPVAOModel(latestZAO2.getID(),
                            selectedStudent.getLatestPassport().getSeriesNumber(), latestZAO2.getEXIT_DATE(),
                            selectedStudent.getLatestPassport().getFIO());
                    PPVAOs.add(newPPVAO);
                    table4.setItems(FXCollections.observableArrayList(PPVAOs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "7" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZVModel latestZV = searcher.searchLatestZV(selectedStudent.getId());
                    PVModel newPV = new PVModel(selectedStudent.getLatestPassport().getSeriesNumber(), latestZV.getID(), selectedStudent.getLatestPassport().getFIO());
                    PVs.add(newPV);
                    table5.setItems(FXCollections.observableArrayList(PVs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "8" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    ZPGModel latestZPG = searcher.searchLatestZPG(selectedStudent.getId());
                    PPGModel newPPG = new PPGModel(selectedStudent.getLatestPassport().getSeriesNumber(), latestZPG.getID(),
                            selectedStudent.getLatestPassport().getFIO(), latestZPG.getGROUP_ID());
                    PPGs.add(newPPG);
                    System.out.println(newPPG.getFIO() + " " + newPPG.getGROUP_ID());
                    table6.setItems(FXCollections.observableArrayList(PPGs));
                    pickedStudents.add(selectedStudent);
                }
            }
            case "9" -> {
                if (!pickedStudents.contains(selectedStudent)) {
                    PPCModel newPPC = new PPCModel(selectedStudent.getLatestPassport().getSeriesNumber(),
                            selectedStudent.getLatestPassport().getFIO(), chooseGroup.getValue(), selectedGroupCourse);
                    PPCs.add(newPPC);
                    table7.setItems(FXCollections.observableArrayList(PPCs));
                    pickedStudents.add(selectedStudent);
                }
            }
        }
    }

    @FXML
    void keyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        list.getItems().clear();
        selectedStudent = null;
        if (!searchTextField.getText().equals("")) {
            switch (orderTypeId) {
                case "1" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZZData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "2" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZOData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "3", "9" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "4" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZUAOData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "5", "6" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZPVAOData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "7" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZVData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
                case "8" -> {
                    ArrayList<studentModel> foundStudents = searcher.searchStudentWithZPGData(searchTextField.getText());
                    lastSearchResult = foundStudents;
                    if (foundStudents != null) {
                        ObservableList<String> results = FXCollections.observableArrayList();
                        for (studentModel student : foundStudents) {
                            passportModel latestPassport = student.getLatestPassport();
                            results.add(latestPassport.getLastName() + " " + latestPassport.getName() + " " + latestPassport.getFathersName());
                        }
                        list.setItems(results);
                    }
                }
            }

        }
    }

    @FXML
    void listClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (lastSearchResult != null) {
            int index = list.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                selectedStudent = lastSearchResult.get(index);
                chooseDirection.getItems().clear();
                chooseGroup.getItems().clear();
                switch (orderTypeId) {
                    case "1" -> {
                        ObservableList<String> directions2 = FXCollections.observableArrayList();
                        for (String DZZid : searcher.searchDZZids(selectedStudent.getLatestPassport().getSeriesNumber())) {
                            directionModel foundDirection = searcher.searchDirectionData(DZZid).get(0);
                            directions.add(foundDirection);
                            System.out.println(foundDirection.getID() + " " + foundDirection.getNAME());
                            directions2.add(foundDirection.getID() + " " + foundDirection.getNAME());
                        }
                        chooseDirection.setItems(directions2);
                    }
                    case "9" -> {
                        groupModel currentGroup = searcher.searchLatestPZDirectionID(selectedStudent.getLatestPassport().getSeriesNumber());
                        ArrayList<groupModel> groups = searcher.searchGroupsNextCourseByDirectionIDAndCourse(currentGroup.getDIRECTION_ID(), currentGroup.getCOURSE());
                        ObservableList<String> groups_ids = FXCollections.observableArrayList();
                        for (groupModel group : groups)
                            groups_ids.add(group.getGROUP_ID());
                        selectedGroupCourse = groups.get(0).getCOURSE();
                        System.out.println(selectedGroupCourse);
                        chooseGroup.setItems(groups_ids);
                    }
                }
                chooseStudent.setDisable(false);
            } else {
                chooseStudent.setDisable(true);
            }
        } else {
            chooseStudent.setDisable(true);
        }
    }

    

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert o2 != null : "fx:id=\"o2\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table1 != null : "fx:id=\"table1\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table2 != null : "fx:id=\"table2\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table3 != null : "fx:id=\"table3\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table4 != null : "fx:id=\"table4\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table5 != null : "fx:id=\"table5\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table6 != null : "fx:id=\"table6\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table7 != null : "fx:id=\"table7\" was not injected: check your FXML file 'orderView.fxml'.";
        assert table8 != null : "fx:id=\"table8\" was not injected: check your FXML file 'orderView.fxml'.";
        assert signDate != null : "fx:id=\"signDate\" was not injected: check your FXML file 'orderView.fxml'.";
        assert releaseDate != null : "fx:id=\"releaseDate\" was not injected: check your FXML file 'orderView.fxml'.";
        assert releaseButton != null : "fx:id=\"releaseButton\" was not injected: check your FXML file 'orderView.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'orderView.fxml'.";
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseStudent != null : "fx:id=\"chooseStudent\" was not injected: check your FXML file 'orderView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'orderView.fxml'.";
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseOrder != null : "fx:id=\"chooseOrder\" was not injected: check your FXML file 'orderView.fxml'.";
        assert z != null : "fx:id=\"z\" was not injected: check your FXML file 'orderView.fxml'.";
        assert o != null : "fx:id=\"o\" was not injected: check your FXML file 'orderView.fxml'.";
        assert uao != null : "fx:id=\"uao\" was not injected: check your FXML file 'orderView.fxml'.";
        assert pao != null : "fx:id=\"pao\" was not injected: check your FXML file 'orderView.fxml'.";
        assert vao != null : "fx:id=\"vao\" was not injected: check your FXML file 'orderView.fxml'.";
        assert v != null : "fx:id=\"v\" was not injected: check your FXML file 'orderView.fxml'.";
        assert pg != null : "fx:id=\"pg\" was not injected: check your FXML file 'orderView.fxml'.";
        assert pc != null : "fx:id=\"pc\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseGroup != null : "fx:id=\"chooseGroup\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseDirection != null : "fx:id=\"chooseDirection\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseSigner != null : "fx:id=\"chooseSigner\" was not injected: check your FXML file 'orderView.fxml'.";
        assert chooseReason != null : "fx:id=\"chooseReason\" was not injected: check your FXML file 'orderView.fxml'.";
        assert r1 != null : "fx:id=\"r1\" was not injected: check your FXML file 'orderView.fxml'.";
        assert r2 != null : "fx:id=\"r2\" was not injected: check your FXML file 'orderView.fxml'.";
        assert r3 != null : "fx:id=\"r3\" was not injected: check your FXML file 'orderView.fxml'.";
        assert r4 != null : "fx:id=\"r4\" was not injected: check your FXML file 'orderView.fxml'.";
        assert r5 != null : "fx:id=\"r5\" was not injected: check your FXML file 'orderView.fxml'.";

        ObservableList<String> signers_fios = FXCollections.observableArrayList();
        Signers = searcher.searchSigners();
        for (employeeModel signer : Signers)
            signers_fios.add(signer.getFIO());
        chooseSigner.setItems(FXCollections.observableArrayList(signers_fios));

        list.setDisable(true);
        chooseGroup.setDisable(true);
        chooseStudent.setDisable(true);
        releaseButton.setDisable(true);
        searchTextField.setDisable(true);
        chooseDirection.setDisable(true);
        chooseReason.setDisable(true);

        TableColumn<PZModel, String> fioColumnPZ = new TableColumn<PZModel, String>("ФИО");
        TableColumn<PZModel, String> directionColumnPZ = new TableColumn<PZModel, String>("Направление");
        TableColumn<PZModel, String> groupColumnPZ = new TableColumn<PZModel, String>("Группа");

        fioColumnPZ.setCellValueFactory(new PropertyValueFactory<PZModel, String>("FIO"));
        directionColumnPZ.setCellValueFactory(new PropertyValueFactory<PZModel, String>("DIRECTION"));
        groupColumnPZ.setCellValueFactory(new PropertyValueFactory<PZModel, String>("GROUP"));

        fioColumnPZ.setStyle("-fx-alignment: CENTER;");
        directionColumnPZ.setStyle("-fx-alignment: CENTER;");
        groupColumnPZ.setStyle("-fx-alignment: CENTER;");

        fioColumnPZ.setMaxWidth(199);
        fioColumnPZ.setPrefWidth(199);
        directionColumnPZ.setMaxWidth(498);
        directionColumnPZ.setPrefWidth(498);
        groupColumnPZ.setMaxWidth(100);
        groupColumnPZ.setPrefWidth(100);

        table.getColumns().addAll(fioColumnPZ, directionColumnPZ, groupColumnPZ);

        TableColumn<POModel, String> fioColumnPO = new TableColumn<POModel, String>("ФИО");
        TableColumn<POModel, String> reasonColumnPO = new TableColumn<POModel, String>("Причина отчисления");

        fioColumnPO.setCellValueFactory(new PropertyValueFactory<POModel, String>("FIO"));
        reasonColumnPO.setCellValueFactory(new PropertyValueFactory<POModel, String>("REASON"));

        fioColumnPO.setStyle("-fx-alignment: CENTER;");
        reasonColumnPO.setStyle("-fx-alignment: CENTER;");

        fioColumnPO.setMaxWidth(199);
        fioColumnPO.setPrefWidth(199);
        reasonColumnPO.setMaxWidth(598);
        reasonColumnPO.setPrefWidth(598);

        table1.getColumns().addAll(fioColumnPO, reasonColumnPO);

        TableColumn<PONPModel, String> fioColumnPONP = new TableColumn<PONPModel, String>("ФИО");
        TableColumn<PONPModel, String> reasonColumnPONP = new TableColumn<PONPModel, String>("Причина отчисления");

        fioColumnPONP.setCellValueFactory(new PropertyValueFactory<PONPModel, String>("FIO"));
        reasonColumnPONP.setCellValueFactory(new PropertyValueFactory<PONPModel, String>("REASON"));

        fioColumnPONP.setStyle("-fx-alignment: CENTER;");
        reasonColumnPONP.setStyle("-fx-alignment: CENTER;");

        fioColumnPONP.setMaxWidth(199);
        fioColumnPONP.setPrefWidth(199);
        reasonColumnPONP.setMaxWidth(598);
        reasonColumnPONP.setPrefWidth(598);

        table8.getColumns().addAll(fioColumnPONP, reasonColumnPONP);

        TableColumn<PUAOModel, String> fioColumnPUAO = new TableColumn<PUAOModel, String>("ФИО");
        TableColumn<PUAOModel, String> reasonColumnPUAO = new TableColumn<PUAOModel, String>("Причина ухода");
        TableColumn<PUAOModel, String> startColumnPUAO = new TableColumn<PUAOModel, String>("Дата начала");
        TableColumn<PUAOModel, String> endColumnPUAO = new TableColumn<PUAOModel, String>("Дата конца");

        fioColumnPUAO.setCellValueFactory(new PropertyValueFactory<PUAOModel, String>("FIO"));
        reasonColumnPUAO.setCellValueFactory(new PropertyValueFactory<PUAOModel, String>("REASON"));
        startColumnPUAO.setCellValueFactory(new PropertyValueFactory<PUAOModel, String>("START_DATE"));
        endColumnPUAO.setCellValueFactory(new PropertyValueFactory<PUAOModel, String>("END_DATE"));

        fioColumnPUAO.setStyle("-fx-alignment: CENTER;");
        reasonColumnPUAO.setStyle("-fx-alignment: CENTER;");
        startColumnPUAO.setStyle("-fx-alignment: CENTER;");
        endColumnPUAO.setStyle("-fx-alignment: CENTER;");

        fioColumnPUAO.setMaxWidth(179);
        fioColumnPUAO.setPrefWidth(179);
        reasonColumnPUAO.setMaxWidth(424);
        reasonColumnPUAO.setPrefWidth(424);
        startColumnPUAO.setMaxWidth(97);
        startColumnPUAO.setPrefWidth(97);
        endColumnPUAO.setMaxWidth(97);
        endColumnPUAO.setPrefWidth(97);

        table2.getColumns().addAll(fioColumnPUAO, reasonColumnPUAO, startColumnPUAO, endColumnPUAO);

        TableColumn<PPVAOModel, String> fioColumnPPVAO1 = new TableColumn<PPVAOModel, String>("ФИО");
        TableColumn<PPVAOModel, String> exitColumnPPVAO1 = new TableColumn<PPVAOModel, String>("Дата выхода");

        fioColumnPPVAO1.setCellValueFactory(new PropertyValueFactory<PPVAOModel, String>("FIO"));
        exitColumnPPVAO1.setCellValueFactory(new PropertyValueFactory<PPVAOModel, String>("EXIT_DATE"));

        fioColumnPPVAO1.setStyle("-fx-alignment: CENTER;");
        exitColumnPPVAO1.setStyle("-fx-alignment: CENTER;");

        fioColumnPPVAO1.setMaxWidth(398);
        fioColumnPPVAO1.setPrefWidth(398);
        exitColumnPPVAO1.setMaxWidth(399);
        exitColumnPPVAO1.setPrefWidth(399);

        table3.getColumns().addAll(fioColumnPPVAO1, exitColumnPPVAO1);

        TableColumn<PPVAOModel, String> fioColumnPPVAO2 = new TableColumn<PPVAOModel, String>("ФИО");
        TableColumn<PPVAOModel, String> exitColumnPPVAO2 = new TableColumn<PPVAOModel, String>("Дата выхода");

        fioColumnPPVAO2.setCellValueFactory(new PropertyValueFactory<PPVAOModel, String>("FIO"));
        exitColumnPPVAO2.setCellValueFactory(new PropertyValueFactory<PPVAOModel, String>("EXIT_DATE"));

        fioColumnPPVAO2.setStyle("-fx-alignment: CENTER;");
        exitColumnPPVAO2.setStyle("-fx-alignment: CENTER;");

        fioColumnPPVAO2.setMaxWidth(398);
        fioColumnPPVAO2.setPrefWidth(398);
        exitColumnPPVAO2.setMaxWidth(399);
        exitColumnPPVAO2.setPrefWidth(399);

        table4.getColumns().addAll(fioColumnPPVAO2, exitColumnPPVAO2);

        TableColumn<PVModel, String> fioColumnPV = new TableColumn<PVModel, String>("ФИО");

        fioColumnPV.setCellValueFactory(new PropertyValueFactory<PVModel, String>("FIO"));

        fioColumnPV.setStyle("-fx-alignment: CENTER;");

        fioColumnPV.setMaxWidth(822);
        fioColumnPV.setPrefWidth(822);

        table5.getColumns().addAll(fioColumnPV);

        TableColumn<PPGModel, String> fioColumnPPG = new TableColumn<PPGModel, String>("ФИО");
        TableColumn<PPGModel, String> groupColumnPPG = new TableColumn<PPGModel, String>("Новая группа");

        fioColumnPPG.setCellValueFactory(new PropertyValueFactory<PPGModel, String>("FIO"));
        groupColumnPPG.setCellValueFactory(new PropertyValueFactory<PPGModel, String>("GROUP_ID"));

        fioColumnPPG.setStyle("-fx-alignment: CENTER;");
        groupColumnPPG.setStyle("-fx-alignment: CENTER;");

        fioColumnPPG.setMaxWidth(411);
        fioColumnPPG.setPrefWidth(411);
        groupColumnPPG.setMaxWidth(411);
        groupColumnPPG.setPrefWidth(411);

        table6.getColumns().addAll(fioColumnPPG, groupColumnPPG);

        TableColumn<PPCModel, String> fioColumnPPC = new TableColumn<PPCModel, String>("ФИО");
        TableColumn<PPCModel, String> courseColumnPPC = new TableColumn<PPCModel, String>("Следующий курс");
        TableColumn<PPCModel, String> groupColumnPPC = new TableColumn<PPCModel, String>("Новая группа");

        fioColumnPPC.setCellValueFactory(new PropertyValueFactory<PPCModel, String>("FIO"));
        courseColumnPPC.setCellValueFactory(new PropertyValueFactory<PPCModel, String>("COURSE"));
        groupColumnPPC.setCellValueFactory(new PropertyValueFactory<PPCModel, String>("GROUP_ID"));

        fioColumnPPC.setStyle("-fx-alignment: CENTER;");
        courseColumnPPC.setStyle("-fx-alignment: CENTER;");
        groupColumnPPC.setStyle("-fx-alignment: CENTER;");

        fioColumnPPC.setMaxWidth(411);
        fioColumnPPC.setPrefWidth(411);
        courseColumnPPC.setMaxWidth(206);
        courseColumnPPC.setPrefWidth(206);
        groupColumnPPC.setMaxWidth(205);
        groupColumnPPC.setPrefWidth(205);

        table7.getColumns().addAll(fioColumnPPC, courseColumnPPC, groupColumnPPC);
    }
}