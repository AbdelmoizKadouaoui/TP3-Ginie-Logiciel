package dao;

public class ConnectionFactory {
    private ConnectionFactory() {}

    public static IConnection getConnection(String type) {
        return new MySqlConnection();
    }
}
