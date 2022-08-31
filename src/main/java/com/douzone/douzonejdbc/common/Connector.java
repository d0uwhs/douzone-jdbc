package com.douzone.douzonejdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connector {
    private static Connection connection = null;
    public static Connection getConnection() {
        String url = "jdbc:oracle:thin:@218.239.207.244:35678:XE";
        String username = "minjunkim";
        String password = "kimminjun";
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
