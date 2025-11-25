package security;

public class SecurityContext {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    private SecurityContext() {}

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }
}
