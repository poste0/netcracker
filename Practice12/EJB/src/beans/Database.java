package beans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public static List<beans.Message> get(String stime,String etime) throws SQLException, NamingException {
       InitialContext context=new InitialContext();
        DataSource source= (DataSource) context.lookup("jdbc/ConnectionPool");
        Connection connection;
        connection=source.getConnection();
        Time rstime=Time.valueOf(stime);
        Time retime=Time.valueOf(etime);
        PreparedStatement statement=connection.prepareStatement("select * from msg where time>? and time <? ");
        statement.setTime(1,rstime);
        statement.setTime(2,retime);
        ResultSet rs=statement.executeQuery();
        List<beans.Message> messages=new ArrayList<>();
        while (rs.next()){
            messages.add(new beans.Message(rs.getString(1),rs.getTime(2).toLocalTime()));
        }

        connection.close();
        statement.close();
        return messages;
    }
}
