package BankingApplication;
import java.util.*;
import java.io.*;


public class Driver {
    public static void main(String[] args) {
        boolean running = true;
        int inpt = 0;
        Scanner scnr = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<User>();

        userLoader(users);

        do{
        try{   
        System.out.println("Welcome to the Bank");
        System.out.println("1. Login ");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        inpt = scnr.nextInt();

        switch(inpt){
            case 1://Logging in
                clearConsole();
                loggingIn(scnr, users);
                break;
            case 2://Account Creation
                clearConsole();
                createAccount(scnr,users);
                break;
            case 3://Exiting
                running = false;
                break;
            default:
                System.out.println("Please choose between 1-3");
            }
        }catch(InputMismatchException e){
        clearConsole();
        System.out.println("-------------------------");
        System.out.println("Please only type numbers!");
        System.out.println("-------------------------");
        scnr.nextLine();
        continue;
        }

        }while(running);

        scnr.close();
    }

    //Logging into Account
    static void loggingIn(Scanner scnr,ArrayList<User> users){
            int index = 0;
            boolean login = false;

            
            System.out.println("Username: ");
            String userName = scnr.next();
            System.out.println("Password: ");
            String password = scnr.next();

            //Check Credentials
            for(User u: users){
                if(userName.equals(u.userName) && password.equals(u.password)){
                    System.out.println("Login Succesful");
                    index = users.indexOf(u);
                    login = true;
                    break;
                }
            }

            if(login){
                //Function for main menu
                clearConsole();
                MainMenu(scnr, users, index);
            }else{
                clearConsole();
                System.out.println("Invalid Login, please try again");
            }
    }

    //Creating Account
    static void createAccount(Scanner scnr,ArrayList<User> users){
        String firstName = "";
        String lastName = "";
        String userName = "";
        String password = "";
        boolean running = true;

        try{
        System.out.println("First Name: ");
        firstName = scnr.next();
        System.out.println("Last Name: ");
        lastName = scnr.next();

        //Checking to make sure there are no duplicate usernames
        do{
        System.out.println("Username(No Spaces): ");
        userName = scnr.next();
        for(User u: users){
                if(userName.toLowerCase().equals(u.userName.toLowerCase())){
                    System.out.println("Username already exists, please try again");
                    break;
                }else{
                    running = false;
                }
            }
        }while(running);

        System.out.println("Password(No Spaces): ");
        password = scnr.next();

        //Checking to make sure user only inputs letters and not numbers    
        }catch(InputMismatchException e){
            System.out.println("Please only enter letters!");
        }

        clearConsole();
        users.add(new User(firstName, lastName, userName, password, 0, 0, 1000));
        userWriter(firstName, lastName, userName, password, 0, 0, 1000);
        System.out.println("Account Created! Each account will get $1000 to start!");
    }
    

    //Main Loop for Program
    static void MainMenu(Scanner scnr, ArrayList<User> users, int index){
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
        System.out.println("3. Account Settings");
        System.out.println("4. Logout");
        System.out.println("-------------------");


        userInpt = scnr.nextInt();

        switch(userInpt){
            case 1://Depositing
                clearConsole();
                depositMoney(scnr, users.get(index));
                break;
            case 2://Withdrawing
                clearConsole();
                withdrawMoney(scnr, users.get(index));
                break;
            case 3://Account Manager
                clearConsole();
                AccountManager(scnr, users.get(index));
                break;
            case 4:
                running = false;
                clearConsole();
                System.out.println("Goodbye!");
                break;
            default:
                clearConsole();
                System.out.println("--------------------------");
                System.out.println("Please choose between 1-4!");
                 System.out.println("--------------------------");
                break;
        }
            
        }catch(InputMismatchException e){
            clearConsole();
            System.out.println("----------------------------------");
            System.out.println("Error! Please only type numbers!");
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
                    clearConsole();
                    System.out.println("Please enter current password: ");
                    inpt = scnr.next();
                    if(!(inpt.equals(user.password))){
                        System.out.println("Passwords do not match!\nPlease try again.");
                    } else{
                        System.out.println("New Password: ");
                        inpt = scnr.next();
                        user.changePassword(inpt);
                        clearConsole();
                        System.out.println("Password successfully changed!");
                    }
                    break;
                case 3:
                    running = false;
                    clearConsole();
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

        //Checks to see if the amount of money the user is depositing isn't more than what they have
        if(!(inpt > user.cash)){
        if(Character.toLowerCase(choice)=='y'){
            user.cash -= inpt;
            user.checking += inpt;
            clearConsole();
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

        //Checks to see if the amount of money the user is withdrawing isn't more than what they have
        if(!(inpt > user.checking)){
        if(Character.toLowerCase(choice)=='y'){
            user.checking -= inpt;
            user.cash += inpt;
            clearConsole();
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

    static void userLoader(ArrayList<User> users){

        try(BufferedReader br = new BufferedReader(new FileReader("BankingApplication/userFile.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] inpt = line.split(",");
                users.add(new User(inpt[0], inpt[1], inpt[2], inpt[3], Integer.parseInt(inpt[4]), Integer.parseInt(inpt[5]), Integer.parseInt(inpt[6])));
            }

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }

    static void userWriter(String firstName, String lastName, String userName, String password, int checkings, int savings, int cash){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("BankingApplication/userFile.csv", true))){
            String line = firstName + "," + lastName + "," + userName + "," + password + "," + checkings + "," + savings + "," + cash;
            bw.newLine(); 
            bw.append(line);

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }


    //Clears console for cleaner look
    static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    }


