package BankingApplication.utilities;

public class MathUtils {

    public static double  roundUp(double amount){
        return Math.round(amount * 100.0)/100.0;
    }

}
