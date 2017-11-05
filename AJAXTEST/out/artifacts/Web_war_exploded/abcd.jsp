<%@ page import="sourcebeans.DataSourceHome" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="sourcebeans.DataSource" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.Employee" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 04.11.17
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        DataSourceHome home= (DataSourceHome) new InitialContext().lookup("ejb/DataSourceEJB");
        DataSource dataSource=home.create();
        dataSource.setUpEntity();
        List<Employee> employees=dataSource.getEmployeesByName("SMITH");
    %>
</head>
<body>
<%for(Employee e:employees){%>
<%=e.getEmployeeName()%><br>
<%}%>
</body>
</html>
