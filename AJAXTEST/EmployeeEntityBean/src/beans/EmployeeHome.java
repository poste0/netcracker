package beans;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collection;


public interface EmployeeHome extends EJBHome {
    Employee findByPrimaryKey(Integer key) throws RemoteException, FinderException;

    Collection findByName(String name) throws FinderException, RemoteException;

    Collection findAll() throws RemoteException, FinderException;
    Collection findByPart(String part) throws RemoteException, FinderException, SQLException;

}
