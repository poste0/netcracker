<%@ page import="javax.jms.ConnectionFactory" %>
<%@ page import="interfaces.MessageBeanRemote" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.sun.corba.se.impl.protocol.giopmsgheaders.Message" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 27.10.17
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <%
      String submit=request.getParameter("submit");
      if(submit!=null){
          InitialContext context=new InitialContext();
        MessageBeanRemote remote= (MessageBeanRemote) context.lookup("MessageBeans");
        remote.send(request.getParameter("input"));
      }
    %>
  </head>
  <body>
    <form id="form" name="=form" action="index.jsp" method="get">
      <input id="input" name="input" type="text"><br>
      <input id="submit" name="submit" type="submit">
    </form>
    <a href="show.jsp">Показать сообщения</a>

  </body>
</html>
