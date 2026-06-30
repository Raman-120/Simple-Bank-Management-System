import java.util.Scanner;

public class SimpleBankManagementSystem {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Account account = new Account(scanner);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();

        if(choice == 1){
            account.CreateAccount();
        } else if(choice == 2){
            account.Login();
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        BankActivities activities = account.getBankActivities();
        if(activities != null){
            boolean running = true;
            while(running){
                activities.Menu();
                activities.action();
            }
        }

        scanner.close();
    }
}