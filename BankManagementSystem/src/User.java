public class User {

    private final String username;
    private final String pin;

    User(String username, String pin){
        this.username = username;
        this.pin = pin;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPin(){
        return this.pin;
    }

}
