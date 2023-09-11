package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class groupOrderModel {
    private String ORDER_ID;
    private String PASSPORT_SERIES_NUMBER;
    private String GROUP_ID;

    public groupOrderModel(String order_id, String passport_series_number, String group_id) {
        this.ORDER_ID = order_id;
        this.PASSPORT_SERIES_NUMBER = passport_series_number;
        this.GROUP_ID = group_id;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Группа-Приказ` VALUES('%s', '%s', '%s')",
                this.ORDER_ID, this.PASSPORT_SERIES_NUMBER, this.GROUP_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getPASSPORT_SERIES_NUMBER() {
        return PASSPORT_SERIES_NUMBER;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }
}
