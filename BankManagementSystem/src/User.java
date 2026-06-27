import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String pin;
    private double balance;   // NEW — each user now carries their own balance

    User(String username, String pin, double balance){
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPin(){
        return this.pin;
    }

    // NEW — getter/setter for balance
    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}