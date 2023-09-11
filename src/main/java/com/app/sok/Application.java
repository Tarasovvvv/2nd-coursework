package com.app.sok;

import com.app.sok.models.employeeModel;
import com.app.sok.models.studentModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    private static Stage CURRENT_STAGE;
    public static employeeModel CURRENT_EMPLOYEE;
    public static String GLOBAL;
    public static studentModel CURRENT_STUDENT;
    private static String CURRENT_USER;

    public Application() throws SQLException, ClassNotFoundException {
        CURRENT_EMPLOYEE = new employeeModel("Tarasovvv","tar02");
        CURRENT_STAGE = new Stage();
        CURRENT_USER = "Тарасов Л.М.";
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://192.168.1.37:3306/mydb", "root", "");
    }

    public static String getCURRENT_USER() {
        return CURRENT_USER;
    }

    public static void setCURRENT_USER(String user) {
        CURRENT_USER = user;
    }

    public static Stage getCURRENT_STAGE() {
        return CURRENT_STAGE;
    }

    public static void switchScene(String view, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load());
        CURRENT_STAGE.setTitle(title);
        CURRENT_STAGE.setScene(scene);
        CURRENT_STAGE.setResizable(false);
        CURRENT_STAGE.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        CURRENT_STAGE = stage;
        stage.setResizable(false);
        switchScene("authView.fxml","");
    }

    public static void main(String[] args) {
        launch();
    }
}