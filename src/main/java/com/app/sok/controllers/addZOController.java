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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addZOController {

    @FXML
    private MenuButton chooseReason;

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
    private MenuItem mi1;

    @FXML
    private MenuItem mi2;

    @FXML
    private MenuItem mi3;

    @FXML
    private MenuItem mi4;

    @FXML
    private MenuItem mi5;

    @FXML
    private MenuItem mi6;

    @FXML
    private MenuItem mi7;

    @FXML
    private ListView<String> ListView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button addButton2;

    UZModel selectedUZ;
    private String selectedZType = "";
    private searcherModel searcher = new searcherModel();
    ArrayList<UZModel> lastSearchResult = null;

    @FXML
    void onKeyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (!searchTextField.getText().equals("")) {
            ArrayList<UZModel> foundUZs = searcher.searchVUZes(searchTextField.getText());
            lastSearchResult = foundUZs;
            if (foundUZs != null) {
                ObservableList<String> results = FXCollections.observableArrayList();
                for (UZModel UZ : foundUZs) results.add(UZ.getNAME());
                lastSearchResult = foundUZs;
                ListView.setItems(results);
            }
        } else if (!ListView.getItems().isEmpty())
            ListView.getItems().clear();
    }

    @FXML
    void ViewListClicked(MouseEvent event) {
        if (lastSearchResult != null) {
            int index = ListView.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                selectedUZ = lastSearchResult.get(index);
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

        if (!selectedZType.equals("")) {
            if (selectedZType == "2")
                new ZO2Model(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(), selectedUZ.getID(), tf5.getText()).insert();
            else
                new ZOModel(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber(), selectedZType, tf5.getText()).insert();
            Stage st = (Stage) addButton2.getScene().getWindow();
            st.close();
        }
    }

    @FXML
    void mi1Clicked(ActionEvent event) {
        chooseReason.setText(mi1.getText());
        selectedZType = "1";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }

    @FXML
    void mi2Clicked(ActionEvent event) {
        chooseReason.setText(mi2.getText());
        selectedZType = "2";
        searchTextField.setDisable(false);
        ListView.setDisable(false);
        addButton2.setDisable(false);
    }

    @FXML
    void mi3Clicked(ActionEvent event) {
        chooseReason.setText(mi3.getText());
        selectedZType = "3";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }

    @FXML
    void mi4Clicked(ActionEvent event) {
        chooseReason.setText(mi4.getText());
        selectedZType = "4";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }

    @FXML
    void mi5Clicked(ActionEvent event) {
        chooseReason.setText(mi5.getText());
        selectedZType = "5";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }

    @FXML
    void mi6Clicked(ActionEvent event) {
        chooseReason.setText(mi6.getText());
        selectedZType = "6";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }

    @FXML
    void mi7Clicked(ActionEvent event) {
        chooseReason.setText(mi7.getText());
        selectedZType = "7";
        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }


    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert l1 != null : "fx:id=\"l1\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert l2 != null : "fx:id=\"l2\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert addButton2 != null : "fx:id=\"addButton2\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert l3 != null : "fx:id=\"l3\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert tf5 != null : "fx:id=\"tf5\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert chooseReason != null : "fx:id=\"chooseReason\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi1 != null : "fx:id=\"mi1\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi2 != null : "fx:id=\"mi2\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi3 != null : "fx:id=\"mi3\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi4 != null : "fx:id=\"mi4\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi5 != null : "fx:id=\"mi5\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi6 != null : "fx:id=\"mi6\" was not injected: check your FXML file 'addZOView.fxml'.";
        assert mi7 != null : "fx:id=\"mi7\" was not injected: check your FXML file 'addZOView.fxml'.";

        searchTextField.setDisable(true);
        ListView.setDisable(true);
        addButton2.setDisable(true);
    }
}
