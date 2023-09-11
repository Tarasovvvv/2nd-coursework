package com.app.sok.models;

import com.app.sok.Application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DPPESModel {
    private String ID;
    private String PASSPORT_SERIES_NUMBER;
    private String COURSE_FEE;
    private String SIGN_DATE;
    private String VALID_COURSES_COUNT;

    public DPPESModel(String id, String passport_series_number, String course_fee, String sign_date, String valid_courses_count) {
        this.ID = id;
        this.PASSPORT_SERIES_NUMBER = passport_series_number;
        this.COURSE_FEE = course_fee;
        this.SIGN_DATE = sign_date;
        this.VALID_COURSES_COUNT = valid_courses_count;
    }

    public DPPESModel(String course_fee, String sign_date, String valid_courses_count) throws SQLException, ClassNotFoundException {
        searcherModel searcher = new searcherModel();
        this.ID = searcher.newId("Договор об оказании плат обр услуг", "Код договора");
        this.PASSPORT_SERIES_NUMBER = Application.CURRENT_STUDENT.getLatestPassport().getSeriesNumber();
        this.COURSE_FEE = course_fee;
        this.SIGN_DATE = sign_date;
        this.VALID_COURSES_COUNT = valid_courses_count;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String values = "'" + this.ID + "', '" + this.PASSPORT_SERIES_NUMBER + "', '"
                + this.COURSE_FEE + "', '" + this.SIGN_DATE + "', '" + this.VALID_COURSES_COUNT + "'";
        String sql = String.format("INSERT INTO `Договор об оказании плат обр услуг` VALUES(%s)", values);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getID() {
        return ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getCOURSE_FEE() {
        return COURSE_FEE;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }

    public String getVALID_COURSES_COUNT() {
        return VALID_COURSES_COUNT;
    }
}
