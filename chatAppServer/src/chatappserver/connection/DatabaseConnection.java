package chatappserver.connection;

import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "localhost";
        String port = "3306";
        String database = "chat_application";
        String userName = "root";
        String password = "";    
        String url = "jdbc:mysql://"+server+":"+port+"/"+database;

        connection = DriverManager.getConnection(url, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
