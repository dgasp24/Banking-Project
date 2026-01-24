package BankingApplication;

public class User {
    
    String FirstName = "John";
    String LastName = "Doe";
    String userName = "";
    String password = "";
    int checking = 0;
    int savings = 0;

    User(String FirstName, String LastName, String userName, String password, int checking, int savings){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.userName = userName;
        this.password = password;
        this.checking = checking;
        this.savings = savings;
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
