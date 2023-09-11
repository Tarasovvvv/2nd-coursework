package com.app.sok.models;

import com.app.sok.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class directionModel {
    private String ID;
    private String NAME;
    private String ATTAINMENT;

    public directionModel(String id, String name) throws SQLException, ClassNotFoundException {
        this.ID = id;
        this.NAME = name;
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(String.format("SELECT `Уровень подготовки`.`Название уровня подготовки` " +
                "FROM `Уровень подготовки`, `Направление` " +
                "WHERE `Направление`.`Код уровня подготовки` = `Уровень подготовки`.`Код уровня подготовки` " +
                "AND `Направление`.`Код направления` = '%s'", this.ID));
        res.next();
        this.ATTAINMENT = res.getString("Название уровня подготовки");
    }

    public String getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getATTAINMENT() {
        return this.ATTAINMENT;
    }

}
