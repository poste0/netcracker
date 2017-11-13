package sourcebeans;


import beans.Employee;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSourceBean implements SessionBean {

    private beans.EmployeeHome employeeHome;

    public void setUpEntity() throws SQLException, NamingException {
        InitialContext initialContext;
        try {
            initialContext = new InitialContext();
            employeeHome = (beans.EmployeeHome) initialContext.lookup("ejb/EmpBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public DataSourceBean() {
    }


    public List<beans.Employee> getAllEmployees() throws SQLException, NamingException {
        List<beans.Employee> employees = new ArrayList<>();
        try {
            employees = (List<Employee>) employeeHome.findAll();
        } catch (RemoteException | FinderException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public List<beans.Employee> getEmployeesByName(String name) throws SQLException, NamingException, CreateException {
        List<beans.Employee> employees = new ArrayList<>();
        try {
            employees = (List<Employee>) employeeHome.findByName(name);
        } catch (RemoteException | FinderException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public beans.Employee getEmployeeById(int id) throws SQLException, NamingException, FinderException {
        beans.Employee employee = null;
        try {
            employee = employeeHome.findByPrimaryKey(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return employee;
    }


    public List<Employee> getEmployeesByPart(String part) {
        List<Employee> result=new ArrayList<>();
        try {
            result= (List<Employee>) employeeHome.findByPart(part);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (FinderException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {

    }


    public void ejbRemove() throws EJBException {

    }

    public void ejbActivate() throws EJBException {

    }


    public void ejbPassivate() throws EJBException {

    }

    public void ejbCreate() {

    }

}
