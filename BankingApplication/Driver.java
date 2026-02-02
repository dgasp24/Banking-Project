package BankingApplication;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<User>();
        file file = new file("BankingApplication/userFile.csv");
        bankProgram bP = new bankProgram(scnr, users,file);

        //Loads csv file
        file.Load(users);
        //Starts the banking program
        bP.run();

    }
    }


