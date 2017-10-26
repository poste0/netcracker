<%@ page import="beans.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.Database" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.10.17
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show</title>
    <%
        String button=request.getParameter("enter");
        String stime=request.getParameter("stime");
        String etime=request.getParameter("etime");
        List<Message> messages = new ArrayList<Message>();
        if(button!=null){
           messages= Database.get(stime,etime);
        }

    %>
</head>
<body>
    <form id="form" name="form" action="show.jsp" method="get">
        <input id="stime" name="stime" type="text" >
        <input id="etime" name="etime" type="text" >
        <input id="enter" name="enter" type="submit">
    </form>
    <table>
        <tr>
            <th>Сообщение </th>
            <th> Время</th>
        </tr>
        <%for(Message message:messages){%>
        <tr>
            <td><%=message.getMessage()%> </td>
            <td> <%=message.getTime().toString()%></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
