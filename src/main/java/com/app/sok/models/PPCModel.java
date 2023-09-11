package com.app.sok.models;

public class PPCModel {
    private String PASSPORT_SERIES_NUMBER;
    private String FIO;
    private String GROUP_ID;
    private String COURSE;

    public PPCModel(String PASSPORT_SERIES_NUMBER, String FIO, String GROUP_ID, String COURSE) {
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.FIO = FIO;
        this.GROUP_ID = GROUP_ID;
        this.COURSE = COURSE;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public void setPASSPORT_SERIES_NUMBER(String passportSeriesNumber) {
        this.PASSPORT_SERIES_NUMBER = passportSeriesNumber;
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

    public String getCOURSE() {
        return COURSE;
    }
}
