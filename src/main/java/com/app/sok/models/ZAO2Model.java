package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class ZAO2Model {
    private String ID;
    private String PASSPORT_SERIES_NUMBER;
    private String EXIT_DATE;
    private String SIGN_DATE;

    public ZAO2Model(String ID, String PASSPORT_SERIES_NUMBER, String EXIT_DATE, String SIGN_DATE) {
        this.ID = ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.EXIT_DATE = EXIT_DATE;
        this.SIGN_DATE = SIGN_DATE;
    }

    public ZAO2Model(String PASSPORT_SERIES_NUMBER, String EXIT_DATE, String SIGN_DATE) throws SQLException, ClassNotFoundException {
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
        this.EXIT_DATE = EXIT_DATE;
        this.SIGN_DATE = SIGN_DATE;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Заявление о продлении/выходе из академ отпуска` VALUES('%s', '%s', '%s', '%s')",
                this.ID, this.PASSPORT_SERIES_NUMBER, this.EXIT_DATE, this.SIGN_DATE);
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

    public String getEXIT_DATE() {
        return EXIT_DATE;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }
}
