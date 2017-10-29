package beans;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

@MessageDriven(name = "MessageEJB",mappedName = "queueDestination")
public class MessageBean implements javax.jms.MessageListener {
    public MessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                InitialContext context=new InitialContext();
                DataSource source= (DataSource) context.lookup("jdbc/ConnectionPool");
                Database.insert(source,message);
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
