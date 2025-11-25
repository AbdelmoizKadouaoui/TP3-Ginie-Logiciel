package security;

public class User {
    private final String username;
    private final boolean granted;

    public User(String username, boolean granted) {
        this.username = username;
        this.granted = granted;
    }

    public String getUsername() {
        return username;
    }

    public boolean isGranted() {
        return granted;
    }
}
