package BankingApplication.services;

import java.util.ArrayList;
import java.util.Scanner;

import BankingApplication.FileService;
import BankingApplication.User;
import BankingApplication.utilities.*;

public class UserService {
    
    public static int loggingIn(Scanner scnr,ArrayList<User> users){
            
            System.out.println("Username: ");
            String userName = scnr.next();
            System.out.println("Password: ");
            String password = scnr.next();

            //Check Credentials
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u.userName.equals(userName) && u.password.equals(password)) {
                    return i; // success
                }
            }

            return -1;
    }

    //Creating Account
    public static void createAccount(Scanner scnr,ArrayList<User> users){
        String firstName = "";
        String lastName = "";
        String userName = "";
        String password = "";
        boolean running = true;

        while(true){
        System.out.println("First Name: ");
        firstName = scnr.next();
        if(firstName.matches("[a-zA-Z]+")){
                break;
            }else{
                System.out.println("Please only enter letters!");
            }
        }

        while(true){
            System.out.println("Last Name: ");
            lastName = scnr.next();

            if(lastName.matches("[a-zA-Z]+")){
                break;
            }else{
                System.out.println("Please only enter letters!");
            }
        }
        

        //Checking to make sure there are no duplicate usernames
        do{
        System.out.println("Username(No Spaces): ");
        userName = scnr.next();
        boolean valid = true;
        for(User u: users){
                if(u.userName.equalsIgnoreCase(userName)){
                    System.out.println("Username already exists, please try again");
                    valid = false;
                    break;
                }
            }
            running = !valid;
        }while(running);

        System.out.println("Password(No Spaces): ");
        password = scnr.next();

        ConsoleUtils.clearConsole();
        users.add(new User(firstName, lastName, userName, password, 0, 0, 1000));
        FileService.write(firstName, lastName, userName, password, 0, 0, 1000);
        System.out.println("Account Created! Each account will get $1000 to start!");
    }

    //Acount Manager
    public static void AccountManager(Scanner scnr, User user){
        boolean running = true;
        int userInpt = 0;
        String inpt = "";
        do{
        
            System.out.println("Account Settings (" + user.userName + ")");
            System.out.println("-------------------");
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Back");
            System.out.println("-------------------");

            userInpt = scnr.nextInt();

            switch(userInpt){
                case 1://New Username
                    System.out.print("Please type new username (No Spaces): ");
                    inpt = scnr.next();
                    user.changeUserName(inpt);
                    System.out.println("Username successfully changed!");
                    System.out.println();
                    running = false;
                    break;
                case 2://New Password
                    ConsoleUtils.clearConsole();
                    System.out.println("Please enter current password: ");
                    inpt = scnr.next();
                    if(!(inpt.equals(user.password))){
                        System.out.println("Passwords do not match!\nPlease try again.");
                    } else{
                        System.out.println("New Password: ");
                        inpt = scnr.next();
                        user.changePassword(inpt);
                        ConsoleUtils.clearConsole();
                        System.out.println("Password successfully changed!");
                    }
                    break;
                case 3:
                    running = false;
                    ConsoleUtils.clearConsole();
                    break;


            }
        
            
        }while(running);
    }
}
