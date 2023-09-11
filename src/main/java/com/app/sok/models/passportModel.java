package com.app.sok.models;

import com.app.sok.Application;

import java.sql.*;
import java.util.Objects;

public class passportModel {
    private String seriesNumber;
    private String name;
    private String lastName;
    private String fathersName;
    private String gender;
    private String birthDate;
    private String birthCity;
    private String birthCountry;
    private String kodPodr;
    private String whoRelease;
    private String releaseDate;
    private String registrationDate;
    private String region;
    private String punct;
    private String district;
    private String street;
    private String house;
    private String apartment;

    public passportModel() {
        this.seriesNumber = null;
        this.lastName = null;
        this.name = null;
        this.fathersName = null;
        this.gender = null;
        this.birthDate = null;
        this.kodPodr = null;
        this.whoRelease = null;
        this.releaseDate = null;
        this.birthCity = null;
        this.birthCountry = null;
        this.registrationDate = null;
        this.region = null;
        this.punct = null;
        this.district = null;
        this.street = null;
        this.house = null;
        this.apartment = null;

    }

    public passportModel(String[] passportData) {
        this.seriesNumber = passportData[0];
        this.lastName = passportData[2];
        this.name = passportData[1];
        this.fathersName = passportData[3];
        this.gender = passportData[4];
        this.birthDate = passportData[5];
        this.kodPodr = passportData[6];
        this.whoRelease = passportData[7];
        this.releaseDate = passportData[8];
        this.birthCity = passportData[9];
        this.birthCountry = passportData[10];
        this.registrationDate = passportData[11];
        this.region = passportData[12];
        this.punct = passportData[13];
        this.district = passportData[14];
        this.street = passportData[15];
        this.house = passportData[16];
        this.apartment = passportData[17];
    }

    public void insert() throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT count('Серия и номер') AS count FROM Паспорт WHERE 'Серия и номер' = %s", this.seriesNumber);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        if (res.getLong("count") == 0L) {
            StringBuilder values = new StringBuilder();
            values.append("'").append(this.seriesNumber);
            values.append("', '").append(this.name);
            values.append("', '").append(this.lastName);
            values.append("', '").append(this.fathersName);
            values.append("', '").append(this.gender);
            values.append("', '").append(this.birthDate);
            values.append("', '").append(this.kodPodr);
            values.append("', '").append(this.whoRelease);
            values.append("', '").append(this.releaseDate);
            values.append("', '").append(this.birthCity);
            values.append("', '").append(this.birthCountry);
            values.append("', '").append(this.registrationDate);
            values.append("', '").append(this.region);
            values.append("', '").append(this.punct);
            values.append("', '").append(this.district);
            values.append("', '").append(this.street);
            values.append("', '").append(this.house);
            values.append("', '").append(this.apartment).append("'");
            sql = String.format("INSERT INTO Паспорт VALUES(%s)", values);
            System.out.println(sql);
            st = Application.getConnection().createStatement();
            st.executeUpdate(sql);
            st.close();
        }
    }

    public String getFIO() {
        return this.lastName + " " + this.name + " " + this.fathersName;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getKodPodr() {
        return kodPodr;
    }

    public void setKodPodr(String kodPodr) {
        this.kodPodr = kodPodr;
    }

    public String getWhoRelease() {
        return whoRelease;
    }

    public void setWhoRelease(String whoRelease) {
        this.whoRelease = whoRelease;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPunct() {
        return punct;
    }

    public void setPunct(String punct) {
        this.punct = punct;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
