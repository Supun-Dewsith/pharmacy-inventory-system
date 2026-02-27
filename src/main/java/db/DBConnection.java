package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException {
        Properties properties = new Properties();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties")){
            properties.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException("Could not find database.properties in resources!");
        }

        connection = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }

    public static DBConnection getInstance() throws SQLException {
        return dbConnection == null?dbConnection=new DBConnection():dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
