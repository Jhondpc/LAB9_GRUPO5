package com.example.lab9_base.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String URL = "jdbc:mysql://localhost:3306/";
        String USER = "root";
        String PASS = "123456";
        String DB = "lab9";

        return DriverManager.getConnection(URL + DB, USER, PASS);
    }
}

