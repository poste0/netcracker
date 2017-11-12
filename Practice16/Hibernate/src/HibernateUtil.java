import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private  static final SessionFactory factory;
    public static SessionFactory getFactory(){return factory;}
    static {
        factory=new Configuration().configure().buildSessionFactory();

    }
}
