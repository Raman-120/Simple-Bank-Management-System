import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Serializable {

     private final Scanner scanner;
     ArrayList<User> users = new ArrayList<>();
     private final BankActivities b;
     String username;
     String pin;
     double balance;
     File file = new File("Info.ser");


    Account(Scanner scanner){
        this.scanner = scanner;
        this.b = new BankActivities(scanner,0);
    }

    public void CreateAccount(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){

            System.out.print("Enter your first name: ");
            String firstName = scanner.next();


            System.out.print("Enter your last name: ");
            String lastName = scanner.next();

            System.out.print("Enter a pin: ");
            pin = scanner.next();

            username = firstName + lastName;

            System.out.println("Your username is " + username);

            User user = new User(username, pin);
            users.add(user);
            oos.writeObject(users);
            System.out.println("Information saved successfully.");

        }catch (Exception e){
            e.printStackTrace();
        }

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
