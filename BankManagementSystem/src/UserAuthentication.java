import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserAuthentication {

    String inputUsername;
    String inputPin;
    private final Scanner scanner;
    File file = new File("Info.ser");
    boolean authenticator;

    UserAuthentication(Scanner scanner){
        this.scanner = scanner;
    }
    public void UserAuthenticate(){
        System.out.println("Enter your name: ");
        inputUsername = scanner.next();

        System.out.println("Enter your pin: ");
        inputPin = scanner.next();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            authenticator = false;

            for(User user : users){
                if(inputUsername.equals(user.getUsername()) && inputPin.equals(user.getPin())){
                    System.out.println("Successfully login.");
                    authenticator = true;
                    break;
                }
            }

            if(authenticator){
                System.out.println("Login successfully!");
            }
            else {
                System.out.println("Unable to login \nPlease Register your account.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
