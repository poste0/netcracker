<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.jms.*" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 24.10.17
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JMS</title>
    <%
      String submit= (String) session.getAttribute("submit");
      String button=request.getParameter("submit");
      if(button!=null) {


        ConnectionFactory factory;
        InitialContext context = new InitialContext();
        factory = (ConnectionFactory) context.lookup("jms/ConnectionFactory");
        Destination destination = (Destination) context.lookup("queueDestination");
        Connection connection = factory.createConnection();
        Session session1 = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session1.createProducer(destination);
        String msg = (String) request.getParameter("input");
        Message message = session1.createTextMessage(msg);
        producer.send(message);
        session.setAttribute("submit",submit);
      }
    %>
  </head>
  <body>
  <form id="form" name="form" method="get">
    <p>Введите сообщение</p><br>
    <input type="text" id="input" name="input">
    <input type="submit" id="submit" name="submit" value="Отправить">

  </form>
  <a href="show.jsp">Вывести сообщения</a>
  </body>
</html>
