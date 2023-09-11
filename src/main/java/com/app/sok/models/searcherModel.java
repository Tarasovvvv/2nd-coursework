package com.app.sok.models;

import com.app.sok.Application;
import com.mysql.cj.conf.PropertyDefinitions;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class searcherModel {

    public searcherModel() {
    }

    public String newId(String tableFor, String idColumn) throws SQLException, ClassNotFoundException {
        final String sql = String.format("SELECT count(`%s`) AS count FROM `%s`", idColumn, tableFor);
        final Statement st = Application.getConnection().createStatement();
        final ResultSet res = st.executeQuery(sql);
        res.next();
        return Long.toString(res.getLong("count") + 1);
    }

    public int getCount(String idColumn, String tableFor, String where) throws SQLException, ClassNotFoundException {
        final String sql = String.format("SELECT count(`%s`) AS count FROM `%s` WHERE %s", idColumn, tableFor, where);
        System.out.println(sql);
        final Statement st = Application.getConnection().createStatement();
        final ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getInt("count");
    }

    public String getLatestDate(ArrayList<String> dates) {
        String latestDate = dates.get(0);
        for (String date : dates)
            if (Date.valueOf(date).getTime() > Date.valueOf(latestDate).getTime())
                latestDate = date;
        return latestDate;
    }

    public ArrayList<studentModel> searchStudentData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<UZModel> searchUZData(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM `Учебное заведение` WHERE Название REGEXP \".*%s.*\"", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<UZModel> UZs = new ArrayList<UZModel>();
        while (res.next()) {
            UZs.add(new UZModel(
                    res.getString("Код учебного заведения"),
                    res.getString("Код вида учебного заведения"),
                    res.getString("Код директора"),
                    res.getString("Название"),
                    res.getString("Область"),
                    res.getString("Населенный пункт"),
                    res.getString("Улица"),
                    res.getString("Дом"),
                    this.searchEmployee(res.getString("Код директора"))));
        }
        return UZs;
    }

    public ArrayList<academicRecordModel> searchAcademicRecordData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<academicRecordModel> academicRecords = new ArrayList<academicRecordModel>();
        String sql = String.format("SELECT * FROM `Документ об образовании` WHERE `Серия и номер паспорта` = '%s'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next()) {
            academicRecords.add(new academicRecordModel(
                    res.getString("Серия и номер"),
                    res.getString("Серия и номер паспорта"),
                    res.getString("Код уровня образования"),
                    res.getString("Код учебного заведения"),
                    res.getString("Код направления"),
                    res.getString("Название направления"),
                    res.getString("Дата выдачи")));
        }
        return academicRecords;
    }

    public ArrayList<directionModel> searchDirectionData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,6}$"))
                sql = String.format("SELECT `Код направления`, `Название направления` " +
                        "FROM Направление " +
                        "WHERE `Код направления` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT `Код направления`, `Название направления` " +
                        "FROM Направление " +
                        "WHERE `Название направления` REGEXP \".*%s.*\"", request);
            System.out.println(sql);
            ArrayList<directionModel> directions = new ArrayList<directionModel>();
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next())
                directions.add(new directionModel(
                        res.getString("Код направления"),
                        res.getString("Название направления")));
            return directions;
        }
        return new ArrayList<directionModel>();
    }

    public employeeModel searchEmployee(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Сотрудник WHERE `Код сотрудника` = %s", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        if (res.isBeforeFirst()) {
            res.next();
            return new employeeModel(
                    res.getString("Фамилия"),
                    res.getString("Имя"),
                    res.getString("Отчество"),
                    res.getString("Телефон"),
                    res.getString("E-mail"));
        } else return new employeeModel("", "", "", "", "");
    }

    public ArrayList<employeeModel> searchSigners() throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Сотрудник.* FROM Сотрудник, `Сотрудник-Должность` " +
                "WHERE (`Сотрудник-Должность`.`Код должности` = '2' OR `Сотрудник-Должность`.`Код должности` = '6') " +
                "AND Сотрудник.`Код сотрудника` = `Сотрудник-Должность`.`Код сотрудника`");
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<employeeModel> Signers = new ArrayList<>();
        while (res.next())
            Signers.add(new employeeModel(res.getString("Код сотрудника"), res.getString("Фамилия"), res.getString("Имя"), res.getString("Отчество")));
        return Signers;
    }

    public ArrayList<ZZModel> searchZZData(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM `Заявление о поступлении` WHERE `Серия и номер паспорта` = %s", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<ZZModel> ZZs = new ArrayList<ZZModel>();
        while (res.next())
            ZZs.add(new ZZModel(res.getString("Код заявления"), request, res.getString("Серия и номер документа об образовании"), res.getString("Дата подписи")));
        return ZZs;
    }

    public orderModel searchLatestOrder(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Приказ.* FROM Приказ, `Приказ-Паспорт` " +
                "WHERE `Приказ-Паспорт`.`Серия и номер паспорта` = '%s' " +
                "AND Приказ.`Код приказа` = `Приказ-Паспорт`.`Код приказа` " +
                "ORDER BY Приказ.`Код приказа` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new orderModel(res.getString("Код приказа"), res.getString("Код вида приказа"),
                res.getString("Дата исполнения"), res.getString("Дата подписи"));


    }

    public ZZModel searchLatestZZ(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление о поступлении`.* FROM Студент, `Заявление о поступлении` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление о поступлении`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление о поступлении`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZZModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                res.getString("Серия и номер документа об образовании"), res.getString("Дата подписи"));
    }

    public ZOModel searchLatestZO(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление об отчислении`.* FROM Студент, `Заявление об отчислении` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление об отчислении`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление об отчислении`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZOModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                res.getString("Код основания отчисления"), res.getString("Дата подписи"));
    }

    public ZAOModel searchLatestZUAO(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление о предоставлении академ отпуска`.* FROM Студент, `Заявление о предоставлении академ отпуска` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление о предоставлении академ отпуска`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление о предоставлении академ отпуска`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZAOModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                res.getString("Код основания для ухода"), res.getString("Дата начала"),
                res.getString("Дата окончания"), res.getString("Дата подписи"));
    }

    public ZAO2Model searchLatestZPVAO(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление о продлении/выходе из академ отпуска`.* FROM Студент, `Заявление о продлении/выходе из академ отпуска` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление о продлении/выходе из академ отпуска`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление о продлении/выходе из академ отпуска`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZAO2Model(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                res.getString("Дата выхода из отпуска"), res.getString("Дата подписи"));
    }

    public ZVModel searchLatestZV(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление о восстановлении`.* FROM Студент, `Заявление о восстановлении` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление о восстановлении`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление о восстановлении`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZVModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"), res.getString("Дата подписи"));
    }

    public ZPGModel searchLatestZPG(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Заявление о переводе в другую группу`.* FROM Студент, `Заявление о переводе в другую группу` " +
                "WHERE Студент.`Серия и номер паспорта` = `Заявление о переводе в другую группу`.`Серия и номер паспорта` " +
                "AND Студент.`Код студента` = '%s' ORDER BY `Заявление о переводе в другую группу`.`Код заявления` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new ZPGModel(res.getString("Код заявления"), res.getString("Код группы"),
                res.getString("Серия и номер паспорта"), res.getString("Дата подписи"));
    }

    public groupModel searchLatestPZDirectionID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Группа.* " +
                "FROM `Группа-Приказ`, Приказ, Группа " +
                "WHERE Группа.`Код группы` = `Группа-Приказ`.`Код группы` " +
                "AND `Группа-Приказ`.`Код приказа` = Приказ.`Код приказа` " +
                "AND `Группа-Приказ`.`Серия и номер паспорта` = '%s' " +
                "ORDER BY Приказ.`Код приказа` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new groupModel(res.getString("Код группы"), res.getString("Код направления"), res.getString("Курс"));
    }

    public ArrayList<DPPESModel> searchDPPESData(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM `Договор об оказании плат обр услуг` WHERE `Серия и номер паспорта` = %s", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<DPPESModel> DPPES = new ArrayList<DPPESModel>();
        while (res.next())
            DPPES.add(new DPPESModel(res.getString(1), request, res.getString(3),
                    res.getString(4), res.getString(5)));
        return DPPES;
    }

    public ArrayList<String> searchGroupsToZZ(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Код группы` FROM Группа WHERE `Код направления` = %s AND Курс = 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<String> groups = new ArrayList<String>();
        while (res.next())
            groups.add(res.getString("Код группы"));
        return groups;
    }

    public ArrayList<String> searchGroupsToP(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Группа.Курс, `Группа-Приказ`.`Код группы` FROM Группа, `Группа-Приказ` WHERE `Серия и номер паспорта` = %s", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        sql = String.format("SELECT `Код группы` FROM Группа WHERE `Код направления` = %s AND Курс = %d",
                res.getString("`Группа-Приказ`.`Код группы`"), res.getInt("Группа.Курс") + 1);
        System.out.println(sql);
        st = Application.getConnection().createStatement();
        res = st.executeQuery(sql);
        ArrayList<String> groups = new ArrayList<String>();
        while (res.next())
            groups.add(res.getString("Код группы"));
        return groups;
    }

    public ArrayList<String> searchDZZids(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("" +
                "SELECT `Заявление-Направление`.`Код направления`" +
                "FROM `Заявление-Направление`, `Заявление о поступлении` " +
                "WHERE `Заявление-Направление`.`Код заявления о поступлении` = `Заявление о поступлении`.`Код заявления` " +
                "AND `Заявление о поступлении`.`Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<String> DZZids = new ArrayList<String>();
        while (res.next())
            DZZids.add(res.getString("Код направления"));
        return DZZids;
    }

    public ArrayList<ZAOModel> searchZAOData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZAOModel> foundZAOs = new ArrayList<ZAOModel>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление о предоставлении академ отпуска` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZAOs.add(new ZAOModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                    res.getString("Код основания для ухода"), res.getString("Дата начала"),
                    res.getString("Дата окончания"), res.getString("Дата подписи")));
        return foundZAOs;
    }

    public ArrayList<ZAO2Model> searchZAO2Data(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZAO2Model> foundZAO2s = new ArrayList<ZAO2Model>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление о продлении/выходе из академ отпуска` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZAO2s.add(new ZAO2Model(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                    res.getString("Дата выхода из отпуска"), res.getString("Дата подписи")));
        return foundZAO2s;
    }

    public ArrayList<ZOModel> searchZOData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZOModel> foundZOs = new ArrayList<ZOModel>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление об отчислении` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZOs.add(new ZOModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"),
                    res.getString("Код основания отчисления"), res.getString("Дата подписи")));
        return foundZOs;
    }

    public ArrayList<ZO2Model> searchZO2Data(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZO2Model> foundZO2s = new ArrayList<ZO2Model>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление об отчислении в связи с перев в др универ` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZO2s.add(new ZO2Model(res.getString("Код заявления"), res.getString("Код университета"),
                    res.getString("Серия и номер паспорта"), res.getString("Дата подписи")));
        return foundZO2s;
    }

    public ArrayList<ZVModel> searchZVData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZVModel> foundZVs = new ArrayList<ZVModel>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление о восстановлении` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZVs.add(new ZVModel(res.getString("Код заявления"), res.getString("Серия и номер паспорта"), res.getString("Дата подписи")));
        return foundZVs;
    }

    public ArrayList<ZPGModel> searchZPGData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZPGModel> foundZPGs = new ArrayList<ZPGModel>();
        String sql = String.format("SELECT * " +
                "FROM `Заявление о переводе в другую группу` " +
                "WHERE `Серия и номер паспорта` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundZPGs.add(new ZPGModel(res.getString("Код заявления"), res.getString("Код группы"),
                    res.getString("Серия и номер паспорта"), res.getString("Дата подписи")));
        return foundZPGs;
    }

    public ArrayList<orderModel> searchOrderEmployeeData(String request) throws SQLException, ClassNotFoundException {
        ArrayList<orderModel> foundOrders = new ArrayList<orderModel>();
        String sql = String.format("SELECT Приказ.* " +
                "FROM Приказ, `Приказ-Паспорт`, Паспорт " +
                "WHERE Паспорт.`Серия и номер` = '%s' " +
                "AND `Приказ-Паспорт`.`Серия и номер паспорта` = Паспорт.`Серия и номер` " +
                "AND Приказ.`Код приказа` = `Приказ-Паспорт`.`Код приказа` ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while (res.next())
            foundOrders.add(new orderModel(res.getString("Код приказа"), res.getString("Код вида приказа"),
                    res.getString("Дата исполнения"), res.getString("Дата подписи")));

        for (orderModel order : foundOrders) {
            sql = String.format("SELECT Сотрудник.Фамилия, Сотрудник.Имя, Сотрудник.Отчество " +
                    "FROM Сотрудник, `Приказ-Сотрудник`, `Сотрудник-Должность`, Приказ " +
                    "WHERE Сотрудник.`Код сотрудника` = `Приказ-Сотрудник`.`Код сотрудника` " +
                    "AND `Сотрудник-Должность`.`Код должности` = 3 " +
                    "AND `Сотрудник-Должность`.`Код сотрудника` =  Сотрудник.`Код сотрудника` " +
                    "AND `Приказ-Сотрудник`.`Код приказа` = Приказ.`Код приказа` " +
                    "AND Приказ.`Код приказа` = '%s'", order.getID());
            ResultSet res2 = st.executeQuery(sql);
            res2.next();
            String release_person_fio = res2.getString("Сотрудник.Фамилия") + " " + res2.getString("Сотрудник.Имя").charAt(0)
                    + "." + res2.getString("Сотрудник.Отчество").charAt(0) + ".";


            sql = String.format("SELECT Сотрудник.Фамилия, Сотрудник.Имя, Сотрудник.Отчество " +
                    "FROM Сотрудник, `Приказ-Сотрудник`, `Сотрудник-Должность`, Приказ " +
                    "WHERE Сотрудник.`Код сотрудника` = `Приказ-Сотрудник`.`Код сотрудника` " +
                    "AND (`Сотрудник-Должность`.`Код должности` = 2 OR `Сотрудник-Должность`.`Код должности` = 6) " +
                    "AND `Сотрудник-Должность`.`Код сотрудника` =  Сотрудник.`Код сотрудника` " +
                    "AND `Приказ-Сотрудник`.`Код приказа` = Приказ.`Код приказа` " +
                    "AND Приказ.`Код приказа` = '%s'", order.getID());
            res2 = st.executeQuery(sql);
            res2.next();
            String sign_person_fio = res2.getString("Сотрудник.Фамилия") + " " + res2.getString("Сотрудник.Имя").charAt(0)
                    + "." + res2.getString("Сотрудник.Отчество").charAt(0) + ".";
            order.setRELEASE_PERSON(release_person_fio);
            order.setSIGN_PERSON(sign_person_fio);
        }
        return foundOrders;
    }

    public String searchOReason(String request) throws SQLException, ClassNotFoundException {
        ArrayList<ZPGModel> foundZPGs = new ArrayList<ZPGModel>();
        String sql = String.format("SELECT `Основание отчисления` " +
                "FROM `Основание отчисления` " +
                "WHERE `Код основания отчисления` = '%s' ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getString("Основание отчисления");
    }

    public ArrayList<studentModel> searchStudentWithZZData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о поступлении` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о поступлении`.`Серия и номер паспорта`" +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о поступлении` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о поступлении`.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<studentModel> searchStudentWithZOData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление об отчислении`,`Заявление об отчислении в связи с перев в др универ` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND (`Заявление об отчислении`.`Серия и номер паспорта` = Паспорт.`Серия и номер` " +
                        "OR `Заявление об отчислении в связи с перев в др универ`.`Серия и номер паспорта` = Паспорт.`Серия и номер`) " +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление об отчислении`,`Заявление об отчислении в связи с перев в др универ` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND (`Заявление об отчислении`.`Серия и номер паспорта` = Паспорт.`Серия и номер` " +
                        "OR `Заявление об отчислении в связи с перев в др универ`.`Серия и номер паспорта` = Паспорт.`Серия и номер`) " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<studentModel> searchStudentWithZUAOData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о предоставлении академ отпуска` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о предоставлении академ отпуска`.`Серия и номер паспорта`" +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о предоставлении академ отпуска` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о предоставлении академ отпуска`.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<studentModel> searchStudentWithZPVAOData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о продлении/выходе из академ отпуска` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о продлении/выходе из академ отпуска`.`Серия и номер паспорта`" +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о предоставлении академ отпуска` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о продлении/выходе из академ отпуска`.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<studentModel> searchStudentWithZVData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о восстановлении` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о восстановлении`.`Серия и номер паспорта`" +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о восстановлении` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о восстановлении`.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public ArrayList<studentModel> searchStudentWithZPGData(String request) throws SQLException, ClassNotFoundException {
        if (request.matches("[^A-z]*") && !request.equals("") && !request.equals(" ")) {
            String sql = null;
            if (request.matches("^[0-9]{1,10}$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о переводе в другую группу` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о переводе в другую группу`.`Серия и номер паспорта`" +
                        "AND Паспорт.`Серия и номер` REGEXP \"[0-9]{0,9}%s[0-9]{0,9}\"", request);
            else if (request.matches("^[А-я ]+$"))
                sql = String.format("SELECT Студент.`Код студента` " +
                        "FROM Паспорт, Студент, `Заявление о переводе в другую группу` " +
                        "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                        "AND Паспорт.`Серия и номер` = `Заявление о переводе в другую группу`.`Серия и номер паспорта` " +
                        "AND CONCAT_WS(' ', Паспорт.`Фамилия`, Паспорт.`Имя`, Паспорт.`Отчество`) REGEXP \".*%s.*\"", request);

            System.out.println(sql);
            Statement st = Application.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res != null) {
                ArrayList<studentModel> students = new ArrayList<studentModel>();
                while (res.next()) {
                    String currentStudentID = res.getString("Код студента");
                    boolean studentNotAdded = true;
                    for (studentModel student : students)
                        if (currentStudentID.equals(student.getId())) {
                            studentNotAdded = false;
                            break;
                        }
                    if (studentNotAdded) {
                        ArrayList<passportModel> passports = new ArrayList<passportModel>();
                        sql = String.format("SELECT Паспорт.* " +
                                "FROM Паспорт, Студент " +
                                "WHERE Паспорт.`Серия и номер` = Студент.`Серия и номер паспорта` " +
                                "AND Студент.`Код студента` = '%s'", currentStudentID);
                        st = Application.getConnection().createStatement();
                        ResultSet tempRes = st.executeQuery(sql);
                        String[] passportData = new String[18];
                        while (tempRes.next()) {
                            for (int i = 0; i < 18; i++)
                                passportData[i] = tempRes.getObject(i + 1).toString();
                            passports.add(new passportModel(passportData));
                        }
                        students.add(new studentModel(currentStudentID, passports));
                    }
                }
                return students;
            } else return null;
        } else return null;
    }

    public orderModel searchOrderByReleaseDate(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Приказ WHERE `Дата исполнения` = '%s' LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return new orderModel(res.getString("Код приказа"), res.getString("Код вида приказа"),
                res.getString("Дата исполнения"), res.getString("Дата подписи"));
    }

    public ArrayList<UZModel> searchVUZes(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM `Учебное заведение` WHERE Название REGEXP \".*%s.*\" AND `Код вида учебного заведения` = '3'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<UZModel> UZs = new ArrayList<UZModel>();
        while (res.next()) {
            UZs.add(new UZModel(
                    res.getString("Код учебного заведения"),
                    res.getString("Код вида учебного заведения"),
                    res.getString("Код директора"),
                    res.getString("Название"),
                    res.getString("Область"),
                    res.getString("Населенный пункт"),
                    res.getString("Улица"),
                    res.getString("Дом"),
                    this.searchEmployee(res.getString("Код директора"))));
        }
        return UZs;
    }

    public String searchGroupIDByOrderID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Группа-приказ`.`Код группы` FROM `Группа-приказ`, Приказ " +
                "WHERE `Группа-приказ`.`Код приказа` = Приказ.`Код приказа` " +
                "AND Приказ.`Код приказа` = '%s'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getString("Код группы");
    }

    public String searchCourseByGroupID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Курс FROM Группа WHERE `Код группы` = '%s'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getString("Курс");
    }

    public String searchDirectionByGroupID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Код направления` FROM Группа WHERE `Код группы` = '%s'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        String result = res.getString("Код направления");
        sql = String.format("SELECT `Название направления` FROM Направление WHERE `Код направления` = '%s'", result);
        System.out.println(sql);
        res = st.executeQuery(sql);
        res.next();
        return result + " " + res.getString("Название направления");
    }

    public String searchEducationFormByDirectionID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Форма обучения`.`Название формы обучения` " +
                "FROM `Направление-Форма_обучения`, `Форма обучения` " +
                "WHERE `Направление-Форма_обучения`.`Код направления` = '%s' " +
                "AND `Форма обучения`.`Код формы обучения` = `Направление-Форма_обучения`.`Код формы обучения`", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getString("Название формы обучения");
    }

    public String searchDirectionIDByPassport(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT Направление.`Код направления` " +
                "FROM Группа, `Группа-Приказ`, Направление " +
                "WHERE Направление.`Код направления` = Группа.`Код направления` " +
                "AND Группа.`Код группы` = `Группа-Приказ`.`Код группы` " +
                "AND `Группа-Приказ`.`Серия и номер паспорта` = %s ", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        res.next();
        return res.getString("Код направления");
    }

    public ArrayList<groupModel> searchGroupsByDirectionID(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Группа WHERE `Код направления` = '%s'", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<groupModel> groups = new ArrayList<groupModel>();
        while (res.next())
            groups.add(new groupModel(res.getString("Код группы"), res.getString("Код направления"), res.getString("Курс")));
        return groups;
    }

    public ArrayList<groupModel> searchGroupsNextCourseByDirectionIDAndCourse(String DirectionRequest, String CourseRequest) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Группа WHERE `Код направления` = '%s' AND Курс = '%s' + 1", DirectionRequest, CourseRequest);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<groupModel> groups = new ArrayList<groupModel>();
        while (res.next())
            groups.add(new groupModel(res.getString("Код группы"), res.getString("Код направления"), res.getString("Курс")));
        return groups;
    }

    public ArrayList<groupModel> searchGroupsByGroup(String DirectionRequest, String CourseRequest) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Группа WHERE `Код направления` = '%s' AND Курс = '%s'", DirectionRequest, CourseRequest);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<groupModel> groups = new ArrayList<groupModel>();
        while (res.next())
            groups.add(new groupModel(res.getString("Код группы"), res.getString("Код направления"), res.getString("Курс")));
        return groups;
    }

    public String searchCurrentGroup(String request) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT `Код группы` FROM `Группа-Приказ` WHERE `Серия и номер паспорта` = '%s' ORDER BY `Код приказа` DESC LIMIT 1", request);
        System.out.println(sql);
        Statement st = Application.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        ArrayList<groupModel> groups = new ArrayList<groupModel>();
        res.next();
        return res.getString("Код группы");
    }
}