package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class ZOModel {
    private String ID;
    private String PASSPORT_SERIES_NUMBER;
    private String REASON_ID;
    private String SIGN_DATE;

    public ZOModel(String ID, String PASSPORT_SERIES_NUMBER, String REASON_ID, String SIGN_DATE) {
        this.ID = ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.REASON_ID = REASON_ID;
        this.SIGN_DATE = SIGN_DATE;
    }

    public ZOModel(String PASSPORT_SERIES_NUMBER, String REASON_ID, String SIGN_DATE) throws SQLException, ClassNotFoundException {
        long newID = -6L;
        searcherModel searcher = new searcherModel();
        newID += Long.parseLong(searcher.newId("Заявление о поступлении", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление об отчислении", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление о восстановлении", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление о переводе в другую группу", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление о предоставлении академ отпуска", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление о продлении/выходе из академ отпуска", "Код заявления"));
        newID += Long.parseLong(searcher.newId("Заявление об отчислении в связи с перев в др универ", "Код заявления"));
        this.ID = Long.toString(newID);
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.REASON_ID = REASON_ID;
        this.SIGN_DATE = SIGN_DATE;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Заявление об отчислении` VALUES('%s', '%s', '%s', '%s')",
                this.ID, this.PASSPORT_SERIES_NUMBER, this.REASON_ID, this.SIGN_DATE);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getID() {
        return ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getREASON_ID() {
        return REASON_ID;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }
}
