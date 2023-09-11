package com.app.sok.models;

import com.app.sok.Application;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DZZModel {
    String ZZ_ID;
    String PRIORITY;
    String DIRECTION_ID;
    String EDUCATION_FORM_ID;

    String ATTAINMENT_NAME;
    String DIRECTION_ID_NAME;
    String EDUCATION_FORM_NAME;


    public DZZModel(String zz_id, String priority, String direction_id, String education_form_id) throws SQLException, ClassNotFoundException {
        this.ZZ_ID = zz_id;
        this.PRIORITY = priority;
        this.DIRECTION_ID = direction_id;

        this.EDUCATION_FORM_ID = education_form_id;
        String id = (direction_id.length() == 5 ? "0" + direction_id : direction_id);
        String id_name = id.substring(0, 2) + "." + id.substring(2, 4) + "." + id.substring(4, 6);
        String sql = String.format("SELECT `Название направления` " +
                "FROM Направление " +
                "WHERE `Код направления` = '%s'", direction_id);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        this.DIRECTION_ID_NAME = id_name + " " + res.getString("Название направления");

        res.getString("Название направления");
        sql = String.format("SELECT `Название формы обучения` " +
                "FROM `Форма обучения` " +
                "WHERE `Код формы обучения` = '%s'", education_form_id);
        st = Application.getConnection().createStatement();
        res = st.executeQuery(sql);
        res.next();
        this.EDUCATION_FORM_NAME = res.getString("Название формы обучения");

        sql = String.format("SELECT `Уровень подготовки`.`Название уровня подготовки` " +
                "FROM `Уровень подготовки`, Направление " +
                "WHERE `Уровень подготовки`.`Код уровня подготовки` = Направление.`Код уровня подготовки`" +
                "AND Направление.`Код направления` = '%s'", direction_id);
        st = Application.getConnection().createStatement();
        res = st.executeQuery(sql);
        res.next();
        this.ATTAINMENT_NAME = res.getString("Название уровня подготовки");
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Заявление-Направление` VALUES('%s', '%s', '%s', '%s')",
                this.ZZ_ID, this.PRIORITY, this.DIRECTION_ID, this.EDUCATION_FORM_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public void setZZ_ID(String zz_id) {
        this.ZZ_ID = zz_id;
    }

    public String getZZ_ID() {
        return ZZ_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public String getEDUCATION_FORM_ID() {
        return EDUCATION_FORM_ID;
    }

    public String getPRIORITY() {
        return this.PRIORITY;
    }

    public String getDIRECTION_ID_NAME() {
        return DIRECTION_ID_NAME;
    }

    public String getEDUCATION_FORM_NAME() {
        return EDUCATION_FORM_NAME;
    }

    public String getATTAINMENT_NAME() {
        return ATTAINMENT_NAME;
    }
}
