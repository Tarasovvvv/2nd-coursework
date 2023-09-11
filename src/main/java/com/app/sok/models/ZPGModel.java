package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class ZPGModel {
    private String ID;
    private String GROUP_ID;
    private String PASSPORT_SERIES_NUMBER;
    private String SIGN_DATE;

    public ZPGModel(String ID, String GROUP_ID, String PASSPORT_SERIES_NUMBER, String SIGN_DATE) {
        this.ID = ID;
        this.GROUP_ID = GROUP_ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.SIGN_DATE = SIGN_DATE;
    }

    public ZPGModel(String GROUP_ID, String PASSPORT_SERIES_NUMBER, String SIGN_DATE) throws SQLException, ClassNotFoundException {
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
        this.GROUP_ID = GROUP_ID;
        this.PASSPORT_SERIES_NUMBER = PASSPORT_SERIES_NUMBER;
        this.SIGN_DATE = SIGN_DATE;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        if(new searcherModel().getCount("Код группы", "Группа", String.format("`Код группы` = '%s'", this.GROUP_ID)) == 1) {
            String sql = String.format("INSERT INTO `Заявление о переводе в другую группу` VALUES('%s', '%s', '%s', '%s')",
                    this.ID, this.GROUP_ID, this.PASSPORT_SERIES_NUMBER, this.SIGN_DATE);
            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            st.executeUpdate(sql);
        }
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getSIGN_DATE() {
        return SIGN_DATE;
    }

    public String getID() {
        return ID;
    }
}
