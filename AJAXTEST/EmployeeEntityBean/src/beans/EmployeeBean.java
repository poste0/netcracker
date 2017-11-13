package beans;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.Vector;


public class EmployeeBean implements EntityBean {

    private Integer employeeNo;
    private String employeeName;
    private String position;
    private String departmentName;
    private Date hiredate;

    private DataSource dataSource;
    private EntityContext entityContext;

    public EmployeeBean() {
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        String query = "SELECT EMPNO FROM EMP  WHERE EMPNO = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, key);
            if (!statement.execute()) {
                throw new SQLDataException("No data");
            }
            ResultSet resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                throw new NoSuchEntityException();
            }
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection ejbFindByName(String employeeName) throws FinderException {
        String query = "SELECT EMPNO FROM EMP  WHERE ENAME = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeName);
            if (!statement.execute()) throw new SQLException("No data");
            Vector keys = new Vector<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Integer key = resultSet.getInt("EMPNO");
                keys.add(key);
            }
            return keys;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection ejbFindAll(){
        String query = "SELECT EMPNO FROM EMP";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            if (!statement.execute(query)) throw new SQLException("No data");
            Vector keys = new Vector<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Integer key = resultSet.getInt("EMPNO");
                keys.add(key);
            }
            return keys;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setEntityContext(EntityContext entityContext) throws EJBException {
        this.entityContext = entityContext;
        try {
            Context initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("jdbc/ConnectionPool");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void unsetEntityContext() throws EJBException {
        this.entityContext = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void ejbLoad() throws EJBException {
        String query = "SELECT EMP.EMPNO , EMP.ENAME , EMP.JOB , EMP.HIREDATE , DEPT.DNAME " +
                "FROM EMP , DEPT WHERE DEPT.DEPTNO = EMP.DEPTNO AND EMP.EMPNO = ?";
        employeeNo = (Integer) entityContext.getPrimaryKey();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeNo);
            if (!statement.execute()) {
                throw new SQLDataException("No data");
            }
            ResultSet resultSet = statement.getResultSet();
            if (!resultSet.next()) {
                throw new NoSuchEntityException();
            }
            employeeName = resultSet.getString("ENAME");
            position = resultSet.getString("JOB");
            departmentName = resultSet.getString("DNAME");
            hiredate = resultSet.getDate("HIREDATE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ejbStore() throws EJBException {
    }


    public Integer getEmployeeNo() {
        return employeeNo;
    }


    public String getEmployeeName() {
        return this.employeeName;
    }


    public String getPosition() {
        return position;
    }


    public String getDepartmentName() {
        return departmentName;
    }


    public Date getHireDate() {
        return hiredate;
    }


    public Collection ejbFindByPart(String part) throws FinderException, SQLException {
        Connection connection=dataSource.getConnection();
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("select empno from emp where ename like '"+part+"%'");
        Vector keys=new Vector();
        while(rs.next()){
            int key=rs.getInt("EMPNO");
            keys.add(key);
        }
        return keys;

    }
}
