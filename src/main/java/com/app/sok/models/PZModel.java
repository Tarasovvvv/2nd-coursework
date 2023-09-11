package com.app.sok.models;

import com.app.sok.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PZModel {
    private String ZZ_ID;
    private String ORDER_ID;
    private String DIRECTION_ID;

    private String PASSPORT_SERIES_NUMBER;
    private String FIO;
    private String DIRECTION;
    private String GROUP;

    public PZModel(String PASSPORT_SERIES_NUMBER, String FIO, String DIRECTION, String GROUP, String ZZ_ID, String DIRECTION_ID) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.FIO = FIO;
        this.DIRECTION = DIRECTION;
        this.GROUP = GROUP;
        this.ZZ_ID = ZZ_ID;
        this.DIRECTION_ID = DIRECTION_ID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getDIRECTION() {
        return DIRECTION;
    }

    public String getGROUP() {
        return GROUP;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getZZ_ID() {
        return ZZ_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public String getPASSPORT_SERIES_NUMBER()
    {
        return PASSPORT_SERIES_NUMBER;
    }
}
