package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class PONPModel {
    private String ID;
    private String PASSPORT_SERIES_NUMBER;
    private String REASON_ID;

    private String FIO;
    private String REASON;

    public PONPModel(String ID, String PASSPORT_SERIES_NUMBER, String REASON_ID, String FIO, String REASON) {
        this.ID = ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.REASON_ID = REASON_ID;
        this.FIO = FIO;
        this.REASON = REASON;
    }

    public PONPModel(String PASSPORT_SERIES_NUMBER, String REASON_ID, String FIO, String REASON) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.REASON_ID = REASON_ID;
        this.FIO = FIO;
        this.REASON = REASON;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Приказ-Паспорт` VALUES('%s', '%s', '%s')",
                this.PASSPORT_SERIES_NUMBER, this.ID, this.REASON_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getREASON_ID() {
        return REASON_ID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getREASON() {
        return REASON;
    }
}
