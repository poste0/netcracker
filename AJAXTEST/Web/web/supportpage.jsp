<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sourcebeans.DataSource" %>
<%@ page import="sourcebeans.DataSourceHome" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="beans.Employee" %>
<%@page errorPage="error.jsp" %>


<html lang="en">
<head>
    <title>Which name?</title>
    <%
        DataSource dataSource = (DataSource) session.getAttribute("datasource");
        if (Objects.isNull(dataSource)) {
            InitialContext initialContext = new InitialContext();
            DataSourceHome dataSourceHome = (DataSourceHome) initialContext.lookup("ejb/DataSourceEJB");
            dataSource = dataSourceHome.create();
            dataSource.setUpEntity();
            session.setAttribute("datasource", dataSource);
        }
    %>
</head>
<body>
<form method="post">
    Employee name :
    <input type="text" name="ENAME" id="in" value="<%=request.getParameter("ENAME")%>"/>
    <br/>
    <br/>
    <button name="name">
        Ok
    </button>
    <br/>
    <br/>
    <br/>
    <br/>
    <table id="table">
        <tr>
            <th>Employee number</th>
            <th>Employee name</th>
            <th>Job</th>
            <th>Hiredate</th>
            <th>Departament</th>
            <th>&#8224;</th>
        </tr>
        <%
            if (request.getParameter("name") != null) {
                List<Employee> employees = dataSource.getEmployeesByName(request.getParameter("ENAME"));
                for (Employee employee : employees) {
        %>
        <tr>
            <td><%=employee.getEmployeeNo()%>
            </td>
            <td><%=employee.getEmployeeName()%>
            </td>
            <td><%=employee.getPosition()%>
            </td>
            <td><%=employee.getHireDate()%>
            </td>
            <td><%=employee.getDepartmentName()%>
            </td>
            <td>
                <a href="javascript:void(0)" onclick="deleteRow(this)">Delete</a>
            </td>
        </tr>
        <%
                }

            }
        %>
    </table>
</form>
<script type="text/javascript">
    function deleteRow(r) {
        var isDelete = confirm('Delete?');
        if (isDelete) {
            var i = r.parentNode.parentNode.rowIndex;
            document.getElementById("table").deleteRow(i);
        }
    }
</script>
</body>
<footer><a href="datasource.jsp">Back</a>
</footer>
</html>
