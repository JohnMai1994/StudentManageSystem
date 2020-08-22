package com.jiadong.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Utils {

    private static DataSource dataSource;
    static {
        try {
            Properties properties = new Properties();
            properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("url", "jdbc:mysql://localhost/mystudentsys");
            properties.setProperty("username", "root");
            properties.setProperty("password", "password");

//            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
//            System.out.println("InputStream "+ is);
//            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        System.out.println("Gain Connection Success");
        return connection;
    }

    public static void  closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
        try{
            if (connection != null) {
                connection.close();
                System.out.println("Close Connection Success");
            }

        } catch (SQLException e) {
            System.out.println("Close Connection Fail");
            e.printStackTrace();
        }

        try{
            if (ps != null) {
                ps.close();
                System.out.println("Close PreparedStatement Success");
            }

        } catch (SQLException e) {
            System.out.println("Close PreparedStatement Fail");
            e.printStackTrace();
        }

        try{
            if (rs != null) {
                rs.close();
                System.out.println("Close ResultSet Success");
            }

        } catch (SQLException e) {
            System.out.println("Close ResultSet Fail");
            e.printStackTrace();
        }

    }

}
