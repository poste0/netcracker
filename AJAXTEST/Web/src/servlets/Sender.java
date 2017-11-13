package servlets;

import beans.Employee;

import org.json.JSONStringer;
import sourcebeans.DataSource;
import sourcebeans.DataSourceHome;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "Sender")
public class Sender extends javax.servlet.http.HttpServlet {
    private DataSource source;
    @Override
    public void init(){
        DataSourceHome home;
        try {
            InitialContext ic=new InitialContext();
            home= (DataSourceHome) ic.lookup("ejb/DataSourceEJB");
            source=home.create();
            source.setUpEntity();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


            response.setContentType("application/json;charset=UTF-8");
            //List<Employee> employees = source.getEmployeesByPart(part);
        String part=request.getParameter("name");
            List<Employee> employees= source.getEmployeesByPart(part);
            Writer writer = response.getWriter();

            org.json.JSONWriter jwriter = new org.json.JSONWriter(writer);
            jwriter.array();
            for (Employee e : employees) {
                jwriter.object();
                jwriter.key("id");
                jwriter.value(e.getEmployeeNo());
                jwriter.key("name");
                jwriter.value(e.getEmployeeName());
                jwriter.key("position");
                jwriter.value(e.getPosition());
                jwriter.key("hiredate");
                jwriter.value(e.getHireDate());
                jwriter.key("dept");
                jwriter.value(e.getDepartmentName());
                jwriter.endObject();
            }
            jwriter.endArray();
           //PrintWriter writer=response.getWriter();
           //writer.print("DONE");




    }
}
