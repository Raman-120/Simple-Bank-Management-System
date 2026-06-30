import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BankActivities {

    private final Scanner scanner;
    private User currentUser;
    double amount;
    int response;
    File file = new File("Info.ser");

    BankActivities(Scanner scanner, User currentUser){
        this.scanner = scanner;
        this.currentUser = currentUser;
    }

    public void Menu(){
        System.out.println("1.Check Balance");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit Money");
        System.out.println("4.Money Transfer");
        System.out.println("5.Interest Calculation");
        System.out.println("6.Exit");
    }

    public void action(){
        System.out.println("What activity would you like to do?: ");
        response = scanner.nextInt();
        scanner.nextLine();

        switch (response){
            case 1 -> System.out.println("Your balance is " + checkBalance());
            case 2 -> withdraw();
            case 3 -> deposit();
            case 4 -> moneyTransfer();
            case 5 -> InterestCalculator();
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid Choice");
        }
    }

    public double checkBalance(){
        return currentUser.getBalance();
    }

    public void withdraw(){
        System.out.println("Enter the amount you want to withdraw: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 0){
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if(currentUser.getBalance() < amount){
            System.out.println("Insufficient Balance!");
        }
        else{
            currentUser.setBalance(currentUser.getBalance() - amount);
            System.out.println("You have successfully withdrawn " + amount);
            System.out.println("Your new balance is " + currentUser.getBalance());
            saveUpdatedUser();
        }
    }

    public void deposit(){
        System.out.println("Enter the amount you want to deposit: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount < 1000){
            System.out.println("Minimum deposit of Rs.1000, please try again!");
            return;
        }

        currentUser.setBalance(currentUser.getBalance() + amount);
        System.out.println("You have successfully deposited " + amount);
        System.out.println("Your new balance is " + currentUser.getBalance());
        saveUpdatedUser();
    }

    // ───────────────────────── UPDATED ─────────────────────────
    public void moneyTransfer(){

        System.out.print("Enter the recipient's username: ");
        String recipientUsername = scanner.nextLine().trim();

        if(recipientUsername.equals(currentUser.getUsername())){
            System.out.println("You cannot transfer money to your own account.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 0){
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }

        if(currentUser.getBalance() < amount){
            System.out.println("Insufficient Balance!");
            return;
        }

        ArrayList<User> users = loadUsers();

        User recipient = null;
        for(User u : users){
            if(u.getUsername().equals(recipientUsername)){
                recipient = u;
                break;
            }
        }

        if(recipient == null){
            System.out.println("Recipient not found. Please check the username and try again.");
            return;
        }

        // Update sender's balance in memory
        currentUser.setBalance(currentUser.getBalance() - amount);

        // Update recipient's balance
        recipient.setBalance(recipient.getBalance() + amount);

        // Reflect the sender's updated balance into the loaded list too,
        // since currentUser may be a separate object reference from the
        // one inside `users` if it was loaded earlier.
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(currentUser.getUsername())){
                users.set(i, currentUser);
                break;
            }
        }

        saveUsers(users);   // single write — both balances persisted together

        System.out.println("You have successfully transferred " + amount + " to " + recipientUsername);
        System.out.println("Your new balance is " + currentUser.getBalance());
    }
    // ─────────────────────────────────────────────────────────────

    public void InterestCalculator() {

        double loanAmount = 0;
        System.out.println("1. Simple Interest on loan");
        System.out.println("2. Simple Interest on saving");
        System.out.print("Enter your choice: ");
        response = scanner.nextInt();
        scanner.nextLine();

        if (response == 1) {
            System.out.print("Enter your loan amount: ");
            loanAmount = scanner.nextDouble();
            scanner.nextLine();
            amount = (double) 10 / 100 * loanAmount;
            System.out.println("Your interest amount is " + amount);
        }
        else if (response == 2) {
            amount = (double) 8 / 100 * currentUser.getBalance();
            System.out.println("Your interest amount is " + amount);
        }
        else {
            System.out.println("Invalid choice.");
        }
    }

    @SuppressWarnings("unchecked")
    private void saveUpdatedUser(){
        ArrayList<User> users = loadUsers();

        boolean found = false;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(currentUser.getUsername())){
                users.set(i, currentUser);
                found = true;
                break;
            }
        }
        if(!found){
            users.add(currentUser);
        }

        saveUsers(users);
    }

    // NEW — shared load helper, used by both saveUpdatedUser() and moneyTransfer()
    @SuppressWarnings("unchecked")
    private ArrayList<User> loadUsers(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (ArrayList<User>) ois.readObject();
        } catch (Exception e){
            System.out.println("Could not read existing data, starting fresh list.");
            return new ArrayList<>();
        }
    }

    // NEW — shared save helper
    private void saveUsers(ArrayList<User> users){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(users);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}