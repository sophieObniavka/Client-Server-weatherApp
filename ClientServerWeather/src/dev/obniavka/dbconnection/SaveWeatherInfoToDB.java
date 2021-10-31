package dev.obniavka.dbconnection;

import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaveWeatherInfoToDB  extends Configs{
    public Connection dataBaseLink;
    Stage window;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dataBaseLink = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dataBaseLink;
    }


}
