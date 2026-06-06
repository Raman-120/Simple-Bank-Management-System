public class User {

    private String username;
    private int pin;

    User(String username, int pin){
        this.username = username;
        this.pin = pin;
    }

    public String getUsername(){
        return this.username;
    }

    public int getPin(){
        return this.pin;
    }

}
