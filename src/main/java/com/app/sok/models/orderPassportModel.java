package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class orderPassportModel {
    private String PASSPORT_SERIES_NUMBER;
    private String ORDER_ID;
    private String REASON_ID;

    public orderPassportModel(String PASSPORT_SERIES_NUMBER, String ORDER_ID) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.ORDER_ID = ORDER_ID;
    }

    public orderPassportModel(String PASSPORT_SERIES_NUMBER, String ORDER_ID, String REASON_ID) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.ORDER_ID = ORDER_ID;
        this.REASON_ID = REASON_ID;
    }

    public void insert1() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Приказ-Паспорт` VALUES('%s', '%s', NULL)",
                this.PASSPORT_SERIES_NUMBER, this.ORDER_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public void insert2() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Приказ-Паспорт` VALUES('%s', '%s', '%s')",
                this.PASSPORT_SERIES_NUMBER, this.ORDER_ID, this.REASON_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getREASON_ID() {
        return REASON_ID;
    }
}
