package com.app.sok.models;

public class groupModel {
    private String GROUP_ID;
    private String DIRECTION_ID;
    private String COURSE;

    public groupModel(String GROUP_ID, String DIRECTION_ID, String COURSE) {
        this.GROUP_ID = GROUP_ID;
        this.DIRECTION_ID = DIRECTION_ID;
        this.COURSE = COURSE;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public String getCOURSE() {
        return COURSE;
    }
}
