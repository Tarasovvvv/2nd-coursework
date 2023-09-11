package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class ZZModel {
    private String ID;
    private String PASSPORT_SERIES_NUMBER;
    private String ACADEMIC_RECORD_ID;
    private String SIGN_DATE;

    public ZZModel(String id, String passport_series_number, String academic_record_id, String release_date){
        this.ID = id;
        this.PASSPORT_SERIES_NUMBER = passport_series_number;
        this.ACADEMIC_RECORD_ID = academic_record_id;
        this.SIGN_DATE = release_date;
    }

    public ZZModel(String passport_series_number, String academic_record_id, String release_date) throws SQLException, ClassNotFoundException {
        long ZZBewId = -6L;
        searcherModel searcher = new searcherModel();
        ZZBewId += Long.parseLong(searcher.newId("Заявление о поступлении", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление об отчислении", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление о восстановлении", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление о переводе в другую группу", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление о предоставлении академ отпуска", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление о продлении/выходе из академ отпуска", "Код заявления"));
        ZZBewId += Long.parseLong(searcher.newId("Заявление об отчислении в связи с перев в др универ", "Код заявления"));
        this.ID = Long.toString(ZZBewId);
        this.PASSPORT_SERIES_NUMBER = passport_series_number;
        this.ACADEMIC_RECORD_ID = academic_record_id;
        this.SIGN_DATE = release_date;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Заявление о поступлении` VALUES('%s', '%s', '%s', '%s')",
                this.ID, this.PASSPORT_SERIES_NUMBER, this.ACADEMIC_RECORD_ID, this.SIGN_DATE);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getID() {
        return this.ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return this.PASSPORT_SERIES_NUMBER;
    }

    public String getACADEMIC_RECORD_ID() {
        return this.ACADEMIC_RECORD_ID;
    }

    public String getSIGN_DATE() {
        return this.SIGN_DATE;
    }
}
