package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class PUAOModel {
    private String ZUAO_ID;
    private String PASSPORT_SERIES_NUMBER;
    private String REASON_ID;
    private String START_DATE;
    private String END_DATE;

    private String FIO;
    private String REASON;

    public PUAOModel(String ZUAO_ID, String PASSPORT_SERIES_NUMBER, String REASON_ID,String END_DATE, String START_DATE , String FIO, String REASON) {
        this.ZUAO_ID = ZUAO_ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.REASON_ID = REASON_ID;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.FIO = FIO;
        this.REASON = REASON;
    }

    public String getZUAO_ID() {
        return ZUAO_ID;
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

    public String getSTART_DATE() {
        return START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }
}
