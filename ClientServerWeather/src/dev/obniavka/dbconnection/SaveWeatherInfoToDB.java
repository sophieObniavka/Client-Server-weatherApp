package dev.obniavka.dbconnection;

import java.sql.*;


public class SaveWeatherInfoToDB  extends Configs{
    public Connection dataBaseLink;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dataBaseLink = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dataBaseLink;
    }

    public boolean authorize(String name, String password) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet admin =  statement.executeQuery("SELECT name, password FROM weather.moderator");
        while (admin.next()) {
            String dataAdmin;
            String dataPass;
            dataAdmin = admin.getString("name");
            dataPass = admin.getString("password");
            if (dataAdmin.equals(name) && dataPass.equals(password)) {
                return true;
            }
        }
        return false;
    }



}
