import java.io.Serializable;
import java.util.Scanner;

public class Account implements Serializable {

     private final Scanner scanner;
     private final BankActivities b;
     String username;
     String pin;
     double balance;


    Account(Scanner scanner){
        this.scanner = scanner;
        this.b = new BankActivities(scanner,0);
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
