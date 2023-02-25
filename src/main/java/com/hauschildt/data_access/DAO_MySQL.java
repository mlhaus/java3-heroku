package com.hauschildt.data_access;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO_MySQL<T> {
    default Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String db_driver = dotenv.get("DB_DRIVER");
        String db_host = dotenv.get("DB_HOST");
        String db_port = dotenv.get("DB_PORT");
        String db_name = dotenv.get("DB_NAME");
        String db_user = dotenv.get("DB_USER");
        String db_password= dotenv.get("DB_PASS");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("driver error");
        }
        String connStr = String.format("jdbc:%s://%s/%s?sslMode=VERIFY_IDENTITY",
                db_driver, db_host, db_name);
        Connection conn = DriverManager.getConnection(connStr,db_user,db_password);
        return conn;
    }

}
