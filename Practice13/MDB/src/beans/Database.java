package beans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class Database {
    public static void insert(DataSource source, Message message) throws SQLException, NamingException, JMSException {
        Connection connection;
        connection=source.getConnection();
        PreparedStatement statement=connection.prepareStatement("insert into msg VALUES (?,?)");
        LocalTime time=LocalTime.now();
        statement.setString(1,((TextMessage) message).getText());
        statement.setTime(2, Time.valueOf(time));
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
