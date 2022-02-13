package com.example.task04app.persistance.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public interface Connector {

    Connection getConnection();

    void closeResultSet(ResultSet resultSet);
    void closePreparedStatement(PreparedStatement stmt);
    void closeConnection(Connection conn);
}
