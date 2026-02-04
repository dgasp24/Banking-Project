package BankingApplication;

import BankingApplication.services.*;
import BankingApplication.utilities.*;

import java.util.*;

public class BankProgram {
    boolean running = true;
    int inpt = 0;
    Scanner scnr;
    ArrayList<User> users;

    public BankProgram(Scanner scnr, ArrayList<User> users){
        this.scnr = scnr;
        this.users = users;

    }

    public void run(){
        do{
        try{   
        System.out.println("Welcome to the Bank");
        System.out.println("1. Login ");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        inpt = scnr.nextInt();

        switch(inpt){
            case 1://Logging in
                ConsoleUtils.clearConsole();

                //Returns user
                int index = UserService.loggingIn(scnr, users);
            
                if(index != -1) {
                    ConsoleUtils.clearConsole();
                    MainMenu(scnr, users,index);
                } else {
                    ConsoleUtils.clearConsole();
                    System.out.println("Invalid login, please try again.");
                }
                break;

            case 2://Account Creation
                ConsoleUtils.clearConsole();
                UserService.createAccount(scnr,users);
                break;

            case 3://Exiting
                running = false;
                break;
            default:
                ConsoleUtils.clearConsole();
                System.out.println("Please choose between 1-3");
            }
        }catch(InputMismatchException e){
            ConsoleUtils.clearConsole();
            System.out.println("-------------------------");
            System.out.println("Please only type numbers!");
            System.out.println("-------------------------");
            scnr.nextLine();
            continue;
        }

        }while(running);

    }
    

    //Main Loop for Program
    public void MainMenu(Scanner scnr, ArrayList<User> users, int index){
        int userInpt = 0;
        boolean running = true;

        do{
        try{
        
        System.out.println("Welcome " + users.get(index).FirstName);
        System.out.println("-------------------");
        if(users.get(index).cash > 0){
            System.out.println("Cash: $" + users.get(index).cash);
        }
        System.out.println("Checkings: $" + users.get(index).checking);
        System.out.println("Savings: $" + users.get(index).savings);
        System.out.println("-------------------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Pay Someone");
        System.out.println("4. Account Settings");
        System.out.println("5. Logout");
        System.out.println("-------------------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1://Depositing
                ConsoleUtils.clearConsole();
                MoneyService.depositMoney(scnr, users.get(index));
                break;
            case 2://Withdrawing
                ConsoleUtils.clearConsole();
                MoneyService.withdrawMoney(scnr, users.get(index));
                break;
            case 3://Account Manager
                ConsoleUtils.clearConsole();
                MoneyService.payment(scnr, users.get(index), users);
                break;
            case 4://Account Manager
                ConsoleUtils.clearConsole();
                UserService.AccountManager(scnr, users.get(index));
                break;
            case 5:
                running = false;
                ConsoleUtils.clearConsole();
                System.out.println("Goodbye!");
                break;
            default:
                ConsoleUtils.clearConsole();
                System.out.println("--------------------------");
                System.out.println("Please choose between 1-4!");
                 System.out.println("--------------------------");
                break;
        }
            
        }catch(InputMismatchException e){
            ConsoleUtils.clearConsole();
            System.out.println("----------------------------------");
            System.out.println("Error! Please only type numbers!");
            System.out.println("----------------------------------");
            scnr.nextLine();
            continue;
        }
        }while(running);
    }

}
