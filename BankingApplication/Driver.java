package BankingApplication;
import java.util.*;


public class Driver {
    public static void main(String[] args) {
        boolean login = false;
        Scanner scnr = new Scanner(System.in);
        int index = 0;
        ArrayList<User> users = new ArrayList<User>();


        users.add(new User("Dominic", "Gasper","domBomb", "Astros2005", 50000, 100000, 0));
        users.add(new User("Dom", "Gas","bartbat", "password", 50000, 100000, 0));
        users.add(new User("Shop", "Gasper","flyfat", "notpassword", 50000, 100000, 0));
        users.add(new User("Sophia", "Gasper","bruh bruh", "hello", 50000, 100000, 0));
        users.add(new User("Dominique", "Gasper","fart", "whyyy", 50000, 100000, 0));


        System.out.println("1. Login");
        System.out.println("Username: ");
        String userName = scnr.next();
        System.out.println("Password: ");
        String password = scnr.next();

        for(User user: users){
            if(userName.equals(user.userName) && password.equals(user.password)){
                System.out.println("Login Succesful");
                index = users.indexOf(user);
                login = true;
                break;
            }
        }
    
        if(login){
            MainMenu(scnr, users, index);
        }else{
            System.out.println("Invalid Login");
        }

        scnr.close();
    }
    

    //Main Loop for Program
    static void MainMenu(Scanner scnr, ArrayList<User> users, int index){
        int userInpt = 0;
        boolean running = true;


        do{
        try{ 
        System.out.println("Welcome " + users.get(index).FirstName +"!");
        System.out.println("--------");
        if(users.get(index).cash > 0){
            System.out.println("Cash: $" + users.get(index).cash);
        }
        System.out.println("Checkings: $" + users.get(index).checking);
        System.out.println("Savings: $" + users.get(index).savings);
        System.out.println("--------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Account Settings");
        System.out.println("4. Logout");
        System.out.println("-------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1:
                depositMoney(scnr, users.get(index));
                break;
            case 2:
                withdrawMoney(scnr, users.get(index));
                break;
            case 3:
                
                AccountManager(scnr, users.get(index));
                break;
            case 4:
                running = false;
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("--------------------------");
                System.out.println("Please choose between 1-4!");
                System.out.println("--------------------------");
        }
            
        }catch(InputMismatchException e){
            System.out.println("----------------------------------");
            System.out.println("Error! Please try again!");
            System.out.println("----------------------------------");
            scnr.nextLine();
            continue;
        }
        }while(running);
    }
    

    //Acount Manager
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


    //Money Deposit Function
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
        if(!(inpt > user.cash)){
        if(Character.toLowerCase(choice)=='y'){
            user.cash -= inpt;
            user.checking += inpt;
            System.out.println("Deposited $" + inpt + " into checking account.\nNew balance is: $"+ user.checking);
            running = false;
        }else if (Character.toLowerCase(choice)=='n'){
            continue;
        }else{
            System.out.println("Invalid option, please pick Y or N");
        }
    } else {
        System.out.println("Insufficient funds, please try again");
    }
    }while(running);
    }


    //Withdraw Money Function
    static void withdrawMoney(Scanner scnr, User user){
        int inpt = 0;
        char choice = ' ';
        boolean running = true;
    
        do{
        System.out.print("How much money would you like to withdraw? ");
        inpt = scnr.nextInt();
        System.out.println();
        System.out.println("You would like to withdraw $" + inpt +", is that correct? (Y/N)");
        choice = scnr.next().charAt(0);
        if(!(inpt > user.checking)){
        if(Character.toLowerCase(choice)=='y'){
            user.checking -= inpt;
            user.cash += inpt;
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


