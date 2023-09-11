package com.app.sok.models;

public class PPVAOModel {
    private String ZPVAO_ID;
    private String PASSPORT_SERIES_NUMBER;
    private String EXIT_DATE;

    private String FIO;

    public PPVAOModel(String ZPVAO_ID, String PASSPORT_SERIES_NUMBER, String EXIT_DATE, String FIO) {
        this.ZPVAO_ID = ZPVAO_ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.EXIT_DATE = EXIT_DATE;
        this.FIO = FIO;
    }

    public String getZPVAO_ID() {
        return ZPVAO_ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getFIO() {
        return FIO;
    }

    public String getEXIT_DATE() {
        return EXIT_DATE;
    }
}
