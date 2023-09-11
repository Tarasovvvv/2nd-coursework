package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class academicRecordModel {
    private String SERIES_NUMBER;
    private String PASSPORT_SERIES_NUMBER;
    private String EDUCATION_LEVEL_ID;
    private String UZ_ID;
    private String DIRECTION_ID;
    private String DIRECTION_NAME;
    private String SIGN_DATE;

    public academicRecordModel(String series_number, String passport_series_number, String education_level_id, String uz_id, String direction_id, String direction_name, String release_date) {
        this.SERIES_NUMBER = series_number;
        this.PASSPORT_SERIES_NUMBER = passport_series_number;
        this.EDUCATION_LEVEL_ID = education_level_id;
        this.UZ_ID = uz_id;
        this.DIRECTION_ID = direction_id;
        this.DIRECTION_NAME = direction_name;
        this.SIGN_DATE = release_date;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String values = "'" +
                this.SERIES_NUMBER + "', '" +
                this.PASSPORT_SERIES_NUMBER + "', '" +
                this.EDUCATION_LEVEL_ID + "', '" +
                this.UZ_ID + "', '" +
                this.DIRECTION_ID + "', '" +
                this.DIRECTION_NAME + "', '" +
                this.SIGN_DATE + "'";
        String sql = String.format("INSERT INTO `Документ об образовании` VALUES(%s)", values);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getSERIES_NUMBER() {
        return SERIES_NUMBER;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getEDUCATION_LEVEL_ID() {
        return EDUCATION_LEVEL_ID;
    }

    public String getUZ_ID() {
        return UZ_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public String getDIRECTION_NAME() {
        return DIRECTION_NAME;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }
}
