package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;


public class DBWorker {

    private final String URL = "jdbc:postgresql://ec2-54-195-248-0.eu-west-1.compute.amazonaws.com:" +
            "5432/d4vj8dpuiehhuf?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private final String USER = "udotunkdbthbwa";
    private final String PASSWORD = "05ab1c7af43d2b1410b09d2d600e9e390e45a4d2aa9ca7add0304062520391f4";

//    private final String URL = "jdbc:postgresql://ec2-79-125-2-69.eu-west-1.compute.amazonaws.com:5432/d1q9mjf3g9ieqf?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
//    private final String USER = "lyuqkxmnruhuhg";
//    private final String PASSWORD = "5a3bc778e39e71c42c4f7b1628bdcb77a86aedd352c8c4583cb82dc539d8c432";
    private Connection connection;

    public DBWorker() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

}
