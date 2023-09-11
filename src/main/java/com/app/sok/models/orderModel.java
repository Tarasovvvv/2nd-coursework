package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class orderModel {
    private String ID;
    private String TYPE_ID;
    private String RELEASE_DATE;
    private String SIGN_DATE;
    private String RELEASE_PERSON;
    private String SIGN_PERSON;

    public orderModel(String id, String type_id, String release_date, String sign_date) {
        this.ID = id;
        this.TYPE_ID = type_id;
        this.RELEASE_DATE = release_date;
        this.SIGN_DATE = sign_date;
    }

    public orderModel(String id, String type_id, String release_date, String sign_date, String release_person, String sign_person) {
        this.ID = id;
        this.TYPE_ID = type_id;
        this.RELEASE_DATE = release_date;
        this.SIGN_DATE = sign_date;
        this.RELEASE_PERSON = release_person;
        this.SIGN_PERSON = sign_person;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Приказ VALUES('%s', '%s', '%s', '%s')",
                this.ID, this.TYPE_ID, this.RELEASE_DATE, this.SIGN_DATE);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public String getID() {
        return ID;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public String getRELEASE_DATE() {
        return RELEASE_DATE;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }

    public void setRELEASE_PERSON(String RELEASE_PERSON) {
        this.RELEASE_PERSON = RELEASE_PERSON;
    }

    public void setSIGN_PERSON(String SIGN_PERSON) {
        this.SIGN_PERSON = SIGN_PERSON;
    }

    public String getRELEASE_PERSON() {
        return RELEASE_PERSON;
    }

    public String getSIGN_PERSON() {
        return SIGN_PERSON;
    }
}
