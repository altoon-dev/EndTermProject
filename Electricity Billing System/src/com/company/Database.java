package com.company;
//importing specific library of sql, so that we could use it then
import java.sql.*;
//in class Database, most part of the code will be written, in order to keep main class clear
public class Database {
    //the method that connects java to database
    public Connection getConnection() {
        Connection connection = null;
        //surrounding with try and catch, in order to get an exception if something unexpected happens
        try {
            Class.forName("org.postgresql.Driver");
            // Establish the connection
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ElectricityBillingSystem", "postgres", "547aea11");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //returning the value of connection connected to database "ElectricityBillingSystem"
        return connection;
    }
}