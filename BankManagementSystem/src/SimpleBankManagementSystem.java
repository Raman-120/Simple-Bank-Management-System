import java.util.Scanner;

public class SimpleBankManagementSystem {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Account account = new Account(scanner);



        scanner.close();

    }
}
