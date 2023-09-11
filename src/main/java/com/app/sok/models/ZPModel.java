package com.app.sok.models;

import com.app.sok.Application;

import java.sql.SQLException;
import java.sql.Statement;

public class ZPModel {
    private String ORDER_ID;
    private String ZZ_ID;
    private String ZO_ID;
    private String ZUAO_ID;
    private String ZPAO_ID;
    private String ZVAO_ID;
    private String ZPG_ID;
    private String ZV_ID;
    private String ZOP_ID;
    private String DIRECTION_ID;

    public ZPModel() {
    }

    public void insert(String ORDER_ID, String ZZ_ID, String ZO_ID, String ZUAO_ID, String ZPAO_ID, String ZVAO_ID, String ZV_ID, String ZPG_ID, String ZOP_ID,
                       String DIRECTION_ID) throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO `Заявление-Приказ` VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s)",
                ORDER_ID, ZZ_ID, ZO_ID, ZUAO_ID, ZPAO_ID, ZVAO_ID, ZPG_ID, ZV_ID, DIRECTION_ID);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
        this.ORDER_ID = ORDER_ID;
        this.ZZ_ID = ZZ_ID;
        this.ZO_ID = ZO_ID;
        this.ZUAO_ID = ZUAO_ID;
        this.ZPAO_ID = ZPAO_ID;
        this.ZVAO_ID = ZVAO_ID;
        this.ZPG_ID = ZPG_ID;
        this.ZV_ID = ZV_ID;
        this.ZOP_ID = ZOP_ID;
        this.DIRECTION_ID = DIRECTION_ID;
    }

    public String getZZ_ID() {
        return ZZ_ID;
    }

    public String getZO_ID() {
        return ZO_ID;
    }

    public String getZOP_ID() {
        return ZOP_ID;
    }

    public String getZUAO_ID() {
        return ZUAO_ID;
    }

    public String getZPAO_ID() {
        return ZPAO_ID;
    }

    public String getZVAO_ID() {
        return ZVAO_ID;
    }

    public String getZPG_ID() {
        return ZPG_ID;
    }

    public String getZV_ID() {
        return ZV_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

}
