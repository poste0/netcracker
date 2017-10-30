package beans;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

     Connection   connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "12345");
        Statement statement=connection.createStatement();
        ResultSet set=statement.executeQuery("select * from message");
        set.next();
        System.out.println(set.getString("message"));

    }
}
