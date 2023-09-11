package com.app.sok.models;

import com.app.sok.Application;

import java.sql.*;
import java.util.Objects;

public class employeeModel {
    private String ID;
    private String LAST_NAME;
    private String NAME;
    private String FATHERS_NAME;
    private String PHONE;
    private String EMAIL;

    public employeeModel() {
        this.ID = null;
        this.LAST_NAME = null;
        this.NAME = null;
        this.FATHERS_NAME = null;
        this.PHONE = null;
        this.EMAIL = null;
    }

    public employeeModel(String lastName, String name, String fathersName, String phone, String email) throws SQLException, ClassNotFoundException {
        if (!name.equals("")) {
            this.ID = new searcherModel().newId("Сотрудник", "Код сотрудника");
            this.LAST_NAME = lastName;
            this.NAME = name;
            this.FATHERS_NAME = fathersName;
            this.PHONE = phone;
            this.EMAIL = email;
        } else {
            this.ID = "";
            this.LAST_NAME = "...";
            this.NAME = "";
            this.FATHERS_NAME = "";
            this.PHONE = "";
            this.EMAIL = "";
        }
    }

    public employeeModel(String id, String lastName, String name, String fathersName) throws SQLException, ClassNotFoundException {
        this.ID = id;
        this.LAST_NAME = lastName;
        this.NAME = name;
        this.FATHERS_NAME = fathersName;
    }

    public employeeModel(String login, String password) throws SQLException, ClassNotFoundException {
        if (!Objects.equals((login + password).trim(), "")) {
            String sql = String.format("SELECT `Код сотрудника`, Фамилия, Имя, Отчество FROM Сотрудник WHERE Логин = '%s' AND Пароль = '%s'", login, password);
            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                this.LAST_NAME = res.getString("Фамилия");
                this.NAME = res.getString("Имя");
                this.FATHERS_NAME = res.getString("Отчество");
                this.ID = res.getString("Код сотрудника");
            }
        }
    }

    public void insert() throws SQLException, ClassNotFoundException {


        String values = "'" +
                this.ID + "', '" +
                this.LAST_NAME + "', '" +
                this.NAME + "', '" +
                this.FATHERS_NAME + "', '" +
                this.PHONE + "', '" +
                this.EMAIL + "', '', ''";
        String sql = String.format("INSERT INTO Сотрудник VALUES(%s)", values);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
        sql = String.format("INSERT INTO `Сотрудник-Должность` VALUES('%s', '1')", this.ID);
        System.out.println(sql);
        st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }

    public String getFIO() {
        return this.LAST_NAME + " " + this.NAME + " " + this.FATHERS_NAME;
    }

    public String getID() {
        return this.ID;
    }

    public String getLAST_NAME() {
        return this.LAST_NAME;
    }

    public String getNAME() {
        return this.NAME;
    }

    public String getFATHERS_NAME() {
        return this.FATHERS_NAME;
    }

    public String getPHONE() {
        return this.PHONE;
    }

    public String getEMAIL() {
        return this.EMAIL;
    }

    public boolean exists() {
        return this.NAME != null;
    }
}
