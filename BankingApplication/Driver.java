package BankingApplication;
import java.util.*;

public class Driver {
    public static void main(String[] args) {

        boolean running = true;
        int userInpt = 0;
        Scanner scnr = new Scanner(System.in);
        User user1 = new User("domBomb", "Astros2005", 50000);

        while(running){
        System.out.println("Welcome!");
        System.out.println("--------");
        System.out.println("1. See Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("-------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1:
                AccountManager(scnr, user1);
                break;
            case 2:
                System.out.println("Depositing");
                break;
            case 3:
                System.out.println("Withdrawing");
                break;
            case 4:
                running = false;
                System.out.println("Goodbye!");
                break;
        }
        }
        scnr.close();
    }
    
    static void AccountManager(Scanner scnr, User user){
        boolean running = true;
        int userInpt = 0;
        String inpt = "";
        while(running){
            System.out.println("--------");
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. View Money");
            System.out.println("4. Back");
            System.out.println("-------");

            userInpt = scnr.nextInt();

            switch(userInpt){
                case 1:
                    System.out.print("Please type new username: ");
                    inpt = scnr.nextLine();
                    System.out.println();
                    user.changeUserName(inpt);
                    break;
                case 2:
                    System.out.print("Password");
                    break;
                case 3:
                    System.out.print("Money");
                    break;
                case 4:
                    running = false;
                    break;


            }
        
        }
    }

}
