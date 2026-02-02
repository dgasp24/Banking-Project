package BankingApplication.services;

import java.util.InputMismatchException;
import java.util.Scanner;
import BankingApplication.User;
import BankingApplication.utilities.ConsoleUtils;

public class MoneyService {

    //Money Deposit Function
    public static void depositMoney(Scanner scnr, User user){
        int amount = 0;
        char choice = ' ';
        boolean running = true;

        while(running){
        System.out.print("How much would you like to deposit:");
        try{
            amount = scnr.nextInt();
        }catch(InputMismatchException e){
            ConsoleUtils.clearConsole();
            System.out.println("Please enter a valid number");
            scnr.nextLine();
            continue;
        }

        System.out.println();

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
        int inpt = 0;
        char choice = ' ';
        boolean running = true;
    
        do{
        System.out.print("How much money would you like to withdraw? ");
        inpt = scnr.nextInt();
        System.out.println();
        System.out.println("You would like to withdraw $" + inpt +", is that correct? (Y/N)");
        choice = scnr.next().charAt(0);

        //Checks to see if the amount of money the user is withdrawing isn't more than what they have
        if(!(inpt > user.checking)){
        if(Character.toLowerCase(choice)=='y'){
            user.checking -= inpt;
            user.cash += inpt;
            ConsoleUtils.clearConsole();
            System.out.println("Withdrew $" + inpt + " from checking account.\nNew balance is: $"+ user.checking);
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

    
}
