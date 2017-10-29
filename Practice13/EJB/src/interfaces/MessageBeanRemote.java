package interfaces;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.NamingException;

@Remote

public interface MessageBeanRemote {



    void send(String message) throws NamingException, JMSException;
}
