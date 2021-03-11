package com.company;
//importing specific libraries for using it then(Arrays, Using a console)
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectricityBilling {
    Database database = new Database();
    public void firstChoice() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("How many electrical techniques you want to add?");
        //getting the number of techniques the user wants to insert
        int number = s.nextInt();
        //creating an Arraylist, to store techniques` name there (EX: kettle, fridge and etc.)
        ArrayList<String> techniques = new ArrayList<>();
        //creating an Arraylist, to store techniques` consumption value (EX: 10, 250 and etc.)
        ArrayList<Integer> technique_consumption = new ArrayList<>();
        //using loop for inserting the n number of items and saving all of them inside the "techniques" array
        for (int i = 0; i < number; i++) {
            System.out.println("Please type the name of the electrical technique: ");
            String tempTechnique = s.next();
            techniques.add(tempTechnique);
        }
        //creation of ResultSet object to get data from Database
        ResultSet rs = null;
        //writing a query, that will be sent to DB then
        String query = "SELECT technique_name, technique_consumption FROM techniques WHERE technique_name = ?";
        //this array is to get the name of exact items that user asked
        for(int i=0; i<number; i++){
            //creating a prepared statement, to get only those items, that user inserted
            try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
                ps.setString(1, techniques.get(i));
                rs = ps.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs.next();
            //adding values that we got from query to the array "technique_consumption"
            technique_consumption.add(rs.getInt("technique_consumption"));
        }
        //loop for outputting consumption in specific format
        for (int i = 0; i < number; i++) {
            System.out.println("Technique " + techniques.get(i) + " consumes " + technique_consumption.get(i) + "W energy!");
        }
        //leaving a line (space)
        System.out.println("\n");
    }
    public void secondChoice() throws SQLException {
        //creation of a statement, to implement some statement
        Statement stmt = database.getConnection().createStatement();
        //creation of ResultSet to store the output of query execution
        ResultSet rs = stmt.executeQuery("SELECT * FROM techniques");
        //loop for outputting all values of ResultSet in specific format
        while (rs.next()) {
            String technique_name = rs.getString("technique_name");
            int technique_consumption = rs.getInt("technique_consumption");
            System.out.println(technique_name + " consumes " + technique_consumption + "W energy!");
        }
        //leaving a line (space)
        System.out.println("\n");
    }
    public void thirdChoice() {
        //creation of scanner to read user input
        Scanner s = new Scanner(System.in);
        System.out.println("How many electrical techniques do you have in your house?");
        //saving the number inputted by user to variable "number"
        int number = s.nextInt();
        //creation of array "techniques" to store the names of techniques
        ArrayList<String> techniques = new ArrayList<>();
        //creation of array "techniques" to store the names of techniques
        ArrayList<Double> technique_power = new ArrayList<>();
        //loop used for receiving the inputted by user values and store it in array "techniques"
        for (int i = 0; i < number; i++) {
            System.out.println("Please type the name of the electrical technique: ");
            String tempTechnique = s.next();
            techniques.add(tempTechnique);
        }
        //creating the variable "query" type of string, to store our future query
        String query = "SELECT technique_name, technique_consumption FROM techniques WHERE technique_name = ?";
        //creating the variable totalAmpere to use it then
        Double totalAmpere = 0.0;
        for (int i = 0; i < number; i++) {
            try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
                ps.setString(1, techniques.get(i));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    double tempPower = rs.getDouble("technique_consumption") / 220;
                    technique_power.add(tempPower);
                    totalAmpere+=tempPower;
                }
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //outputting the list of techniques with its power
        for (int i = 0; i < number; i++) {
            System.out.println("Technique " + techniques.get(i) + " have power of " + technique_power.get(i) + " amperes!");
        }
        //outputting the minimum amount of amperes should support fuse in your house
        System.out.println("The fuse in your house should support at least " + totalAmpere+ " amperes!");
        //leaving a line (space)
        System.out.println("\n");
    }
    public void fourthChoice() throws SQLException {
        System.out.println("Thank you for using our Electricity Billing System!");
        database.getConnection().close();
    }


}