public class User {

    private final String username;
    private final int pin;

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
