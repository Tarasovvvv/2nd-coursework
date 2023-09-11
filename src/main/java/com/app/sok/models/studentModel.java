package com.app.sok.models;

import com.app.sok.Application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class studentModel {
    private String id;
    private passportModel passport;
    private ArrayList<passportModel> passports;

    public studentModel(passportModel passport) throws SQLException, ClassNotFoundException {
        this.passports = new ArrayList<passportModel>();
        this.passports.add(passport);
        this.passport = passport;
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery("SELECT count('Код студента') AS count FROM Студент");
        res.next();
        this.id = Long.toString(res.getLong("count") + 1);
    }

    public studentModel(String id, passportModel passport) {
        this.passports = new ArrayList<passportModel>();
        this.passports.add(passport);
        this.id = id;
    }

    public studentModel(String id, ArrayList<passportModel> passports) {
        this.passports = passports;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newID) {
        this.id = newID;
    }

    public ArrayList<passportModel> getPassports() {
        return this.passports;
    }

    public passportModel getLatestPassport() {
        Date latestDate = Date.valueOf(passports.get(0).getReleaseDate());
        passportModel latestPassport = passports.get(0);
        for (passportModel passport : this.passports)
            if (Date.valueOf(passport.getReleaseDate()).getTime() > latestDate.getTime()) {
                latestPassport = passport;
                latestDate = Date.valueOf(passport.getReleaseDate());
            }
        return latestPassport;
    }

    public void addPassport(passportModel passport) {
        this.passports.add(passport);
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Студент VALUES('%s', '%s')", this.id, this.passport.getSeriesNumber());
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        st.executeUpdate(sql);
    }
}
