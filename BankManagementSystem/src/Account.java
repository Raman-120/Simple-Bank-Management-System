import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Account implements Serializable {

     Scanner scanner;
     private final BankActivities b;
     String username;
     String pin;
     double balance;

    Account(Scanner scanner, BankActivities b){
        this.scanner = scanner;
        this.b = new BankActivities(scanner);
    }

    public void CreateAccount(){
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        System.out.print("Enter a pin: ");
        pin = scanner.next();

        username = firstName + lastName;

        System.out.println("Your username is " + username);
    }

    public void Login(){
        System.out.print("Enter your username: ");
        username = scanner.next();

        System.out.print("Enter your pin: ");
        pin = scanner.next();

    }

    public BankActivities getBankActivities(){
        return  b;
    }

}
