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
        System.out.println("3. Withdraw");
        System.out.println("3. Account Settings");
        System.out.println("4. Exit");
        System.out.println("-------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1:
                System.out.println("Depositing");
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
        try{
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
                    System.out.println();
                    user.changeUserName(inpt);
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
        
    }catch(InputMismatchException e){
        System.out.println("----------------------------------");
        System.out.println("Error! Please only enter a number!");
        System.out.println("----------------------------------");
        scnr.nextLine();
    }
}while(running);
    }

}
