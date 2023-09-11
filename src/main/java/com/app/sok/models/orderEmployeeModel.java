package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class orderEmployeeModel {
    private String ORDER_ID;
    private String EMPLOYEE_ID;

    public orderEmployeeModel(String ORDER_ID, String EMPLOYEE_ID) {
        this.ORDER_ID = ORDER_ID;
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Приказ-Сотрудник` VALUES('%s', '%s')",this.ORDER_ID, this.EMPLOYEE_ID);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }
}
