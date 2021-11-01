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


    public void addWeather(String text,int temp,String pressure, java.sql.Date date, String sky) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO weather.weather_history(oblast, temp, pressure, date, sky)"
                + "VALUES(?,?,?,?,?)";
        PreparedStatement prST = getConnection().prepareStatement(insert);
        prST.setString(1, text);
        prST.setInt(2, temp);
        prST.setString(3,pressure);
        prST.setDate(4,date);
        prST.setString(5,sky);
        prST.executeUpdate();
    }


}