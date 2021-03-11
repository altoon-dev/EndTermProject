package com.company;
//importing the libraries to use it then (here we will use SQLException class and Scanner)
import java.sql.SQLException;
import java.util.Scanner;
//class Driver, object of which will be called in main class
public class Driver {
    //the only method of class Driver. It will be called from main class and will start the main process of the program
    public void start() throws SQLException {
        ElectricityBilling eb = new ElectricityBilling();
        //creating the scanner to let user insert values by keyboard
        Scanner s = new Scanner(System.in);
        //using while loop, in order to create an endless loop (this is needed to work with console)
        while (true) {
            //these lines of code will appear in console
            System.out.println("What do you want to do? Choose one of the options:");
            System.out.println("1. Calculate the total energy consumption");
            System.out.println("2. See energy consumption of different electrical techniques:");
            System.out.println("3. Calculate the total power of household techniques: ");
            System.out.println("4. Exit program");
            //getting the number inserted by user
            int input = s.nextInt();
            //if user inserts "1"
            if (input == 1) {
                //calling the method firstChoice of the object eb
                eb.firstChoice();
                continue;
            }
            //if user inserts "2"
            if (input == 2){
                //calling the method secondChoice of the object eb
                eb.secondChoice();
                continue;
            }
            //if user inserts "3"
            if (input == 3){
                //calling the method thirdChoice of the object eb
                eb.thirdChoice();
                continue;
            }
            //if user inserts "4" or anything else
            else {
                //calling the method fourthChoice of the object eb
                eb.fourthChoice();
                //ending the loop, consequently the function
                break;
            }
        }
    }
}
