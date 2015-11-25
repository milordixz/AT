package ru.rostelecom;
import java.sql.*;
/**
 * Created by Admin on 25.11.2015.
 */
public class ConnectionFactory implements ConnectionProvider {
    static Connection conn = null;
    public static Connection getCon(){

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(connURL,login,pwd);

        }catch ( Exception ex) {
            System.out.println(ex);
        }

        return conn;
    }
}
