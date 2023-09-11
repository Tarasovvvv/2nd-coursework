package com.app.sok.models;

import com.app.sok.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UZModel {
    private String ID;
    private String TYPE;
    private String DIRECTOR_ID;
    private String NAME;
    private String REGION;
    private String PUNCT;
    private String STREET;
    private String HOUSE;
    private employeeModel DIRECTOR;

    public UZModel(String id, String type, String director_id, String name, String region, String punct, String street, String house, employeeModel director) {
        this.ID = id;
        this.TYPE = type;
        this.DIRECTOR_ID = director_id;
        this.NAME = name;
        this.REGION = region;
        this.PUNCT = punct;
        this.STREET = street;
        this.HOUSE = house;
        this.DIRECTOR = director;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String values = "'" +
                this.ID + "', '" +
                this.TYPE + "', " +
                (this.DIRECTOR_ID.equals("") ? "NULL" : "'" + this.DIRECTOR_ID + "'") + ", '" +
                this.NAME + "', '" +
                this.REGION + "', '" +
                this.PUNCT + "', '" +
                this.STREET + "', '" +
                this.HOUSE + "'";
        String sql = String.format("INSERT INTO `Учебное заведение` VALUES(%s)", values);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }


    public String getID() {
        return this.ID;
    }

    public String getNAME() {
        return this.NAME;
    }

    public String getTYPE() {
        return this.TYPE;
    }

    public String getDIRECTOR_ID() {
        return this.DIRECTOR_ID;
    }

    public String getREGION() {
        return this.REGION;
    }

    public String getPUNCT() {
        return this.PUNCT;
    }

    public String getSTREET() {
        return this.STREET;
    }

    public String getHOUSE() {
        return this.HOUSE;
    }

    public employeeModel getDIRECTOR() {
        return this.DIRECTOR;
    }

}
