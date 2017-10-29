<%@ page import="entities.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.ShowBean" %>
<%@ page import="interfaces.ShowBeanRemote" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 28.10.17
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show</title>
    <%
        String submit=request.getParameter("submit");
        String stime=request.getParameter("stime");
        String etime=request.getParameter("etime");
        List<Message> messages = new ArrayList<Message>();
        if(submit!=null){

            InitialContext ic=new InitialContext();
            ShowBeanRemote remote= (ShowBeanRemote) ic.lookup("ShowBeans");
            messages=remote.get(stime,etime);
        }
    %>
</head>
<body>
    <form name="form" id="form" action="show.jsp" method="get">
        <input id="stime" name="stime" type="text">
        <input id="etime" name="etime" type="text"><br>
        <input id="submit" name="submit" type="submit">
    </form>
    <table>
        <tr>
            <th>Сообщение</th>
            <th>Время</th>

        </tr>
        <%for(Message m:messages){%>
            <tr>
                <td><%=m.getMessage()%></td>
                <td><%=m.getTime()%></td>
            </tr>
        <%}%>
    </table>
</body>
</html>
