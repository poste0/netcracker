<%@ page import="beans.Employee" %>
<%@ page import="sourcebeans.DataSource" %>
<%@ page import="sourcebeans.DataSourceHome" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<html>
<head>
    <title>Employees</title>
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
<form name="input" method="post" action="datasource.jsp">
    <b>Employee number :</b>
    <input type="text" name="no" id="input" onblur="isNumber()"/>
    <button name="EMPNO" value="yes" onclick="isNumber()">
        Ok
    </button>
    <br/>
    <br/>
    Employee name :
    <input type="text" id="one" name="name" value="Ivanov" onfocus="document.getElementById('one').value = ''"
           onblur="if(document.getElementById('one').value == '') document.getElementById('one').value = 'Ivanov'"/>
    <input type="button" name="ENAME" value="Ok" onclick="getName()"/>
    <br/>
    <br/>
    <button name="allEmployees" value="yes">
        Output data of all employees
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
            if (request.getParameter("allEmployees") != null) {
                List<Employee> employees = dataSource.getAllEmployees();
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
        <%
            if (request.getParameter("EMPNO") != null) {
                Employee employee = dataSource.getEmployeeById(Integer.valueOf(request.getParameter("no")));
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
    function isNumber() {
        if (isNaN(document.getElementById("input").value)) {
            alert("Input a number!");
        }
    }
    function getName() {
        if (document.getElementById('one').value != '') {
            location.href = ('supportpage.jsp?ENAME=' +
            document.getElementById('one').value);
        }
    }
</script>
</body>
</html>

