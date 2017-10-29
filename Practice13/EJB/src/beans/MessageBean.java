package beans;

import interfaces.MessageBeanRemote;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.NamingException;


@Stateless
public class MessageBean implements MessageBeanRemote {
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory factory;
    @Resource(mappedName = "queueDestination")
    private Destination destination;
    @Override
    public void send(String message) throws NamingException, JMSException {
        Connection connection=factory.createConnection();
        Session session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer=session.createProducer(destination);
        TextMessage textMessage=session.createTextMessage(message);
        producer.send(textMessage);

    }
}
