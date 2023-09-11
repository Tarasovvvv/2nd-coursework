package com.app.sok.models;

public class documentModel {
    private String ID;
    private String TYPE;
    private String SIGN_DATE;
    private String SIGN_PERSON;
    private String CREATOR_PERSON;

    public documentModel(String id, String type, String sign_date, String sign_person, String creator_person) {
        this.ID = id;
        this.TYPE = type;
        this.SIGN_DATE = sign_date;
        this.SIGN_PERSON = sign_person;
        this.CREATOR_PERSON = creator_person;
    }

    public String getID() {
        return ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }

    public String getSIGN_PERSON() {
        return SIGN_PERSON;
    }

    public String getCREATOR_PERSON() {
        return CREATOR_PERSON;
    }
}
