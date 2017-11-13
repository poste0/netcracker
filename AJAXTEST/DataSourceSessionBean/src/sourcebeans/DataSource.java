package sourcebeans;


import beans.Employee;

import javax.ejb.CreateException;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public interface DataSource extends EJBObject {
    void setUpEntity() throws SQLException, NamingException, RemoteException;

    List<Employee> getAllEmployees() throws SQLException, RemoteException, NamingException;

    List<Employee> getEmployeesByName(String name) throws SQLException, RemoteException, NamingException, CreateException;

    Employee getEmployeeById(int id) throws SQLException, RemoteException, NamingException, FinderException;
    List<Employee> getEmployeesByPart(String part) throws RemoteException;
}
