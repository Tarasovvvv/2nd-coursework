package com.app.sok.models;

public class PVModel {
    private String PASSPORT_SERIES_NUMBER;
    private String ZV_ID;
    private String FIO;

    public PVModel(String PASSPORT_SERIES_NUMBER, String ZV_ID, String FIO) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.ZV_ID = ZV_ID;
        this.FIO = FIO;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return this.PASSPORT_SERIES_NUMBER;
    }

    public String getZV_ID() {
        return this.ZV_ID;
    }

    public void setPASSPORT_SERIES_NUMBER(String passportSeriesNumber) {
        this.PASSPORT_SERIES_NUMBER = passportSeriesNumber;
    }

    public void setZV_ID(String ZV_ID) {
        this.ZV_ID = ZV_ID;
    }

    public String getFIO() {
        return this.FIO;
    }
}
