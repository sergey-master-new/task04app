package com.example.task04app.persistance.connector.impl;

import com.example.task04app.persistance.connector.Connector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Scope("singleton")
@Repository
public class JdbcConnector implements Connector {

    private final static String JDBC_USERNAME = "root";
    private final static String JDBC_PASSWORD = "123";
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/library";
    private final static String DATABASE_DRIVER_NAME = "org.postgresql.Driver";

    static {
        try {
            Class.forName(DATABASE_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            log.info("Error loading driver: {}", e.getMessage());
            throw new RuntimeException("Error loading driver: " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (Exception e) {
            log.info("Error create database connection: {}", e.getMessage());
            throw new RuntimeException("Error create database connection: " + e.getMessage());
        }
    }

    @Override
    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info("Error close ResultSet: {}", e.getMessage());
            }
        }
    }

    public void closePreparedStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("Error close PreparedStatement: {}", e.getMessage());
            }
        }
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.info("Error close Connection: {}", e.getMessage());
            }
        }
    }
}
