package dao.connection;

// Factory Pattern
public class ConnectionFactory {

    public static IConnection getConnection() {
        // The factory can decide which implementation to return.
        // This could be based on a configuration file, for example.
        return new MySqlConnection();
    }

    // A simple implementation for demonstration
    private static class MySqlConnection implements IConnection {
        @Override
        public void insert(String query) {
            System.out.println("MySqlConnection: Executing INSERT.");
        }

        @Override
        public void delete(String query) {
            System.out.println("MySqlConnection: Executing DELETE.");
        }

        @Override
        public void select(String query) {
            System.out.println("MySqlConnection: Executing SELECT.");
        }
    }
}
