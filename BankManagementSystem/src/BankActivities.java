import java.util.Scanner;

public class BankActivities {

    Scanner scanner;
    double balance;
    int response;
    UserAuthentication userAuthentication;

    BankActivities(Scanner scanner, double balance){
        this.scanner = scanner;
        this.balance = balance;
        this.userAuthentication = new UserAuthentication(scanner);
    }








}
