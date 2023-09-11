package com.app.sok.models;

public class PPGModel {
    private String PASSPORT_SERIES_NUMBER;
    private String ZPG_ID;
    private String FIO;
    private String GROUP_ID;

    public PPGModel(String PASSPORT_SERIES_NUMBER, String ZPG_ID, String FIO, String GROUP_ID) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.ZPG_ID = ZPG_ID;
        this.FIO = FIO;
        this.GROUP_ID = GROUP_ID;
    }

    public String getZPG_ID() {
        return this.ZPG_ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return this.PASSPORT_SERIES_NUMBER;
    }

    public void setPASSPORT_SERIES_NUMBER(String passportSeriesNumber) {
        this.PASSPORT_SERIES_NUMBER = passportSeriesNumber;
    }

    public void setZPG_ID(String ZPG_ID) {
        this.ZPG_ID = ZPG_ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }
}
