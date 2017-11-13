package beans;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.sql.Date;


public interface Employee extends EJBObject {
    Integer getEmployeeNo() throws RemoteException;

    String getEmployeeName() throws RemoteException;

    String getPosition() throws RemoteException;

    String getDepartmentName() throws RemoteException;

    Date getHireDate() throws RemoteException;
}
