package sourcebeans;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;


public interface DataSourceHome extends EJBHome {
    DataSource create() throws RemoteException, CreateException;
}
