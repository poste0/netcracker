package interfaces;

import entities.Message;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ShowBeanRemote {
    List<Message> get(String stime,String etime);
}
