package com.app.sok.models;

public class POModel {
    private String ZO_ID;
    private String PASSPORT_SERIES_NUMBER;
    private String FIO;
    private String REASON;

    public POModel(String FIO, String REASON) {
        this.FIO = FIO;
        this.REASON = REASON;
    }

    public POModel(String PASSPORT_SERIES_NUMBER, String FIO, String REASON, String ZO_ID) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.FIO = FIO;
        this.REASON = REASON;
        this.ZO_ID = ZO_ID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getREASON() {
        return REASON;
    }

    public String getZO_ID() {
        return ZO_ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }
}
