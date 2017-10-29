package beans;

import entities.Message;
import interfaces.ShowBeanRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Remote(ShowBeanRemote.class)
@Stateless
public class ShowBean implements ShowBeanRemote {
    @PersistenceContext
    private EntityManager manager=Persistence.createEntityManagerFactory("Messages").createEntityManager();
    @Override
    public List<Message> get(String stime,String etime) {

        List<Message> result=new ArrayList<Message>();
        Time time1=Time.valueOf(stime);
        Time time2=Time.valueOf(etime);
        //Query query=manager.createQuery("select m from Message m where m.time> :time1 and m.time < :time2");
        Query query=manager.createQuery("SELECT a from Message a where a.time>:time1 and a.time<:time2");
        query.setParameter("time1",time1);
        query.setParameter("time2",time2);
        result=query.getResultList();
        return result;



    }
}
