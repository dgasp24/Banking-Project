package BankingApplication;
import java.io.*;
import java.util.*;

public class FileService {
    String FileName = "";

    public FileService(String FileName){
        this.FileName = FileName;
    }

    public void Load(ArrayList<User> users){

        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String line;
            while((line = br.readLine()) != null){
                String[] inpt = line.split(",");
                users.add(new User(inpt[0], inpt[1], inpt[2], inpt[3], Integer.parseInt(inpt[4]), Integer.parseInt(inpt[5]), Integer.parseInt(inpt[6])));
            }

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }

    public static void write(String firstName, String lastName, String userName, String password, int checkings, int savings, int cash){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("BankingApplication/userFile.csv", true))){
            String line = firstName + "," + lastName + "," + userName + "," + password + "," + checkings + "," + savings + "," + cash;
            bw.newLine(); 
            bw.append(line);

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }
}
