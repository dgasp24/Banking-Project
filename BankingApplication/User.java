package BankingApplication;

public class User {
    
    public String FirstName = "John";
    public String LastName = "Doe";
    public String userName = "";
    public String password = "";
    public double checking = 0;
    public double savings = 0;
    public double cash = 0;

    public User(String FirstName, String LastName, String userName, String password, double checking, double savings, double cash){
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
