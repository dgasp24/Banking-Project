package BankingApplication;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<User>();
        FileService file = new FileService("BankingApplication/userFile.csv");
        BankProgram bP = new BankProgram(scnr, users);

        //Loads csv file
        file.Load(users);
        //Starts the banking program
        bP.run();

        scnr.close();
    }
    }


