package BankingApplication;

public class User {
    
    String FirstName = "John";
    String LastName = "Doe";
    String userName = "";
    String password = "";
    int checking = 0;
    int savings = 0;
    int cash = 0;

    User(String FirstName, String LastName, String userName, String password, int checking, int savings, int cash){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.userName = userName;
        this.password = password;
        this.checking = checking;
        this.savings = savings;
        this.cash = cash;
    }

    void changeUserName(String userName){
        this.userName = userName;
    }

    void changePassword(String password){
        this.password = password;
    }


    @Override
    public String toString() {
        
        return super.toString();
    }
}
