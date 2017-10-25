package beans;

import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.registry.Registry;
import java.sql.*;
import java.time.LocalTime;

public class MessageBean implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
    private MessageDrivenContext context;
    private Connection connection;
    public MessageBean() {
    }

    public void onMessage(javax.jms.Message message) {
        if(message instanceof TextMessage){
            try {

                InitialContext initialContext=new InitialContext();
                //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "12345");
                DataSource source= (DataSource) initialContext.lookup("jdbc/ConnectionPool");
                connection=source.getConnection();
                PreparedStatement statement=connection.prepareStatement("insert into message VALUES (?,?)");
                LocalTime time=LocalTime.now();
                statement.setString(1,((TextMessage) message).getText());
                statement.setString(2,time.toString());
                statement.executeUpdate();
                connection.close();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }

    public void ejbRemove() throws javax.ejb.EJBException {
    }

    public void setMessageDrivenContext(javax.ejb.MessageDrivenContext messageDrivenContext) throws javax.ejb.EJBException {
    this.context=messageDrivenContext;
    }

    public void ejbCreate() {
    }
}
