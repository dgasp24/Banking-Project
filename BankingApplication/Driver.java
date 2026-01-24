package BankingApplication;
import java.util.*;


public class Driver {
    public static void main(String[] args) {

        boolean running = true;
        int userInpt = 0;
        Scanner scnr = new Scanner(System.in);
        User user = new User("Dominic", "Gasper","domBomb", "Astros2005", 50000, 100000);

        do{
        try{
        System.out.println("Welcome " + user.FirstName +"!");
        System.out.println("--------");
        System.out.println("Checkings: " + user.checking);
        System.out.println("Savings: " + user.savings);
        System.out.println("--------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Account Settings");
        System.out.println("4. Exit");
        System.out.println("-------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1:
                depositMoney(scnr, user);
                break;
            case 2:
                System.out.println("Withdrawing");
                break;
            case 3:
                
                AccountManager(scnr, user);
                break;
            case 4:
                running = false;
                System.out.println("Goodbye!");
                break;
        }
            
        }catch(InputMismatchException e){
            System.out.println("----------------------------------");
            System.out.println("Error! Please only enter a number!");
            System.out.println("----------------------------------");
            scnr.nextLine();
            continue;
        }
        }while(running);
        scnr.close();
    }
    
    static void AccountManager(Scanner scnr, User user){
        boolean running = true;
        int userInpt = 0;
        String inpt = "";
        do{
        
            System.out.println("Account Settings (" + user.userName + ")");
            System.out.println("-------------------");
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Back");
            System.out.println("-------");

            userInpt = scnr.nextInt();

            switch(userInpt){
                case 1:
                    System.out.print("Please type new username (No Spaces): ");
                    inpt = scnr.next();
                    user.changeUserName(inpt);
                    System.out.println("Username successfully changed!");
                    System.out.println();
                    running = false;
                    break;
                case 2:
                    System.out.println("Please enter current password: ");
                    inpt = scnr.next();
                    if(!(inpt.equals(user.password))){
                        System.out.println("Passwords do not match!\nPlease try again.");
                    } else{
                        System.out.println("New Password: ");
                        inpt = scnr.next();
                        user.changePassword(inpt);
                        System.out.println("Password successfully changed!");
                    }
                    break;
                case 3:
                    running = false;
                    break;


            }
        
            
        }while(running);
    }

    static void depositMoney(Scanner scnr, User user){
        int inpt = 0;
        char choice = ' ';
        boolean running = true;

        do{
        System.out.print("How much would you like to deposit:");
        inpt = scnr.nextInt();
        System.out.println();
        System.out.println("You would like to deposit $" + inpt +", is that correct? (Y/N)");
        choice = scnr.next().charAt(0);
        if(Character.toLowerCase(choice)=='y'){
            user.checking += inpt;
            System.out.println("Deposited $" + inpt + "into checking account.\nNew balance is: $"+ user.checking);
            running = false;
        }else if (Character.toLowerCase(choice)=='n'){
            continue;
        }else{
            System.out.println("Invalid option, please pick Y or N");
        }
    }while(running);
    }


}
