import java.util.Scanner;

public class BankActivities {

    Scanner scanner;
    Balance balance;
    double amount;
    int response;
    UserAuthentication userAuthentication;

    BankActivities(Scanner scanner, Balance balance){
        this.scanner = scanner;
        this.balance = balance;
        this.userAuthentication = new UserAuthentication(scanner);
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
        return  balance.getBalance();
    }

    public void withdraw(){
        System.out.println("Enter the amount you want to withdraw: ");
        amount = scanner.nextDouble();
        if(balance.getBalance() < amount){
            System.out.println("Insufficient Balance!");
        }
        else{
            balance.setBalance(balance.getBalance() - amount);
            System.out.println("You have successfully withdrawn " + amount);
            System.out.println("Your new balance is " + balance.getBalance());
        }

    }

    public void deposit(){
        System.out.println("Enter the amount you want to deposit: ");
        amount = scanner.nextDouble();
        while (amount < 1000){
            System.out.println("Minimum deposit of Rs.1000 \n please try again!");
        }

        balance.setBalance(balance.getBalance() + amount);
        System.out.println("You have successfully deposited " + amount);
        System.out.println("Your new balance is " + balance.getBalance());
    }

    public void moneyTransfer(){
        System.out.println("Enter the amount ");
        amount = scanner.nextDouble();
        if(balance.getBalance() < amount){
            System.out.println("Insufficient Balance!");
        }
        else{
            balance.setBalance(balance.getBalance() - amount);
            System.out.println("You have successfully transferred " + amount);
            System.out.println("Your new balance is " + balance.getBalance());
        }

    }

    public void InterestCalculator() {

        double loanAmount = 0;
        System.out.println("1. Simple Interest on loan");
        System.out.println("2. Simple Interest on saving");
        System.out.print("Enter your choice: ");
        response = scanner.nextInt();

        if (response == 1) {
            System.out.print("Enter your loan amount: ");
            loanAmount = scanner.nextDouble();
            amount = (double) 10 / 100 * loanAmount;
            System.out.println("Your interest amount is " + amount);
        }

        else if (response == 2) {
            amount = (double) 8 / 100 * balance.getBalance();
            System.out.println("Your interest amount is " + amount);
        }

        else {
            System.out.println("Invalid choice.");
        }


    }

}
