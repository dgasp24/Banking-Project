package BankingApplication.services;

import java.util.*;

import BankingApplication.User;
import BankingApplication.utilities.ConsoleUtils;
import BankingApplication.utilities.MathUtils;

public class MoneyService {

    //Money Deposit Function
    public static void depositMoney(Scanner scnr, User user){
        double amount = 0;
        char choice = ' ';
        boolean running = true;

        while(running){
        System.out.print("How much would you like to deposit:");
        try{
            amount = scnr.nextDouble();
            amount = MathUtils.roundUp(amount);
        }catch(InputMismatchException e){
            ConsoleUtils.clearConsole();
            System.out.println("Please enter a valid number");
            scnr.nextLine();
            continue;
        }

        if(amount < 0){
            ConsoleUtils.clearConsole();
            System.out.println("Deposits must be greater than 0.");
            continue;
        }

        ConsoleUtils.clearConsole();
        System.out.println("You would like to deposit $" + amount +", is that correct? (Y/N)");
        choice = scnr.next().toLowerCase().charAt(0);

        if(amount > user.cash){
            ConsoleUtils.clearConsole();
            System.out.println("Insufficient Funds.");
            continue;
        }


        //Checks to see if the amount of money the user is depositing isn't more than what they have
        if(choice=='y'){
            user.cash -= amount;
            user.checking += amount;
            ConsoleUtils.clearConsole();
            System.out.println("Deposited $" + amount + " into checking account.\nNew balance is: $"+ user.checking);
            running = false;
        }else if (choice !='n'){
            System.out.println("Invalid option, please pick Y or N");
            continue;
        }
        }
    }
    


    //Withdraw Money Function
    public static void withdrawMoney(Scanner scnr, User user){
        double amount = 0;
        char choice = ' ';
        boolean running = true;
    
        do{
        System.out.print("How much money would you like to withdraw? ");
        amount = scnr.nextDouble();
        amount = MathUtils.roundUp(amount);
        System.out.println();
        System.out.println("You would like to withdraw $" + amount +", is that correct? (Y/N)");
        choice = scnr.next().charAt(0);

        //Checks to see if the amount of money the user is withdrawing isn't more than what they have
        if(!(amount > user.checking)){
        if(Character.toLowerCase(choice)=='y'){
            user.checking -= amount;
            user.cash += amount;
            ConsoleUtils.clearConsole();
            System.out.println("Withdrew $" + amount + " from checking account.\nNew balance is: $"+ user.checking);
            running = false;
        }else if (Character.toLowerCase(choice)=='n'){
            continue;
        }else{
            System.out.println("Invalid option, please pick Y or N");
        }
    }else{
        System.out.println("Insufficient funds, please try again.");
    }
    }while(running);
    }

    //FOR PAYING ANOTHER USER
    public static void payment(Scanner scnr, User user, ArrayList<User> users){
        String userName = "";
        int index = 0;
        double payment = 0;
        boolean running = true;
        char choice = ' ';

        while(running){
        ConsoleUtils.clearConsole();
        System.out.println("Who would you like to pay? (Please type Username): ");
        userName = scnr.next();

        index = UserService.findUser(users, userName);

            //If it can find a user
        if (index != -1){
            ConsoleUtils.clearConsole();
            System.out.println("How much would you like to pay " + users.get(index).FirstName + "?");

            try{
                payment = scnr.nextDouble();
                payment = MathUtils.roundUp(payment);
            }catch(InputMismatchException e){
                ConsoleUtils.clearConsole();
                System.out.println("Please enter a valid number");
                scnr.nextLine();
                continue;
            }

            //If user tries to pay negative money
            if(payment < 0){
            ConsoleUtils.clearConsole();
            System.out.println("Deposits must be greater than 0.");
            continue;
            }

            ConsoleUtils.clearConsole();
            System.out.println("You would like to pay $" + payment +" to " + users.get(index).FirstName +" is that correct? (Y/N)");
            choice = scnr.next().toLowerCase().charAt(0);
            
            //If user doesn't have enough money
            if(payment > user.checking){
                ConsoleUtils.clearConsole();
                System.out.println("Insufficient Funds.");
                continue;
            }

            //Sees if user typed y or n
            if(choice=='y'){
                user.checking -= payment;
                users.get(index).checking += payment;
                ConsoleUtils.clearConsole();
                System.out.println("Paid $" + payment + " to " + users.get(index).FirstName + ".\nNew balance is: $"+ user.checking);
                running = false;
            }else if (choice !='n'){
                System.out.println("Invalid option, please pick Y or N");
                continue;
            }
        }else{
            //If the program cannot find a user
            ConsoleUtils.clearConsole();
            System.out.println("Cannot find user, please try again");
            continue;
        }
     }
    }


    //TRANSFERING BETWEEN ACCOUNTS
    public static void transfer(Scanner scnr, User user){
        int inpt = 0;
        boolean running = true;
        double amount = 0;
        char choice = ' ';
        System.out.println("Where would you like to transfer from?\n1. Checkings\n2. Savings");
        inpt = scnr.nextInt();

        while(running){
        System.out.print("How much would you like to transfer:");
        try{
            amount = scnr.nextDouble();
            amount = MathUtils.roundUp(amount);

        }catch(InputMismatchException e){
            ConsoleUtils.clearConsole();
            System.out.println("Please enter a valid number");
            scnr.nextLine();
            continue;
        }

        if(amount < 0){
            ConsoleUtils.clearConsole();
            System.out.println("Transfers must be greater than 0.");
            continue;
        }

        if(inpt == 1){//FOR TRANSFER FROM CHECKINGS TO SAVINGS
        ConsoleUtils.clearConsole();
        System.out.println("You would like to transfer $" + amount +" to your savings account, is that correct? (Y/N)");
        choice = scnr.next().toLowerCase().charAt(0);

        if(amount > user.checking){
            ConsoleUtils.clearConsole();
            System.out.println("Insufficient Funds.");
            continue;
        }

        
        if(choice=='y'){
            user.checking -= amount;
            user.savings += amount;
            ConsoleUtils.clearConsole();
            System.out.println("Transfered $" + amount + " into savings account.\nCheckings: $"+ user.checking +"\nSavings: $" + user.savings);
            running = false;
        }else if (choice !='n'){
            System.out.println("Invalid option, please pick Y or N");
            continue;
        }

    }else if (inpt == 2){//FOR TRANSFER FROM SAVINGS TO CHECKINGS
        ConsoleUtils.clearConsole();
        System.out.println("You would like to transfer $" + amount +" to your checkings account, is that correct? (Y/N)");
        choice = scnr.next().toLowerCase().charAt(0);

        if(amount > user.savings){
            ConsoleUtils.clearConsole();
            System.out.println("Insufficient Funds.");
            continue;
        }

        
        if(choice=='y'){
            user.savings -= amount;
            user.checking += amount;
            ConsoleUtils.clearConsole();
            System.out.println("Transfered $" + amount + " into savings account.\nCheckings: $"+ user.checking +"\nSavings: $" + user.savings);
            running = false;
        }else if (choice !='n'){
            System.out.println("Invalid option, please pick Y or N");
            continue;
        }else{
            System.out.println("Please choose either 1 or 2");
        }
    }

    }
        
        }
    }
