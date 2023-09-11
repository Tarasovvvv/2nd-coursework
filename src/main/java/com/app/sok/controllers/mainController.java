package com.app.sok.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static com.itextpdf.text.html.HtmlTags.FONT;

public class mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitAccountButton;

    @FXML
    private ListView<String> studentsListView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label fioLabel;

    @FXML
    private MenuItem addPassportMenuItem;

    @FXML
    private MenuItem addAcademicRecordMenuItem;

    @FXML
    private MenuItem addDPPESMenuItem;

    @FXML
    private MenuItem addZZMenuItem;

    @FXML
    private MenuItem addZAOMenuItem;

    @FXML
    private MenuItem addZOMenuItem;

    @FXML
    private MenuItem addZVMenuItem;

    @FXML
    private MenuItem addZPGMenuItem;

    @FXML
    private Button releaseOrder;

    @FXML
    private ComboBox<String> choosePassport;

    @FXML
    private Label fioStudent;

    @FXML
    private Label birthdate;

    @FXML
    private Label passportSN;

    @FXML
    private Label releaseData;

    @FXML
    private Label address;

    @FXML
    private Label status;

    @FXML
    private Label direction;

    @FXML
    private Label group;

    @FXML
    private Label course;

    @FXML
    private Label fundingForm;

    @FXML
    private Label educationForm;

    @FXML
    private TableView<documentModel> table;

    @FXML
    private TableColumn<documentModel, String> code;

    @FXML
    private TableColumn<documentModel, String> docType;

    @FXML
    private TableColumn<documentModel, String> signData;

    @FXML
    private TableColumn<documentModel, String> signPerson;

    @FXML
    private TableColumn<documentModel, String> releasePerson;

    @FXML
    private MenuButton orderMenu;

    @FXML
    private MenuButton doxMenu;

    @FXML
    private Button updateButton;

    studentModel selectedStudent;
    passportModel selectedPassport;

    private searcherModel searcher = new searcherModel();

    ArrayList<studentModel> lastSearchResult = null;

    private void turnAdding(boolean flag) {
        if (flag) {
            addZAOMenuItem.setDisable(false);
            addDPPESMenuItem.setDisable(false);
            addAcademicRecordMenuItem.setDisable(false);
            addZOMenuItem.setDisable(false);
            addZPGMenuItem.setDisable(false);
            addZVMenuItem.setDisable(false);
            addZZMenuItem.setDisable(false);
        } else {
            addZAOMenuItem.setDisable(true);
            addDPPESMenuItem.setDisable(true);
            addAcademicRecordMenuItem.setDisable(true);
            addZOMenuItem.setDisable(true);
            addZPGMenuItem.setDisable(true);
            addZVMenuItem.setDisable(true);
            addZZMenuItem.setDisable(true);
        }
    }

    private void clearStudentData() {
        choosePassport.setItems(FXCollections.observableArrayList());
        choosePassport.setPromptText("Студент не выбран");
        fioStudent.setText("ФИО");
        birthdate.setText("Дата рождения");
        passportSN.setText("Серия и номер паспорта");
        releaseData.setText("Код подразделения, кем выдан, дата выдачи");
        address.setText("Адрес региистрации, дата регистрации");
        status.setText("Статус");
        direction.setText("Направление");
        group.setText("Группа");
        course.setText("Курс");
        fundingForm.setText("Форма финансирования");
        educationForm.setText("Форма обучения");
        table.setItems(FXCollections.observableArrayList());

    }

    @FXML
    void onAddAcademicRecordMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addAcademicRecordView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавить документ об образовании");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddDPPESMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addDPPESView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление документа об оказании платных образовательных услуг");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddPassportMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addPassportView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление паспорта");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddZAOMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addZAOView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление заявления об академическом отпуске");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddZOMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addZOView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление заявления об отчислении");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddZPGMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addZPGView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление заявления о переводе в другую группу");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddZVMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addZVView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление заявления о восстановлении");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onAddZZMenuItemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addZZView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Добавление заявления о зачислении");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();

    }

    @FXML
    void onExitAccountButtonClicked(MouseEvent event) throws IOException {
        Application.setCURRENT_USER("");
        Application.switchScene("authView.fxml", "");
        fioLabel.setText("Не авторизирован");
    }

    @FXML
    void onReleaseOrderClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("orderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage tempStage = new Stage();
        tempStage.setTitle("Исполнение приказа");
        tempStage.setScene(scene);
        tempStage.setResizable(false);
        tempStage.show();
    }

    @FXML
    void onKeyTyped(KeyEvent event) throws SQLException, ClassNotFoundException {
        studentsListView.getItems().clear();
        selectedStudent = null;
        clearStudentData();
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
        }
    }

    @FXML
    void contextMenuAction(ActionEvent event){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("document.pdf"));
            document.open();
            document.addTitle("Отчет о паспорте");
            Font font = FontFactory.getFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            document.add(new Paragraph(selectedPassport.getFIO() + "(" + selectedPassport.getGender().substring(0,3)+ ".)", font));
            document.add(new Paragraph("Серия и номер паспорта: " + selectedPassport.getSeriesNumber(), font));
            document.add(new Paragraph("Дата и место рождения: " + selectedPassport.getBirthDate() + " " + selectedPassport.getBirthCountry() + ", "
                    + selectedPassport.getBirthCity(), font));
            document.add(new Paragraph("Дата и место выдачи: " + selectedPassport.getReleaseDate() + ", " + selectedPassport.getKodPodr() + " "
                    + selectedPassport.getWhoRelease(), font));
            document.add(new Paragraph("Дата и адрес регистрации: " + selectedPassport.getRegistrationDate() + ", " +
                    selectedPassport.getRegion() + ", " + selectedPassport.getPunct() + ", " + selectedPassport.getDistrict() + " р-н, ул. " +
                    selectedPassport.getStreet() + ", д. " + selectedPassport.getHouse() + ", кв. " + selectedPassport.getApartment() + " ", font));
            document.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    @FXML
    void updateTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (lastSearchResult != null) {
            ArrayList<academicRecordModel> academicRecords = new ArrayList<academicRecordModel>();
            ArrayList<DPPESModel> DPPESs = new ArrayList<DPPESModel>();
            ArrayList<ZZModel> ZZs = new ArrayList<ZZModel>();
            ArrayList<ZAOModel> ZAOs = new ArrayList<ZAOModel>();
            ArrayList<ZAO2Model> ZAO2s = new ArrayList<ZAO2Model>();
            ArrayList<ZOModel> ZOs = new ArrayList<ZOModel>();
            ArrayList<ZO2Model> ZO2s = new ArrayList<ZO2Model>();
            ArrayList<ZPGModel> ZPGs = new ArrayList<ZPGModel>();
            ArrayList<ZVModel> ZVs = new ArrayList<ZVModel>();
            ArrayList<orderModel> Orders = new ArrayList<orderModel>();
            ObservableList<String> passports = FXCollections.observableArrayList();
            for (passportModel passport : selectedStudent.getPassports()) {
                passports.add(passport.getSeriesNumber());
                academicRecords.addAll(searcher.searchAcademicRecordData(passport.getSeriesNumber()));
                DPPESs.addAll(searcher.searchDPPESData(passport.getSeriesNumber()));
                //PZ.addAll(searcher.searchPZData(passport.getSeriesNumber()));
                ZZs.addAll(searcher.searchZZData(passport.getSeriesNumber()));
                ZAOs.addAll(searcher.searchZAOData(passport.getSeriesNumber()));
                ZAO2s.addAll(searcher.searchZAO2Data(passport.getSeriesNumber()));
                ZOs.addAll(searcher.searchZOData(passport.getSeriesNumber()));
                ZO2s.addAll(searcher.searchZO2Data(passport.getSeriesNumber()));
                ZPGs.addAll(searcher.searchZPGData(passport.getSeriesNumber()));
                ZVs.addAll(searcher.searchZVData(passport.getSeriesNumber()));
                Orders.addAll(searcher.searchOrderEmployeeData(passport.getSeriesNumber()));
            }

            ObservableList<documentModel> documents = FXCollections.observableArrayList();
            for (passportModel passport : selectedStudent.getPassports())
                documents.add(new documentModel(passport.getSeriesNumber(), "Паспорт", "", "", ""));
            for (academicRecordModel academicRecord : academicRecords)
                documents.add(new documentModel(academicRecord.getSERIES_NUMBER(), "Документ об образовании", academicRecord.getSIGN_DATE(), "", ""));
            for (ZZModel zz : ZZs)
                documents.add(new documentModel(zz.getID(), "Заявление о поступлении", zz.getSIGN_DATE(), "", ""));
            ArrayList<String> dppessDates = new ArrayList<String>();
            for (DPPESModel dppes : DPPESs) {
                documents.add(new documentModel(dppes.getID(), "Договор об оказании плат. образ. услуг", dppes.getSIGN_DATE(), "", ""));
                dppessDates.add(dppes.getSIGN_DATE());
                System.out.println(dppes.getCOURSE_FEE());
            }
            for (ZAOModel zao : ZAOs)
                documents.add(new documentModel(zao.getID(), "Заявление о предоставлении академ. отпуска", zao.getSIGN_DATE(), "", ""));
            for (ZAO2Model zao2 : ZAO2s)
                documents.add(new documentModel(zao2.getID(), "Заявление о выходе(продлении) академ. отпуска", zao2.getSIGN_DATE(), "", ""));
            for (ZOModel zo : ZOs)
                documents.add(new documentModel(zo.getID(), "Заявление об отчислении", zo.getSIGN_DATE(), "", ""));
            for (ZO2Model zo2 : ZO2s)
                documents.add(new documentModel(zo2.getID(), "Заявление об отчислении в связи с переводом в другой университет", zo2.getSIGN_DATE(), "", ""));
            for (ZVModel zv : ZVs)
                documents.add(new documentModel(zv.getID(), "Заявление о восстановлении", zv.getSIGN_DATE(), "", ""));
            for (ZPGModel zpg : ZPGs)
                documents.add(new documentModel(zpg.getID(), "Заявление о переводе в другую группу", zpg.getSIGN_DATE(), "", ""));
            ArrayList<String> orderDates = new ArrayList<String>();
            for (orderModel order : Orders) {
                String orderType = "Приказ";
                switch (order.getTYPE_ID()) {
                    case "1" -> orderType = "Приказ о зачислении";
                    case "2" -> orderType = "Приказ об отчислении";
                    case "3" -> orderType = "Приказ о переводе на след. курс";
                    case "4" -> orderType = "Приказ о переводе в др. группу";
                    case "5" -> orderType = "Приказ об уходе в академ. отпуск";
                    case "6" -> orderType = "Приказ о продлении академ. отпуска";
                    case "7" -> orderType = "Приказ о выходе из академ. отпуска";
                    case "8" -> orderType = "Приказ о восстановлении";
                    case "9" -> orderType = "Приказ об отчислении по неуважительной причине";
                }
                documents.add(new documentModel(order.getID(), orderType, order.getSIGN_DATE(), order.getSIGN_PERSON(), order.getRELEASE_PERSON()));
                if (order.getTYPE_ID().matches("[125]")) orderDates.add(order.getRELEASE_DATE());
            }
            if (!Orders.isEmpty()) {
                orderModel latestOrder = searcher.searchLatestOrder(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber());
                switch (latestOrder.getTYPE_ID()) {
                    case "1", "3", "4", "7", "8" -> status.setText("Обучающийся");
                    case "2", "9" -> status.setText("Отчислен");
                    case "5", "6" -> status.setText("В академическом отпуске");
                }
                String groupID = searcher.searchCurrentGroup(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber());
                group.setText("Группа " + groupID);
                direction.setText(searcher.searchDirectionByGroupID(groupID));
                course.setText(searcher.searchCourseByGroupID(groupID) + " курс");
                String latestDPPESDate = null;
                if (!DPPESs.isEmpty()) {
                    latestDPPESDate = searcher.getLatestDate(dppessDates);
                    fundingForm.setText(Date.valueOf(latestOrder.getRELEASE_DATE()).getTime() < Date.valueOf(latestDPPESDate).getTime() ? "Платное" : "Бюджет");
                } else
                    fundingForm.setText("Бюджет");
                educationForm.setText(searcher.searchEducationFormByDirectionID(direction.getText()));
            }
            table.setItems(documents);
            choosePassport.setItems(passports);
            choosePassport.getSelectionModel().select(Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber());
        }

    }

    @FXML
    void onStudentsListViewClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (lastSearchResult != null) {
            int index = studentsListView.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                this.turnAdding(true);
                selectedStudent = lastSearchResult.get(index);
                passportModel latestPassport = selectedStudent.getLatestPassport();
                selectedPassport = latestPassport;
                fioStudent.setText("" +
                        latestPassport.getLastName() + " " +
                        latestPassport.getName() + " " +
                        latestPassport.getFathersName() + " (" +
                        latestPassport.getGender() + ")");
                birthdate.setText(latestPassport.getBirthDate());
                passportSN.setText(latestPassport.getSeriesNumber());
                releaseData.setText("" +
                        latestPassport.getKodPodr() + ", " +
                        latestPassport.getWhoRelease() + ", " +
                        latestPassport.getReleaseDate());
                address.setText("" +
                        latestPassport.getRegistrationDate() + ", " +
                        latestPassport.getRegion() + ", " +
                        latestPassport.getPunct() + ", " +
                        latestPassport.getDistrict() + " р-н, ул. " +
                        latestPassport.getStreet() + ", д. " +
                        latestPassport.getHouse() + ", кв. " +
                        latestPassport.getApartment());
                Application.CURRENT_STUDENT = selectedStudent;

                ArrayList<academicRecordModel> academicRecords = new ArrayList<>();
                ArrayList<ZZModel> ZZs = new ArrayList<ZZModel>();
                ArrayList<DPPESModel> DPPESs = new ArrayList<DPPESModel>();
                ArrayList<PZModel> PZ = new ArrayList<PZModel>();
                ObservableList<String> passports = FXCollections.observableArrayList();
                for (passportModel passport : selectedStudent.getPassports()) {
                    passports.add(passport.getSeriesNumber());
                }
                choosePassport.setItems(passports);
                choosePassport.getSelectionModel().select(latestPassport.getSeriesNumber());

                this.updateTable(event);
            } else {
                this.turnAdding(false);
                clearStudentData();
            }
        } else clearStudentData();
    }

    @FXML
    void onChoose(ActionEvent event) {
        int index = choosePassport.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            selectedPassport = selectedStudent.getPassports().get(index);
            fioStudent.setText(selectedPassport.getLastName() + " " + selectedPassport.getName() + " " + selectedPassport.getFathersName() +
                    " (" + selectedPassport.getGender() + ")");
            birthdate.setText(selectedPassport.getBirthDate());
            passportSN.setText(selectedPassport.getSeriesNumber());
            releaseData.setText(selectedPassport.getKodPodr() + ", " + selectedPassport.getWhoRelease() + ", " + selectedPassport.getReleaseDate());
            address.setText(selectedPassport.getRegistrationDate() + ", " + selectedPassport.getRegion() + ", " + selectedPassport.getPunct()
                    + ", " + selectedPassport.getDistrict() + " р-н, ул. " + selectedPassport.getStreet() + ", д. "
                    + selectedPassport.getHouse() + ", кв. " + selectedPassport.getApartment());
            choosePassport.setPromptText(selectedPassport.getSeriesNumber());
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        code.setStyle("-fx-alignment: CENTER;");
        docType.setStyle("-fx-alignment: CENTER;");
        signData.setStyle("-fx-alignment: CENTER;");
        signPerson.setStyle("-fx-alignment: CENTER;");
        releasePerson.setStyle("-fx-alignment: CENTER;");
        code.setCellValueFactory(new PropertyValueFactory<>("ID"));
        docType.setCellValueFactory(new PropertyValueFactory<>("TYPE"));
        signData.setCellValueFactory(new PropertyValueFactory<>("SIGN_DATE"));
        signPerson.setCellValueFactory(new PropertyValueFactory<>("SIGN_PERSON"));
        releasePerson.setCellValueFactory(new PropertyValueFactory<>("CREATOR_PERSON"));

        assert exitAccountButton != null : "fx:id=\"exitAccountButton\" was not injected: check your FXML file 'mainView.fxml'.";
        assert studentsListView != null : "fx:id=\"studentsListView\" was not injected: check your FXML file 'mainView.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'mainView.fxml'.";
        assert fioLabel != null : "fx:id=\"fioLabel\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addPassportMenuItem != null : "fx:id=\"addPassportMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addAcademicRecordMenuItem != null : "fx:id=\"addAcademicRecordMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addDPPESMenuItem != null : "fx:id=\"addDPPESMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addZZMenuItem != null : "fx:id=\"addZZMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addZAOMenuItem != null : "fx:id=\"addZAOMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addZOMenuItem != null : "fx:id=\"addZOMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addZVMenuItem != null : "fx:id=\"addZVMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert addZPGMenuItem != null : "fx:id=\"addZPGMenuItem\" was not injected: check your FXML file 'mainView.fxml'.";
        assert choosePassport != null : "fx:id=\"choosePassport\" was not injected: check your FXML file 'mainView.fxml'.";
        assert fioStudent != null : "fx:id=\"fioStudent\" was not injected: check your FXML file 'mainView.fxml'.";
        assert birthdate != null : "fx:id=\"birthdate\" was not injected: check your FXML file 'mainView.fxml'.";
        assert passportSN != null : "fx:id=\"passportSN\" was not injected: check your FXML file 'mainView.fxml'.";
        assert releaseData != null : "fx:id=\"releaseData\" was not injected: check your FXML file 'mainView.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'mainView.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'mainView.fxml'.";
        assert direction != null : "fx:id=\"direction\" was not injected: check your FXML file 'mainView.fxml'.";
        assert group != null : "fx:id=\"group\" was not injected: check your FXML file 'mainView.fxml'.";
        assert course != null : "fx:id=\"course\" was not injected: check your FXML file 'mainView.fxml'.";
        assert fundingForm != null : "fx:id=\"fundingForm\" was not injected: check your FXML file 'mainView.fxml'.";
        assert educationForm != null : "fx:id=\"educationForm\" was not injected: check your FXML file 'mainView.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'mainView.fxml'.";
        assert code != null : "fx:id=\"code\" was not injected: check your FXML file 'mainView.fxml'.";
        assert docType != null : "fx:id=\"docType\" was not injected: check your FXML file 'mainView.fxml'.";
        assert signData != null : "fx:id=\"signData\" was not injected: check your FXML file 'mainView.fxml'.";
        assert signPerson != null : "fx:id=\"signPerson\" was not injected: check your FXML file 'mainView.fxml'.";
        assert releasePerson != null : "fx:id=\"releasePerson\" was not injected: check your FXML file 'mainView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'mainView.fxml'.";

        this.turnAdding(false);

        fioLabel.setText(Application.getCURRENT_USER());
    }
}
