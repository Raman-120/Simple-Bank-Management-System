import java.util.Scanner;

public class UserAuthentication {

    String username;
    String pin;
    private final Scanner scanner;

    UserAuthentication(Scanner scanner){
        this.scanner = scanner;
    }
    public void demo(){
        System.out.println("Enter your name: ");
        username = scanner.next();
    }



}
