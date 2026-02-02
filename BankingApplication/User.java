package BankingApplication;

public class User {
    
    public String FirstName = "John";
    public String LastName = "Doe";
    public String userName = "";
    public String password = "";
    public int checking = 0;
    public int savings = 0;
    public int cash = 0;

    public User(String FirstName, String LastName, String userName, String password, int checking, int savings, int cash){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.userName = userName;
        this.password = password;
        this.checking = checking;
        this.savings = savings;
        this.cash = cash;
    }

    public void changeUserName(String userName){
        this.userName = userName;
    }

    public void changePassword(String password){
        this.password = password;
    }


    @Override
    public String toString() {
        
        return userName;
    }
}
