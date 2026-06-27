import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Scanner scanner;
    String username;
    String pin;
    private User loggedInUser;          // NEW — tracks who is currently logged in

    File file = new File("Info.ser");

    Account(Scanner scanner){
        this.scanner = scanner;
    }

    public void CreateAccount(){

        ArrayList<User> users = loadUsers();   // CHANGED — load existing users first

        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        System.out.print("Enter a pin: ");
        pin = scanner.next();

        username = firstName + lastName;

        // NEW — reject duplicate usernames so two people can't collide
        for(User u : users){
            if(u.getUsername().equals(username)){
                System.out.println("That username already exists. Please log in instead.");
                return;
            }
        }

        System.out.println("Your username is " + username);

        User user = new User(username, pin, 0.0);   // CHANGED — starts at balance 0
        users.add(user);

        saveUsers(users);                            // CHANGED — save the FULL updated list
        System.out.println("Information saved successfully.");
    }

    public void Login(){
        System.out.print("Enter your username: ");
        username = scanner.next();

        System.out.print("Enter your pin: ");
        pin = scanner.next();

        ArrayList<User> users = loadUsers();

        for(User u : users){
            if(u.getUsername().equals(username) && u.getPin().equals(pin)){
                System.out.println("Login successful! Welcome back, " + username + ".");
                System.out.println("Your current balance is " + u.getBalance());
                loggedInUser = u;             // NEW — remember who's logged in
                return;
            }
        }

        System.out.println("Invalid username or pin. Please try again or register.");
    }

    // NEW — returns a BankActivities bound to the user who just logged in
    public BankActivities getBankActivities(){
        if(loggedInUser == null){
            System.out.println("No user is logged in yet.");
            return null;
        }
        return new BankActivities(scanner, loggedInUser);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<User> loadUsers(){
        if(!file.exists()){
            return new ArrayList<>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (ArrayList<User>) ois.readObject();
        } catch (Exception e){
            System.out.println("Could not read existing data, starting fresh list.");
            return new ArrayList<>();
        }
    }

    private void saveUsers(ArrayList<User> users){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(users);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}