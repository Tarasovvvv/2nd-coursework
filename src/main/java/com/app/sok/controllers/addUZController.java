package com.app.sok.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.app.sok.Application;
import com.app.sok.models.UZModel;
import com.app.sok.models.employeeModel;
import com.app.sok.models.searcherModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addUZController {

    @FXML
    private URL location;

    @FXML
    private MenuItem mi1;

    @FXML
    private MenuItem mi2;

    @FXML
    private MenuItem mi3;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf5;

    @FXML
    private Button add;

    @FXML
    private MenuButton type;

    @FXML
    private TextField tf6;

    @FXML
    private TextField tf7;

    @FXML
    private TextField tf8;

    @FXML
    private TextField tf9;

    @FXML
    private TextField tf10;

    private int UZtype = 3;

    @FXML
    void selectShcool(ActionEvent event) {
        type.setText(mi1.getText());
        UZtype = 1;
    }

    @FXML
    void selectPU(ActionEvent event) {
        type.setText(mi2.getText());
        UZtype = 2;
    }

    @FXML
    void selectVUZ(ActionEvent event) {
        type.setText(mi3.getText());
        UZtype = 3;
    }

    @FXML
    void adding(MouseEvent event) throws SQLException, ClassNotFoundException {
        employeeModel director = new employeeModel(tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText(), tf10.getText());
        if (!tf6.getText().equals(""))director.insert();


        String uz_id = new searcherModel().newId("Учебное заведение", "Код учебного заведения");
        UZModel UZ = new UZModel(uz_id, String.valueOf(UZtype), director.getID(), tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(), director);
        UZ.insert();

        Stage st = (Stage) add.getScene().getWindow();
        st.close();
    }

    @FXML
    void initialize() {
        Application.getCURRENT_STAGE().setResizable(false);
        assert tf1 != null : "fx:id=\"tf1\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf2 != null : "fx:id=\"tf2\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf4 != null : "fx:id=\"tf4\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf3 != null : "fx:id=\"tf3\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf5 != null : "fx:id=\"tf5\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert mi1 != null : "fx:id=\"mi1\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert mi2 != null : "fx:id=\"mi2\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert mi3 != null : "fx:id=\"mi3\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf6 != null : "fx:id=\"tf6\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf7 != null : "fx:id=\"tf7\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf8 != null : "fx:id=\"tf8\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf9 != null : "fx:id=\"tf9\" was not injected: check your FXML file 'addUZView.fxml'.";
        assert tf10 != null : "fx:id=\"tf10\" was not injected: check your FXML file 'addUZView.fxml'.";

    }
}
