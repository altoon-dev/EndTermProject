package com.company;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        //creation of object "driver" of class "Driver" to implement its method "start()"
        Driver driver = new Driver();
        try {
            //running the method start()
            driver.start();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}