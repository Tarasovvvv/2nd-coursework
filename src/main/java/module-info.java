module com.app.sok {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires itextpdf;
    //requires eu.hansolo.tilesfx;

    opens com.app.sok to javafx.fxml;
    exports com.app.sok;
    exports com.app.sok.models;
    opens com.app.sok.models to javafx.fxml;
    exports com.app.sok.controllers;
    opens com.app.sok.controllers to javafx.fxml;
}