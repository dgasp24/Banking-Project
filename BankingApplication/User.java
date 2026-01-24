package BankingApplication;

public class User {
    
    String userName = "";
    String password = "";
    int money = 0;

    User(String userName, String password, int money){
        this.userName = userName;
        this.password = password;
        this.money = money;
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
