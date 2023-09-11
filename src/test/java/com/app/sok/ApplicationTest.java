package com.app.sok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class ApplicationTest {

    @Test
    void Application() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        }

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.37:3306/mydb", "root", "");
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            con.close();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(true);

    }
}