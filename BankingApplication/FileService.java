package BankingApplication;
import java.io.*;
import java.util.*;

public class FileService {
    String FileName = "";

    public FileService(String FileName){
        this.FileName = FileName;
    }

    //Loading Users
    public void Load(ArrayList<User> users){

        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String line;
            while((line = br.readLine()) != null){
                String[] inpt = line.split(",");
                users.add(new User(inpt[0], inpt[1], inpt[2], inpt[3], Double.parseDouble(inpt[4]), Double.parseDouble(inpt[5]), Double.parseDouble(inpt[6])));
            }

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }

    //Adding User to CSV file
    public static void write(String firstName, String lastName, String userName, String password, int checkings, int savings, int cash){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("BankingApplication/userFile.csv", true))){
            String line = firstName + "," + lastName + "," + userName + "," + password + "," + checkings + "," + savings + "," + cash;
            bw.newLine(); 
            bw.append(line);

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }

    //Saving user data
    public void save(ArrayList<User> users){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FileName))){
            
            for(User u : users){
                String line = u.FirstName + "," +
                              u.LastName + "," +
                              u.userName + "," +
                              u.password + "," +
                              String.format("%.2f", u.checking) + "," +
                              String.format("%.2f", u.savings) + "," +
                              String.format("%.2f", u.cash);               

                
               bw.write(line);
               bw.newLine();
            }

        }catch(IOException e){
            System.out.println("Cannot access file");
        }
    }
}
